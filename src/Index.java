/**
 * Created by Regina on 22.10.2015.
 */
public class Index {

    public int horizontal;
    public int vertical;

/*
            3  | X | O | X |
               -------------
            2  | O | X | O |
               -------------
            1  | X | O | X |
            v  -------------
            / h  1   2   3
*/

    Index(){

    }

    Index(int horizontal, int vertical){
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
