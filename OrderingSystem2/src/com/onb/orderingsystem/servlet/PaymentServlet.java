package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.orderingsystem.bean.CustomerObject;
import com.onb.orderingsystem.service.CustomerServiceManager;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerServiceManager customerService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
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
		String orderId = request.getParameter("customer");
		if(orderId==null){
			throw new IllegalArgumentException();
		}
		int customerId = Integer.parseInt(orderId);
		request.setAttribute("customerId", customerId);
		CustomerObject customer = new CustomerObject();
		customer.setId(customerId);
		Collection<CustomerObject> customerList = customerService.getCustomersWithUnpaidOrder();
		request.setAttribute("customerList", customerList);
		
		if(!customerList.contains(customer)){
			throw new NullPointerException();
		}
		
		for(CustomerObject person:customerList){
			if(person.getId()==customerId){
				request.setAttribute("orderList", person.getOrders());
				break;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("pay.order");
		rd.forward(request, response);
	}

}
