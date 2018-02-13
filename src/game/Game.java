/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;


/**
 *
 * @author zianinou
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ParserConfigurationException, SAXException, SAXException, IOException, IOException, TransformerException {
        //Instanciate a new Jeu
        Jeu jeu = new Jeu();
        //Play the game
        jeu.jouer();

  
    }
    
}
