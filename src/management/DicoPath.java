package management;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class DicoPath extends DefaultHandler {

    private StringBuffer buffer;
    private int currentWordLevel;
    private ArrayList<String> listLevel1;
    private ArrayList<String> listLevel2;
    private ArrayList<String> listLevel3;
    private ArrayList<String> listLevel4;
    private ArrayList<String> listLevel5;

    private String pathToDicoFile;
    

    public String getWordFromListLevel(int level) {
        ArrayList<String> listCourante = new ArrayList<String>();
            switch(level){
                case 1:
                    listCourante = listLevel1;
                    break;
                case 2:
                    listCourante = listLevel2;
                    break;
                case 3:
                    listCourante = listLevel3;
                    break;
                case 4:
                    listCourante = listLevel4;
                    break;
                case 5:
                    listCourante = listLevel5;
                    break;
            }

        Random rand = new Random();
        int alea = rand.nextInt(listCourante.size()); 

        return listCourante.get(alea);          
    }

    public DicoPath() {
        super();
        listLevel1 = new ArrayList<String>();
        listLevel2 = new ArrayList<String>();
        listLevel3 = new ArrayList<String>();
        listLevel4 = new ArrayList<String>();
        listLevel5 = new ArrayList<String>();       
    }

/*** PROGRAME DE LECTURE DU PARSEUR ****/
    public void readDictionnary(String pathname) {
        pathToDicoFile = pathname;

        try {
            // création d'une fabrique de parseurs SAX
            SAXParserFactory fabrique = SAXParserFactory.newInstance();

            // création d'un parseur SAX
            SAXParser parseur = fabrique.newSAXParser();

            // lecture d'un fichier XML avec un DefaultHandler
            File fichier = new File(pathToDicoFile);
            parseur.parse(fichier, this);

        } catch (ParserConfigurationException pce ) {
            System.out.println("Erreur de configuration du parseur");
            System.out.println("Lors de l'appel à newSAXParser()");
        } catch (SAXException se) {
            System.out.println("Erreur de parsing");
            System.out.println("Lors de l'appel à parse()");
        } catch (IOException ioe) {
            System.out.println("Erreur d'entrée/sortie");
            System.out.println("Lors de l'appel à parse()");
        }
    }


    public boolean addWordToDico(int level, String mot) {
        switch (level) {
            case 1:listLevel1.add(mot);
            case 2:listLevel2.add(mot);
            case 3:listLevel3.add(mot);
            case 4:listLevel4.add(mot);
            case 5:listLevel5.add(mot);
            default: break;
        }
        return true;
    }
   public String getPathToDicoFile() {
        return this.pathToDicoFile;
    }

    public ArrayList<String> getListLevel1() {
        return listLevel1;
    }

    public ArrayList<String> getListLevel2() {
        return listLevel2;
    }

    public ArrayList<String> getListLevel3() {
        return listLevel3;
    }

    public ArrayList<String> getListLevel4() {
        return listLevel4;
    }

    public ArrayList<String> getListLevel5() {
        return listLevel5;
    }
    


    //SAX , on evalue les elements
    @Override
    public void startElement(String uri, String elem_now, String nom_element, Attributes attributes) throws SAXException {
        
        if (nom_element.equals("ns1:dictionnaire")) {
        } else if (nom_element.equals("ns1:mot")) {
            buffer = new StringBuffer();

            int index = attributes.getIndex("niveau");
            if (index != -1)currentWordLevel = Integer.parseInt(attributes.getValue(index));
            else  System.out.println("XML : Word non valide"); // si erreur on notifie dans la console
            
        } else {
            throw new SAXException("Element nom Valide " + nom_element);
        }
    }

    @Override
    public void endElement(String uri, String elem_now, String nom_element)
            throws SAXException {
       
        if (nom_element.equals("ns1:dictionnaire")) { // Onn parcoure seulement
            
        } else if (nom_element.equals("ns1:mot")) { //rajout du mot au dicionnaire

            addWordToDico(currentWordLevel, buffer.toString().toLowerCase());
            buffer = null;
        } else {
           
            throw new SAXException("Balise " + nom_element + " inconnue.");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) // on evalue le buffer, si ok on ajoute le string lecture
            throws SAXException {
        String lecture = new String(ch, start, length);
        if (buffer != null) {
            buffer.append(lecture);
        }
    }

}
