package homework03;


import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        View view = new View(scn, System.out);
        Model model = new Model();
        Presenter program = new Presenter(view,model);
        program.execution();
    }

}
