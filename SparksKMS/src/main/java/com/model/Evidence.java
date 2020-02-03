package com.model;

public class Evidence extends Fact{
    
        public static final String DRUG_USE = "Está sob a influência de algum dos seguintes fármacos?";
        public static final String IDADE_TSH = "Indique a fase da sua idade";
        public static final String VALOR_TSH = "Insira o valor de TSH";
        public static final String IDADE_T4L = "Indique qual a faixa etária mais próxima";
        public static final String VALOR_T4L = "Indique o valor de T4L";
        public static final String TSH_LEVELS = "Niveis de TSH";
        public static final String T4L_LEVELS = "Niveis de T4L";
        public static final String THYROID_SHOULD_DO_TEST = "Selecione o critério que o levou a fazer exames TSH e T4L";
        
        public static final String HYPERTENSION_TA_DG="Indique a tensão arterial diastólica global";
        public static final String HYPERTENSION_TA_SG="Indique a tensão arterial sistólica global";
        public static final String HTA_TA_DG_NOC="Indique a tensão arterial diastólica noturna";
        public static final String HTA_TA_SG_NOC="Indique a tensão arterial sistólica noturna";
        //public static final String HYPERTENSION_DAY_PHASE="Indique o período do dia em que mediu a TA";
        public static final String HYPERTENSION_SHOULD_DO_TEST="Selecione o critério que o levou a fazer exame de Hipertensão (HTA)";
        
        public static final String HTA_SISTOLICA_ISOLADA="HTA Sistólica Isolada";
        public static final String HTA_DIASTOLICA_ISOLADA="HTA Diastólica Isolada";
        
        public static final String HYPERTENSION_HINT_SECONDARY="Indique qual a característica clínica que o doente tem";
        public static final String HTA_SECUNDARY_REQUISITES="Indique se não cumpre alguma das seguintes características";
        
        public static final String ESCALA_EPWORTH="Insira a pontuação obtida na Escala de Epworth";
        public static final String VALOR_IAH="Insira o valor de IAH (resultado da polissonografia)";
        public static final String HIPERSONOLENCIA_OU_PATOLOGIA="Tem hipersonolência e/ou alguma patologia cardiovascular?";
        
        public static final String SUSPEITA_DOENCA_RENAL = "O doente tem suspeita de doença renal?";
        public static final String ALTERACOES_RENAIS = "Indique se o doente, após investigação laboratorial e uma ecografia renal, tem alguma das seguintes";
                
        /**
         * These are also evidences for further diagnosics...
         */
        public static final String HTA_PADRAO_NAO_DIPPER = "HTA Padrão Não-Dipper";
        public static final String HTA_PADRAO_REVERSE_DIPPER = "HTA Padrão Reverse-Dipper";
        public static final String HTA_NOTURNA_ISOLADA = "HTA Noturna Isolada";
        
        
	
	private String evidence;
	private String value;
	
	public Evidence(String ev, String v) {
		evidence = ev;
		value = v;
	}
	
	public String getEvidence() {
		return evidence;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return (evidence + " = " + value);
	}

}
