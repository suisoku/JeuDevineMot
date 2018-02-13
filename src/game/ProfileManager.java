/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import tux.ObjectFactory;
import tux.Profile;
/**
 *
 * @author Last_Pacifist
 */
public class ProfileManager {
    public Profile profil;
    
    
    public ProfileManager() throws DatatypeConfigurationException{
     //instantiation et initialisation   
        try{
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Profile.class.getPackage().getName());
        } catch (javax.xml.bind.JAXBException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        ObjectFactory profileOF = new ObjectFactory();
        profil = profileOF.createProfile();

        //------------------------------------------------------------------
        // remplissage des données
        profil.setName("Krogoth");
        profil.setAvatar("bonjour.jpg");
        GregorianCalendar calendar = new GregorianCalendar();        
        calendar.setTime(new Date(26,05,1995));        
        profil.setBirthday(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
        profil.setGames(new ObjectFactory().createGames());
    }
    public void toXML(String filename){
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(profil.getClass().getPackage().getName());

            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();

            // quelques paramètres pour le support du bon encoding et pour l'affichage simplifié pour les humains
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); 
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

          
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = sf.newSchema(new File("src/xml/profile.xsd"));
            marshaller.setSchema(schema);
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_SCHEMA_LOCATION, "http://myGame/profile src/xml/profile.xsd");

            // et c'est parti pour l'opération de marshalling (
            marshaller.marshal(profil, new FileOutputStream(filename));

        } catch (javax.xml.bind.JAXBException ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Probleme d'ouverture fichier " + filename + " en ecriture !");
        } catch (SAXException ex) {
            Logger.getLogger(ProfileManager.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    public Profile fromXML(String filename){
        
        try {
            // création du contexte à partir de la classe Profile (ici, encore non instanciée, donc on génère le contexte à partir de la classe)
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Profile.class.getPackage().getName());

            // création d'un unmarshaller 
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

            // unmarshalling... (utilisation d'un transtypage)
            Profile profile = (Profile) unmarshaller.unmarshal(new java.io.File(filename)); //NOI18N

            // et voila, ça marche. Ici on affiche le nom et l'age du joueur
            LocalDate today = LocalDate.now();
            XMLGregorianCalendar birthdayCalendar = profile.getBirthday();
            LocalDate birthday = LocalDate.of(birthdayCalendar.getYear(), birthdayCalendar.getMonth(), birthdayCalendar.getDay());
            Period p = Period.between(birthday, today);
            System.out.println("Joueur: " + profile.getName() + " (" + p.getYears() + " ans)");
            return profile;
            // ici, pour tester le unmarshalling, utilisez la methode playerToString() rajoutée à la classe Profile ..........
            // [à faire]
            // ...
            // ...

        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            return null;
        }
        
    }
    
}
