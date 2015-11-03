import java.util.HashMap;
import java.util.Map;

/**
 * Created by Regina on 22.10.2015.
 */
public class Board {

    //public String boardLine;
    public Cell whoWin;
    public HashMap<Index, Cell> map;

    Board() {

        whoWin = Cell.FREE;

        map = new HashMap<Index, Cell>();
        //System.out.println("Board creation started");


        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {

                map.put(new Index(i, j), Cell.FREE);
                //System.out.println("Field created: " + i + "," + j);

            }
        }

        //boardLine = "1";

        //System.out.println("Board creation finished. Board line is: " + boardLine);
        //System.out.println();

    }

    Board(Board prevBoard, Move move) {

        whoWin = Cell.FREE;

        Index moveIndex = move.index;
        Cell moveCell = move.cell;
        map = new HashMap<Index, Cell>();

        for (Map.Entry<Index, Cell> field : prevBoard.map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();


            if ((moveIndex.horizontal == indexKey.horizontal) && (moveIndex.vertical == indexKey.vertical)) {
                map.put(indexKey, moveCell);
            } else {
                map.put(indexKey, cellValue);
            }

        }

    }

    public Move obtainWinMove(Move currentMove, Board board){

        //System.out.println("check obtainWinMove =================");

        Cell targetCell = currentMove.cell;

        Move nextMove = new Move(currentMove);

        int counterXO;
        Index currentIndex = new Index();

        //System.out.println("check vertical lines");
        for (int h = 1; h < 4; h++) {
            counterXO = 0;

            for (int v = 1; v < 4; v++) {
                //System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){

                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;
                        }
                    }
                }
            }

            if (counterXO == 2) {
                nextMove.index = currentIndex;
                //System.out.println("counter = 2. win found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(nextMove)) {
                     //System.out.println("win found!!! h: " + nextMove.index.horizontal + " v: " + nextMove.index.vertical);

                     return nextMove;
                 }
                //else System.out.println("cant go to " + nextMove.index.horizontal + "," + nextMove.index.vertical);
            }
        }

        //System.out.println("check horizontal lines");
        for (int v = 1; v < 4; v++) {
            counterXO = 0;

            for (int h = 1; h < 4; h++) {

                //System.out.println("h: " + h + " v: " + v);

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){

                        if(entry.getValue() == targetCell) counterXO++;
                        else {
                            currentIndex.horizontal = h;
                            currentIndex.vertical = v;
                        }
                    }
                }
            }

            if (counterXO == 2) {
                nextMove.index = currentIndex;
                //System.out.println("counter = 2. win found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);

                if(board.canMakeMove(nextMove)) {
                    //System.out.println("win found!!! h: " + nextMove.index.horizontal + " v: " + nextMove.index.vertical);

                    return nextMove;
                }
                //else System.out.println("cant go to " + nextMove.index.horizontal + "," + nextMove.index.vertical);
            }
        }

        //System.out.println("check diagonal right-left line");
        counterXO = 0;
        for (int hv = 1; hv < 4; hv++) {
            //System.out.println("h: " + hv + " v: " + hv);


            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == hv) && (index.vertical == hv)){

                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = hv;
                        currentIndex.vertical = hv;
                    }
                }
            }
        }

        if (counterXO == 2) {
            nextMove.index = currentIndex;
            //System.out.println("counter = 2. win found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);
            if(board.canMakeMove(nextMove)) {
                //System.out.println("win found!!! h: " + nextMove.index.horizontal + " v: " + nextMove.index.vertical);
                return nextMove;
            }
            //else System.out.println("cant go to " + nextMove.index.horizontal + "," + nextMove.index.vertical);
        }

        //System.out.println("check diagonal left-right line");
        counterXO = 0;
        for (int h = 1; h < 4; h++) {
            int v = 4 - h;
            //System.out.println("h: " + h + " v: " + v);


            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == h) && (index.vertical == v)){



                    if(entry.getValue() == targetCell) counterXO++;
                    else {
                        currentIndex.horizontal = h;
                        currentIndex.vertical = v;
                    }
                }
            }
        }

        if (counterXO == 2) {
            nextMove.index = currentIndex;
            //System.out.println("counter = 2. win found? current h: " + currentIndex.horizontal + " v: " + currentIndex.vertical);
            if(board.canMakeMove(nextMove)) {
                //System.out.println("win found!!! h: " + nextMove.index.horizontal + " v: " + nextMove.index.vertical);
                return nextMove;
            }
            //else System.out.println("cant go to " + nextMove.index.horizontal + "," + nextMove.index.vertical);

        }

        return null;
    }

    public Move obtainBlockMove(Move currentMove, Board board){

        //System.out.println("check obtainBlockMove =================");

        Cell targetCell = currentMove.cell;
        if(currentMove.cell == Cell.O) {
            targetCell = Cell.X;
        }
        else {
            if (currentMove.cell == Cell.X) {
                targetCell = Cell.O;
            } else {
                System.out.println("Who steps????");
                Tictactoe.sec(10);
            }
        }

        Move nextMove = new Move(currentMove);
        nextMove.cell = targetCell;
        nextMove = obtainWinMove(nextMove, board);
        if(nextMove != null){
            nextMove.cell = currentMove.cell;
        }
        return nextMove;

    }

    public void checkWin() {

        int counterX;
        int counterO;





        for (int h = 1; h < 4; h++) {

             counterX = 0;
             counterO = 0;

            for (int v = 1; v < 4; v++) {

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.O) counterO++;
                        if(cell == Cell.X) counterX++;

                    }

                }

            }

            if (counterX == 3) {
                whoWin = Cell.X;

            }
            if (counterO == 3) {
                whoWin = Cell.O;

            }

        }




        for (int v = 1; v < 4; v++) {

             counterX = 0;
             counterO = 0;

            for (int h = 1; h < 4; h++) {

                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == h) && (index.vertical == v)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.O) counterO++;
                        if(cell == Cell.X) counterX++;

                    }

                }

            }

            if (counterX == 3) {
                whoWin = Cell.X;

            }
            if (counterO == 3) {
                whoWin = Cell.O;

            }

        }


        counterX = 0;
        counterO = 0;


        for (int hv = 1; hv < 4; hv++) {







                for(Map.Entry<Index, Cell> entry : map.entrySet()){
                    Index index = entry.getKey();

                    if((index.horizontal == hv) && (index.vertical == hv)){
                        Cell cell = entry.getValue();

                        if(cell == Cell.O) counterO++;
                        if(cell == Cell.X) counterX++;

                    }

                }



            if (counterX == 3) {
                whoWin = Cell.X;

            }
            if (counterO == 3) {
                whoWin = Cell.O;

            }

        }


        counterX = 0;
        counterO = 0;

        for (int h = 1; h < 4; h++) {
            int v = 4 - h;







            for(Map.Entry<Index, Cell> entry : map.entrySet()){
                Index index = entry.getKey();

                if((index.horizontal == h) && (index.vertical == v)){
                    Cell cell = entry.getValue();

                    if(cell == Cell.O) counterO++;
                    if(cell == Cell.X) counterX++;

                }

            }



            if (counterX == 3) {
                whoWin = Cell.X;

            }
            if (counterO == 3) {
                whoWin = Cell.O;

            }

        }










    }

    public void printBoardPG() {




        for (int j = 3; j > 0; j--) {
            System.out.print(j + "  | ");
            for (int i = 1; i < 4; i++) {

                System.out.print(getCellFilling(i, j) + " | ");
            }
            System.out.println();
            System.out.println("   -------------");
        }
        System.out.println("     1   2   3  ");




        if (whoWin != Cell.FREE) {
            System.out.println(whoWin + " WIN! ========================");
            System.out.println();
            System.out.println();


        }
        else if(isFull()) {
            System.out.println("DRAW! ========================");
            System.out.println();
            System.out.println();


        }


        //Tictactoe.sec(1);

    }
