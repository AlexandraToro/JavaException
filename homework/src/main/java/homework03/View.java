package homework03;

import java.io.PrintStream;
import java.util.Scanner;

public class View {

    private Scanner scn;
    private PrintStream out;

    public View(Scanner scn, PrintStream out) {
        this.scn = scn;
        this.out = out;
    }

    public String getValue(String message) {
        out.print(message);
        return scn.nextLine();
    }

    public void print(String message) {
        out.print(message);
    }
}
