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
import com.model.interfaces.UserInputETL;

/**
 *
 * @author jbraga
 */
public class Hypertension {
    
    /**
     * Reasons to do a test.
     */
    public static final String[] TEST_REASONS = new String[]{
        "Suspeita de HTA de bata branca",
        "Suspeita de HTA mascarada",
        "Identificação do efeito de bata branca no utente hipertenso",
        "Variabilidade considerável da TA no consultório, na mesma consulta ou em consulta diferentes",
        "Hipotensão autonómica, postural, pós-prandial, na sesta ou induzida por fármacos",
        "TA elevada no consultório ou suspeita de pré-eclâmpsia em mulheres grávidas",
        "Identificação de hipertensão resistente falsa e verdadeira",
        "Discordância marcada entre TA no consultório e em casa",
        "Avaliação do estado de dipping",
        "Suspeita de HTA noturna ou ausência de dipping (comum em doentes com SAOS, DRC ou DM)",
        "Avaliação da variabilidade da TA",
        "nenhuma das anteriores"
    };
    
    public static final String[] HTA_SEC_HINTS = new String[]{
        "TA não controlada com 3 classes anti hipertensores",
        "Necessidade de >= 4 classes de anti hipertensores para controlo de TA",
        "Início precoce (ex. <30 anos) em doente sem fatores de risco",
        "Aumento abrutpo da TA em utente previamente estável",
        "Ocorrência de HTA severa ou emergência",
        "Perfil não-dipper ou reverse-dipper em MAPA",
        "Início de HTA diastólica em doentes com idade >= 65 anos",
        "Excessiva redução do potássio sérico com dose pequena de diurético",
        "Excessiva redução da TFG com dose pequena de inibidores da IECA",
        "nenhuma das anteriores"
    };
    
    public static final String[] HTA_SECUNDARY_REQUISITES = new String[]{
        "Identifica e corrige estilo de vida passível de contruibir para HTA",
        "Descontinua/minimiza substância passível de interferir com HTA, tendo história sugestiva de SAOS",
        "nenhuma das anteriores"
    };
    
    public static final String[] ALTERACOES_RENAIS = new String[]{
        "Ecogenicidade renal",
        "Diminuição TFG",
        "Aumento da Creatinina",
        "Proteinúria",
        "Leucocitúria",
        "Eritrocitúria",
        "nenhuma das anteriores"
    };
}
