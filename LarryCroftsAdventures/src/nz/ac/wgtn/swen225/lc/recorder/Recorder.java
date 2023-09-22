package nz.ac.wgtn.swen225.lc.recorder;
import java.util.Stack;
import java.util.regex.Pattern;


public class Recorder {

    static final Pattern TAB = Pattern.compile("    "); // four white spaces
    static final Pattern HALF_TAB = Pattern.compile("  ");
    private static Stack<String> actions;
    private int timeStamp;
    private int level;
    private int x;
    private int y;
    private static boolean rec;

    public Recorder(int timeStamp, int level, int x, int y, boolean rec) {

    }


    public void record(){
        actions = new Stack<>();
        if(!rec){
            stopRecord();
        }
    }
    public void stopRecord(){
        // call method in persistency passing all the values in this class to it, so it can make a JSON
        // file out of it.


    }
    public void stepByStep(){
        //load the file
    }
    public void replaySpeed(int speed) throws InterruptedException {
        Thread.sleep(speed * 100);
        actions.pop();
    }
    public void replay(){
        //load the file eg Recorder recorder = getRecorderFromFile();

    }
    /**
     * adds an action to the stack only if the global variable "record" is true.
     * @param str the action needing to be added to the stack
     */
    public static void addAction(String str){
        if (rec){
            actions.push(str);
        }
    }

    public void setRec(){
        rec = !rec;
    }
}
