/**
 * @(#)ColorPanelA.java
 * each book in the bookshelf display is represented by a colorpanel
 *
 * @author Jordan Altaffer and LQ Bach
 * @version 1.00 2015/3/11
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class ColorPanelA extends JPanel{ //implements mouseListener
    Book book;
    JButton info;
    public ColorPanelA(Book b) //used constructor
    {
        setBackground(Color.gray); //default color is gray
        book = b; //each panel relates to a corresponding book
        info = new JButton ("?"); // button to get info about book
        info.addActionListener(new Listener()); //adds listener
        add(info); //adds button
    }
    public ColorPanelA() //default constructor
    {
        setBackground(Color.gray);
        book = new Book(null, 0, null, false);
    }
    public void updateColor(Color input)
    {
        setBackground (input); //changes color of panel
    }
    public void paintComponent (Graphics g) //this method formats the panels so that it looks like a bookshelf when put together
    {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0, 0, 0, 100);
        g.drawLine(25, 0, 25, 100);
        g.setColor(new Color(162,42,42));
        g.fillRect (0, 0, 25, 10);
        g.fillRect(0, 100, 25, 10);
    }
    private class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e)//gives button ability to show info
        {
            
            String [] options = {"Mark as Read", "Mark as Unread", "Cancel"}; //list of options
            int n = JOptionPane.showOptionDialog(null, "Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nNumber of Pages: " + book.getNumPages() + "\nFinished: " + book.getReadStatus(), "\"" + book.getTitle() + "\" Details", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[2]); //shows options and gets response when button is pressed.
            switch(n)
            {
                case 0: //if they mark the book as read
                try{
                    Shelf shelf = new Shelf();
                    shelf.updateStatus(book, true);
                    BookShelfOutput newOutput = new BookShelfOutput();
                }
                catch(Exception z)
                {

                }
                break;
                case 1: //if they mark the book as unread
                try{
                    Shelf shelf = new Shelf();
                    shelf.updateStatus(book, false);
                    BookShelfOutput newOutput = new BookShelfOutput();
                }
                catch(Exception z)
                {

                }
                break;
            }
        }
    }
    

}