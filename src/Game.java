import java.io.IOException;

/**
 * Created by Regina on 27.10.2015.
 */
public class Game {

    public Board board;
    public Gamer gamerX;
    public Gamer gamerO;
    public Gamer currentGamer;
    int level;

    Game(Gamer gamerX, Gamer gamerO, int level){
        board = new Board();
        this.gamerX = gamerX;
        this.gamerO = gamerO;
        this.level = level;
    }

    public void processGame() throws IOException {

        board.printBoardPG();
        System.out.println();

        Move nextMove = new Move();
        nextMove.index = new Index();
        nextMove.cell = Cell.oCell;

        while ((board.whoWin == Cell.emptyCell.filling) && (!board.isFull())){

            if(nextMove.cell == Cell.xCell){
                currentGamer = gamerO;
            }
            else if(nextMove.cell == Cell.oCell){
                currentGamer = gamerX;
            }

//=======

            while(true){

                nextMove = currentGamer.getMove(board, level);

                if(board.canMakeMove(nextMove)){
                    board.makeMove(nextMove);
                    board.checkWin();

                    System.out.println(currentGamer.cell.filling + " steps to " + nextMove.index.horizontal + "." + nextMove.index.vertical);

                    System.out.println();
                    board.printBoardPG();
                    System.out.println();

                    break;
                }
                else{
                    System.out.println("Target cell is not empty");
                }
            }
        }
    }

}
