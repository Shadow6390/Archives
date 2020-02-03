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
public class AppUtils {
    
    /**
     * Checks if the object array has the target object
     * @param o The object to check
     * @param arr Array of objects to check against 
     * @return 
     */
    public static boolean hasOneOf(Object o, Object[] arr)
    {
        boolean result = false;
        for (int i=0; i<arr.length && !result;i++)
        {
            result = arr[i].equals(o);
        }
        return result;
    }
    
    /**
     * Checks if the string array has the target string
     * @param o The string to check
     * @param arr Array of strings to check against 
     * @param ignoreCase Should ignore casing or not
     * @return 
     */
    public static boolean hasOneOf(String o, String[] arr,boolean ignoreCase)
    {
        boolean result = false;
        for (int i=0; i<arr.length && !result;i++)
        {
            result = (ignoreCase) ? arr[i].equalsIgnoreCase(o) : arr[i].equals(o);
        }
        return result;
    }
}
