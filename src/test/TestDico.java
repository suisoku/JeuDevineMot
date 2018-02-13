/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import management.Dico;

/**
 *
 * @author zianinou
 */
public class TestDico {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dico dictionnaire = new Dico("aa");
    
        dictionnaire.addWordToDico(1, "patate");
        dictionnaire.addWordToDico(1, "barkouk");
        dictionnaire.addWordToDico(1, "chaise");
        dictionnaire.addWordToDico(1, "casque");
        dictionnaire.addWordToDico(1, "pasteque");
        dictionnaire.addWordToDico(1, "bateau");
        dictionnaire.addWordToDico(1, "pere");
        dictionnaire.addWordToDico(1, "mere");
        dictionnaire.addWordToDico(1, "avant");
        dictionnaire.addWordToDico(1, "noix");
        dictionnaire.addWordToDico(1, "lezard");
        
    }
    
}
