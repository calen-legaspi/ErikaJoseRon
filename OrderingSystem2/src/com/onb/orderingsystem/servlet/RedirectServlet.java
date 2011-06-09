package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onb.orderingsystem.dao.*;
import com.onb.orderingsystem.domain.Customer;
/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAOFactory dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        try {
			dao = DAOFactory.getFactory();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String useCase = request.getParameter("useCase");
		
		CustomerDAO customerDao = dao.getCustomerDAO();
		//ProductDAO productDao = dao.getProductDAO();
		try {
			Set<Customer> customers = customerDao.listAllCustomer();
			if(useCase.equals("createOrder")){
				HttpSession session = request.getSession();
				session.setAttribute("customerList", customers);
				response.sendRedirect("createorder.jsp");
			}
			else if(useCase.equals("payment")){
				//customerlist - w/ unpaid orders
				response.sendRedirect("payment.jsp");
			}
			else if(useCase.equals("history")){
				//customerlist - all
				response.sendRedirect("history.jsp");
			}
			else response.sendError(404);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
