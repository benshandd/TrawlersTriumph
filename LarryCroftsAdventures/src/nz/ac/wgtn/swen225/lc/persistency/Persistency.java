package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.*;
import com.google.gson.stream.*;
import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import java.io.*;
import java.util.*;

/**
 * This class handles the persistence of the game state, allowing loading and
 * saving of games.
 */
public class Persistency {

    public Stack<String> actions;
    private File newFile;
    public int timeLeft;
    public int treasureLeft;
    public int level;
    public int x;
    public int y;
    public String message;

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
            int numRows = boardArray.size();
            int numCols = boardArray.get(0).getAsJsonArray().size();

            // Initialize the maze array based on board dimensions
            maze = new Tile[numCols][numRows];

            // Get player information
            JsonObject playerObject = jsonObject.getAsJsonObject("player");
            x = playerObject.get("x").getAsInt();
            y = playerObject.get("y").getAsInt();

            // Get time, level, treasure amount and initialize message
            timeLeft = jsonObject.get("timeLeft").getAsInt();
            treasureLeft = jsonObject.get("treasureLeft").getAsInt();
            level = jsonObject.get("level").getAsInt();
            message = null;

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
                        case "InfoBox" -> new InfoField(message, j, i);
                        default -> new Free(j, i);
                    };

                    switch (item) {
                        case "Treasure" -> maze[j][i] = new Treasure(j, i);
                        case "Exit_Lock" -> maze[j][i] = new ExitLock(j, i);
                        case "Exit" -> maze[j][i] = new Exit(j, i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    /**
     * Saves the current game state to a JSON file.
     *
     * @param newFileNum The number associated with the new save file.
     * @param actions    The list of actions performed in the game.
     * @param x          The x-coord of the player.
     * @param y          The y-coord of the player.
     * @param level      The current level.
     * @throws IOException If an I/O error occurs while saving the game.
     */
    public void saveGame(int newFileNum, ArrayList<Move> actions, int x, int y, int level) throws IOException {
        // Create the directory for saves if it doesn't exist already
        File newFile = new File("LarryCroftsAdventures" + File.separator + "Saves");
        newFile.mkdir();

        // Create a new save file
        newFile = new File("LarryCroftsAdventures" + File.separator + "Saves" + File.separator + "saved-game_"
                + newFileNum + ".json");

        FileWriter fileWriter = new FileWriter(newFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);

        JsonObject gameData = new JsonObject();
        Gson gson = new Gson();

        JsonObject playerObject = new JsonObject();
        playerObject.addProperty("x", x);
        playerObject.addProperty("y", y);

        gameData.add("player", playerObject);

        gameData.addProperty("timeLeft", timeLeft);
        gameData.addProperty("level", level);
        playerObject.addProperty("treasureLeft", treasureLeft);

        JsonArray actionsArray = new JsonArray();
        for (Move action : actions) {
            actionsArray.add(action.move());
        }
        gameData.add("actions", actionsArray);

        JsonArray boardArray = new JsonArray();
        for (int i = 0; i < 15; i++) {
            JsonArray rowArray = new JsonArray();
            for (int j = 0; j < 15; j++) {
                JsonObject cellObject = new JsonObject();
                cellObject.addProperty("x", j);
                cellObject.addProperty("y", i);
                cellObject.addProperty("tile", "Wall");
                cellObject.addProperty("item", "none");
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
}
