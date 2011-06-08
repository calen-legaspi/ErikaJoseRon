package com.onb.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onb.domain.Customer;



/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			// do whatever
			
			Customer customer = new Customer(Integer.parseInt(id), name);
			
			System.out.println("ENTER SERVLET "+name);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("testText", customer.toString());
			
			System.out.println("EXIT SERVLET "+request.getAttribute("testText"));
			
			response.sendRedirect("index.jsp");
			
			//RequestDispatcher rd = request.getRequestDispatcher("index.html");
			//rd.forward(request, response);
		
	}

}
