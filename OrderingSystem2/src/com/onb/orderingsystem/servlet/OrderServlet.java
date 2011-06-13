package com.onb.orderingsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.orderingsystem.domain.Order;
import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.service.OrderServiceManager;
import com.onb.orderingsystem.service.ProductServiceManager;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductServiceManager productmanager;   
    private OrderServiceManager ordermanager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        ordermanager = new OrderServiceManager();
        productmanager = new ProductServiceManager();
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
		//cheat here for testing purposes
		int fakeId = 99999;
		Order order = new Order(fakeId);
		String skuNumber = "";
		int itemIndex = 0;
		int quantity = 0;
		String productstr = "product";//refactor: should be StringBuilder or StringBuffer?
		String quantitystr = "quantity";
		int OrderItemID = 0; 
		/*while(true){//make this less dumb
			String str = productstr+Integer.toString(itemIndex);
			skuNumber = request.getParameter(str);
			if (skuNumber == null) break;//itemIndex is greater than the number of OrderItems
			str = quantitystr+Integer.toString(itemIndex);
			str = request.getParameter(str);
			if (str == null) throw new IllegalArgumentException("Invalid quantity");
			quantity = 	Integer.parseInt(str);
			OrderItem orderitem = new OrderItem(OrderItemID++,productmanager.findProductBySKU(skuNumber), quantity);
			order.add(orderitem);
		}*/
		ordermanager.insertOrder(order);
	}

}
