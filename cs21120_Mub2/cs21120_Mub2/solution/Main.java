package uk.ac.aber.cs21120.solution;
import uk.ac.aber.cs21120.interfaces.ISolver;
import uk.ac.aber.cs21120.tests.Examples;

public class Main {
    public static void main(String [] args) {
        for(int k=0; k < 400; k++) {//iterate 400 times K
            long start = System.currentTimeMillis(); // start a long int in milisecconds
            new Solver(Examples.getExample(k)).solve(); //new solver runs the exampletestclass K amount of times in .solve
            long end = System.currentTimeMillis();// end the time when needed
            System.out.println("For " + k + " it took " + ((end - start)) + "ms to complete"); //prints a time and string
        }
    }
}