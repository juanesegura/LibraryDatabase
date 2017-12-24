import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.LinkedList;
import java.io.*;

public class Library {
	private static TreeMap<String, LinkedList<Book>> mapAuthor;
	private static TreeMap<String, LinkedList<Book>> mapTitle;
	private static TreeMap<String, LinkedList<Book>> mapSubject;
	private static Scanner scan;
	private String author, fullAuthors, bookTitle, edition, location, publisher, year, pages, isbn;
	private LinkedList<String> subjects; 
	private int count;
	
	public Library() throws FileNotFoundException {		
		mapAuthor = new TreeMap<String, LinkedList<Book>>();
		mapTitle = new TreeMap<String, LinkedList<Book>>();
		mapSubject = new TreeMap<String, LinkedList<Book>>();
		File file = new File("CardCatalog.txt");
		scan = new Scanner(file);
		readInput();
        start();
        userInput();
        
	}
	public static void main(String[] args)  {
		try {
			new Library();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	private  void readInput() throws FileNotFoundException {
		Book book = new Book();
		while (scan.hasNext()) {
            author =  getAuthor(scan.nextLine());
            book.setAuthor(author);
            bookTitle =  getTitle(scan.nextLine());
            book.setBookTitle(bookTitle);
            fullAuthors = getFullAuthors(scan.nextLine());
            book.setFullAuthors(fullAuthors);
            seperateInformation(scan.nextLine());          
            book.setEdition(edition);
            book.setLocation(location);
            book.setPublisher(publisher);
            book.setYear(year);
            pages = getPageNumber(scan.nextLine());
            isbn = getISBN(scan.nextLine()) ;
            subjects = getSubjects(scan.nextLine());

            if (! mapAuthor.containsKey(author)) {
            	mapAuthor.put(author,new LinkedList<Book> ());
            }
            mapAuthor.get(author).add(book);            

            if (! mapTitle.containsKey(bookTitle)) {
            	mapTitle.put(bookTitle,new LinkedList<Book> ());
            }
            mapTitle.get(bookTitle).add(book);

            for(int  x = 0; x < subjects.size(); x++) {
                if (! mapSubject.containsKey(subjects.get(x))) {
                	mapSubject.put(subjects.get(x), new LinkedList<Book>());
                }
                mapSubject.get(subjects.get(x)).add(book);
            }
        }

	}
	private void start() {
		System.out.println("Hello, this is the library. If you ever want to get back to this menu, press 0");
		System.out.println("You can search using author, title, or subject");
	}
	
	private String getAuthor(String s) {
		int counter1 = 0, counter2 = 0, counter3 = 0;
        String temp = "";
        while (s.charAt(counter1)== ' ') {
            count++;
        }
        for (int x = counter1; x < s.length(); x++) {
            if (s.charAt(x) == '.') {
                counter2++;
            }
        }
        for (int x = counter1; x < s.length(); x++) {
            if (s.charAt(x) == '.') {
                counter3++;
            }
            if (counter3 < counter2 ){
                temp+=s.charAt(x);
            }
        }
        return temp;
	}
	private String getTitle(String s) {
		String title = "";
		count = 0;
		s.trim();
		while (count<s.length()) {
			title += s.charAt(count);
			count ++;
		}
		return title;
	}
	private String getFullAuthors(String s) {
		return s.substring(3, s.indexOf('.'));	
	}
	private void seperateInformation(String s){
		edition = "";
		location = "";
		publisher = "";
		year = "";
		
		s = s.trim();
		count = 2; //To exclude the -
		while (count < s.length() && s.charAt(count) != 'e') {
			edition += s.charAt(count);
			count++;
		}
		
		count += 6;
		while (count < s.length() && s.charAt(count) != ':') {
            location+= s.charAt(count);
            count++;
        }
		
        count++;
        while (count < s.length() && s.charAt(count) != ',') {
            publisher+= s.charAt(count);
            count++;
        }
        
        count++;
        while(count < s.length() && s.charAt(count)!= '.') {
            year+=s.charAt(count);
            count++;
        }
	}
	private String getPageNumber(String s){
		pages = "";
		count = s.indexOf(' ');
		while (count < s.length() && s.charAt(count) != 'p') {
			pages += s.charAt(count);
			count++;
		}
		return pages;
	}
	private String getISBN(String s){
		isbn = "";
		s = s.trim();
		for(int x = 4; x < s.length(); x++) {
			isbn += s.charAt(x);
		}
		return isbn;
		
	}
	private LinkedList<String> getSubjects(String s) {
		LinkedList<String> subjects = new LinkedList<String>();
		count = 0;
		String temp;
		while (s.length()>0) {
			s = s.trim();
			count+=3;
			temp = s.substring(count, s.indexOf(".", count));
			subjects.add(temp);
			if (s.length()>1) {
				s = s.substring((s.indexOf(".", count) +1 ));
			}
			count = 0;
		}
		return subjects;
	}
	
	private void userInput() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		System.out.println("Which would you like to search by? Remember to write in all lowercase");
		input += scan.next();
		if (input.equals("0")) {
			start();
		}if (input.equals("author")) {
			findAuthor();
		}else if (input.equals("title")){
			findTitle();
		}else if (input.equals("subject")) {
			findSubject();
		}else {
			System.out.println("That was not recognized, try again");
			start();
		}
		
				
			
		
	}
	private void findAuthor() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		System.out.println("Okay, what author do you want to search?");
		input = scan.next();
		for( String s : mapAuthor.keySet()) {
			System.out.println(s);
		}
		if (mapAuthor.containsKey(input)) {
            LinkedList <Book> bookLinkedList = mapAuthor.get(input);
            int size = bookLinkedList.size();
            if (bookLinkedList.size()>1) {
                Object [] bookArray = bookLinkedList.toArray();
                Arrays.sort(bookArray);
                bookLinkedList.clear();
                for(int x = 0; x < size; x++) {
                    bookLinkedList.add((Book)(bookArray[x]));
                }

                System.out.println(" "+(bookLinkedList.size())+" books found");
                for (int x = 0; x< bookLinkedList.size(); x++) {
                	Book book = bookLinkedList.get(x);
                	book.printForMultipleBooks(x);
                }
                System.out.println("Which book number would you like to choose?");
                int choice = scan.nextInt(); 
                if (choice == 0) {
                    start();
                }else {
                    Book book = bookLinkedList.get(choice-1);
                    book.print();     
                }

            }else {
                Book book = bookLinkedList.get(0);
                book.print();
            }           

        }else
        {
            System.out.println("No book according to the author inputed was found");
        }
	}
	private void findTitle() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		System.out.println("Okay, what title do you want to search?");
		input = scan.next();
		if (mapTitle.containsKey(input)) {
            LinkedList <Book> bookLinkedList = mapTitle.get(input);
            int size = bookLinkedList.size();
            if (bookLinkedList.size()>1) {
                Object [] bookArray = bookLinkedList.toArray();
                Arrays.sort(bookArray);
                bookLinkedList.clear();
                for(int x = 0; x < size; x++) {
                    bookLinkedList.add((Book)(bookArray[x]));
                }

                System.out.println(" "+(bookLinkedList.size())+" books found");
                for (int x = 0; x< bookLinkedList.size(); x++) {
                	Book book = bookLinkedList.get(x);
                	book.printForMultipleBooks(x);
                }
                System.out.println("Which book number would you like to choose?");
                int choice = scan.nextInt(); 
                if (choice == 0) {
                    start();
                }else {
                    Book book = bookLinkedList.get(choice-1);
                    book.print();     
                }

            }else {
                Book book = bookLinkedList.get(0);
                book.print();
            }           

        }else
        {
            System.out.println("No book according to the title inputed was found");
        }
	}
	private void findSubject() {
		Scanner scan = new Scanner(System.in);
		String input = "";
		System.out.println("Okay, what subject do you want to search?");
		input = scan.next();
		LinkedList <Book> bookLinkedList = mapSubject.get(input);
        int size = mapSubject.size();
        if (mapSubject.size()>1) {
            Object [] bookArray = bookLinkedList.toArray();
            Arrays.sort(bookArray);
            bookLinkedList.clear();
            for(int x = 0; x < size; x++) {
                bookLinkedList.add((Book)(bookArray[x]));
            }
            
            if (bookLinkedList.size() > 1) {
            	System.out.println(" "+(bookLinkedList.size())+" books found");
                for (int x = 0; x< bookLinkedList.size(); x++) {
                	Book book = bookLinkedList.get(x);
                	book.printForMultipleBooks(x);
                }
                System.out.println("Which book number would you like to choose?");
                int choice = scan.nextInt(); 
                if (choice == 0) {
                    start();
                }else {
                    Book book = bookLinkedList.get(choice-1);
                    book.print();     
                }

            }else {
                Book book = bookLinkedList.get(0);
                book.print();
            }           

        }else
        {
            System.out.println("No book according to the subject inputed was found");
        }
	}
}
