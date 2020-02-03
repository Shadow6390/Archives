/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bragasQuest;

/**
 * Listener que escuta mudanças de posição em {@link JPositionPickerPanel}.
 * @author DC_G2
 */
public interface PositionPickerListener {
    
    /**
     * Este método é invocado quando o utilizador especifica uma posição.
     */
    public void onPositionChanged();
}
