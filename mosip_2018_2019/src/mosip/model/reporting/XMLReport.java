/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.reporting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import mosip.model.interfaces.IReport;

/**
 *
 * @author Diogo
 */
public class XMLReport implements IReport
{
    
    private XMLBundle bundle;
    
    public XMLReport()
    {
        this.bundle = new XMLBundle();
        bundle.data.add(new LeixoesCargoReportData());
    }

    @Override
    public LeixoesCargoReportData getReportData()
    {
        return bundle.data.get(0);
    }

    @Override
    public void storeResults(File file)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLBundle.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            XMLBundle uixml = bundle;
            if (!file.getAbsolutePath().endsWith(".xml"))
            {
                file = new File(file.getAbsolutePath()+".xml");
            }
            marshaller.marshal(uixml, file);
        }
        catch (JAXBException ex)
        {
            Logger.getLogger(XMLReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void mergeWith(IReport other)
    {
        bundle.data.add(other.getReportData());
    }
    
    @XmlRootElement
    public static class XMLBundle
    {
        protected List<LeixoesCargoReportData> data;
        
        public XMLBundle()
        {
            data = new ArrayList<>();
        }
    }
}
