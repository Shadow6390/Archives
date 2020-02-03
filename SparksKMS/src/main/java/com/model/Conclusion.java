package com.model;

import com.diagnostics.KBSEngine;

public class Conclusion extends Fact{
    
	public static final String UNKNOWN = "Consulte o médico.";
        
        public static final String HIPOTIROIDISMO = "Hipotiroidismo";
        public static final String HIPERTIROIDISMO_SECUNDARIO = "Hipertiroidismo Secundário";
        public static final String HIPOTIROIDISMO_SUBCLINICO = "Hipotiroidismo Subclínico";
        public static final String HIPERTIROIDISMO_SUBCLINICO = "Hipertiroidismo Subclínico";
        public static final String HIPERTIROIDISMO = "Hipertiroidismo";
        public static final String EUTIROIDISMO = "Eutiroidismo";
        public static final String T3L_BAIXA = "T3L Baixa";
        
        public static final String HTA_SISTOLICA_ISOLADA = "HTA Sistólica Isolada";
        public static final String HTA_DIASTOLICA_ISOLADA = "HTA Diastólica Isolada";
        
        public static final String HTA_PADRAO_DIPPER_NORMAL = "HTA Padrão Dipper Normal";
        
        public static final String MEDICINA_SONO = "Referenciação Medicina do Sono";
        
        public static final String REFERENCIACAO_HOSPITAL = "Referenciação Hospital Renal";
        

	private String description;
        
        private String additionalInfo;
	
	public Conclusion(String description) {
		this.description = description;
		KBSEngine.getAgendaEventListener().addRhs(this);
	}
	
        public Conclusion(String description,String additionalInfo)
        {
            this(description);
            this.additionalInfo=additionalInfo;
        }
        
        public Conclusion(String[] condensedData)
        {
            this(condensedData[0],condensedData[1]);
        }
        
	public String getDescription() {
		return description;
	}
        
        /**
         * Returns additional info about the conclusion (such as treatment methods
         * and follow-up actions).
         * @return String
         */
        public String getAdditionalInfo()
        {
            return additionalInfo;
        }
        
        
	@Override
	public String toString() {
		return (description);
	}

}



