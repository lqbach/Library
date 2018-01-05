
/**
 * Write a description of class ShelfDisplay here.
 * Displays all of the books
 * @author (your name) Jordan Altaffer and LQ Bach
 * @version (a version number or a date) 1/5/15
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class ShelfDisplay extends ColorPanelA
{
    private Shelf shelf;
    private ColorPanelA [] [] books;
    private final Color UNREAD = Color.red;
    private final Color READ = Color.green;
    public ShelfDisplay() throws IOException
    {
        shelf = new Shelf();
        books = new ColorPanelA[4][25];
        int size = shelf.getNumBooks();
        
        int count = 1;
        int numberCount = 0;
        int [] rowSize = new int[4];
        
        for (int i = 0; i < 100; i++)
        {
            int k = i;
            int j = 0;
            Book b = shelf.getBooks()[i];
            while (k >= 25)
            {
                k -= 25;
                j++;
            }
            
            if (b != null)
                books[j][k] = new ColorPanelA(b);
            else
            {
                books [j][k] = new ColorPanelA();
            }
        }
        GridLayout bookLayout = new GridLayout (4, 25);
        setLayout(bookLayout);
        for (int i = 0; i < books.length; i++)
            for (int j = 0; j < books[0].length; j++)
            {
                
                add(books[i][j]);
            }
        int sizeCount = 0;
        while (size > 25)
        {
            rowSize[sizeCount] = 25;
            size -= 25;
            sizeCount++;
        }
        rowSize[sizeCount] = size;
        for (int i = 0; i < rowSize.length; i++)
        {
            for (int j = 0; j < rowSize[i]; j++)
            {
               
                if (shelf.getBooks()[numberCount].getReadStatus())
                {
                    books[i][j].updateColor(Color.green);
                }
                else if (!shelf.getBooks()[numberCount].getReadStatus())
                    books[i][j].updateColor(Color.red);
                numberCount++;
            }
        }
        
       
    }

    public void updateDisplay()
    {
        for (int i = 0; i < 100; i++)
        {
            int k = i;
            int count = 0;
            if (shelf.getBooks()[i].getReadStatus())
            {
               while (k >= 25)  
               {
                   k -= 25;
                   count++;
               }
               books[count][k].updateColor(READ);
            }
        }
    }
}
