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

/**
 * The Renderer class is responsible for rendering the game board and characters on the JPanel.
 */
public class Renderer {

    Board board;
    Tile[][] grid;
    public Camera camera = new Camera(3, 3, 9, 9);

    /**
     * Constructor for the Renderer class.
     *
     * @param board The game board to render.
     */
    public Renderer(Board board){
        this.board = board;
        grid = board.getTiles();
    }

    /**
     * Draws the game board, characters and items on the provided JPanel.
     *
     * @param mazePanel The JPanel on which to render the game.
     * @param g         The Graphics object to use for rendering.
     * @throws IOException If there is an error loading image files.
     */
    public void draw(JPanel mazePanel, Graphics g) throws IOException {
        int mazePanelWidth = mazePanel.getWidth();
        int mazePanelHeight = mazePanel.getHeight();
        int tileWidth = mazePanelWidth/ camera.getWidth();
        int tileHeight = mazePanelHeight/camera.getHeight();

        // Set background colour
        g.setColor(new Color(232, 220, 202));
        g.fillRect(0, 0, mazePanelWidth, mazePanelHeight);

        // Dimensions for board and tile (for window resizing)
        int clampedValue = Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = mazePanelWidth/2 - (clampedValue* camera.getWidth()/2);
        int distanceFromTopBorder = mazePanelHeight/2 - (clampedValue* camera.getHeight()/2);

        // Go through all tiles visible to camera and draw them on the JPanel
        for (int x = camera.getX(); x < camera.getX() + camera.getWidth(); x++){
            for (int y = camera.getY(); y < camera.getY() + camera.getHeight(); y++){
                int cameraX = camera.worldXToCameraX(x);
                int cameraY = camera.worldYToCameraY(y);
                Tile tile;
                // If coordinates trying to be drawn is out of bounds of board then just draw a wall tile.
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

        // Draw Chap at Chap's coordinates
        File chapFile;
        chapFile = new File("LarryCroftsAdventures" + File.separator + "assets" + File.separator + "Chap.png");
        Image chapImage = ImageIO.read(chapFile);
        int chapX = camera.worldXToCameraX(board.getChap().getX());
        int chapY = camera.worldYToCameraY(board.getChap().getY());
        g.drawImage(chapImage, chapX*clampedValue + distanceFromLeftBorder, chapY*clampedValue + distanceFromTopBorder, clampedValue, clampedValue, null);
    }

    private Image getTileImage(Tile tile) throws IOException {
        String fileName = "LarryCroftsAdventures" + File.separator + "assets" + File.separator;

        // Assign filename depending on tile type and tile item
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

    /**
     * Move the camera one tile to the left.
     */
    public void moveCameraLeft() { camera.setX(camera.getX() - 1); }

    /**
     * Move the camera one tile to the right.
     */
    public void moveCameraRight() { camera.setX(camera.getX() + 1); }

    /**
     * Move the camera one tile up.
     */
    public void moveCameraUp() { camera.setY(camera.getY() - 1); }

    /**
     * Move the camera one tile down.
     */
    public void moveCameraDown() { camera.setY(camera.getY() + 1); }
}
