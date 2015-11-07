import java.util.ArrayList;

/**
 * Created by Regina on 27.10.2015.
 */
public class GamerBot extends Gamer {

    private Cell cell;

    GamerBot(Cell cell){
        super(cell);
        this.cell = cell;
    }

    public Move getMove(Board board, int level){
        //System.out.println(cell.filling + " thinking...");

        Move previousMove = new Move();

        Move nextMove = new Move();
        nextMove.cell = cell;

        if(cell == Cell.X){
            previousMove.cell = Cell.O;
        }
        else if(cell == Cell.O){
            previousMove.cell = Cell.X;
        }

        previousMove.nextBoard = board;

        Move root = previousMove.getRoot(level);
        //System.out.println(root.cell.filling + ": I'm thinking!");

        int bestPriority = -10000;
        for(Move move : root.movesList){
            System.out.println(move.cell + ": maybee there?  " + move.index.horizontal + move.index.vertical);
            System.out.println("Priority is " + move.priority);
            if(move.priority > bestPriority) {
                bestPriority = move.priority;
                //System.out.println(move.cell.filling + ": better to "+ move.index.horizontal + move.index.vertical);
            }
        }

        ArrayList bestMovesList = new ArrayList<Move>();

        for(Move move1 : root.movesList){
            if(move1.priority == bestPriority) {
                bestMovesList.add(move1);
                System.out.println(move1.cell + ": I can go there "+ move1.index.horizontal + move1.index.vertical);
                System.out.println("Priority is " + move1.priority);
            }
        }

int random = (int)(Math.random() * (bestMovesList.size()));

//random


        nextMove = (Move)bestMovesList.get(random);

        //System.out.println("priority step " + nextMove.index.horizontal + "." + nextMove.index.vertical);

        Move winMove = board.obtainWinMove(nextMove, board);
        if(winMove != null) {
            //System.out.println("win step " + winMove.index.horizontal + "." + winMove.index.vertical);
            return winMove;
        }

        Move blockMove = board.obtainBlockMove(nextMove, board);
        if(blockMove != null) {
            //System.out.println("block step " + blockMove.index.horizontal + "." + blockMove.index.vertical);
            return blockMove;
        }

        return nextMove;
    }

}
