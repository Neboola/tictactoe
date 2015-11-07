
import java.io.IOException;


/**
 * Created by Regina on 22.10.2015.
 */
public class Tictactoe {

    public static void main(String[] args) throws IOException {

        //

        int gamerLevel = 5;
        GamerMan gamerMan = new GamerMan(Cell.X);
        GamerBot gamerBot = new GamerBot(Cell.O, gamerLevel);

        Gamer gamerX = gamerMan;
        Gamer gamerO = gamerBot;


        Game game = new Game(gamerX, gamerO);
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
