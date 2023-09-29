package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * The Renderer class is responsible for rendering the game board and characters on the JPanel.
 */
public class Renderer {

    Board board;
    Tile[][] grid;
    public Camera camera = new Camera(3, 3, 9, 9);
    private JPanel boardPanel;
    public enum State {
        IDLE, UP, DOWN, LEFT, RIGHT
    }
    private State state = State.IDLE;
    private Timer timer;
    private double distanceTotal = 0;
    private double distance = 0.125;
    /**
     * Constructor for the Renderer class.
     *
     * @param board The game board to render.
     */
    public Renderer(Board board, JPanel panel){
        panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                try {
                    draw(g);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        this.board = board;
        grid = board.getTiles();
        this.boardPanel = panel;
        startTimer();
    }

    public void startTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {;
                boardPanel.repaint();
            }
        });
        timer.start();
    }

    /**
     * Draws the game board, characters and items on the provided JPanel.
     *
     * @param g         The Graphics object to use for rendering.
     * @throws IOException If there is an error loading image files.
     */
    public void draw(Graphics g) throws IOException {
        // Dimensions
        int mazePanelWidth = boardPanel.getWidth();
        int mazePanelHeight = boardPanel.getHeight();
        double tileWidth = mazePanelWidth/ camera.getWidth();
        double tileHeight = mazePanelHeight/camera.getHeight();
        int clampedValue = (int)Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = (int)(mazePanelWidth/2 - (clampedValue* camera.getWidth()/2));
        int distanceFromTopBorder = (int)(mazePanelHeight/2 - (clampedValue* camera.getHeight()/2));

        // Go through all tiles visible to camera and draw them on the JPanel
        for (int x = (int)camera.getX()-1; x < camera.getX() + camera.getWidth()+1; x++){
            for (int y = (int)camera.getY()-1; y < camera.getY() + camera.getHeight()+1; y++){
                Tile tile;
                // If coordinates trying to be drawn is out of bounds of board then just draw a wall tile.
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length){
                    tile = new Wall();
                }
                else {
                    tile = grid[x][y];
                }

                Image image = getTileImage(tile);
                //drawImageAt(image, x, y, g);
                double cameraX = camera.worldXToCameraX(x);
                double cameraY = camera.worldYToCameraY(y);
                g.drawImage(image, (int) (cameraX*clampedValue + distanceFromLeftBorder), (int) (cameraY*clampedValue + distanceFromTopBorder), (int) (clampedValue), (int) (clampedValue), null);
            }
        }

        // Draw border
        g.setColor(new Color(232, 220, 202));
        g.fillRect(0, 0, mazePanelWidth, distanceFromTopBorder);
        g.fillRect(0, mazePanelHeight-distanceFromTopBorder, mazePanelWidth, distanceFromTopBorder);
        g.fillRect(0, 0, distanceFromLeftBorder, mazePanelHeight);
        g.fillRect(mazePanelWidth-distanceFromLeftBorder, 0, distanceFromLeftBorder, mazePanelHeight);

        // Draw Chap at Chap's coordinates
        File chapFile;
        chapFile = new File("LarryCroftsAdventures" + File.separator + "assets" + File.separator + "Chap.png");
        Image chapImage = ImageIO.read(chapFile);
        double chapX = camera.getX() + (clampedValue * 4);
        double chapY = camera.getY() + (clampedValue * 4);
        g.drawImage(chapImage, (int) (chapX + distanceFromLeftBorder), (int) (chapY + distanceFromTopBorder), (int) (clampedValue), (int) (clampedValue), null);

        switch (state){
            case IDLE:
                break;
            case UP:
                camera.setY(camera.getY() - distance);
                break;
            case DOWN:
                camera.setY(camera.getY() + distance);
                break;
            case LEFT:
                camera.setX(camera.getX() - distance);
                break;
            case RIGHT:
                camera.setX(camera.getX() + distance);
                break;
            default:
                break;
        }
        if((camera.getX() == board.getChap().getX() - 4) && (camera.getY() == board.getChap().getY() - 4)){
            state = State.IDLE;
        }
        System.out.println("cam X: " + camera.getX());
        double sdjvb = board.getChap().getX()-4;
        System.out.println("chap cam X: " + sdjvb);

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

    public State getState(){ return state; }
    public void setState(State state){this.state = state;}
    public JPanel getBoardPanel(){ return boardPanel; }
}
