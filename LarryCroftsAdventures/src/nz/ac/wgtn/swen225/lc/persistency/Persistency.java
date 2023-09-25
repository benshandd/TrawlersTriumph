package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Persistency {
    private Stack<Chap> actions;
    private File newFile;
    private int timeLeft;
    private int level;
    private int x;
    private int y;

    public void loadGame(String fileName, Tile[][] mazeObject) throws FileNotFoundException {
        File loadedFile = new File(fileName);
        if (!loadedFile.exists()) {
            return; // Exit if the file doesn't exist
        }

        try (JsonReader reader = new JsonReader(new FileReader(loadedFile))) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject playerObject = jsonObject.getAsJsonObject("player");
            int loadedX = playerObject.get("x").getAsInt();
            int loadedY = playerObject.get("y").getAsInt();
            int loadedTimeLeft = jsonObject.get("timeLeft").getAsInt();
            int loadedLevel = jsonObject.get("level").getAsInt();
            JsonArray boardArray = jsonObject.getAsJsonArray("board");
            JsonArray actionsArray = jsonObject.getAsJsonArray("actions");
            x = loadedX;
            y = loadedY;
            timeLeft = loadedTimeLeft;
            level = loadedLevel;
            actions.clear();
            Gson gson = new Gson();
            for (int i = 0; i < actionsArray.size(); i++) {
                Chap chap = gson.fromJson(actionsArray.get(i), Chap.class);
                actions.push(chap);
            }
            for (int row = 0; row < boardArray.size(); row++) {
                JsonArray rowArray = boardArray.get(row).getAsJsonArray();
                for (int col = 0; col < rowArray.size(); col++) {
                    JsonObject cellObject = rowArray.get(col).getAsJsonObject();
                    String type = cellObject.get("type").getAsString();
                    String item = cellObject.get("item").getAsString();
                    //mazeObject[row][col] = new Free(type, item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(int newFileNum) throws IOException {
        newFile = new File("saved-game_" + newFileNum + ".json");
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
        for (Chap chap : actions) {
            JsonObject chapObject = gson.toJsonTree(chap).getAsJsonObject();
            actionsArray.add(chapObject);
        }
        originalObject.add("actions", actionsArray);

        jsonWriter.setIndent("    ");
        gson.toJson(originalObject, jsonWriter);
        jsonWriter.close();
        fileWriter.close();
    }
}
