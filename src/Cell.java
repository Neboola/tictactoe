/**
 * Created by Regina on 22.10.2015.
 */
public enum Cell {

    //private char filling;
    _,
    X,
    O;

    @Override
    public String toString() {
        switch (this){
            case _:
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
