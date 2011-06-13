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
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private CustomerServiceManager customerService;  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
		int customerId = Integer.parseInt(request.getParameter("customer"));
		
		CustomerObject customer = new CustomerObject();
		customer.setId(customerId);
		Collection<CustomerObject> customerList = customerService.getCustomerList();
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
		//response.sendRedirect("order.history");
		RequestDispatcher rd = request.getRequestDispatcher("order.history");
		rd.forward(request, response);
	}

}
