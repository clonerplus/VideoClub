import java.io.IOException;
import java.util.*;

/*
 * Human class declaration that Consumer class and Admin class inherit from
 * has some basic attributes and methods for setting and getting them
 */
class Human {
	private String firstName, lastName;
	private String ID;
	private String phoneNo;
	public Human(String firstName, String lastName) { // class constructor
		this.firstName = firstName;
		this.lastName  = lastName;
	}
	public Human(String firstName, String lastName, String ID) { // constructor overloading
		this(firstName, lastName);
		this.ID = ID;
	}
	public Human(String firstName, String lastName, String ID, String phoneNo) { // constructor overloading
		this(firstName, lastName, ID);
		this.phoneNo = phoneNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getID() {
		return ID;
	}
}

/*
 * Consumer class declaration that extends Human class and can make Consumers  
 * adds some more attributes to it's superclass and some getters and setters respectively
 */

class Consumer extends Human {
	private final String userName;
	private String passWord;
	private Movie[] borrowedMovies;
	public Consumer(String firstName, String lastName, String userName, String passWord) {
		super(firstName, lastName);
		this.userName = userName;
		this.passWord = passWord; 
	}
	public Consumer(String firstName, String lastName, String userName, String passWord, String ID) {
		super(firstName, lastName, ID);
		this.userName = userName;
		this.passWord = passWord; 
	}
	public Consumer(String firstName, String lastName, String userName, String passWord, String ID, String phoneNo) {
		this(firstName, lastName, userName, passWord, ID);
		this.setPhoneNo(phoneNo);
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public Movie[] getBorrowedMovies() {
		return borrowedMovies;
	}
	public void setBorrowedMovies(Movie[] updatedBorrowedMovies) {
		this.borrowedMovies = updatedBorrowedMovies;
	}
	@Override
	public String toString() {
		return ("User Status: Consumer" + "\nUsername: " + this.userName + "\nFirst name: " + this.getFirstName() 
		+ "\nLast name: " + this.getLastName() + "\nID: " + this.getID() + "\nPhone No: " + this.getPhoneNo());
	}
}

/*
 * Admin class declaration that inherits Human class and can make managers
 * adds some more attributes to it's superclass and some getters and setters respectively
 */

class Admin extends Human {
	private final String userName;
	private String passWord;
	public Admin(String firstName, String lastName, String ID, String userName, String password) {
		super(firstName, lastName, ID);
		this.userName = userName;
		this.passWord = password; 
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void lendMovie(Movie movie, Consumer borrower) {
		movie.setBorrower(borrower);
		ArrayList<Movie> updatedAvailableMovies = new ArrayList<Movie>(demo.getAvailableMovies());
		ArrayList<Movie> updatedBorrowedMovies  = new ArrayList<Movie>(demo.getBorrowedMovies());
		
		for (int i = 0; i < updatedAvailableMovies.size(); i++) { // find and remove borrowed movie from the Available movies list
			if (updatedAvailableMovies.get(i).getName().equals(movie.getName())) {
				updatedAvailableMovies.remove(i);
			}
		}
		
		updatedBorrowedMovies.add(movie); // add the borrowed movie to the borrowed movies list
		
	}
	public void showToBeVerifiedConsumers() {
		if (demo.getToBeVerifiedConsumers().size() == 0) {
			System.out.println("No available consumers to be verified!");
			return;
		}
		for (int i = 0; i < demo.getToBeVerifiedConsumers().size(); i++) {
			System.out.print("# " + Integer.toString(i+1) + ")");
			System.out.println(demo.getToBeVerifiedConsumers().get(i).toString());
		}
		System.out.println("Please enter number of the consumer you want to verify");
		System.out.print("* Note that you can enter 0 to exit: ");
		verifyConsumers();
	}
	public void verifyConsumers() {
		int selected = -1;
		selected = demo.scanner.nextInt();
		if (selected == 0) return;
		Consumer verifiedConsumer = demo.getToBeVerifiedConsumers().get(selected-1);
		ArrayList<Consumer> updatedConsumers = new ArrayList<Consumer>(demo.getVerifiedConsumers());
		updatedConsumers.add(verifiedConsumer);
		demo.setVerifiedConsumers(updatedConsumers);
		demo.getToBeVerifiedConsumers().remove(selected-1);
		showToBeVerifiedConsumers();
	}
	public void addMovie() {
		String name;
		Double IMDBscore;
		String[] stars = new String[3];
		int productionYear;
		Human director, producer;
		String firstName, lastName;
		Movie newMovie;
		
		System.out.println("name: ");
		name = demo.scanner.next();
		System.out.println("production year: ");
		productionYear = demo.scanner.nextInt();
		System.out.println("IMDB score: ");
		IMDBscore = demo.scanner.nextDouble();
		System.out.println("star actors (3 names would be enough!)");
		for (int i = 0; i < 3; i++) {
			System.out.print(i+1);
			System.out.print(" :");
			stars[i] = demo.scanner.next();
		}
		System.out.println("director (firstname): ");
		firstName = demo.scanner.next();
		System.out.println("director (lastname): ");
		lastName = demo.scanner.next();
		director = new Human(firstName, lastName);
		
		System.out.println("producer (firstname): ");
		firstName = demo.scanner.next();
		System.out.println("producer (lastname): ");
		lastName = demo.scanner.next();
		producer = new Human(firstName, lastName);
		
		newMovie = new Movie(name, stars, IMDBscore, director, producer, productionYear);
		ArrayList<Movie> updatedAvailableMovies = new ArrayList<Movie>(demo.getAvailableMovies());
		updatedAvailableMovies.add(newMovie);
		demo.setAvailableMovies(updatedAvailableMovies);
		
		System.out.println("movie added to library successfully");
		
	}
	@Override
	public String toString() {
		return ("User Status: Administrator" + "\nFirst name: " + this.getFirstName() + "\nLast name: " + this.getLastName() + 
				"ID: " + this.getID() + "Phone No: " + this.getPhoneNo());
	}
}

/*
 * Movie class that is used to define movies and their identity
 * it has some attributes and some getters and setters in order to 
 * get info and change some of them 
 */

class Movie {
	private final String name;
	private final String[] stars = new String[3];
	private Double IMDBscore;
	private final Human director;
	private final Human producer;
	private final int productionYear;
	private Consumer borrower;
	
