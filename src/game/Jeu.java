package game;

import management.LectureClavier;
import env3d.Env;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import management.DevineLeMot;
import management.DicoPath;
import org.xml.sax.SAXException;

public class Jeu {
    private Env env;
    private Tux tux;
    private String mot;
    private DevineLeMot gameplay;
    private int level;
    private DicoPath dico;
    private Partie partie;
    private Profil profil;
    private boolean finished;
    
    public Jeu() throws ParserConfigurationException, SAXException, SAXException, IOException{
        this.initialiser();
        
      /*  this.dico = new Dico(""); // "" dans le cas d'un 
        this.dico.addWordToDico(1, "bateau"); // On peut charger la liste des mots directement dans la classe jeu
        this.dico.addWordToDico(1, "salon");
        this.dico.addWordToDico(1, "chaise");
        this.dico.addWordToDico(1, "coeur");
        this.dico.addWordToDico(1, "lit");
        this.dico.addWordToDico(1, "voiture");
        this.dico.addWordToDico(1, "casque");
        this.dico.addWordToDico(1, "souris");
        */
       this.dico = new DicoPath();
       this.dico.readDictionnary("src/xml/dico.xml"); // chargement par xml
       profil = new Profil("src/xml/profile.xml");
       
 
        
    }

    /**** Initialisation de l'envirenement 3D ****/
    public void initialiser(){ 
        env = new Env();
       
        Room room = new Room();
        env.setRoom(room);

        tux = new Tux(20,4,20);
        env.addObject(tux);
        
        
        env.setCameraXYZ(25, 30, 80);
        env.setCameraPitch(-25);
        env.setDefaultControl(false);
    }
    
    public void jouer() throws ParserConfigurationException, SAXException, IOException, IOException, TransformerException {
         
        // The main game loop
        
        env.soundLoop("sounds/music_back2.wav");
        
        while(!finished){
             // On peut imaginer plusieurs gameplay , ici c'est devine le mot
            int level_user = LectureClavier.lireEntier("tapez level: ");
            this.mot =  dico.getWordFromListLevel(level_user);
            gameplay = new DevineLeMot(this.mot , 60 ,this.env, this.tux); // 2eme argument c'est la limite du temps
            //gameplay = new DevineLeMot("salut", 30 ,this.env, this.tux);
            gameplay.jouer();
            
            if(LectureClavier.lireOuiNon("Voulez vous rejouer?")){ // Tapez oui pour jouer
                finished = false;
                this.env.update();
            }
            else finished=true;

        }
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy"); 
       // String date = sdf.format(new Date()); 
        this.partie = new Partie("10/12/2017", this.mot, this.level);   //  Probleme de import , donc je met une date temporaire en attendant
        partie.setTemps(this.gameplay.getChrono().tempsPro());
        partie.setTrouve(this.gameplay.getNbLettresRestantes());
        //  this.partie.toString();
        profil.ajouterPartie(this.partie);
        profil.sauvegarder("src/xml/profile.xml"); 
        
         System.out.println("RESUME DE LA PARTIE:");         
         this.profil.toString();
        // exit
        env.exit();
    }
    
   
}
