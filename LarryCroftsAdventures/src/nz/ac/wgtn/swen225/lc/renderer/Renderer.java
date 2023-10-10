package nz.ac.wgtn.swen225.lc.renderer;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.domain.Board;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The Renderer class is responsible for rendering the game board, characters and items on the JPanel as well as changing the view of the board when character moves.
 *
 * @author  Alex Manning (300600549)
 */
public class Renderer extends JPanel{

    // Game objects
    Board board;
    Tile[][] grid;
    public Camera camera;
    private final AudioUnit audioUnit;
    private final Random random = new Random();

    // Image fields
    public enum Images { DOOR_BLUE, DOOR_GREEN, DOOR_RED, DOOR_YELLOW, EXIT, FREE, INFOBOX, KEY_BLUE, KEY_GREEN, KEY_RED, KEY_YELLOW, WALL, BOAT, SEAGULL_LEFT, SEAGULL_RIGHT, ENEMY, FISH, BOTTLE,EXIT_LOCK, INFOPANEL, WHIRLPOOL, DOCK}
    private final HashMap<Images, BufferedImage> images = new HashMap<>();
    private final HashMap<Images, ArrayList<BufferedImage>> animations = new HashMap<>();
    private final ArrayList<BufferedImage> currentTileImage = new ArrayList<>();

    // Misc fields
    private int count = 0;
    private int cellSize;
    private double seagullX ;
    private double seagullY;
    private boolean seagullActivated = false;

    private App app;

    /**
     * Constructor for the Renderer class.
     *
     * @param board The game board to render.
     */
    public Renderer(Board board, int focusAreaSize, AudioUnit au, App app) throws IOException {
        this.board = board;
        this.grid = board.getTiles();
        this.app = app;
        this.camera = new Camera(board.getChap().getTile().getX() - (focusAreaSize/2), board.getChap().getTile().getY() - (focusAreaSize/2), focusAreaSize, focusAreaSize);
        this.audioUnit = au;
        loadImages();
        loadAllAnimations();
        startTimer();
    }

    /**
     * Starts timer that calls repaint on this JPanel to refresh what is on screen
     */
    private void startTimer(){
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.treasureLabel.setText("" + (app.getBoard().getBoardTreasureCount() - app.getBoard().getChap().getPlayerTreasureCount()));
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Draws the game board, characters and items.
     *
     * @param g         Graphics
     * @throws IOException If there is an error loading image files.
     */
    public void draw(Graphics g) throws IOException {
        cellSize = (int)Math.max(0, Math.min(this.getWidth()/ camera.getWidth(), this.getHeight()/camera.getHeight()));

        drawBoard(g);
        drawBoat(g);
        drawSeagull(g);
        drawBorder(new Color(232, 220, 202), cellSize, g);
        drawInfoPanel(g);

        camera.updateCameraPosition(board.getChap());

        count++;
    }

    /**
     * Draws all tiles that make up board
     * @param g Graphics
     * @throws IOException A tile image was not read
     */
    private void drawBoard(Graphics g) throws IOException {
        // Only draw tiles within or one outside of camera area
        for (int x = (int)camera.getX()-1; x < camera.getX() + camera.getWidth()+1; x++){
            for (int y = (int)camera.getY()-1; y < camera.getY() + camera.getHeight()+1; y++){
                Tile tile;
                // If coordinates trying to be drawn is out of bounds of board then just draw a wall tile.
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length){
                    tile = new Wall(0,0);
                }
                else {
                    tile = grid[x][y];
                }
                // Get images that make up tile the draw to screen
                ArrayList<BufferedImage> images = getTileImage(tile);
                for (Image img : images){
                    g.drawImage(img, (int)worldXToPanelX(x), (int)worldYToPanelY(y), cellSize, cellSize, null);
                }
            }
        }
        app.updateInventory(board.getChap().getInventory());
    }

    /**
     * Draws boat at center of board
     * @param g Graphics
     */
    private void drawBoat(Graphics g){
        int boatX = (this.getWidth() /2)  - cellSize/2;
        int boatY = (this.getHeight() /2)  - cellSize/2;
        g.drawImage(animations.get(Images.BOAT).get(count/16 % animations.get(Images.BOAT).size()), boatX, boatY, cellSize, cellSize, null);
    }

