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

    Board board;
    Tile[][] grid;
    public Camera camera = new Camera(3, 3, 9, 9);
    public Renderer(Board board){
        this.board = board;
        grid = board.getTiles();
    }

    /**
     * Draws each tile to form maze
     * @param mazePanel
     * @param g
     */
    public void draw(JPanel mazePanel, Graphics g) throws IOException {
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
                Image image = getTileImage(tile);
                g.drawImage(image, cameraX*clampedValue + distanceFromLeftBorder, cameraY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue, null);
            }
        }

        // draw Chap
        File chapFile;
        chapFile = new File("LarryCroftsAdventures" + File.separator + "assets" + File.separator + "Chap.png");

        Image chapImage = ImageIO.read(chapFile);
        int chapX = camera.worldXToCameraX(board.getChap().getX());
        int chapY = camera.worldYToCameraY(board.getChap().getY());

        g.drawImage(chapImage, chapX*clampedValue + distanceFromLeftBorder, chapY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue, null);
    }

    private Image getTileImage(Tile tile) throws IOException {
        String fileName = "LarryCroftsAdventures" + File.separator + "assets" + File.separator;

        switch (tile.getClass().getSimpleName()) {
            case "Door" -> {
                Key.Colour doorColour = ((Door) tile).getColour();
                fileName += "Door_" + doorColour.name();
            }
            case "Exit", "ExitLock" -> fileName += "Exit";
            case "Treasure" -> fileName += "Treasure";
            case "InfoField" -> fileName += "InfoBox";
            case "KeyTile" -> {
                Key.Colour keyColour = ((KeyTile) tile).getColour();
                fileName += "Key_" + keyColour.name();
            }
            case "Free" -> {
                if (((Free) tile).getItem() != null) fileName += ((Free) tile).getItem();
                else fileName += "Free";
            }
            case "Wall" -> fileName += "Wall";
            default -> {
                return null; // Unknown tile type
            }
        }

        fileName += ".png";
        File file = new File(fileName);

        return ImageIO.read(file);
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
