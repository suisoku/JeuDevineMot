/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;


import env3d.Env;
import env3d.EnvObject;
import org.lwjgl.input.Keyboard;



/**
 *
 * @author zianinou
 */
public class Tux extends EnvObject {
    
    public Tux (double x, double y, double z){
        //Dans le constructeur
            setX(x);
            setY(y);
            setZ(z);
            setScale(2);
            setTexture("models/tux/tux.png");
            setModel("models/tux/tux.obj");
    }

    public void move(int currentKey, float ecart, Env env) {
        
        switch (currentKey) {
            case Keyboard.KEY_UP:
                if (this.getZ() > 5) {   // on prend on compte les collisions avec le mur
                    this.setZ(this.getZ() - ecart);
                    this.setRotateY(180);
                }
                else{
                    env.soundPlay("sounds/collision.wav");
                }
                break;
            case Keyboard.KEY_DOWN:
                if (this.getZ() < 53) {
                    this.setZ(this.getZ() + ecart);
                    this.setRotateY(0); // vers nous c'est l'angle 0
                }
                else{
                    env.soundPlay("sounds/collision.wav");
                }
                break;
            case Keyboard.KEY_LEFT:
                if (this.getX() > 1) {
                    this.setX(this.getX() - ecart);
                    this.setRotateY(-90);
                }
                else{
                    env.soundPlay("sounds/collision.wav");
                }
                break;

            case Keyboard.KEY_RIGHT:
                if (this.getX() < 49) {
                    this.setX(this.getX() + ecart);
                    this.setRotateY(90);

                }
                else{
                    env.soundPlay("sounds/collision.wav");
                }
                break;
        }
    }
    
    public void turn(Env env) { // permet la rotation du tux (animation)
    env.soundPlay("sounds/turn.wav"); 
        while (this.getY() < 10 ) {
            this.setY(this.getY() + 1);
            env.advanceOneFrame();
            //double init=this.getRotateY();   
        }
        double init=this.getRotateY();
        while (this.getRotateY() <=360 ) { 
            this.setRotateY(this.getRotateY() + 20);
            env.advanceOneFrame();
        }
        this.setRotateY(init); 
        while (this.getY() > 4) {
                this.setY(this.getY() - 1);
                env.advanceOneFrame();
        }
        env.soundStop("sounds/turn.wav");
    }
}
