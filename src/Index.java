/**
 * Created by Regina on 22.10.2015.
 */
public class Index {

    public static int endIndex = 2;


//=
    public int horizontal;
    public int vertical;

/*
            2  | X | O | X |
               -------------
            1  | O | X | O |
               -------------
            0  | X | O | X |
            v  -------------
            / h  0   1   2
*/

    Index(){

    }

    Index(int horizontal, int vertical){
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
