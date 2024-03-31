package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;
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
	courier.deleteCourier(courier.getJsonWithLoginAndPassword());
	}

	@Test
	@Description("Пооверка что при получении списка заказов тело ответа не пустое")
	public void checkExistOrdersTest() {
		OrdersListGetBodyResponsPojo response = given().header("Content-type", "application/json").body(order.getOrderJsonWithOneColor())
		 .when().get(CommonData.ORDER_LIST_API).then().extract().as(OrdersListGetBodyResponsPojo.class);
        assertFalse(response.getOrders().length==0);
	}
}