    /**
     * Draws Seagulls at random intervals at random y coordinates
     * @param g Graphics
     */
    private void drawSeagull(Graphics g){
        if (!seagullActivated && random.nextInt(0, 1000) == 0){
            seagullActivated = true;
            audioUnit.playSeagullSFX(); // Play seagull sound
            int lowerBound = (int)camera.getY();
            int upperBound = (int)(camera.getY() + camera.getHeight());
            seagullY = random.nextInt(lowerBound, upperBound + 1);
        }
        if (seagullActivated){
            int x = (int)worldXToPanelX(seagullX);
            int y = (int)worldYToPanelY(seagullY);
            // Draw seagull
            g.drawImage(animations.get(Images.SEAGULL_RIGHT).get(count/16 % animations.get(Images.SEAGULL_RIGHT).size()), x, y, cellSize, cellSize, null);
            seagullX += 0.05;
            if (seagullX > grid.length){ // Seagull off the screen so deactivated
                seagullActivated = false;
                seagullX = 0;
            }
        }
    }

    /**
     * Draws border around game board
     * @param c Colour of border
     * @param cellSize Size of board cell
     * @param g Graphics
     */
    private void drawBorder(Color c, int cellSize, Graphics g){
        int left = (int)(this.getWidth()/2 - (cellSize* camera.getWidth()/2));
        int top = (int)(this.getHeight()/2 - (cellSize* camera.getHeight()/2));
        // Draw border
        g.setColor(c);
        g.fillRect(0, 0, this.getWidth(), top);
        g.fillRect(0, this.getHeight()-top, this.getWidth(), top);
        g.fillRect(0, 0, left, this.getHeight());
        g.fillRect(this.getWidth()-left, 0, left, this.getHeight());
    }

    /**
     * Display info panel if player is on info panel tile
     */
    private void drawInfoPanel(Graphics g){
        if (board.getChap().getTile() instanceof  InfoField){
            g.drawImage(images.get(Images.INFOPANEL), this.getWidth()/2 - cellSize*4, this.getHeight()/2 - cellSize*3, cellSize*8, cellSize*6, null);
        }
    }

    /**
     * Returns arraylist of images that make up tile
     * @param tile The tile of which its images need to be retrieved
     * @return Arraylist of images that make up tile
     */
    private ArrayList<BufferedImage> getTileImage(Tile tile){
        currentTileImage.clear();
        // Assign filename depending on tile type and tile item
        currentTileImage.add(images.get(Images.FREE));
        switch (tile.getClass().getSimpleName()) {
            case "Door" -> {
                Key.Colour doorColour = ((Door) tile).getColour();
                if (doorColour == Key.Colour.RED) {
                    currentTileImage.add(images.get(Images.DOOR_RED));
                }
                if (doorColour == Key.Colour.BLUE) {
                    currentTileImage.add(images.get(Images.DOOR_BLUE));
                }
                if (doorColour == Key.Colour.GREEN) {
                    currentTileImage.add(images.get(Images.DOOR_GREEN));
                }
                if (doorColour == Key.Colour.YELLOW) {
                    currentTileImage.add(images.get(Images.DOOR_YELLOW));
                }
            }
            case "Exit"-> currentTileImage.add(animations.get(Images.WHIRLPOOL).get(count / 16 % animations.get(Images.WHIRLPOOL).size()));
            case "ExitLock" -> currentTileImage.add(images.get(Images.DOCK));
            case "Treasure" -> currentTileImage.add(animations.get(Images.FISH).get(count / 16 % animations.get(Images.FISH).size()));
            case "InfoField" -> {
                currentTileImage.add(animations.get(Images.BOTTLE).get(count / 10 % animations.get(Images.BOTTLE).size()));
            }
            case "KeyTile" -> {
                Key.Colour keyColour = ((KeyTile) tile).getColour();
                if (keyColour == Key.Colour.RED) {
                    currentTileImage.add(images.get(Images.KEY_RED));
                }
                if (keyColour == Key.Colour.BLUE) {
                    currentTileImage.add(images.get(Images.KEY_BLUE));
                }
                if (keyColour == Key.Colour.GREEN) {
                    currentTileImage.add(images.get(Images.KEY_GREEN));
                }
                if (keyColour == Key.Colour.YELLOW) {
                    currentTileImage.add(images.get(Images.KEY_YELLOW));
                }
            }
            case "Free" -> {
            }
            case "Wall" -> currentTileImage.add(images.get(Images.WALL));
            default -> {
                currentTileImage.add(images.get(Images.WALL)); // Unknown tile type.
            }
        }
        return currentTileImage;
    }

