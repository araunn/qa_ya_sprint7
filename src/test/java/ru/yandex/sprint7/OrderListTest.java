package ru.yandex.sprint7;

import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.pojo.OrdersListGetBodyResponsPojo;

public class OrderListTest {
	CourierApi courier = new CourierApi();
	OrderApi order = new OrderApi();
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
		courier.createCourier();
	}
	
	@After
	public void deleteCourier() {
	courier.deleteCourier();
	}

	@Test
	@Description("Пооверка что при получении списка заказов тело ответа не пустое")
	public void checkExistOrdersTest() {
        assertFalse(order.getOrderListResponse(order.getOrderJsonWithOneColor()).as(OrdersListGetBodyResponsPojo.class)
        		.getOrders().length==0);
	}
}
