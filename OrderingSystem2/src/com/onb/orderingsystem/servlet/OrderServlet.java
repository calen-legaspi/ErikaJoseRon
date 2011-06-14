package com.onb.orderingsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.orderingsystem.dao.DAOFactory;
import com.onb.orderingsystem.domain.Order;
<<<<<<< HEAD
=======
import com.onb.orderingsystem.domain.OrderItem;
import com.onb.orderingsystem.domain.Product;
>>>>>>> orderexperiment
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
		OrderServiceManager ordermanager = new OrderServiceManager();
		int orderID = ordermanager.getNewID();
		Order order = new Order(orderID);
		String skuNumber = "";
<<<<<<< HEAD
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
=======
		int OrderItemID, quantity, numItems;
		OrderItemID = quantity = 0;
		numItems = Integer.parseInt(request.getParameter("numItems"));
		String str1 = "product";//refactor: should be StringBuilder or StringBuffer?
		String str2 = "quantity"; 
		for(int i = 1; i<numItems; i++){
			skuNumber = parseOrderItemParameter(i, str1, request);
			String quantityString = parseOrderItemParameter(i, str2, request);
			if (quantityString == null||quantityString.equals("")) 
				throw new IllegalArgumentException("Invalid quantity");
			quantity = 	Integer.parseInt(quantityString);
			Product product = productmanager.findProductBySKU(skuNumber);
			OrderItem orderitem = new OrderItem(OrderItemID++,product, quantity);
>>>>>>> orderexperiment
			order.add(orderitem);
		}*/
		ordermanager.insertOrder(order);
	}

	private String parseOrderItemParameter(int i, String string1, HttpServletRequest request){
		String str = string1+Integer.toString(i);
		str = request.getParameter(str);
		return str;
	}
	
	
}
