package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onb.orderingsystem.domain.Customer;
import com.onb.orderingsystem.service.CustomerServiceManager;
import com.onb.orderingsystem.bean.CustomerObject;

/**
 * Servlet implementation class RedirectServlet
 */
@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerServiceManager customerService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        customerService = new CustomerServiceManager();
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
		Collection<CustomerObject> customerList = new HashSet<CustomerObject>();
		
		if(useCase.equals("createOrder")){
			customerList = customerService.getCustomersWithValidCreditLimit();
			request.setAttribute("customerList", customerList);
			
			RequestDispatcher rd = request.getRequestDispatcher("create.order");
			rd.forward(request, response);
		}
		else if(useCase.equals("payment")){
			customerList = customerService.getCustomersWithUnpaidOrder();
			request.setAttribute("customerList", customerList);
			//response.sendRedirect("payment.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("payment.jsp");
			rd.forward(request, response);
			
		}
		else if(useCase.equals("history")){
			customerList = customerService.getCustomerList();
			request.setAttribute("customerList", customerList);
			//response.sendRedirect("history.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
			rd.forward(request, response);
		}
		else response.sendError(404);
		
	}

}

