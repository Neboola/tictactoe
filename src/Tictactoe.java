
import java.io.IOException;


/**
 * Created by Regina on 22.10.2015.
 */
public class Tictactoe {

    public static void main(String[] args) throws IOException {

        //

        Gamer gamerX = new GamerMan(Cell.X);
        Gamer gamerO = new GamerBot(Cell.O);
        int level = 5;

        Game game = new Game(gamerX, gamerO, level);
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
