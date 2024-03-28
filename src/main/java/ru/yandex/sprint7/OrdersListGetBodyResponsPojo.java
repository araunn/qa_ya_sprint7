package ru.yandex.sprint7;

public class OrdersListGetBodyResponsPojo {
	private OrderGetBodyResponsePojo[] orders;

	public OrdersListGetBodyResponsPojo(OrderGetBodyResponsePojo[] orders) {
		super();
		this.orders = orders;
	}

	public OrdersListGetBodyResponsPojo() {
		super();
	}

	public OrderGetBodyResponsePojo[] getOrders() {
		return orders;
	}

	public void setOrders(OrderGetBodyResponsePojo[] orders) {
		this.orders = orders;
	}
	
	

}
