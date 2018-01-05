/**
 * Write a description of class Book here.
 * Creates a book to hold basic information
 * @author (your name) Jordan Altaffer and LQ Bach
 * @version (a version number or a date) 1/5/15
 */
public class Book
{
    private String title;
    private int numPage;
    private String author;
    private boolean readStatus;

    public Book(String t, int p, String a, boolean s) //initializes information about book
    {
        title = t; 
        numPage = p;
        author = a;
        readStatus = s;
    }
    public void markAsRead()
    {
        readStatus = true;
    }
    public void markAsUnread()
    {
        readStatus = false;
    }
    public boolean getReadStatus()
    {
        return readStatus;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public int getNumPages()
    {
        return numPage;
    }
    
    public String toString()
    {
        return (title + "," + numPage + "," + author + "," + readStatus + ","); //this is written directly to     the csv file in a format that allows the code to read it.
    }
}
