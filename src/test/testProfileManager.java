/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import game.ProfileManager;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author Last_Pacifist
 */
public class testProfileManager {

    
    public static void main(String[] args) throws DatatypeConfigurationException {
        ProfileManager manager = new ProfileManager();
        
        manager.profil = manager.fromXML("src/xml/profile.xml");
        manager.toXML("src/xml/profile5.xml");
    }
    
}