// Delete Filling
    public Cell getCellFilling(int h, int v){

        Index indexKey = new Index(h, v);

        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            indexKey = field.getKey();
            //Cell cellValue = field.getValue();

            if ((indexKey.horizontal == h) && (indexKey.vertical == v)) {
                break;
            }

        }
        return map.get(indexKey);


    }

    public boolean isFull(){

        boolean full = true;

        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            //Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if (cellValue == Cell.FREE) {
                full = false;
            }

        }

        return full;
    }

    public boolean canMakeMove(Move move){
        //Index moveIndex = move.index;
        //Cell moveCell = move.cell;


        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if ((move.index.horizontal == indexKey.horizontal) && (move.index.vertical == indexKey.vertical)) {
                if(cellValue != Cell.FREE){
                    return false;
                }
                else{

                    return true;
                }


            }

        }


        return true;
    }

    public boolean makeMove(Move move){

        for (Map.Entry<Index, Cell> field : map.entrySet()) {
            Index indexKey = field.getKey();
            Cell cellValue = field.getValue();

            if ((move.index.horizontal == indexKey.horizontal) && (move.index.vertical == indexKey.vertical)) {
                if(cellValue != Cell.FREE){
                    return false;
                }
                else{
                    field.setValue(move.cell);
                    return true;
                }

            }

        }

        return true;
    }

}