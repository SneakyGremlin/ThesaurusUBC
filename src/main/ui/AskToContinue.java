package ui;

import java.util.Scanner;

import static java.lang.System.exit;

// !!!
public class AskToContinue {

    Scanner inputFromUser;
    CommandLineInterface commandLineInterface;

    // REQUIRES: Must be called outside the loop of runCommandLineInterface. This exits the whole program
    // EFFECTS: determines if the program should continue; if the user inputs "Y" then yes
    //          else if "N" terminate program
    public void askToContinueAlpha() {
        System.out.println("If you wish to exit program and go to option to save data, enter 'N', else 'Y': ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        if (input.equals("Y")) {
            commandLineInterface = new CommandLineInterface(); // RECURSIVE CALL
        } else { // !!! if parameter ^^^ (introduce) equals ###TERMINATE### ask if save changes and if yes new method
                 // which has thesaurus as entry and utilises SaveValues.
            return;
        }
    }

    // REQUIRES: Must be called as a part of a control flow statement. Determinant decides which domain we're continuing
    //          in, for example if domain is View inside Singular ask if continue in View.
    public boolean askToContinueBeta(String determinant) {
        System.out.println("If you wish to continue in domain: \"" + determinant + "\" ,enter 'Y', else 'N': ");
        inputFromUser = new Scanner(System.in);
        String input = inputFromUser.nextLine();
        return (input.equals("Y"));
    }

}

// classes cannot be static
// try to see if I make the methods static, can I call the methods using ClassName.methodName() !!!
// DONT USE EXIT UNLES ABS NECESSARY