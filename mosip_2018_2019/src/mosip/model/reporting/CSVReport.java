/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import mosip.model.interfaces.IReport;
import mosip.model.reporting.LeixoesCargoReportData.ReportBundle;

/**
 *
 * @author Diogo
 */
public class CSVReport implements IReport
{

    private LeixoesCargoReportData data;
    private List<LeixoesCargoReportData> otherData;

    public CSVReport()
    {
        this.data = new LeixoesCargoReportData();
        otherData = new ArrayList<>();
    }

    @Override
    public LeixoesCargoReportData getReportData()
    {
        return data;
    }

    @Override
    public void storeResults(File file)
    {
        try
        {
            if (!file.getAbsolutePath().endsWith(".csv"))
            {
                file = new File(file.getAbsolutePath() + ".csv");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writeHeaders(writer);
            writeContents(writer, data);
            for (LeixoesCargoReportData elem:otherData)
            {
                writeContents(writer, elem);
            }
            writer.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(CSVReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeContents(BufferedWriter writer,LeixoesCargoReportData data) throws IOException
    {
        String contents = data.globalResults.averageWaitingTime + ","
                + data.globalResults.getAverageFor(data.globalResults.averageDocking) + ","
                + data.globalResults.getAverageFor(data.globalResults.averageUnloading) + ","
                + data.globalResults.getAverageFor(data.globalResults.averageLoading) + ","
                + data.globalResults.getAverageFor(data.globalResults.averageUndocking) + ","
                + data.globalResults.averageDelayTime + ",";
        Set<Entry<String, ReportBundle>> bays = data.bayData.entrySet();
        Iterator<Entry<String, ReportBundle>> it = bays.iterator();
        while (it.hasNext())
        {
            ReportBundle elem = it.next().getValue();
            contents += elem.averageWaitingTime + ","
                    + elem.getAverageFor(elem.averageDocking) + ","
                    + elem.getAverageFor(elem.averageUnloading) + ","
                    + elem.getAverageFor(elem.averageLoading) + ","
                    + elem.getAverageFor(elem.averageUndocking)+ ","
                    + elem.averageDelayTime;
            if (it.hasNext())
            {
                contents += ",";
            }
        }
        writer.write(contents);
        writer.newLine();
    }

    private void writeHeaders(BufferedWriter writer) throws IOException
    {
        String header = "GlobalAverageWaitingTime,GlobalAverageDockTime,GlobalAverageUnloadingTime,GlobalAverageLoadingTime,GlobalAverageUndockTime,GlobalAverageDelayTime,";
        for (int i = 0; i < this.data.bayData.size(); i++)
        {
            header += String.format("DockingBay%d_AverageWaitingTime,DockingBay%d_AverageDockTime,DockingBay%d_AverageUnloadingTime,DockingBay%d_AverageLoadingTime,DockingBay%d_AverageUndockTime,DockingBay%d_AverageDelayTime", i, i, i, i, i,i);
            if (i < this.data.bayData.size() - 1)
            {
                header += ",";
            }
        }
        writer.write(header);
        writer.newLine();
    }

    @Override
    public void mergeWith(IReport other)
    {
        otherData.add(other.getReportData());
    }
}
