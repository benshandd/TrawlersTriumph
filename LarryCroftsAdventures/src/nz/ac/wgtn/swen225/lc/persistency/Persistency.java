package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.renderer.Renderer;

import javax.swing.*;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class handles the persistence of the game state, allowing loading and
 * saving of games.
 */
public class Persistency {
    /////////////////////
    private Chap chap;
    private File newFile;
    public int originalBoardTreasureCount;


    public static File level1 = new File("LarryCroftsAdventures/levels/level1.json");
    public static File level2 = new File("LarryCroftsAdventures/levels/level2.json");
    private int newFileNum;
    public Stack<String> actions;
    public int playerX;
    public int playerY;
    public int playerTreasureCount;
    Item[][] invent = new Item[2][4];
    public int boardTreasureCount;

    public int timeLeft;

    public int level;

    public String message;

    /////////////////////
    public int newFileNumToSave;
    public ArrayList<Move> actionsToSave;
    public int playerXToSave;
    public int playerYToSave;
    public int playerTreasureCountToSave;
    public int boardTreasureCountToSave;
    public int levelToSave;
    public int timeLeftToSave;
    Tile[][] boardToSave;

    /////////////////////

    public Persistency() {
        actionsToSave = new ArrayList<>();
    }

    /**
     * Loads game from a JSON file.
     *
     * @param fileName The name of the file containing the game state.
     * @return A 2D array representing the game board with tiles and items.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public Tile[][] loadGame(File fileName) throws FileNotFoundException {
        Tile[][] maze = null;
        try (JsonReader reader = new JsonReader(new FileReader(fileName))) {
            // Read the JSON file and parse it into a JsonObject
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Get board information
            JsonArray boardArray = jsonObject.getAsJsonArray("board");
            JsonArray inventoryArray = jsonObject.getAsJsonArray("inventory");

            int numRows = boardArray.size();
            int numCols = boardArray.get(0).getAsJsonArray().size();

            // Initialize the maze array based on board dimensions
            maze = new Tile[numCols][numRows];

            // Get player information
            JsonObject playerObject = jsonObject.getAsJsonObject("player");
            playerX = playerObject.get("x").getAsInt();
            playerY = playerObject.get("y").getAsInt();

            // Get time, level, treasure amount and initialize message
            timeLeft = jsonObject.get("timeLeft").getAsInt();

            playerTreasureCount = jsonObject.get("playerTreasureCount").getAsInt();
            boardTreasureCount = jsonObject.get("boardTreasureCount").getAsInt();
            level = jsonObject.get("level").getAsInt();
            message = null;

            for (int i = 0; i < inventoryArray.size(); i++) {
                JsonArray rowArray = inventoryArray.get(i).getAsJsonArray();
                for (int j = 0; j < rowArray.size(); j++) {
                    String itemName = rowArray.get(j).getAsString();
                    if (!itemName.equals("none")) {
                        invent[i][j] = getItemByName(itemName);
                    }
                }
            }

            // Populate the maze array
            for (int i = 0; i < numRows; i++) {
                JsonArray columnArray = boardArray.get(i).getAsJsonArray();
                for (int j = 0; j < numCols; j++) {
                    JsonObject cellObject = columnArray.get(j).getAsJsonObject(); // Access cell data

                    // Get tile and item information from JSON
                    String tileType = cellObject.get("tile").getAsString();
                    String item = cellObject.get("item").getAsString();
                    if (cellObject.has("message")) {
                        message = cellObject.get("message").getAsString();
                    }

                    // Create each tile based on tileType and item
                    maze[j][i] = switch (tileType) {
                        case "Free" -> new Free(j, i);
                        case "Wall" -> new Wall(j, i);
                        case "Door_Yellow" -> new Door(Key.Colour.YELLOW, j, i);
                        case "Door_Red" -> new Door(Key.Colour.RED, j, i);
                        case "Door_Green" -> new Door(Key.Colour.GREEN, j, i);
                        case "Door_Blue" -> new Door(Key.Colour.BLUE, j, i);
                        case "Key_Yellow" -> new KeyTile(Key.Colour.YELLOW, j, i);
                        case "Key_Red" -> new KeyTile(Key.Colour.RED, j, i);
                        case "Key_Green" -> new KeyTile(Key.Colour.GREEN, j, i);
                        case "Key_Blue" -> new KeyTile(Key.Colour.BLUE, j, i);
                        case "InfoBox" -> new InfoField(j, i);
                        case "ExitLock" -> maze[j][i] = new ExitLock(j, i);
                        case "Exit" -> maze[j][i] = new Exit(j, i);
                        default -> new Free(j, i);
                    };

                    switch (item) {
                        case "Treasure" -> maze[j][i] = new Treasure(j, i);
                    }
                    /*
                     * if (jsonObject.has("enemies")) {
                     * JsonArray enemiesArray = jsonObject.getAsJsonArray("enemies");
                     *
                     * for (JsonElement enemyElement : enemiesArray) {
                     * JsonObject enemyObject = enemyElement.getAsJsonObject();
                     *
                     * int enemyX = enemyObject.get("x").getAsInt();
                     * int enemyY = enemyObject.get("y").getAsInt();
                     * maze[enemyX][enemyY] = new EnemyTile(enemyX, enemyY, Enemy.Direction.UP,Instant.now(), chap);
                     * }
                     * }
                     */
                }

            }
            originalBoardTreasureCount = jsonObject.get("boardTreasureCount").getAsInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    /**
     * Setter method to set the instance variables for saving parameters.
     */
    public void setSaveParameters(int newFileNum, ArrayList<Move> actions, int x, int y, int playerTreasureCount,
                                  int boardTreasureCount, int level, int timeLeft, Tile[][] board, Chap chap) {
        this.newFileNumToSave = newFileNum;
        this.actionsToSave = actions;
        this.playerXToSave = x;
        this.playerYToSave = y;
        this.levelToSave = level;
        this.playerTreasureCountToSave = playerTreasureCount;
        this.boardTreasureCountToSave = boardTreasureCount;
        this.timeLeftToSave = timeLeft;
        this.boardToSave = board;
        this.chap = chap;
    }

    /**
     * Saves the current game state to a JSON file.
     */
    /**
     * Saves the current game state to a JSON file.
     */
    public void saveGame() throws IOException {
        File savesDirectory = new File("LarryCroftsAdventures" + File.separator + "Saves");
        savesDirectory.mkdir();
        newFileNum++;

        // Create a new save file
        newFile = new File(savesDirectory, "saved-game_" + newFileNum + ".json");

        FileWriter fileWriter = new FileWriter(newFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        JsonObject gameData = new JsonObject();
        Gson gson = new Gson();

        JsonObject playerObject = new JsonObject();
        playerObject.addProperty("x", playerXToSave);
        playerObject.addProperty("y", playerYToSave);

        gameData.add("player", playerObject);
        gameData.addProperty("timeLeft", timeLeftToSave);
        gameData.addProperty("level", levelToSave);
        gameData.addProperty("playerTreasureCount", playerTreasureCountToSave);
        gameData.addProperty("boardTreasureCount", boardTreasureCountToSave);


        JsonArray inventoryArray = new JsonArray();
        // Iterate through the inventory and add each item to the JSON array

        for (Item[] row : chap.getInventory()) {
            JsonArray rowArray = new JsonArray();
            for (Item item : row) {
                if (item == null) {
                    rowArray.add("none");
                } else if (item instanceof Key key) {
                    String colorName = ((Key) item).colour().name();
                    rowArray.add("Key_" + colorName.substring(0, 1).toUpperCase() + colorName.substring(1).toLowerCase());
                }
            }
            inventoryArray.add(rowArray);
        }
        // Add the inventory array to the gameData object
        gameData.add("inventory", inventoryArray);



        JsonArray actionsArray = new JsonArray();
        if (this.actionsToSave != null) {
            for (Move move : this.actionsToSave) {
                actionsArray.add(move.move());
            }
        }
        gameData.add("actions", actionsArray);

        JsonArray boardArray = new JsonArray();
        for (int i = 0; i < 15; i++) {
            JsonArray rowArray = new JsonArray();
            for (int j = 0; j < 15; j++) {
                JsonObject cellObject = new JsonObject();
                cellObject.addProperty("x", j);
                cellObject.addProperty("y", i);

                // Get the tile and item from the board
                Tile currentTile = boardToSave[j][i];
                switch (currentTile.getClass().getSimpleName()) {
                    case "Free":
                        cellObject.addProperty("tile", "Free");
                        break;
                    case "Wall":
                        cellObject.addProperty("tile", "Wall");
                        break;
                    case "Door":
                        Key.Colour doorColour = ((Door) currentTile).getColour();
                        if (doorColour == Key.Colour.RED) {
                            cellObject.addProperty("tile", "Door_Red");
                        }
                        if (doorColour == Key.Colour.BLUE) {
                            cellObject.addProperty("tile", "Door_Blue");
                        }
                        if (doorColour == Key.Colour.GREEN) {
                            cellObject.addProperty("tile", "Door_Green");
                        }
                        if (doorColour == Key.Colour.YELLOW) {
                            cellObject.addProperty("tile", "Door_Yellow");
                        }
                        break;
                    case "KeyTile":
                        Key.Colour keyColour = ((KeyTile) currentTile).getColour();
                        if (keyColour == Key.Colour.RED) {
                            cellObject.addProperty("tile", "Key_Red");
                        }
                        if (keyColour == Key.Colour.BLUE) {
                            cellObject.addProperty("tile", "Key_Blue");
                        }
                        if (keyColour == Key.Colour.GREEN) {
                            cellObject.addProperty("tile", "Key_Green");
                        }
                        if (keyColour == Key.Colour.YELLOW) {
                            cellObject.addProperty("tile", "Key_Yellow");
                        }
                        break;
                    case "InfoField":
                        cellObject.addProperty("tile", "InfoBox");
                        break;
                    case "ExitLock":
                        cellObject.addProperty("tile", "ExitLock");
                        break;
                    case "Exit":
                        cellObject.addProperty("tile", "Exit");
                        break;
                    default:
                        cellObject.addProperty("tile", "none");
                }

                String itemType = switch (currentTile.getClass().getSimpleName()) {
                    case "Treasure" -> "Treasure";
                    default -> "none";
                };
                cellObject.addProperty("item", itemType);

                rowArray.add(cellObject);
            }
            boardArray.add(rowArray);
        }
        gameData.add("board", boardArray);

        jsonWriter.setIndent("    ");

        // Write game data to the file
        gson.toJson(gameData, jsonWriter);

        // Close writers
        System.out.println("Game saved...");
        jsonWriter.close();
        fileWriter.close();
    }

    Item getItemByName(String itemName) {
        switch (itemName) {
            case "Key_Yellow":
                return new Key(Key.Colour.YELLOW);
            case "Key_Red":
                return new Key(Key.Colour.RED);
            case "Key_Green":
                return new Key(Key.Colour.GREEN);
            case "Key_Blue":
                return new Key(Key.Colour.BLUE);
            default:
                return null;
        }
    }

    public void resumeGame() {
        JFileChooser fileChooser = new JFileChooser("LarryCroftsAdventures/Saves");
        fileChooser.setDialogTitle("Choose a saved game file");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Load the selected saved game file
                Tile[][] loadedGame = loadGame(selectedFile);
                JOptionPane.showMessageDialog(null, "Game resumed successfully!", "Resume Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading the saved game file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}