	public Movie(String name, String[] stars, Double IMDBscore, Human director, Human producer, int productionYear) {
		this.name = name;
		for (int n = 0; n < 3; n++) {
			this.stars[n] = stars[n];
		}
		this.IMDBscore = IMDBscore;
		this.director = director;
		this.producer = producer;
		this.productionYear = productionYear;
		
	}

	public Double getIMDBscore() {
		return IMDBscore;
	}

	public void setIMDBscore(Double iMDBscore) {
		IMDBscore = iMDBscore;
	}

	public Human getDirector() {
		return director;
	}

	public Human getProducer() {
		return producer;
	}

	public String getName() {
		return name;
	}

	public String[] getStars() {
		return stars;
	}

	public Consumer getBorrower() {
		return borrower;
	}

	public void setBorrower(Consumer borrower) {
		this.borrower = borrower;
	}

	public int getProductionYear() {
		return productionYear;
	}
	
	@Override
	public String toString() {
		String actors = "";
		for (String actor : this.stars)
			actors += actor + "\n";
		return "name: " + name + "\n\nproduction year: " + productionYear + "\n\nstarring: \n\n" + actors + "\nIMDB score: " 
					+ IMDBscore + "\n\ndirected by: " + director.getFirstName() + " " +
					director.getLastName() + "\n\nproduced by: " + producer.getFirstName() + " " + producer.getLastName();
	}
}

/*
 * demo public class that holds default Consumers and Admins in separate ArrayLists
 * defines some methods for sub menus 
 */

public class demo {
	private static List<Consumer> VerifiedConsumers;
	private static List<Consumer> ToBeVerifiedConsumers;
	private static List<Admin>    Admins;
	private static List<Movie>    AvailableMovies;
	private static List<Movie>	  BorrowedMovies;
	static Scanner scanner = new Scanner(System.in);
	
	private static void initializer() {// Defines default Consumers and Admins
		Consumer ali   = new Consumer("Ali", "Ahmadi", "ali1", "password", "1234567890");
		Consumer gholi = new Consumer("Gholi", "Akbari", "gholi_se", "password1", "2345678901");
		Admin manager  = new Admin("Taghi", "Rajabi", "3456789012", "admin", "admin");
		Human FFC      = new Human("Francis Ford", "Coppola");
		Human AR       = new Human("Albert S.", "Ruddy");
		Movie godfather= new Movie("The Godfather", new String[] {"Marlon Brando", "Al Pacino", "James Caan"}, 9.2, FFC, AR, 1976);
		Human BJh      = new Human("Joon-ho", "Bong");
		Human KSa      = new Human("Sin-ae", "Kwak");
		Movie parasite = new Movie("Parasite", new String[] {"Song Kang-ho", "Lee Sun-kyun", "Cho Yeo-jeong"}, 8.6, BJh, KSa, 2019);
		AvailableMovies = Arrays.asList(godfather);
		BorrowedMovies = Arrays.asList(parasite);
		VerifiedConsumers = Arrays.asList(ali, gholi);
		Admins = Arrays.asList(manager);
		ToBeVerifiedConsumers = new ArrayList<Consumer>();
		manager.lendMovie(parasite, gholi);
		
		
	}
	
