package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonArray;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

//TODO:implement loading 
//TODO:implement saving 

public class Persistency {
    private Stack<Chap> actions;
    private File newFile;
    private int timeLeft;
    private int level;
    private int x;
    private int y;

    public Persistency(int timeLeft, int level, int x, int y, Stack<Chap> actions) {
        this.timeLeft = timeLeft;
        this.level = level;
        this.x = x;
        this.y = y;
        this.actions = actions;
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

        for (int i = 0; i < 15; i++) { //15cols
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
