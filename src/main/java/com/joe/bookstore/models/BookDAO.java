package com.joe.bookstore.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {

	String jdbcUrl;
	String username;
	String password;
	Connection conn;

	public BookDAO(){
		jdbcUrl = "jdbc:mysql://localhost:3306/bookstore_db";
		username = "root";
		password = "admin";
	}

	//Make a connection to the database
	private void connect() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//Getting all books from the database and return a books list
	public ArrayList<Book> getBooks() throws SQLException {

		connect();

		ArrayList<Book> books = new ArrayList<>();

		//Querying the database to get all books
		String sql = "Select * from book";

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(sql);

		//Looping through the books received from the database
		while(result.next()) {
			int id = result.getInt("id");
			String title = result.getString("title");
			String author = result.getString("author");
			double price = result.getDouble("price");
			//Adding the book to the books Array list
			books.add(new Book(id, title, author, price));
		}

		//Close the connections
		conn.close();
		stmt.close();
		result.close();

		return books;
	}

	//Inserting a book to the database
	public void addBook(Book book) throws SQLException {

		connect();
		
		//Querying the database to insert the book
		String sql = "INSERT INTO bookstore_db.book"
				+ "(title,author,price)"
				+ "VALUES"
				+ "(?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, book.getTitle());
		stmt.setString(2, book.getAuthor());
		stmt.setDouble(3, book.getPrice());
		
		stmt.execute();
		
		//Closing connection
		conn.close();
		stmt.close();
	}

	//delete book by its id
	public void deleteBook(int id) throws SQLException {

		connect();
		
		//Querying the database to delete the book by id
		String sql = "DELETE from book where id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		stmt.execute();
		
		//Closing connection
		conn.close();
		stmt.close();
	}

	//Get book from database by id
	public Book getBookById(int id) throws SQLException {

		connect();
		
		//Querying the database to get the book by id
		String sql = "Select * from book where id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet result = stmt.executeQuery();
		result.next();
		
		//Getting book data
		String title = result.getString("title");
		String author = result.getString("author");
		double price = result.getDouble("price");
		
		//creating the book object
		Book book = new Book(id, title, author, price);

		//Close the connections
		conn.close();
		stmt.close();
		result.close();

		return book;
	}

	public void updateBook(Book book) throws SQLException {

		connect();
		
		//Querying the database to update the book
		String sql = "Update book set title=?, author=?, price=?"
				+"where id=?";

		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, book.getTitle());
		stmt.setString(2, book.getAuthor());
		stmt.setDouble(3, book.getPrice());
		stmt.setInt(4, book.getId());
		
		stmt.execute();
		
		//Closing connection
		conn.close();
		stmt.close();
	}
}
