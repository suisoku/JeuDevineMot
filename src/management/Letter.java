/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import env3d.EnvObject;

/**
 *
 * @author zianinou
 */
public class Letter extends EnvObject {
    
    private char lettre;
    
    
    public Letter(char l, double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
        setScale(1);
        this.lettre = l;
        if(this.lettre != ' '){
            setTexture("models/letter/" + l + ".png");
        }
        else{
            setTexture("textures/black.png");
        }
        setModel("models/letter/cube.obj");
    }
    public char getChar(){
        return this.lettre;
    }
}
   