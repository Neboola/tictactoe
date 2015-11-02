/**
 * Created by Regina on 22.10.2015.
 */
public enum Cell {

    //private char filling;
    FREE,
    X,
    O;

    @Override
    public String toString() {
        switch (this){
            case FREE:
                return " ";
            case X:
                return "X";
            case O:
                return "O";
            default:
                return this.toString();
        }
    }
}
