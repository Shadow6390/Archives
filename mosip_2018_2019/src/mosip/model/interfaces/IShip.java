/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

import mosip.model.DockingBay;

/**
 *
 * @author jbraga
 */
public interface IShip {
    public void performUnloading(int numberExtractors,int expectedNumberExtractors);
    public void performLoading(int numberExtractors,int expectedNumberExtractors);
    public void dock(DockingBay bay);
    public void undock(DockingBay bay);
    public double getBridgeHeightLimit();
    public double[] getTimes();
    public String getShipClassName();
    public void registerStartWaitingTime();
    public void registerEndWaitingTime();
    public double getWaitingTime();
    public void reportDelayTime(DockingBay bay);
}
