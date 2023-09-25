package nz.ac.wgtn.swen225.lc.persistency;
import com.google.gson.*;
import com.google.gson.stream.*;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.tiles.Free;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Wall;
import java.io.*;
import java.util.*;

public class Persistency {
    private Stack<Chap> actions;
    private File newFile;
    private int timeLeft;
    private int level;
    private int x;
    private int y;

    public Tile[][] loadGame(String fileName, Tile[][] mazeObject) throws FileNotFoundException {
        File loadedFile = new File(fileName);
        if (!loadedFile.exists()) {
            System.out.println("File not found: " + fileName);
            return mazeObject;
        }

        try (JsonReader reader = new JsonReader(new FileReader(loadedFile))) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray boardArray = jsonObject.getAsJsonArray("board");

            if (boardArray.size() != 15) {
                System.out.println(boardArray.size());
                throw new IllegalStateException("Invalid board dimensions.");
            }

            Tile[][] maze = new Tile[15][15];

            for (int i = 0; i < 15; i++) {
                JsonArray rowArray = boardArray.get(i).getAsJsonArray();

                if (rowArray.size() != 15) {
                    System.out.println(rowArray.size());
                    throw new IllegalStateException("Invalid row dimensions.");
                }

                for (int j = 0; j < 15; j++) {
                    JsonObject tileObject = rowArray.get(j).getAsJsonObject();
                    String tileType = tileObject.get("tile").getAsString();
                    String item = tileObject.get("item").getAsString();

                    if (tileType.startsWith("Door_")) {
                        String color = tileType.substring(5);
                        maze[j][i] = new Free();
                    } else {
                        switch (tileType) {
                            case "Free":
                                maze[j][i] = new Free();
                                break;
                            case "Door_Exit":
                                maze[j][i] = new Free();
                                break;
                            case "Door":
                                maze[j][i] = new Free();
                                break;
                            case "Wall":
                                maze[j][i] = new Wall();
                                break;
                            case "Exit":
                                maze[j][i] = new Free();
                                break;
                            default:
                                throw new IllegalStateException("Unknown tile type: " + tileType);
                        }
                    }
                    if (item.startsWith("Key_")) {
                        String color = tileType.substring(4);
                        maze[j][i] = new Free();
                    } else {
                        switch (item) {
                            case "Treasure":
                                maze[j][i] = new Free();
                                break;
                            case "none":
                                maze[j][i] = new Free();
                                break;
                            case "InfoBox":
                                maze[j][i] = new Free();
                                break;
                            default:
                                throw new IllegalStateException("Unknown tile type: " + item);
                        }
                    }
                }
            }
            x = jsonObject.getAsJsonObject("player").get("x").getAsInt();
            y = jsonObject.getAsJsonObject("player").get("y").getAsInt();
            timeLeft = jsonObject.get("timeLeft").getAsInt();
            level = jsonObject.get("level").getAsInt();

            System.out.println("Loaded X: " + x);
            System.out.println("Loaded Y: " + y);
            System.out.println("Loaded Time Left: " + timeLeft);
            System.out.println("Loaded Level: " + level);
            System.out.println("Board: " + boardArray);

            return maze;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mazeObject;
    }

    public void saveGame(int newFileNum,Stack<String> moves, int x, int y) throws IOException {
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
