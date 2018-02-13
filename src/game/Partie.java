package game;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class Partie {
    
    private String date;
    private String mot;
    private int niveau;
    private int trouve;
    private int temps;
    
    
    
// CONSTRUCTEUR 1 : Prend une date , un mot et un level
    public Partie(String uneDate, String unMot, int unLevel){ 
        this.date = uneDate;
        this.mot = unMot;
        this.niveau = unLevel;
    }
    

 // CONSTRUCTEUR 2 : PASSE PAR LE PARSEUR
    public Partie(Element domPartie){ 
          
        this.date=domPartie.getAttribute("date");
        Element domMot = (Element)domPartie.getElementsByTagName("mot").item(0);
        this.niveau=Integer.parseInt(domMot.getAttribute("niveau"));
                      
        this.mot=domMot.getTextContent();
 
    }
    
    
// getDomElement : utilisation du parseur , accede au doc dom pour retourner un element
    public Element getDomElement(Document doc) throws ParserConfigurationException, SAXException, IOException{
    
        File inputFile = new File("src/test/test.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(inputFile);
            
        return (Element) doc.getElementsByTagName("game").item(0);
 
    }

    public int getNiveau() {
        return niveau;
    }
    public String getDate() {
        return date;
    }
    public int getTemps() {
        return temps;
    }
    public String getMot() {
        return mot;
    }
    public int getTrouve() {
        return trouve;
    }
    public void setTrouve(int nbLettresRestantes){
        int lettreTrouve = mot.length() - nbLettresRestantes;
        this.trouve = (lettreTrouve * 100) / mot.length();
    }
    public void setTemps(int temps) {
        this.temps = temps;
    }
    

    
    

    @Override
    public String toString() {
        String console;
        
        
        console = " ======== Partie ========"; // on interface les donnes dans la console ^^
        console += "     \nJoué le : "+ date +"\nMot : " + mot +"\n difficulté du niveau : " + niveau + "\nPourcentage de réussite : " + trouve + "%\nTemps réalisé : " + temps +" secondes";
        console += "  \n======= Fin de la Partie =======\n";
        
        
        
        return console;
    }
    
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }
    
   //Transforme la date
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }
 



    
}
