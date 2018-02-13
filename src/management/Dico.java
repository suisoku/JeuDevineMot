/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zianinou
 */
public class Dico {
    public  ArrayList<String> listLevel1;
    private  ArrayList<String> listLevel2;
    private  ArrayList<String> listLevel3;
    private  ArrayList<String> listLevel4;
    private  ArrayList<String> listLevel5;
    private  String pathToDicoFile;
    
    
    public Dico(String path){
        listLevel1 = new ArrayList<String>();
        listLevel2 = new ArrayList<String>();
        listLevel3 = new ArrayList<String>();
        listLevel4 = new ArrayList<String>();
        listLevel5 = new ArrayList<String>();
        pathToDicoFile = path;
    }
    
    public String getWordListLevel(int level_user){
        Random random_pos = new Random();
       
        switch (level_user) {
            case 1: return listLevel1.get(random_pos.nextInt(listLevel1.size()));
            case 2: return listLevel2.get(random_pos.nextInt(listLevel2.size()));
            case 3: return listLevel3.get(random_pos.nextInt(listLevel3.size()));
            case 4: return listLevel4.get(random_pos.nextInt(listLevel4.size()));
            case 5: return listLevel5.get(random_pos.nextInt(listLevel5.size()));
            default: return "";
        }
    }
    
    
    public boolean addWordToDico(int level, String Word){
        switch (level) {
            case 1:
                listLevel1.add(Word);
                return true;
            case 2:
                listLevel2.add(Word);
                return true;
            case 3:
                listLevel3.add(Word);
                return true;
            case 4:
                listLevel4.add(Word);
                return true;
            case 5:
                listLevel5.add(Word);
                return true;
            default:
                return false;
        }
    }
    
    public String getPathToDicoFile(String a){return a; }
}