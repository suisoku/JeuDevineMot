/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import game.BrowserUtil;
import game.FileUtil;
import game.ProfileManager;
import game.XMLUtil.DocumentFactory;
import game.XMLUtil.DocumentTransform;
import game.XMLUtil.XPathEvaluateExpression;
import java.io.File;
import java.io.FileWriter;
import org.w3c.dom.Document;
/**
 *
 * @author Nord_38
 */
public class TestDOM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       // ProfileManager manager = new ProfileManager();
        
      //  manager.profil = manager.fromXML("src/xml/profile.xml");
      //  Document document1 = DocumentFactory.fromObject(manager.profil);
        Document document1 = DocumentFactory.fromFile("src/xml/profile.xml");
        String nom =  XPathEvaluateExpression.getString("//name", document1);
        System.out.println("le nom est " + nom);
        
        System.out.println("Temps moyen par partie "+ XPathEvaluateExpression.getNumber("//time", document1));
        
        
        
        String html_doc = DocumentTransform.fromXSLTransformation("src/xml/profile.xsl", document1);
        System.out.println(html_doc);
        BrowserUtil.launch("src/xml/test.html");
        FileUtil.ecrire(html_doc, "src/xml/test.html");
        
    }
    
}
