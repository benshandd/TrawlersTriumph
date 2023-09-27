package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.*;
import com.google.gson.stream.*;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.items.Key;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

import java.io.*;
import java.util.*;

public class Persistency {
    // private Stack<String> actions;
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
                        case "Free" -> new Free();
                        case "Wall" -> new Wall();
                        case "Door_Yellow" -> new Door(Key.Colour.YELLOW);
                        case "Door_Red" -> new Door(Key.Colour.RED);
                        case "Door_Green" -> new Door(Key.Colour.GREEN);
                        case "Door_Blue" -> new Door(Key.Colour.BLUE);
                        default -> new Free();
                    };
                    if (!item.equals("none") && maze[j][i] instanceof Free f){
                        f.setItem(item);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }


    public void saveGame(int newFileNum, ArrayList<String> actions, int x, int y, int level) throws IOException {
        newFile = new File("LarryCroftsAdventures" + File.separator + "Saves");
        newFile.mkdir();
        newFile = new File("LarryCroftsAdventures" + File.separator + "Saves" + File.separator + "saved-game_"+newFileNum+".json");
        FileWriter fileWriter = new FileWriter(newFile);
        JsonWriter jsonWriter = new JsonWriter(fileWriter);
        Gson gson = new Gson();
        JsonObject originalObject = new JsonObject();
        JsonObject playerObject = new JsonObject();

        playerObject.addProperty("x", x);
        playerObject.addProperty("y", y);
        originalObject.add("player", playerObject);
        originalObject.addProperty("timeLeft", "100"); // Time left
        originalObject.addProperty("level", level); // Level
        JsonArray boardArray = new JsonArray();
        JsonObject rowObject = new JsonObject();
        JsonArray rowArray = new JsonArray();
        rowObject.add("row", rowArray);

        for (int i = 0; i < 15; i++) { // 15 rows
            JsonObject cellObject = new JsonObject();
            cellObject.addProperty("type", "Wall");
            cellObject.addProperty("item", "none");
            rowArray.add(cellObject);
        }

        boardArray.add(rowObject);
        originalObject.add("board", boardArray);
        JsonArray actionsArray = new JsonArray();
        for (String a : actions) {
            actionsArray.add(a);
        }
        originalObject.add("actions", actionsArray);

        jsonWriter.setIndent("    ");
        gson.toJson(originalObject, jsonWriter);
        jsonWriter.close();
        fileWriter.close();
    }

}