    /**
     * Loads game images into a map for easy access
     * @throws IOException File was not read
     */
    private void loadImages() throws IOException {
        String path = "LarryCroftsAdventures/assets/";
        images.put(Images.BOAT, ImageIO.read(new File(path + "Boat.png")));
        images.put(Images.DOOR_BLUE, ImageIO.read(new File(path + "Door_BLue.png")));
        images.put(Images.DOOR_GREEN, ImageIO.read(new File(path + "Door_Green.png")));
        images.put(Images.DOOR_RED, ImageIO.read(new File(path + "Door_Red.png")));
        images.put(Images.DOOR_YELLOW, ImageIO.read(new File(path + "Door_Yellow.png")));
        images.put(Images.EXIT, ImageIO.read(new File(path + "Exit.png")));
        images.put(Images.EXIT_LOCK, ImageIO.read(new File(path + "ExitLock.png")));
        images.put(Images.FREE, ImageIO.read(new File(path + "Free.png")));
        images.put(Images.INFOBOX, ImageIO.read(new File(path + "InfoBox.png")));
        images.put(Images.KEY_BLUE, ImageIO.read(new File(path + "Key_Blue.png")));
        images.put(Images.KEY_GREEN, ImageIO.read(new File(path + "Key_Green.png")));
        images.put(Images.KEY_RED, ImageIO.read(new File(path + "Key_Red.png")));
        images.put(Images.KEY_YELLOW, ImageIO.read(new File(path + "Key_Yellow.png")));
        images.put(Images.WALL, ImageIO.read(new File(path + "Wall.png")));
        images.put(Images.SEAGULL_LEFT, ImageIO.read(new File(path + "SeagullLeft.png")));
        images.put(Images.SEAGULL_RIGHT, ImageIO.read(new File(path + "SeagullRight.png")));
        images.put(Images.FISH, ImageIO.read(new File(path + "Fish.png")));
        images.put(Images.INFOPANEL, ImageIO.read(new File(path + "InfoPanel.png")));
        images.put(Images.DOCK, ImageIO.read(new File(path + "Dock.png")));
    }

    /**
     * Loads animation arraylists into animation list
     * @throws IOException File was not read
     */
    private void loadAllAnimations() throws IOException {
        String path = "LarryCroftsAdventures/assets/";
        animations.put(Images.BOAT, loadAnimation(ImageIO.read(new File(path + "Boat.png"))));
        animations.put(Images.FISH, loadAnimation(ImageIO.read(new File(path + "Fish.png"))));
        animations.put(Images.BOTTLE, loadAnimation(ImageIO.read(new File(path + "Bottle.png"))));
        animations.put(Images.SEAGULL_RIGHT, loadAnimation(ImageIO.read(new File(path + "SeagullRight.png"))));
        animations.put(Images.WHIRLPOOL, loadAnimation(ImageIO.read(new File(path + "WhirlPool.png"))));
    }

    /**
     * Creates array of all image frames in animation
     * @param img Image to be broken into image frames
     * @return ArrayList of image frames
     */
    private ArrayList<BufferedImage> loadAnimation(BufferedImage img){
        int numOfFrames = img.getWidth()/img.getHeight();
        int frameWidth = img.getWidth()/numOfFrames;
        int frameHeight = img.getHeight();
        ArrayList<BufferedImage> imgList = new ArrayList<>();
        for (int i = 0; i < numOfFrames; i++){
            imgList.add(img.getSubimage(i * frameWidth,0,frameWidth,frameHeight));
        }
        return imgList;
    }

    /**
     *  Converts world x coordinate to panel x coordinate
     */
    private double worldXToPanelX(double worldX){
        double tileWidth = this.getWidth()/ camera.getWidth();
        double tileHeight = this.getHeight()/camera.getHeight();
        int clampedValue = (int)Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromLeftBorder = (int)(this.getWidth()/2 - (clampedValue* camera.getWidth()/2));
        return (worldX - camera.getX())*clampedValue + distanceFromLeftBorder;
    }

    /**
     *  Converts world y coordinate to panel y coordinate
     */
    private double worldYToPanelY(double worldY){
        double tileWidth = this.getWidth()/ camera.getWidth();
        double tileHeight = this.getHeight()/camera.getHeight();
        int clampedValue = (int)Math.max(0, Math.min(tileWidth, tileHeight));
        int distanceFromTopBorder = (int)(this.getHeight()/2 - (clampedValue* camera.getHeight()/2));
        return (worldY - camera.getY())*clampedValue + distanceFromTopBorder;
    }

    /**
     * Returns the renderer's camera
     * @return the renderer's camera
     */
    public Camera getCamera(){ return camera; }


    /**
     * Override JPanel paint method to call the draw method
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        try {
            draw(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
