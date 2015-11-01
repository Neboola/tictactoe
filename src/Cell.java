/**
 * Created by Neboola on 22.10.2015.
 */
public class Cell {
    public char filling;

    public static final Cell emptyCell = new Cell(' ');
    public static final Cell xCell = new Cell('X');
    public static final Cell oCell = new Cell('O');


    Cell(char filling){
        this.filling = filling;
    }
}
