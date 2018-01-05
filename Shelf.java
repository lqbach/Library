
/**
 * Write a description of class Shelf here.
 * holds all of the data about all of the books
 * @author (your name) Jordan Altaffer and LQ Bach
 * @version (a version number or a date) 1/5/15
 */
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Shelf
{
    private Book [] collection;
    private Scanner reader;
    private Scanner goalReader;
    private int size;
    private int goal;
    public Shelf() throws IOException
    {
        collection = new Book [100]; //array of books
        reader = new Scanner(new File("BookList.csv")); //reads in list of books
        goalReader = new Scanner(new File("goal.txt")); //reads in goal
        size = 0; // size keeps track of number of books in array
        int c1, c2, c3, c4; //commas in each line
        goal = goalReader.nextInt(); //reads in the goal from goal.txt
        
        while (reader.hasNextLine())
        {
            try
            {
                /*
                below finds all of the commas in the read line from BookList.csv. reads in data to create a new book in the array
                */
                String line = reader.nextLine();
                c1 = line.indexOf(",");
                c2 = line.indexOf(",", c1 + 1);
                c3 = line.indexOf(",", c2 + 1);
                c4 = line.indexOf(",", c3 + 1);
                collection[size] = new Book(line.substring(0, c1), Integer.parseInt(line.substring(c1 + 1, c2)), 
                         line.substring(c2 + 1, c3), Boolean.parseBoolean(line.substring(c3 + 1, c4)));
                size++;
            }
            catch(Exception e)
            {
            }
        }
        reader.close();
    }
    
    public void addBook(String t, int p, String a, boolean s) throws IOException //adds book to library
    {
        reader = new Scanner(new File("BookList.csv"));
        Scanner reader2 = new Scanner(new File("BookList.csv"));
        int count = 0;
        boolean skip = false;
        try
        {
            //counts up number of books in the library to avoid white spaces
            while (reader2.hasNextLine())
            {
                String line = reader2.nextLine();
                if (line.indexOf(',') != -1)
                    count++;
            }
            if (count > 99)
                skip = true;
            int breaker;
            if (skip)
                breaker = 100/0;
                
            String text = "";
                
            while (reader.hasNextLine())
            {
                text += reader.nextLine() + '\n'; //adds up all of the lines
            }
            reader.close();
            collection[size] = new Book(t, p, a, s); //adds a book to the list of books held in RAM
            text +=collection[size]; //adds a book to the library to be written to the file (ROM)
            PrintWriter writer = new PrintWriter("BookList.csv");
            writer.println(text);
            size++;
            writer.close();
        }
        catch(Exception z)
        {
            JOptionPane.showMessageDialog(null, "Unable to Add Book");
        }

        
    }
    public void updateStatus(Book b, boolean c) throws IOException //changes whether or not book is unread based on the boolean
    {
        reader = new Scanner(new File("BookList.csv"));
        String line;
        String text = "";
        if (c){
            /*
            reads in all of the lines of the book. If input book matches one of the lines, then it replaces the read line with a new, updated line.
            */
            while (reader.hasNextLine())
            {
                
                line = reader.nextLine();
                
                if (line.equalsIgnoreCase(b.toString()))
                {
                  
                    b.markAsRead();
                    line = b.toString();
                }
                text += line + '\n';                    
            }
            PrintWriter writer = new PrintWriter("BookList.csv");
            writer.println(text);
            writer.close();
            reader.close();
        }
        else{

                while (reader.hasNextLine())
            {
                
                line = reader.nextLine();
               
                if (line.equalsIgnoreCase(b.toString()))
                {
                  
                    b.markAsUnread();
                    line = b.toString();
                }
                text += line + '\n';                    
            }
            PrintWriter writer = new PrintWriter("BookList.csv");
            writer.println(text);
            writer.close();
            reader.close();
        
        }
    }
    public void changeGoal(int n) throws IOException //changes the goal
    {
        PrintWriter goalWriter = new PrintWriter("goal.txt");
        goalWriter.println(n);
        goalWriter.close();
    }
    public void removeBook(String t) throws IOException //removes a book
    {
        reader = new Scanner(new File("BookList.csv"));
        
        String line;
        String text = "";
        boolean found = false;
        try{
            /*
            below removes first instance of the unwanted book by reading through and comparing titles
            */
            while (reader.hasNextLine())
            {
                
                line = reader.nextLine();
                if (!line.equals(""))
                {    
                    
                    if (line.substring(0, line.indexOf(",")).equalsIgnoreCase(t) && !found)
                    {
                        found = true;
                    }
                    
                    else
                    {
                        text += line + '\n'; 
                    }
                }
            }
            text = text.trim(); //remove white space found at end because of the newline character
            reader.close();
            PrintWriter writer = new PrintWriter("BookList.csv");
            writer.println(text);
            writer.close();
        }
        catch (Exception z)
        {
            JOptionPane.showMessageDialog(null, "Unable to Remove Book", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public int searchBook(String t)//searches for the book
    {
        int found = 0; //number of books found, increments if matches title
        for (int i = 0; i < size; i++)
        {
            String title = collection[i].getTitle(); //if this books title matches the input title
            if (title.equalsIgnoreCase(t))
                found ++;
        }
        return found; //returns number of books that have the same title
    }
    
    public Book[] getBooks(){ //gets the list of books in library
        return collection;
    }
    public Book getBook(int n) //gets a book from the library
    {
        return collection[n];
    }
    public int getNumBooks(){ //gets the number of books in the library
        return size;
    }
    public int getGoal(){ //gets the reading goal
        return goal;
    }
    public String toString() //info about library
    {
        return ("This bookshelf has a capacity of 100 and currently holds " + size + " books.");
    }
}