	private static void logInAuthentication() { // Authenticator method to sign Consumer or Admin in
		int selected = 0;
		while (selected != 3) {
			System.out.println("# 1) Sign as Admin\n# 2) Sign as Consumer\n# 3) Previous menu");
			selected = scanner.nextInt();
			if (selected == 1) {
				String userName;
				String passWord;
				System.out.print("Username: ");
				userName = scanner.next();
				System.out.print("Password: ");
				passWord = scanner.next();
				for (Admin admin : Admins) {
					if (admin.getUserName().equals(userName) && admin.getPassWord().equals(passWord)) {
						successfulAdminLogIn(admin);
					}
				}
			} else if (selected == 2) {
				String userName = "";
				String passWord = "";
				System.out.print("Username: ");
				userName = scanner.next();
				System.out.print("Password: ");
				passWord = scanner.next();
				for (Consumer consumer : VerifiedConsumers) {
					if (consumer.getUserName().equals(userName) && consumer.getPassWord().equals(passWord)) {
						successfulConsumerLogIn(consumer);
					}
				}
			} else if (selected == 3) {
				return;
			}
		}
		
	}
	
	private static void successfulAdminLogIn(Admin admin) {
		System.out.println("Good day manager");
		System.out.println("# 1) Add new movie\n# 2) verify new member\n# 3) lend movie to consumer\n# 4) search in consumers"
				+ "\n# 5) Exit account");
		int selected = 0;
		while (selected != 4) {
			selected = scanner.nextInt();
			if (selected == 1) {
				admin.addMovie();
			} else if (selected == 2) {
				admin.showToBeVerifiedConsumers();
							
			} else if (selected == 3) {
				Movie toBeBorrowed = null;
				showAvailableMovies();
				System.out.print("Enter the fullname of movie you want to lend: ");
				scanner.nextLine();
				String name = scanner.nextLine();
				for (Movie movie : AvailableMovies) {
					if (movie.getName().toLowerCase().equals(name.toLowerCase())) {
						toBeBorrowed = movie;
						break;
					}
				}
				showVerifiedConsumers();
				System.out.print("Enter the username of the consumer you wish to borrow: ");
				name = scanner.next();
				for (Consumer consumer : VerifiedConsumers) {
					if (consumer.getUserName().equals(name)) {
						admin.lendMovie(toBeBorrowed, consumer);
						System.out.println("The Movie is now borrowed by " + consumer.getFirstName() + " " 
						+ consumer.getLastName());
						break;
					}
				}
				
				
			} else if (selected == 4) {
				
				
			} else if (selected == 5) {
				return;
			}
			
			System.out.println("# 1) Add new movie\n# 2) verify new member\n# 3) lend movie to consumer\n# 4) search in consumers"
					+ "\n# 5) Exit account");
		}
	}
	
	private static void successfulConsumerLogIn(Consumer consumer) { // When some Consumer successfully logs in 
		System.out.println("Signed in successfully");
		System.out.println("# 1) My Account Details\n# 2) Edit Account Details");
		int selected = scanner.nextInt();
		if (selected == 1) {
			System.out.println(consumer.toString());
		} else if (selected == 2) {
			editAccountDetails(consumer);
		}
	}
	
	private static void editAccountDetails(Consumer consumer) { // When logged in Consumer, asks to change identity
		System.out.println("What do you want to edit:\n# 1) firstname\n# 2) lastname\n# 3) phone No\n# 4) password");
		int selected = scanner.nextInt();
		if (selected == 1) {
			System.out.print("Enter your new firstname : ");
			String firstName = scanner.next();
			consumer.setFirstName(firstName);
			System.out.println("Your firstname updated successfully");
		
		} else if (selected == 2) {
			System.out.print("Enter your new lastname : ");
			String lastName = scanner.next();
			consumer.setLastName(lastName);
			System.out.println("Your lastname updated successfully");
		
		} else if (selected == 3) {
			System.out.print("Enter your new phone No : ");
			String phoneNo = scanner.next();
			consumer.setPhoneNo(phoneNo);
			System.out.println("Your phone No updated successfully");
		} else if (selected == 4) {
			System.out.print("Enter your new password : ");
			String passWord = scanner.next();
			consumer.setPassWord(passWord);
			System.out.println("Your password updated successfully");
		}
	}
	
