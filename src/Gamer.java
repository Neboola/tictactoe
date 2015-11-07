import java.io.IOException;

/**
 * Created by Regina on 27.10.2015.
 */
public abstract class Gamer {

    private Cell cell;

    Gamer(Cell cell){
        this.cell = cell;
    }

    public abstract Move getMove(Board board) throws IOException;

    @Override
    public String toString(){
        return cell.toString();
    }

}
