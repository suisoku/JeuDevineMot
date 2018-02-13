/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;

import org.xml.sax.SAXException;

public class Profil {
    
    private String nom;
    private String dateNaissance;
    private String avatar;
    private ArrayList<Partie> parties;
    
    


    public Profil(String nom, String dateNaissance){
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.avatar = "";
        this.parties = new ArrayList<Partie>();
        
    }
    
    

    public Profil(String filename) throws ParserConfigurationException, SAXException, IOException{

            File inputFile = new File(filename);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            
            this.parties = new ArrayList<Partie>();
            this.avatar = doc.getElementsByTagName("avatar").item(0).getTextContent();
            this.dateNaissance = doc.getElementsByTagName("birthday").item(0).getTextContent();
            this.nom = doc.getElementsByTagName("name").item(0).getTextContent();
            
            NodeList AllParties = doc.getElementsByTagName("game");
            
            System.out.println(AllParties.item(0));
      
            for(int i = 0; i < AllParties.getLength() ; i++){
                Node noeudCourant = AllParties.item(i);
                Element e = (Element)noeudCourant;
                Partie p = new Partie(e);
                this.ajouterPartie(p);
            }
    
    }

    @Override
    public String toString(){
        String stringPartie ="";
        
        for(int i = 0 ; i < this.parties.size() ; i++){
            stringPartie = stringPartie+"\n"+parties.get(i).toString();
        }
        return "Joueur : "+this.nom+"\nNé le "+this.dateNaissance+"\nAvatar : "+this.avatar+"\nEnsemble des parties :"+stringPartie;
                
    }

    
    
    
//gere la sauvegarde
    public void sauvegarder(String filename) throws ParserConfigurationException, SAXException, IOException, TransformerException{

        
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder p = dbFactory.newDocumentBuilder();
         // récupération de la structure objet du document
         Document doc = p.newDocument();
         
         
        Element racine = doc.createElement("profile");
        doc.appendChild(racine);
        
        Element name = doc.createElement("name");
        name.setTextContent(this.nom);
        racine.appendChild(name);
        
        Element avatar = doc.createElement("avatar");
        avatar.setTextContent(this.avatar);
        racine.appendChild(avatar);
        
        Element birthday = doc.createElement("birthday");
        birthday.setTextContent(this.dateNaissance);
        racine.appendChild(birthday);
        
        Element games = doc.createElement("games");
        racine.appendChild(games);
        
        for(int i = 0 ; i < this.parties.size() ; i++){
            
            Element game = doc.createElement("game");
            game.setAttribute("date", this.parties.get(i).getDate());
            game.setAttribute("found", Integer.toString(this.parties.get(i).getTrouve())+"%");           
            racine.appendChild(game);
            
            Element word = doc.createElement("mot");
            word.setAttribute("niveau", Integer.toString(this.parties.get(i).getNiveau()));
            word.setTextContent(this.parties.get(i).getMot());
            game.appendChild(word);
            
            Element time = doc.createElement("time");
            time.setTextContent(Integer.toString(this.parties.get(i).getTemps()));
            game.appendChild(time);
        }
        
        Source source = new DOMSource(doc);
         

        Result resultat = new StreamResult(new File(filename));
         
        /************************** PARTIE TRANSFORMATEUR ************/
        Transformer ts = null;
        try {
            ts = TransformerFactory.newInstance().newTransformer();
        } catch(TransformerConfigurationException e) {
            System.err.println("Impossible de créer un transformateur XML.");
            System.exit(1);
        }
         
        // PARAMETRAGE
        ts.setOutputProperty(OutputKeys.ENCODING, "utf-8"); // on encode en utf 8 comme les xmls
        ts.setOutputProperty(OutputKeys.METHOD, "xml"); // on specifie que c'est xml doc
        ts.setOutputProperty(OutputKeys.INDENT, "yes");  // permet l'indentation
        ts.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); // xml strict requis
        ts.transform(source, resultat);
    }
    
    
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
    
    
    
      
    public void ajouterPartie(Partie p){
        this.parties.add(p);
    }
    public int getDernierNiveau(){
        return parties.get(parties.size() - 1).getNiveau();
    }
    
}
