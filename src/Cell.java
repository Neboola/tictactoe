/**
 * Created by Regina on 22.10.2015.
 */
public class Cell {

    private char filling;
    public static final Cell emptyCell = new Cell(' ');
    public static final Cell xCell = new Cell('X');
    public static final Cell oCell = new Cell('O');

    Cell(char filling){
        this.filling = filling;
    }

    public String toString(){

        return String.valueOf(filling);
    }
}