	public static void signUp() { // When a new Consumer wants to join the club
		String firstName, lastName, ID, phoneNo, userName, passWord;
		System.out.println("Let's make your account");
		System.out.print("Please Enter your firstname: ");
		firstName = scanner.next();
		System.out.print("\nEnter your lastname: ");
		lastName  = scanner.next();
		System.out.print("\nEnter your prefered username: ");
		userName = scanner.next();
		System.out.print("\nEnter your password: ");
		passWord = scanner.next();
		System.out.print("\nEnter your ID: ");
		ID = scanner.next();
		System.out.print("\nEnter your phone No: ");
		phoneNo = scanner.next();
		Consumer newConsumer = new Consumer(firstName, lastName, userName, passWord, ID, phoneNo);
		ArrayList<Consumer> updatedConsumers = new ArrayList<Consumer>(getToBeVerifiedConsumers());
		updatedConsumers.add(newConsumer);
		setToBeVerifiedConsumers(updatedConsumers);
		System.out.println("Congrates! your account has been created\nEnjoy having good time in the club");
		System.out.println("\nchoose what you want to do:\n# 1) Log In\n# 2) Sign Up\n# 3) Current Available Movies"
				+ "\n# 4) Borrowed Movies Report\n# 5) Search in movies");
		
	}
	
	public static void showAvailableMovies() {
		
		for (int i = 0; i < AvailableMovies.size(); i++) {
			
			printMovieInfo(AvailableMovies.get(i), null);
		}
	}
	
	public static void borrowedMoviesreport() {
		for (int i = 0; i < BorrowedMovies.size(); i++) {
			Movie borrowedMovie = BorrowedMovies.get(i);
			Consumer borrower = borrowedMovie.getBorrower();
			
			printMovieInfo(borrowedMovie, borrower);
			

		}
	}
	
	public static void printMovieInfo(Movie movie, Consumer borrower) {
		System.out.println("-------------------------");
		if (borrower == null) {
			System.out.println("available");
		} else {
			System.out.print("borrower: ");
			System.out.print(borrower.getFirstName() + " ");
			System.out.println(borrower.getLastName());
		}
		System.out.println("-------------------------");
		System.out.println(movie.toString());
		System.out.println("=========================");
	}
	
	public static void searchInMovies() {
		while (true) {
			String searchPhrase;
			System.out.print("Enter your search phrase: ");
			searchPhrase = scanner.nextLine();
			if (searchPhrase.isEmpty()) {
				System.out.println("\nExited the search section");
				return;
			}
			for (Movie movie : AvailableMovies) {
				if (movie.getName().toLowerCase().contains(searchPhrase.toLowerCase())) {
					printMovieInfo(movie, null);
				}
			} for (Movie movie : BorrowedMovies) {
				if (movie.getName().toLowerCase().contains(searchPhrase.toLowerCase())) {
					printMovieInfo(movie, movie.getBorrower());
				}
			}
		}
	}
	
	public static void showVerifiedConsumers() {
		for (Consumer consumer : VerifiedConsumers) {
			System.out.println(consumer.toString());
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
	}
	
	public static void main(String[] args) { // *** main method used to start the code and show the first menu ***
		
		initializer();
		
		System.out.println("# Welcome\n# choose what you want to do:\n# 1) Log In\n# 2) Sign Up\n# 3) Current Available Movies"
				+ "\n# 4) Borrowed Movies Report\n# 5) Search in movies");
		
		int selected = 0;
		
		while (selected != 7) {
			
			try {
				selected = scanner.nextInt();
				
				if (selected == 1) {
					logInAuthentication();
				} else if (selected == 2) {
					signUp();
					
				} else if (selected == 3) {
					showAvailableMovies();
					
				} else if (selected == 4) {
					borrowedMoviesreport();
					
				} else if (selected == 5) {
					scanner.nextLine();
					searchInMovies();
					
				} else {
					throw new IOException();
				}
			} catch (IOException e) {
				System.out.print("Please enter a valid number! ");
				scanner.nextLine();
			} finally {
				System.out.println("\n# choose what you want to do:\n# 1) Log In\n# 2) Sign Up\n# 3) Current Available Movies"
				+ "\n# 4) Borrowed Movies Report\n# 5) Search in movies");
			}
		}	
		scanner.close();
	}


	public static List<Movie> getAvailableMovies() {
		return AvailableMovies;
	}

	public static void setAvailableMovies(List<Movie> availableMovies) {
		AvailableMovies = availableMovies;
	}

	public static List<Movie> getBorrowedMovies() {
		return BorrowedMovies;
	}

	public static void setBorrowedMovies(List<Movie> borrowedMovies) {
		BorrowedMovies = borrowedMovies;
	}
	
	public static List<Consumer> getVerifiedConsumers() {
		return  VerifiedConsumers;
	}

	public static void setVerifiedConsumers(List<Consumer> consumers) {
		VerifiedConsumers = consumers;
	}
	
	public static List<Consumer> getToBeVerifiedConsumers() {
		return  ToBeVerifiedConsumers;
	}
	
	public static void setToBeVerifiedConsumers(List<Consumer> consumers) {
		ToBeVerifiedConsumers = consumers;
	}

	public static List<Admin> getAdmins() {
		return Admins;
	}

	public static void setAdmins(List<Admin> admins) {
		Admins = admins;
	}

}





