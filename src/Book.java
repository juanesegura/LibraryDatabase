import java.util.LinkedList;

public class Book implements Comparable{
	private String author;
	private String fullAuthor;
	private String bookTitle;
	private String edition;
	private String location;
	private String publisher;
	private String year;
	private String pages;
	private String isbn;
	private String subject;
	
	public void setAuthor(String s) {
		author = s;
	}
	public void setBookTitle(String s){
		bookTitle = s;
	}
	public void setFullAuthors(String s) {
		fullAuthor = s;
	}
	public void setEdition(String s) {
		edition = s;
	}
	public void setLocation(String s) {
		location = s;
	}
	public void setPublisher(String s) {
		publisher = s;
	}
	public void setYear(String s) {
		year = s;
	}
	public void setPageNumber(String s) {
		pages = s;
	}
	public void setISBN(String s) {
		isbn = s;
	}
	public void setSubject(String s) {
		subject = s;
	}

	public void print() {
		System.out.println("Author: " + author);
		System.out.println("Title: " + bookTitle);
		System.out.println("Subjects: " + subject);
		System.out.println("Edition: " + edition);
		System.out.println("Location: " + location);
		System.out.println("Publisher: " + publisher);
		System.out.println("Year: " + year);
		System.out.println("Pages: " + pages);
		System.out.println("ISBN: " + isbn);
	}
	public void printForMultipleBooks(int num) {
		System.out.print(num + ") " + author + ", " + bookTitle + " - " + subject + ", " + year + ".");
	}
	public int compareTo(Object o) {
		Book book = (Book)o;
		return (author+bookTitle).compareTo(book.author + book.bookTitle);
	}
	
}
