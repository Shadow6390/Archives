/*
 * Copyright 2018 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.model;

import com.diagnostics.AppUtils;
import com.model.interfaces.UserInput;
import com.model.interfaces.UserInputETL;

/**
 *
 * @author jbraga
 */
public class Thyroid {

    public static final String TSH_STAGE_LIFE_FIRST_WEEK = "Primeira Semana";
    public static final String TSH_STAGE_LIFE_SECOND_TO_ELEVEN = "Segunda semana até onze meses";
    public static final String TSH_STAGE_LIFE_ONE_TO_6_YEARS = "1 a 6 anos";
    public static final String TSH_STAGE_LIFE_SEVEN_TO_SEVENTEEN_YEARS = "7 a 17 anos";
    public static final String TSH_STAGE_LIFE_OVER_18 = "Maiores de 18 anos";

    public static final String T4L_STAGE_PRENATAL = "Recém-nascido";
    public static final String T4L_STAGE_ADULTS_AND_OTHERS = "Adultos";

    public static final String[] DRUG_CHOICES = new String[]{"amiodarona", "lítio", "interferão","nenhuma"};
    public static final String[] TSH_STAGE_CHOICES = new String[]{TSH_STAGE_LIFE_FIRST_WEEK,
        TSH_STAGE_LIFE_SECOND_TO_ELEVEN,TSH_STAGE_LIFE_ONE_TO_6_YEARS,TSH_STAGE_LIFE_SEVEN_TO_SEVENTEEN_YEARS,
    TSH_STAGE_LIFE_OVER_18};
    public static final String[] T4L_STAGE_CHOICES = new String[]{T4L_STAGE_PRENATAL,T4L_STAGE_ADULTS_AND_OTHERS};
    
    /**
     * Reasos to do a test.
     */
    public static final String[] TEST_REASONS = new String[]{
        "Doente com bócio",
        "Sobre irradiação cervical",
        "Indivíduo com nódulos da tiroide não palpáveis, detetados por técnicas com base em imagem",
        "Doente com quadro clínico que sugere hipotiroidismo ou hipertiroidismo",
        "Doente tiroidectomizado",
        "Doente com patologia hipotálamo-hipofisária",
        "Doente com hipotiroidismo congénito",
        "Criança com crescimento atrasado",
        "Doente com anemia de causa inexplicada, hipercolesterolémia ou variações ponderais",
        "Doente com doenças autoimunes, arritmias, osteoporose, irregularidades menstruais ou infertilidade",
        "Grávida com elevado risco de desenvolver doenças da tiroide",
        "nenhuma das anteriores"
    };
    
    /*public static final UserInput TSH_INPUTS = new UserInput() {
        @Override
        public String getUserInput() {
            
        }
    };*/
            
    

    public static final UserInputETL TSH_CONVERTER = (UserInputETL<String>) (String input) -> {
        String result = null;
        try {
            String[] cache = input.split(";");
            result = fromTSHValueToClassification(Double.parseDouble(cache[0]), cache[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return result;
    };

    public static final UserInputETL T4L_CONVERTER = (UserInputETL<String>) (String input) -> {
        String result = null;
        try {
            result = fromT4LValueToClassification(Double.parseDouble(input), T4L_STAGE_ADULTS_AND_OTHERS);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    };

    public static String underInfluenceDrugs(String drug) {
        String result = "no";
        if (AppUtils.hasOneOf(drug, new String[]{"AMIODARONA", "LÍTIO", "INTERFERÃO"}, true)) {
            result = "yes";
        }
        return result;
    }

    public static String fromT4LValueToClassification(double val, String stageLife) {
        String result = "NORMAL";
        if (T4L_STAGE_PRENATAL.equals(stageLife)) {
            if (val < 2.6) {
                result = "LOW";
            } else if (val > 6.3) {
                result = "HIGH";
            }
        } else if (T4L_STAGE_ADULTS_AND_OTHERS.equals(stageLife)) {
            if (val < 0.8) {
                result = "LOW";
            } else if (val > 2.7) {
                result = "HIGH";
            }
        }
        return result;
    }

    /**
     * Values must be in μUI/mL
     *
     * @param val the TSH value.
     * @param stageLife The stage life of the patient.
     * @return HIGH, LOW, NORMAL
     */
    public static String fromTSHValueToClassification(double val, String stageLife) {
        String result = "NORMAL";

        if (TSH_STAGE_LIFE_FIRST_WEEK.equals(stageLife)) {
            if (val < 14.5) {
                result = "LOW";
            } else if (val > 15.5) {
                result = "HIGH";
            }
        } else if (TSH_STAGE_LIFE_SECOND_TO_ELEVEN.equals(stageLife)) {
            if (val < 0.8) {
                result = "LOW";
            } else if (val > 6.3) {
                result = "HIGH";
            }
        } else if (TSH_STAGE_LIFE_ONE_TO_6_YEARS.equals(stageLife)) {
            if (val < 0.9) {
                result = "LOW";
            } else if (val > 6.5) {
                result = "HIGH";
            }
        } else if (TSH_STAGE_LIFE_SEVEN_TO_SEVENTEEN_YEARS.equals(stageLife)) {
            if (val < 0.3) {
                result = "LOW";
            } else if (val > 4.2) {
                result = "HIGH";
            }
        } else if (TSH_STAGE_LIFE_OVER_18.equals(stageLife)) {
            if (val < 0.3) {
                result = "LOW";
            } else if (val > 4.0) {
                result = "HIGH";
            }
        }
        return result;
    }
}
