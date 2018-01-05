
/**
 * Write a description of class CommandPanel here.
 * A panel that controls to functions of the main window
 * @author (your name) Jordan Altaffer and LQ Bach
 * @version (a version number or a date) 1/20/15
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class CommandPanel extends JPanel 
{
    Shelf shelf;
    private JButton add;
    private JButton remove;
    private JButton change;
    private JButton search;
    private JTextArea goal;
    private JPanel goalPanel;
    public CommandPanel() throws IOException
    {
        shelf = new Shelf(); //creates a new bookshelf to access information
        add = new JButton("Add"); //button for adding
        remove = new JButton ("Remove"); //button for removing
        change = new JButton ("Change Goal"); //button for changing the goal
        search = new JButton ("Search"); //button for searching the library
        goalPanel = new JPanel(); //I thought this would look differently when I ran it, but decided to leave it in anyways. It consolidate the goal info to a single panel, and that is added to the command panel
        /*
        below sums up all of the read books to display in the goal text area.
        */
        int read = 0;
        for (int i = 0; i < shelf.getNumBooks(); i++)
            if (shelf.getBooks()[i].getReadStatus())
                read++;
        goal = new JTextArea("Yearly Goal:\n " + read + "/" + shelf.getGoal()); //sets goal text
        /*
        below adds the different listeners to the different buttons, and the different objects to the panel
        */
        add.addActionListener(new Listener()); 
        remove.addActionListener(new RemovalListener());
        change.addActionListener(new GoalChanger());
        search.addActionListener(new SearchListener());
        goal.setEditable(false);
        add(add);
        add(remove);
        add(search);
        goalPanel.add(goal);
        goalPanel.add(change);
        add(goalPanel);

        
    }
    private class Listener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) //adds book to library
        
        {
            boolean skip = false; //this value appears a lot. if set to true, then button does nothing.
            String [] options = {"Cancel", "No", "Yes"}; //basic options
            int n = 2; //default choice for if book was finished (see below)
            int p = 100; //default for number of pages (see below)
            String t = ""; //default title
            String a = ""; //default author name
            try{
                t = JOptionPane.showInputDialog(null, "What is the name of the book that you are adding?", "Title", JOptionPane.QUESTION_MESSAGE);
                if (t.equals(null)) //if no value entered
                    skip = true; //skip is set to true
                a = JOptionPane.showInputDialog(null, "Who is the author of the book that you are adding?", "Author", JOptionPane.QUESTION_MESSAGE);
                if (a.equals(null))
                    skip = true;
                p = Integer.parseInt(JOptionPane.showInputDialog(null, "How many pages are in the book that you are adding?", "Number of Pages", JOptionPane.QUESTION_MESSAGE));
                if (p <= 0) //if input number doesnt make sense for book pages
                    skip = true;
                n = JOptionPane.showOptionDialog(null, "Have you finished the book?", "Status", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[2]);
            }
            catch(Exception z)
            {
                skip = true;
                JOptionPane.showMessageDialog(null, "Unable to Add Book");
            }
            
            boolean s = false;
            switch(n)
            {
                case 2: //if finished
                s = true;//read status is true
                break;
                case 1: //if not finished
                s = false;//read status is false
                break;
                case 0://if cancel
                skip = true; 
                break;
            }
            try{
                if (skip == false)
                {
                    shelf.addBook(t, p, a, s); //adds a book to the library with the fields as answered above
                }
            }
            catch(Exception z)
            {
                JOptionPane.showMessageDialog(null, "Unable to Add Book", "Error", JOptionPane.ERROR_MESSAGE); //gives error message if operation cannot be completed.
            }
            try{
                BookShelfOutput newOutput = new BookShelfOutput(); //creates a new window
            }
            catch(Exception z)
            {
            }
        }
    }
    private class RemovalListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) //removes book
        
        {
            boolean skip = false;
            /*
            gets list of all book names
            */
            String [] str = new String[shelf.getNumBooks() + 1]; 
            str[0] = "Cancel";
            for (int i = 0; i < shelf.getNumBooks(); i++)
            {
                str[i + 1] = shelf.getBooks()[i].getTitle();
            }
            String name = "";
            int n = 0;
            n = JOptionPane.showOptionDialog(null, "What is the title of the book that you would like to remove", "Remove Book", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, str, str[0]); ///window for selecting book to remove
            try
            {
                name = str[n];
                if (name.equals("Cancel"))
                    skip = true;
            }
            catch (Exception z)
            {
                name = "Cancel";
                skip = true;
            }
            try{
                if (skip == false)
                {
                    shelf.removeBook(name);
                }
            }
            catch(Exception z)
            {
            }
            try{
                BookShelfOutput newOutput = new BookShelfOutput();
            }
            catch(Exception z)
            {
            }
        }
    }
    private class GoalChanger implements ActionListener
    {
        public void actionPerformed(ActionEvent e) //changes goal
        {
            String t;
            int n = 100;
            boolean skip = false;
            try{
                t = JOptionPane.showInputDialog(null, "What is your new yearly goal?", "Title", JOptionPane.QUESTION_MESSAGE);
                n = Integer.parseInt(t);
                if (n <= 0 )
                {
                    skip = true;
                    JOptionPane.showMessageDialog(null, "That is not a valid yearly goal. Enter a number greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }  
                if (n > 100)
                {
                    JOptionPane.showMessageDialog(null, "Due to our bookshelf only holding 100 books, we must limit your goal to 100 books. Sorry for the inconvenience.", "Goal Too Large", JOptionPane.ERROR_MESSAGE);
                    n = 100;
                }
            }
            catch (Exception z)
            {
                JOptionPane.showMessageDialog(null, "That is not a valid yearly goal.", "Error", JOptionPane.ERROR_MESSAGE);
                skip = true;
            }
            try{
                if (!skip)
                    shelf.changeGoal(n);
            }
            catch(Exception z)
            {
                JOptionPane.showMessageDialog(null, "Goal could not be changed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            try{
                BookShelfOutput newOutput = new BookShelfOutput();
            }
            catch(Exception z)
            {
            }
        }
    }
    private class SearchListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            String t = "";
            try{
                t = JOptionPane.showInputDialog(null, "What is the title of the book that you would like to search for?", "Search", JOptionPane.QUESTION_MESSAGE);
            }
            catch (Exception z)
            {
                JOptionPane.showMessageDialog(null, "Could not search.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            int n = 0;
            try{
                n = shelf.searchBook(t);
                JOptionPane.showMessageDialog(null, "Results for " + t + ":\n" + n + " copies found", "Result", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception z)
            {
                 JOptionPane.showMessageDialog(null, "Could not search for title.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
