/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IShip;

/**
 * Triggered when a ship is undocked from a bay.
 * @author Diogo
 */
public interface UndockListener
{
    public void onUndock(IShip ship, DockingBay bay);
}
