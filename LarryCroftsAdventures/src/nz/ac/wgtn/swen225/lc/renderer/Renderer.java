package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {

    Board board = new Board();
    Tile[][] grid = board.getTiles();
    public Camera camera = new Camera(3, 3, 9, 9);
    public Renderer(){

    }

    /**
     * Draws each tile to form maze
     * @param mazePanel
     * @param g
     */
    public void draw(JPanel mazePanel, Graphics g){
        int mazePanelWidth = mazePanel.getWidth();
        int mazePanelHeight = mazePanel.getHeight();
        int tileWidth = mazePanelWidth/ camera.getWidth();
        int tileHeight = mazePanelHeight/camera.getHeight();

        // Set background colour
        g.setColor(new Color(232, 220, 202));
        g.fillRect(0, 0, mazePanelWidth, mazePanelHeight);

        int clampedValue = Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = mazePanelWidth/2 - (clampedValue* camera.getWidth()/2);
        int distanceFromTopBorder = mazePanelHeight/2 - (clampedValue* camera.getHeight()/2);

        for (int x = camera.getX(); x < camera.getX() + camera.getWidth(); x++){
            for (int y = camera.getY(); y < camera.getY() + camera.getHeight(); y++){
                int cameraX = camera.worldXToCameraX(x);
                int cameraY = camera.worldYToCameraY(y);
                Tile tile;
                // If coord trying to be drawn is out of bounds of board then just draw a wall tile.
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length){
                    tile = new Wall();
                }
                else {
                    tile = grid[x][y];
                }
                System.out.println("X: " + x  + "Y: " + y + "Class: " + tile.getClass());
                Image image = getTileImage(tile);
                g.drawImage(image, cameraX*clampedValue + distanceFromLeftBorder, cameraY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue, null);
            }
        }
    }

    private Image getTileImage(Tile tile){
        File file = null;
        try {
            if (tile instanceof Door d) {
                if (d.getColour() == Key.Colour.BLUE) {file = new File("LarryCroftsAdventures/assets/Door_Blue.png");}
                else if (d.getColour() == Key.Colour.GREEN) {file = new File("LarryCroftsAdventures/assets/Door_Green.png");}
                else if (d.getColour() == Key.Colour.RED) {file = new File("LarryCroftsAdventures/assets/Door_Red.png");}
                else if (d.getColour() == Key.Colour.YELLOW) {file = new File("LarryCroftsAdventures/assets/Door_Yellow.png");}
            }
            else if (tile instanceof Exit){
                file = new File("LarryCroftsAdventures/assets/Exit.png");
            }
            else if (tile instanceof ExitLock){
                file = new File("LarryCroftsAdventures/assets/Exit.png");
            }
            else if (tile instanceof Free){
                if (tile instanceof Treasure){
                    file = new File("LarryCroftsAdventures/assets/Treasure.png");
                }
                else if (tile instanceof InfoField){
                    file = new File("LarryCroftsAdventures/assets/InfoBox.png");
                }
                else if (tile instanceof KeyTile kt){
                    if (kt.getColour() == Key.Colour.BLUE) {file = new File("LarryCroftsAdventures/assets/Key_Blue.png");}
                    else if (kt.getColour() == Key.Colour.GREEN) {file = new File("LarryCroftsAdventures/assets/Key_Green.png");}
                    else if (kt.getColour() == Key.Colour.RED) {file = new File("LarryCroftsAdventures/assets/Key_Red.png");}
                    else if (kt.getColour() == Key.Colour.YELLOW) {file = new File("LarryCroftsAdventures/assets/Key_Yellow.png");}
                }
                else {
                    file = new File("LarryCroftsAdventures/assets/Free.png");
                }
            }
            else if (tile instanceof Wall){
                file = new File("LarryCroftsAdventures/assets/Wall.png");
            }
            else{
                return null;
            }
            // Load the PNG image from a file
            BufferedImage bufferedImage = ImageIO.read(file);

            // Create an Image object from the BufferedImage
            Image image = bufferedImage;

            return image;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void moveCameraLeft(){
        camera.setX(camera.getX()-1);
    }
    public void moveCameraRight(){
        camera.setX(camera.getX()+1);
    }
    public void moveCameraUp(){
        camera.setY(camera.getY()-1);
    }
    public void moveCameraDown(){
        camera.setY(camera.getY()+1);
    }

}