/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model.interfaces;

import mosip.model.BaseShip;

/**
 *
 * @author Diogo
 */
public interface IShipController
{
    public boolean canSpawn(BaseShip.ShipType type);
    
    @Deprecated
    public IShip spawnActivityShip(BaseShip.ShipType type,double suggestedArrivalTime);
    public IShip spawnEventShip(BaseShip.ShipType type,double arrivalTime);
}
