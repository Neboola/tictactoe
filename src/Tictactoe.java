
import java.io.IOException;


/**
 * Created by Neboola on 22.10.2015.
 */
public class Tictactoe {



    public static void main(String[] args) throws IOException {







        Gamer gamerO = new GamerMan(Cell.oCell);
        Gamer gamerX = new GamerBot(Cell.xCell);
        Game game = new Game(gamerX, gamerO, 8);
        game.processGame();





    }



    public static void sec(int n){
        try {
            Thread.sleep(n*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
