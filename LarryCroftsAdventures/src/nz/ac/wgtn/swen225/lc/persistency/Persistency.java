package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.*;
import com.google.gson.stream.*;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import java.io.*;
import java.util.*;

public class Persistency {
    private Stack<String> actions;
    private File newFile;
    public int timeLeft;
    public int level;
    private int x;
    private int y;

    public Tile[][] loadGame(String fileName) throws FileNotFoundException {
        File loadedFile = new File(fileName);
        Tile[][] maze = new Tile[15][15];
        try (JsonReader reader = new JsonReader(new FileReader(loadedFile))) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray boardArray = jsonObject.getAsJsonArray("board");

            timeLeft = jsonObject.get("timeLeft").getAsInt();
            level = jsonObject.get("level").getAsInt();

            for (int i = 0; i < 15; i++) {
                JsonArray columnArray = boardArray.get(i).getAsJsonArray(); // Loop through columns
                for (int j = 0; j < 15; j++) {
                    JsonObject cellObject = columnArray.get(j).getAsJsonObject(); // Access cell data

                    // Extract tile and item information from JSON
                    String tileType = cellObject.get("tile").getAsString();
                    String item = cellObject.get("item").getAsString();

                    // Create the appropriate tile based on tileType and item
                    maze[j][i] = switch (tileType) {
                        case "Free" -> new Free(j, i);
                        case "Wall" -> new Wall(j ,i);
                        case "Door_Yellow" -> new Door(Key.Colour.YELLOW, j, i);
                        case "Door_Red" -> new Door(Key.Colour.RED, j, i);
                        case "Door_Green" -> new Door(Key.Colour.GREEN, j, i);
                        case "Door_Blue" -> new Door(Key.Colour.BLUE, j, i);
                        default -> new Free(j, i);
                    };
                    /*if (!item.equals("none") && maze[j][i] instanceof Free f){
                        f.setItem(item);
                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    public void saveGame(int newFileNum, ArrayList<String> actions, int x, int y, int level) throws IOException {

        File newFile = new File("LarryCroftsAdventures" + File.separator + "Saves");
        newFile.mkdir();
        newFile = new File("LarryCroftsAdventures" + File.separator + "Saves" + File.separator + "saved-game_" + newFileNum + ".json");

        FileWriter fileWriter = new FileWriter(newFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        JsonObject gameData = new JsonObject();
        Gson gson = new Gson();

        // Player object
        JsonObject playerObject = new JsonObject();
        playerObject.addProperty("x", x);
        playerObject.addProperty("y", y);
        gameData.add("player", playerObject);

        // Time left and level
        gameData.addProperty("timeLeft", "100");
        gameData.addProperty("level", level);

        // Actions array
        JsonArray actionsArray = new JsonArray();
        for (String action : actions) {
            actionsArray.add(action);
        }
        gameData.add("actions", actionsArray);

        // Board array
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
        gson.toJson(gameData, jsonWriter);
        jsonWriter.close();
        fileWriter.close();
    }
}
