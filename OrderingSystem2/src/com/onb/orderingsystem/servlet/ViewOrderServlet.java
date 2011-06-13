package com.onb.orderingsystem.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.orderingsystem.bean.OrderItemObject;
import com.onb.orderingsystem.bean.OrderObject;
import com.onb.orderingsystem.bean.ProductObject;
import com.onb.orderingsystem.service.OrderServiceManager;
import com.onb.orderingsystem.service.ProductServiceManager;

/**
 * Servlet implementation class ViewOrderServlet
 */
@WebServlet("/ViewOrderServlet")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderServiceManager orderService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrderServlet() {
        super();
        orderService = new OrderServiceManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("selectedOrder");
		if(orderId==null){
			throw new IllegalArgumentException();
		}
		
		OrderObject order = orderService.findOrderByID(Integer.parseInt(orderId));
		Collection<OrderItemObject> orderItems = new LinkedHashSet<OrderItemObject>();
		
		for(OrderItemObject orderItem: order.getOrders()){
			//Collection<ProductObject> products = new LinkedHashSet<ProductObject>();
			ProductObject productItem = orderItem.getProduct();
			productItem.setName(orderItem.getProduct().getName());
			productItem.setSku(orderItem.getProduct().getSku());
			productItem.setPrice(orderItem.getProduct().getPrice());
			//products.add(productItem);
			
			OrderItemObject item = new OrderItemObject();
			item.setAmount(orderItem.getAmount());
			item.setId(orderItem.getId());
			item.setProduct(productItem);
			item.setQuantity(orderItem.getQuantity());
			orderItems.add(item);
		}
		
		BigDecimal total = order.getTotal();
		int customerId = order.getCustomerID();
		String status = (order.getStatus()==1)? "Paid" : "Unpaid";
		
		request.setAttribute("orderItemList", orderItems);
		request.setAttribute("totalAmt", total);
		request.setAttribute("customerId", customerId);
		request.setAttribute("orderStatus", status);
		RequestDispatcher rd = request.getRequestDispatcher("vieworder.jsp");
		rd.forward(request, response);
	}

}
