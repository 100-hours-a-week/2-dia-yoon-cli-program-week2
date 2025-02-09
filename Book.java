public class Book {
    protected int bookID;
    protected String title;
    protected String author;
    protected boolean isBorrowed;
    public String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isBorrowed = false;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void getDetails() {
        System.out.println("책 ID: " + bookID + ", 제목: " + title + ", 작가: " + author + ", 대여 여부: " + isBorrowed);
    }

    public int getBookID() {
        return bookID;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}