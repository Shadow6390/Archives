package com.diagnostics;

import java.util.Map;

import com.model.Fact;
import com.model.Hypothesis;
import com.model.Justification;
import java.util.ArrayList;
import java.util.List;

public class Explanation {
	private Map<Integer, Justification> justifications;
	
	public Explanation(Map<Integer, Justification> justifications) {
		this.justifications = justifications;
	}
	
        public String getHowExplanationArray(Integer factNumber)
        {
            return getHowExplanationComposite(factNumber,0);
        }
        
        private String[] toArray(List<String> list)
        {
            String[] result = new String[list.size()];
            for (int i=0;i<list.size();i++)
            {
                result[i]=list.get(i);
            }
            return result;
        }
        
	public String getHowExplanation(Integer factNumber) {
		return (getHowExplanation(factNumber, 0));
	}
	
        /**
         * Returns the explanation in a composite fashion, formatted as a nested JSON
         * array.
         * @param factNumber
         * @param level
         */
        private String getHowExplanationComposite(Integer factNumber, int level) 
        {
            String quote = "\"";
            Justification j = justifications.get(factNumber);
            String temp = "[";
            if (j != null) { // justification for Fact factNumber was found
                    temp+=quote+j.getConclusion()+quote+","+quote+j.getRuleName()+quote+",[";
                    int l = level + 1;
                    Fact f;
                    for (int i=0;i<j.getLhs().size();i++) {
                        f=j.getLhs().get(i);
                        temp+=quote+f+quote;
                        if (f instanceof Hypothesis) {
                                String s = getHowExplanationComposite(f.getId(), l + 1);
                                temp+=",";
                                temp+=s;
                        }
                        if (i<j.getLhs().size()-1)
                        {
                            temp+=",";
                        }
                    }
                    temp+="]";
            }
            temp+="]";
            return temp;
	}
        
	private String getHowExplanation(Integer factNumber, int level) {
		StringBuilder sb = new StringBuilder();
		Justification j = justifications.get(factNumber);
		if (j != null) { // justification for Fact factNumber was found
			sb.append(getIdentation(level));
			sb.append(j.getConclusion() + " was obtained by rule " + j.getRuleName() + " because");
			sb.append('\n');
			int l = level + 1;
			for (Fact f : j.getLhs()) {
				sb.append(getIdentation(l));
				sb.append(f);
				sb.append('\n');
				if (f instanceof Hypothesis) {
					String s = getHowExplanation(f.getId(), l + 1);
					sb.append(s);
				}
			}
		}
		
		return sb.toString();
	}
	
	private String getIdentation(int level) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < level; i++) {
			sb.append('\t');
		}
		return sb.toString();
	}
}
