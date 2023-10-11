package nz.ac.wgtn.swen225.lc.recorder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.Move;
import nz.ac.wgtn.swen225.lc.domain.Chap;
import nz.ac.wgtn.swen225.lc.domain.IllegalMove;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.renderer.Camera;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Recorder {
    private ArrayList<Move> movesList;
    private int x;
    private int y;
    private int numberOfTreasuresPlayer;
    private int numberOfTreasuresBoard;
    int initLevel;
    int timeLeft;
    Tile[][] board;
     Chap chap;


    public Recorder(ArrayList<Move> movesList, int x, int y, int numberOfTreasuresPlayer, int numberOfTreasuresBoard,
                    int initLevel, int timeLeft, Tile[][] board, Chap chap) {
        this.movesList = movesList;
        this.x = x;
        this.y = y;
        this.numberOfTreasuresPlayer = numberOfTreasuresPlayer;
        this.numberOfTreasuresBoard = numberOfTreasuresBoard;
        this.initLevel = initLevel;
        this.timeLeft = timeLeft;
        this.board = board;
        this.chap = chap;
    }

    public Recorder(){}

    /**
     * saves the recording
     * @param count file count
     * @throws IOException for the file writer
     */
    public void saveRecorder(int count) throws IOException {
        Persistency p = new Persistency();
        p.setSaveParameters(count, movesList, this.x, this.y, this.numberOfTreasuresPlayer,
                this.numberOfTreasuresBoard, this.initLevel, this.timeLeft, this.board, this.chap);
        p.saveGame();

    }

    /**
     * loads the moves from the chosen file
     * @param file chosen file
     * @return moves in that file
     */
    public ArrayList<Move> loadSave(File file,App app) {
        try(JsonReader reader = new JsonReader(new FileReader(file))){
            ArrayList<Move> moves = new ArrayList<>();
            //getting the actions array out of the file to then be able to use the actions
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonMoves = jsonObject.getAsJsonArray("actions");

            for (int i = 0; i < jsonMoves.size(); i++){
                moves.add(new Move(jsonMoves.get(i).getAsString(),1));
            }
            //pass file name to load the level the player started on and position where they started from
            app.setup(file);
            return moves;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * step by step playback moves
     * @param move move to be played back
     */
    public void step(Move move) throws IllegalMove {
        Chap chap = App.getBoard().getChap();
        switch (move.move()){
            case "UP" -> {
                App.getRenderer().getCamera().setState(Camera.State.UP);
                chap.move(Chap.Direction.UP);
            }
            case "DOWN" -> {
                App.getRenderer().getCamera().setState(Camera.State.DOWN);
                chap.move(Chap.Direction.DOWN);
            }
            case "LEFT" -> {
                App.getRenderer().getCamera().setState(Camera.State.LEFT);
                chap.move(Chap.Direction.LEFT);
            }
            case "RIGHT" -> {
                App.getRenderer().getCamera().setState(Camera.State.RIGHT);
                chap.move(Chap.Direction.RIGHT);
            }
        }
    }



}
