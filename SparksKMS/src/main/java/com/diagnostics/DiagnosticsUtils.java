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
package com.diagnostics;

/**
 *
 * @author jbraga
 */
public class DiagnosticsUtils {
    
    /**
     * Checks if a value is between min and max. Interval is inclusive
     * 
     * @param min
     * @param value
     * @param max
     * @return 
     */
    public static boolean between(double min, double value, double max)
    {
        return (min<=value && value<=max);
    }
}