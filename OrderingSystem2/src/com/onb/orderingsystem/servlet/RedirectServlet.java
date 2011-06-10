package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onb.orderingsystem.domain.Customer;
import com.onb.orderingsystem.service.CustomerServiceManager;
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
		Collection<Customer> customers;
		
		if(useCase.equals("createOrder")){
			//customers = customerService.getCustomersWithValidCreditLimit();
			HttpSession session = request.getSession();
			//session.setAttribute("customerList", customers);
			response.sendRedirect("createorder.jsp");
		}
		else if(useCase.equals("payment")){
			customers = customerService.getCustomersWithUnpaidOrder();
			response.sendRedirect("payment.jsp");
		}
		else if(useCase.equals("history")){
			customers = customerService.getCustomerList();
			response.sendRedirect("history.jsp");
		}
		else response.sendError(404);
		
	}

}
