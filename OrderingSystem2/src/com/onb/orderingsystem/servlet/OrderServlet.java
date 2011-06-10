package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.orderingsystem.domain.Order;
import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.service.OrderServiceManager;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		String customerName = request.getParameter("customer");
		String ParameterNames = "";
		String str = request.getParameter("product0");
		String str1 = request.getParameter("product1");
		String str2 = request.getParameter("product2");
		String qty = request.getParameter("quantity0");
		String qty2 = request.getParameter("quantity1");
		String qty3 = request.getParameter("quantity2");
		//cheat here for testing purposes
		int fakeId = 99999;
		Order order = new Order(fakeId);
		OrderServiceManager ordermanager = new OrderServiceManager();
		int skuNumber = -1;
		
		while(true){
			String product = request.getParameter("");
			break;
		}
		for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); ){
			ParameterNames = e.nextElement();
			System.out.println(ParameterNames + "<br/>");
		}
		
	}

}
