import java.io.IOException;

/**
 * Created by Neboola on 27.10.2015.
 */
public abstract class Gamer {

    Cell cell;

    Gamer(Cell cell){
        this.cell = cell;
    }

    public abstract Move getMove(Board board, int level) throws IOException;




}
