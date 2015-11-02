import java.util.*;

/**
 * Created by Regina on 22.10.2015.
 */
public class Move {

    public static int counter;
    public Move preMove;
    public Index index;
    public Cell cell;
    public Board nextBoard;
    public ArrayList<Move> movesList;
    public int priority;

    Move(){
        this.priority = 0;
    }

    Move(Move move){

        preMove = move.preMove;

        index = new Index(move.index.horizontal, move.index.vertical);
        cell = move.cell;

        nextBoard = move.nextBoard;
        movesList = move.movesList;

        priority = move.priority;

    }

    Move(Index index, Cell cell, Move preMove){
        this.index = index;
        this.cell = cell;
        this.preMove = preMove;
        this.priority = 0;
    }

    public static Move treeRoot(){
        Move root = new Move();
        root.cell = Cell.oCell;
        root.nextBoard = new Board();
        root.generateTree();
        root.generatePriorities();
        return root;
    }

    public Move getRoot(int level){
        Move root = new Move();
        root.cell = cell;
        root.nextBoard = nextBoard;
        root.generateNextMoves(level);
        root.generatePriorities();
        return root;
    }

    public void generateNextMoves(int level){

        if(nextBoard.isFull()) return;
        if(level == 0) return;
        level--;

            Cell steper;
            if(cell == Cell.oCell){
                steper = Cell.xCell;
            }
            else{
                steper = Cell.oCell;
            }
            //System.out.println("Steper is: " + steper.filling);

            movesList = new ArrayList<Move>();

            HashMap<Index, Cell> map = nextBoard.map;


            int j = 1;
            for(Map.Entry<Index, Cell> field : map.entrySet()){
                Index indexKey = field.getKey();

                Cell cellValue = field.getValue();

                //================

                if(cellValue == Cell.emptyCell){

                    //System.out.println(indexKey.horizontal + "," + indexKey.vertical + " : is empty");


                    Move newMove = new Move(indexKey, steper, this);
                    newMove.nextBoard = new Board(nextBoard, newMove);
                    newMove.nextBoard.checkWin();
                    newMove.nextBoard.boardLine = nextBoard.boardLine + "." + j;
                    movesList.add(newMove);

                    j++;
                    counter++;
                    //System.out.println("New move added: " + nextBoard.boardLine + "." + k + "    Move# " + counter);

                }
/*
            else{
                //System.out.println(indexKey.horizontal + "," + indexKey.vertical + " : " + cellValue.filling);
            }
*/
                //================

            }



            for(Move nextMove : movesList){

/*
            System.out.println();
            System.out.println(nextMove.cell.filling + " steps to " + nextMove.index.horizontal + "," + nextMove.index.vertical );
            System.out.println("New board created. Board Line is: " + nextMove.nextBoard.boardLine);
            System.out.println("Priority is " + priority);

            nextMove.nextBoard.printBoardPG();
*/

                if(nextMove.nextBoard.whoWin == Cell.emptyCell) nextMove.generateNextMoves(level);






            }





    }

    public void generateTree(){
        generateNextMoves(9); // full tree if level > 8

    }

    public void tryStepTreeDownForLose(){

        //System.out.println("=================== ST DOWN");

        if(movesList != null){


            for (Move nextMove : movesList) {
                if (nextMove.nextBoard.whoWin != Cell.emptyCell) {

                    if(nextMove.nextBoard.whoWin != cell){
                        //System.out.println("=================== Loose");
                        try2StepTreeUpForLose(9);
                        break;
                    }

                }
            }


            for (Move nextMove : movesList) {
                nextMove.tryStepTreeDownForLose();
            }
        }
    }

    public void try2StepTreeUpForLose(int prior){
        //System.out.println("============= inside ST Up");
            if(-prior < priority) priority = -prior;
        if ((preMove != null) && (preMove.preMove != null)) preMove.preMove.try2StepTreeUpForLose(prior - 1);
    }

    public void tryStepTreeDownForWin(){

        //System.out.println("=================== ST DOWN");

        if(nextBoard != null){

            //System.out.println("Board Line is: " + nextBoard.boardLine);

            if (nextBoard.whoWin != Cell.emptyCell) {

                if(nextBoard.whoWin == cell){
                    //System.out.println("=================== Loose");
                    try2StepTreeUpForWin(9);
                }

            }


            //else {
            //if (nextBoard.isFull()) {
            //System.out.println("=================== Draw");
            //this.stepTreeUpForDraw(nextBoard.whoWin, 0);
            //}
            //}

        }
        else{
            System.out.println("nextBoard = null !");
            Tictactoe.sec(10);
        }

        if(movesList != null){
            for (Move nextMove : movesList) {
                nextMove.tryStepTreeDownForWin();
            }
        }
    }

    public void try2StepTreeUpForWin(int prior){
        //System.out.println("============= inside ST Up");
        //if(prior > priority) priority = prior;
        priority = priority + prior;
        if ((preMove != null) && (preMove.preMove != null)) preMove.preMove.try2StepTreeUpForWin(prior - 1);
    }

    public void generatePriorities(){
        tryStepTreeDownForLose();
        tryStepTreeDownForWin();
    }




}
