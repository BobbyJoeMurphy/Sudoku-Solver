package uk.ac.aber.cs21120.solution;
import uk.ac.aber.cs21120.interfaces.IGrid;
import uk.ac.aber.cs21120.interfaces.ISolver;

public class Solver  implements ISolver {
    IGrid grid;//making obect grid
    double speed = 5.0; // change to speed up visualisation

    public Solver(IGrid g) {
        this.grid = g;
    }//public constructor for solver

    @Override
    public boolean solve() {
        for(int i = 0; i < 9; i++) { // For Each Row in the Grid
            for(int j = 0; j < 9; j++) { // For Each Cell in a Row
                if(grid.get(j,i) == 0) { // If the Cell is Empty
                    for(int k = 1; k <= 9; k++) {//iterates 9 times to grid set  if its valid and if its solved to return true
                        grid.set(j,i,k); // k is testing every empty cell and cycling through 1-9
                        if(grid.isValid() && solve()) return true; // if the grid is valid and can be solved
                    }
                    grid.set(j, i, 0);// Reset the Grid
                    return false;
                }
            }
        }
        return true;
    }

    public boolean visuallySolve(visuallySolve visualization) {
        for(int i = 0; i < 9; i++) { // For Each Row in the Grid
            for(int j = 0; j < 9; j++) { // For Each Cell in a Row
                if(grid.get(i,j) == 0) { // If the Cell is Empty
                    for(int k = 1; k <= 9; k++) {//iterates 9 times to grid set  if its valid and if its solved to return true
                        grid.set(i,j,k);// k is testing every empty cell and cycling through 1-9
                        visualization.gridUpdate(grid);//updates the visualization with grid.
                        try {
                            Thread.sleep((long) (1000 * 1.0/speed));//Do nothing for (1000 * 1/speed) milliseconds.
                        } catch (InterruptedException e) {// catch errors
                            e.printStackTrace(); //print stack trace
                        }
                        if(grid.isValid() && visuallySolve(visualization)) return true;// if the grid is valid and can be solved
                    }
                    grid.set(i, j, 0);// Reset the Grid
                    return false;
                }
            }
        }
        return true;
    }

    public void increaseSpeed() {
        speed *= 2;
    } //how increments should be added to speed

    public void decreaseSpeed() {
        speed /= 2;
    }//how increments should be added to speed
}