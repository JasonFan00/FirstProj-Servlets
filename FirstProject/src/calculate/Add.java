package calculate;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
// You use web.xml OR annotations, either or, NOT BOTH
@WebServlet(name = "AddServlet", urlPatterns = {"/yeet", "/yeeeeet"}, initParams = {@WebInitParam(name = "Farewell", value = "Goodbye!"), @WebInitParam(name = "MyName", value = "Jason")}) //  Has to be prefaced with the '/' ... 
public class Add extends HttpServlet {	
	private static final long serialVersionUID = 1L;
		
    /**
     * Default constructor.
     */
    public Add() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/DispatchedServlet");
		dispatcher.include(request, response);
		
		PrintWriter pw = response.getWriter();
		ServletConfig sc = getServletConfig();
		pw.println(sc.getInitParameter("Farewell"));
		pw.println(sc.getInitParameter("MyName"));
		
		Object obj = "hello";
		String obj2 = (String)obj;
		
		pw.println("Hello");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}