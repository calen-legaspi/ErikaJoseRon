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

import com.onb.orderingsystem.bean.CustomerObject;
import com.onb.orderingsystem.bean.OrderObject;
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
     * @see HttpServletdoGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("customer"); 
		int customerId = Integer.parseInt(custId);
		
		request.setAttribute("customerId", customerId);
		CustomerObject customer = new CustomerObject();
		customer.setId(customerId);
		Collection<CustomerObject> customerList = customerService.getCustomersWithUnpaidOrder();
		Collection<OrderObject> orderList = new HashSet<OrderObject>();
		request.setAttribute("customerList", customerList);
		
		if(!customerList.contains(customer)){
			throw new NullPointerException();
		}
		
		for(CustomerObject person:customerList){
			if(person.getId()==customerId){
				for(OrderObject order: person.getOrders()){
					if(order.getStatus()==0){
							orderList.add(order);
					}
				}
			}
		}
		
		String orderId = request.getParameter("order");
		if(orderId!=null){
			
			int selectedOrder = Integer.parseInt(orderId);
			for(OrderObject order:orderList){
				if(order.getId()==selectedOrder){

					//set order as paid
					orderList.remove(order);
					order.setStatus(1);
				}
			}
		}
		
		request.setAttribute("orderList", orderList);
		
		boolean show = orderList.isEmpty()? false: true;
		request.setAttribute("show", show);
		RequestDispatcher rd = request.getRequestDispatcher("pay.order");
		rd.forward(request, response);
	}

}

