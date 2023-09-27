package nz.ac.wgtn.swen225.lc.recorder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.renderer.Renderer;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Stack;


public class Recorder {
    private ArrayList<String> movesList;
    private int x;
    private int y;
    int initLevel;

    public Recorder(ArrayList<String> movesList, int x, int y, int initLevel) {
        this.movesList = movesList;
        this.x = x;
        this.y =y;
        this.initLevel = initLevel;
    }
    public Recorder(){}

    /**
     * saves the recording
     * @param count file count
     * @throws IOException for the file writer
     */
    public void saveRecorder(int count) throws IOException {
       new Persistency().saveGame(count,this.movesList,this.x, this.y, this.initLevel);
    }

    /**
     * loads the moves from the chosen file
     * @param file chosen file
     * @return moves in that file
     */
    public ArrayList<String> loadSave(File file) {
        try(JsonReader reader = new JsonReader(new FileReader(file))){
            ArrayList<String> moves = new ArrayList<>();
            //getting the actions array out of the file to then be able to use the actions
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray jsonMoves = jsonObject.getAsJsonArray("actions");

            for (int i = 0; i < jsonMoves.size(); i++){
                moves.add(jsonMoves.get(i).getAsString());
            }

            return moves;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * step by step playback moves
     * @param move move to be played back
     */
    public void step(String move){
        switch (move){
            case "UP" :
                //Renderer renderer =
                //renderer.moveCameraUp();
                //App.getBoard().getChap().move("UP");
            case "DOWN" :
                //Renderer renderer =
                //renderer.moveCameraDown();
                //App.getBoard().getChap().move("DOWN");
            case "LEFT" :
                //Renderer renderer =
                //renderer.moveCameraLeft();
                //App.getBoard().getChap().move("LEFT");
            case "RIGHT" :
                //Renderer renderer =
                //renderer.moveCameraRight();
                //App.getBoard().getChap().move("RIGHT");
            default:
                break;

        }
    }

    /**
     * method to auto playback the moves
     * @param moves moves in the file chosen
     * @param speed speed of the playback
     */
    public void auto(ArrayList<String> moves, int speed){

    }


}
