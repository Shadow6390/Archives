/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosip.model;

import mosip.model.interfaces.IBayPolicy;
import mosip.model.interfaces.IShip;

/**
 *
 * @author Diogo
 */
public class BayPolicyAcceptAll implements IBayPolicy
{

    @Override
    public boolean obeysPolicy(IShip ship)
    {
        return ship!=null;
    }
    
}
