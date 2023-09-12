/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


// line 14 "model.ump"
// line 86 "model.ump"
public class Free implements Tile {

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Free Attributes
    private Chap chap;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Free(Chap aChap) {
        chap = aChap;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setChap(Chap aChap) {
        boolean wasSet = false;
        chap = aChap;
        wasSet = true;
        return wasSet;
    }

    public Chap getChap() {
        return chap;
    }

    public void delete() {
    }


    public String toString() {
        return super.toString() + "[" + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "chap" + "=" + (getChap() != null ? !getChap().equals(this) ? getChap().toString().replaceAll("  ", "    ") : "this" : "null");
    }
}