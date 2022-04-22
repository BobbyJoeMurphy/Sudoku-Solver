package uk.ac.aber.cs21120.solution;
import uk.ac.aber.cs21120.interfaces.IGrid;
import uk.ac.aber.cs21120.solution.Solver;
import uk.ac.aber.cs21120.tests.Examples;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class visuallySolve extends JFrame {
    IGrid grid;
    public visuallySolve(Solver solver) {
        super("Solving Visualization");//Set Window Title
        setSize(1000, 1000);//Set Window Size
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//If you close it, the application exits too.
        new Thread(() -> {
            solver.visuallySolve(this);//Create a parallel Thread for solving the Puzzle
        }).start();//Start said Thread


        //Add Keyboard Controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_UP) {//If <Up-Arrow> then Speed Up
                    solver.increaseSpeed();
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {//If <Down-Arrow> then Speed Down
                    solver.decreaseSpeed();
                }
            }
        });
        this.repaint();//Repaint the Frame (Executes paint()-Method, which shows up later)
        setVisible(true);//Display the Frame
    }

    public void gridUpdate(IGrid grid) {
        this.grid = grid;//Update the Grid
        this.repaint(); //Repaint / Update the Window
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.clearRect(0, 0, 1000, 1000);//Clear Frame
        Graphics2D g = (Graphics2D) graphics;//Create Object for Drawing Stuff
        //Draw Grid
        int offset = 80;//Sudoku x and y Offset (Padding)
        int gridSize = 80;// The Gridsize
        for(int i = 0; i < 10; i++) {// Draws Grid
            g.drawLine(offset + i*gridSize, offset, offset+i*gridSize, offset+9*gridSize);//Draws the Vertical Lines
            g.drawLine(offset, offset + i*gridSize, offset+9*gridSize, offset + i*gridSize);//Draws the Horizontal Lines
        }

        //Draws all the Numbers
        g.setFont(new Font("Serif", Font.PLAIN, 64));//Sets Font to 64, so it looks good with the grid.
        for(int i = 0; i < 9; i++) {//Do the Rows
            for(int j = 0; j < 9; j++) {//Do the Cells in a Row
                int value = grid.get(j, i);//Get the Cell Value
                if(value != 0) {
                    //Draw the Cell Value at the appropriate location, if it's not 0
                    g.drawString("" + value, offset+gridSize/4+i*gridSize, offset+2*gridSize/3+j*gridSize);
                }
            }
        }
    }

    public static void main(String[] args) {
        new visuallySolve(new Solver(Examples.getExample(398)));
    }
}
