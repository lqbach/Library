
/**
 * Write a description of class BookShelfOutput here.
 * Note: I wrote this on a mac, and the output came out fine on the mac. It looks a bit distorted on a              windows machine.
 * Displays the bookshelf
 * @author (your name) Jordan Altaffer and LQ Bach
 * @version (a version number or a date) 1/5/15
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class BookShelfOutput
{
    
    public static void main (String [] args) throws IOException
    {
        BookShelfOutput frame = new BookShelfOutput();
    }
    public BookShelfOutput() throws IOException
    {   ShelfDisplay shelf = new ShelfDisplay(); //creates the display of the bookshelf
        CommandPanel panel = new CommandPanel(); //creates the command panel
        JFrame frame = new JFrame("My Bookshelf"); //creates a frame for the display and command panel
        Container c = frame.getContentPane();
        c.add(shelf, BorderLayout.CENTER); //adds the display
        c.add(panel, BorderLayout.SOUTH); //adds the panel
        frame.setBounds(100,100,500,500); //sets the size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
}
