/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

/**
 *
 * @author jbraga
 */
public interface IExtractor {
    public boolean canExtract(IShip ship);
    @Deprecated
    public void doExtraction(IShip ship,int numberOfExtractors);
    public void doEventExtraction(IShip ship,int numberOfExtractors,int expectedNumberExtractors);
    public void doEventLoad(IShip ship,int numberOfExtractors,int expectedNumberExtractors);
    /**
     * Attempts to perform a fault in the extractor.
     * @param canFault whether it is possible to fault or not.
     * @return true if fault happened
     */
    public boolean doFaultEvent(boolean canFault);
    public double getRepairTime();
}
