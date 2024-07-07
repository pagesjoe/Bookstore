package com.joe.bookstore.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joe.bookstore.models.Book;
import com.joe.bookstore.models.BookDAO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//Declaring DAO for the Book
	private BookDAO bookDao;

    public HomeServlet() {
        super();
    }


	@Override
	public void init() throws ServletException {
		super.init();
		//Instantiating the DAO Object
		bookDao = new BookDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting the command value
		String command = request.getParameter("command");
		
		//Checking the command value to choose corresponding method
		if(command != null) {
			switch(command) {
				case "add": addBook(request,response); break;
				case "delete": deleteBook(request,response); break;
				case "edit": editBook(request,response); break;
				case "update": updateBook(request,response); break;
			}
		}else {
			listBooks(request,response);

		}
	}
	
	//Update the book details
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Getting update information from request
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		double price = Double.parseDouble(request.getParameter("price"));
		
		//Creating new book Object
		Book book = new Book(id, title,author,price);
		//Editing the book in the database
		try{
			bookDao.updateBook(book);
		}catch(Exception e){
			e.printStackTrace();
		}
		//Go to list books page
		listBooks(request, response);
	}


	//Go to edit form page
		private void editBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			Book book = null;
			//Get book id needed to edit
			int id = Integer.parseInt(request.getParameter("id"));
			
			//Get book data from database
			try{
				book = bookDao.getBookById(id);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//Direct to edit_book Jsp page
			request.setAttribute("book", book);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_book.jsp");

			dispatcher.forward(request, response);
		}


	//Delete the book received from the request
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting book id
		int id = Integer.parseInt(request.getParameter("id"));
		
		//deleting the book from the database
		try{
			bookDao.deleteBook(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Directing to list books page
		listBooks(request,response);
	}


	//Add the book received from the request
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Getting book form parameters
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		double price = Double.parseDouble(request.getParameter("price"));
		//Creating new book Object
		Book book = new Book(title,author,price);
		//Adding the book to the database
		try{
			bookDao.addBook(book);
		}catch(Exception e){
			e.printStackTrace();
		}
		//Go to list books page
		listBooks(request, response);
	}


	private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Book> books = new ArrayList<>();
		
		try{
			books = bookDao.getBooks();
		}catch(Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("books", books);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/view_books.jsp");

		dispatcher.forward(request, response);
	}




}
