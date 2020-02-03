package com.diagnostics;

import com.controller.KnowledgeDiscoverySession;
import java.util.Map;
import java.util.TreeMap;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.ViewChangedEventListener;

import com.model.Conclusion;
import com.model.Evidence;
import com.model.Hypothesis;
import com.model.Justification;
import com.view.DroolsInterface;
import com.web.Main;
import java.io.IOException;
import java.util.Collection;
import org.kie.api.KieBase;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.Row;

public class KBSEngine {

    public KieSession kSession;
    public Map<Integer, Justification> justifications;
    public LiveQuery query;
    public KnowledgeDiscoverySession kds;

    private DroolsInterface dui;
    private static TrackingAgendaEventListener agendaEventListener;

    private String sessionName;

    public KBSEngine(String sessionName) {
        kds = new KnowledgeDiscoverySession();
        this.sessionName = sessionName;
        setupEngine();
        dui = new DroolsInterface(this, kds);
        
    }

    public KieSession getKieSession() {
        return kSession;
    }

    public static TrackingAgendaEventListener getAgendaEventListener() {
        return agendaEventListener;
    }

    private void setupEngine() {
        try {
            justifications = new TreeMap<Integer, Justification>();

            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();

            KieContainer kb = setupKnowledgeFileSystem(ks);

            KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
            config.setOption(ClockTypeOption.get("realtime"));
            kSession = kb.newKieSession();
            

            KBSEngine.agendaEventListener = new TrackingAgendaEventListener(justifications);
            kSession.addEventListener(agendaEventListener);

            // Query listener
            ViewChangedEventListener listener = new ViewChangedEventListener() {
                @Override
                public void rowDeleted(Row row) {
                }

                @Override
                public void rowInserted(Row row) {
                    Conclusion conclusion = (Conclusion) row.get("$conclusion");
                    //System.out.println(">>>" + conclusion.toString());

                    kds.addConclusion(conclusion);
                    //System.out.println(Haemorrhage.justifications);
                    Explanation how = new Explanation(justifications);
                    Main.log(how.getHowExplanation(conclusion.getId()));
                    kds.addExplanation(conclusion, how);
                    kds.inputSemaphore.release();

                    Collection<Evidence> evidences = (Collection<Evidence>) kSession.getObjects(new ClassObjectFilter(Evidence.class));
                    for (Evidence e : evidences) {
                        Main.log("[EVIDENCE]"+e);
                    }
                    
                    Collection<Hypothesis> hs = (Collection<Hypothesis>) kSession.getObjects(new ClassObjectFilter(Hypothesis.class));
                    for (Hypothesis h : hs) {
                        Main.log("[Hypothesis]"+h);
                    }
                    
                    // stop inference engine after as soon as got a conclusion
                    kSession.halt();

                }

                @Override
                public void rowUpdated(Row row) {
                }

            };

            LiveQuery query = kSession.openLiveQuery("Conclusions", null, listener);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private KieContainer setupKnowledgeFileSystem(KieServices ks) throws IOException{
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write("src/main/resources/"+sessionName+".drl",
                ks.getResources().newInputStreamResource(Main.class.getClassLoader().getResourceAsStream("rules/"+sessionName+".drl")));
// end
        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();

        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer kieContainer
                = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();
        return kieContainer;
    }

    public void runEngine() {
        new Thread(() -> {
            kSession.fireUntilHalt();
        }).start();
    }

}
