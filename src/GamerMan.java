import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Created by Regina on 27.10.2015.
 */
public class GamerMan extends Gamer {

    Cell cell;

    GamerMan(Cell cell){

        super(cell);
        this.cell = super.cell;
    }

    public Move getMove(Board board, int level) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Move nextMove = new Move();

        nextMove.index = new Index();
        nextMove.cell = cell;


        System.out.println(nextMove.cell + " will step to");

        while(true){
            System.out.println("H: ");
            nextMove.index.horizontal = Integer.parseInt(reader.readLine());
            if((nextMove.index.horizontal > 0) && (nextMove.index.horizontal < 4)){
                break;
            }
            else{
                System.out.println("Please enter 1-3");
            }
        }


        while(true){
            System.out.println("V: ");
            nextMove.index.vertical = Integer.parseInt(reader.readLine());
            if((nextMove.index.vertical > 0) && (nextMove.index.vertical < 4)){
                break;
            }
            else{
                System.out.println("Please enter 1-3");
            }
        }

        return nextMove;
    }



}
