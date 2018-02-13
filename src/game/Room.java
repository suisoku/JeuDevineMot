/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author zianinou
 */
public class Room {
    private int depth;
    private int height;
    private int width;
    private String textureTop;
    private String textureBottom;
    private String textureWest;
    private String textureEast;
    private String textureNorth;
    private String textureSouth;
    
    
    public Room(){
        this.textureBottom = "textures/skybox/interstellar/floor_future.png";
        this.textureNorth = "textures/skybox/interstellar/wall.png";
        this.textureEast = "textures/skybox/interstellar/wall.png";
        this.textureWest = "textures/skybox/interstellar/wall.png";

        this.depth = 50;
        this.height = 30;
        this.width = 50;
    }
        
    
}
