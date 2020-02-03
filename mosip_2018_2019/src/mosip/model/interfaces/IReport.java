/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

import java.io.File;
import mosip.model.reporting.LeixoesCargoReportData;

/**
 *
 * @author Diogo
 */
public interface IReport
{
    public LeixoesCargoReportData getReportData();
    public void storeResults(File file);
    public void mergeWith(IReport other);
}
