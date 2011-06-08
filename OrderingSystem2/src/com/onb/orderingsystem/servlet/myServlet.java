package com.onb.orderingsystem.servlet;
//import com.onb.orderingsystem.dao.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class myServlet
 */
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   // private DAOFactory dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServlet() {
        super();
     /*   try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		HttpSession session = request.getSession();
		//CustomerDAO customer = dao.getCustomerDAO();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		session.setAttribute("testText", name+ " " + id);
		response.sendRedirect("index.jsp");
	}

}
