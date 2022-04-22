package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;

import java.util.ArrayList;

public class Grid implements IGrid {
    public int[][] Grid;// making a grid constructor

    public String toString() {// debugging from the assignment breif
        StringBuilder b = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                b.append(get(x, y));
            }
            b.append('\n');
        }
        return b.toString();

    }


    public Grid() {
        Grid = new int[9][9];
    } // installising a grid


    public int get(int x, int y) throws IGrid.BadCellException { // getting the grid cell's throwing an bad exemption if it isnt in the grid
        if (x > 8 || x < 0 || y < 0 || y > 8) {// checking if its inbounds
            throw new IGrid.BadCellException(x, y); // throwing new out of bounds
        } else {
            return Grid[x][y]; //retuning the grid if its valid
        }
    }

    public void set(int x, int y, int digit) throws IGrid.BadCellException, IGrid.BadDigitException {
        if (x > 8 || x < 0 || y < 0 || y > 8) { //checking if its in bounds
            throw new IGrid.BadCellException(x, y); //throwing out of bounds cell exception
        } else {
            if (digit > 9 || digit < 0) { // if digits more than 9 or less than 0 aka out of reach of 1-9 sudoku rules
                throw new IGrid.BadDigitException(digit); // throwing out of bounds
            } else {
                Grid[x][y] = digit;//returning the grid location as a digit
            }
        }
    }

    public static boolean isInValidArray(int[] array) {
        ArrayList<Integer> list = new ArrayList<Integer>(); //< making an array list of int's
        for(int i:array) { // < for int we search the array
            if(i != 0 && list.contains(i)) return true;// < if i is anything other than 0 and the list contains i return true add to int i
            list.add(i);
        }//if isnt in the list and we dont add to i return false and its an valid array
        return false;
    }

    public static int[][] swapRows(int[][] grid) {//static grid ints
        int[][] swapped = new int[9][9]; //making a new int called swapped
        for(int i = 0; i < 9; i++) { // and row
            for(int j = 0; j < 9; j++) { // checking the position
                swapped[i][j] = grid[j][i];// swapping the rows and collums
            }
        }
        return swapped; //returns them as swapped
    }

    public static int[][] subgrid(int[][] grid) {// this is my sub grid function easier to explain in report
        int[][] subgrids = new int[9][9];// initialising a subgrid [9][9]
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {//we iterate through the positions
                int xGrid = i / 3;// We simple divide the "i" (which represents the y coordinate) by 3. Since we got 3 possible equally divided subgrids.
                int yGrid = j / 3;// Same will happen with "j" to get the xGrid (which should actually be called yGrid).
                int arrayPos = 3 * yGrid + xGrid;// Flattens the 2D-Index (xGrid,yGrid) to a 1D-Index (arrayPos)
                int arrPosX = i % 3;// we then use modulus to make them 3 up
                int arrPosY = j % 3;// we then use modulus to 3 down
                int arrPos = 3 * arrPosY + arrPosX;// finds the arr position after modulas has been added and the number before swapping rows.
                subgrids[arrayPos][arrPos] = grid[i][j];// adds array pos and arr pos to grid i and j
            }
        }
        return subgrids;//returns subgrid
    }


    public boolean isValid() {
        int[][] cols = swapRows(Grid);// initialises swap rows with cols
        int[][] rows = Grid; //initialises rows with grid
        int[][] subGrids = subgrid(Grid);//checks subgrid gives it subgrids [][]

        for(int i = 0; i < 9; i++) {
            if(isInValidArray(cols[i])) return false; //checks if array cols is working
            if(isInValidArray(rows[i])) return false;// checks if array rows are working
            if(isInValidArray(subGrids[i])) return false; // checks if my subgrids work
        }

        return true;
    }

}

