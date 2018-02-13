/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import env3d.Env;
import game.Room;
import game.Tux;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author zianinou
 */
public class DevineLeMot {
        private Env env;
        private Tux tux;
        private ArrayList<Letter> letters;
        private int temps;
        private Chronometre chrono;
        private String complete_word; // mot afiché a l'ecran en temps reel
        
        public DevineLeMot(String mot, int limite , Env env, Tux tux){
            this.env = env;
            this.tux = tux;
            this.letters = new ArrayList<Letter>();
            this.chrono = new Chronometre(limite);
            this.complete_word = "";
            Random posX = new Random();
            Random posZ = new Random();
            
            for(int i = 0 ; i<mot.length() ; i++){
                letters.add(new Letter(mot.charAt(i), 5 + posX.nextInt(35),3, 10 + posZ.nextInt(40)));
            }

        }
        
        public void jouer(){

            // Add the letters
            for(int i=0 ; i< this.letters.size(); i++){
                env.addObject(letters.get(i));
            }
            // Start chrono
            chrono.start();
            // The main game loop
            while (env.getKey() != 1 && this.letters.size() > 0 && chrono.remainsTime()){
                // Ask for user input, check if it collides and remove letters if necessary
                this.checkUserKey();

                

                if(this.tuxMeetsLetter()){
                    this.tux.turn(env);
                    this.complete_word += this.letters.get(0).getChar();
                    this.env.removeObject(this.letters.get(0));
                    this.letters.remove(0);
                }
                
                
                env.setDisplayStr(""+ chrono.toString(), 20, 480);
                env.setDisplayStr("MOT :  " +complete_word,480,480);

                
                // Update display
                env.advanceOneFrame();
                
                if(env.getKey() == 1)env.exit();
            } 

            //Post-Process: game is finished
            //we have to keep the data to save our score (chrono, temps, nbLettresRestantes) 
            
        }
        
        
        public void checkUserKey(){
            tux.move(env.getKeyDown(), 0.4f, env);
        }
        private boolean tuxMeetsLetter(){
            return collision(this.tux , this.letters.get(0));
        }
        private double distance(Tux tux , Letter letter){
            return Math.sqrt(Math.pow(letter.getX() - tux.getX(), 2) + pow(letter.getZ() - tux.getZ(), 2)) ;
        }
        private boolean collision(Tux tux , Letter letter){
            return distance(tux , letter) <= 1.1;
        }
        public int getTemps(){
            return this.temps;
        }
     
        public ArrayList<Letter> getLetters() {
            return letters;
        }

        public int getNbLettresRestantes() {
            return this.letters.size();
        }
        public Chronometre getChrono() {
            return chrono;
        }
        
}
