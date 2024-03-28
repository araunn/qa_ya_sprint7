package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateCourierPostBodyData;
import ru.yandex.sprint7.data.CreateOrderPostBodyData;

public class OrderListTest {
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
		given().header("Content-type", "application/json")
		.body(getJson()).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
	}
	
	@After
	public void deleteCourier() {
	given().delete(CommonData.CREATE_COURIER_API + "/" + getCourier().getId().toString()).then().statusCode(200);
	}

	@Test
	@Description("Пооверка что при получении списка заказов тело ответа не пустое")
	public void test() {
		CreateOrderPostBodyRequestPojo createOrderJson = new CreateOrderPostBodyRequestPojo(CreateOrderPostBodyData.FIRSTNAME,CreateOrderPostBodyData.LASTNAME,
				CreateOrderPostBodyData.ADDRESS, CreateOrderPostBodyData.METRO_STATION,CreateOrderPostBodyData.PHONE,
				CreateOrderPostBodyData.RENT_TIME, CreateOrderPostBodyData.DELIVERY_DATE, CreateOrderPostBodyData.COMMENT,
				CreateOrderPostBodyData.COLOUR_BLACK);
		OrdersListGetBodyResponsPojo response = given().header("Content-type", "application/json")
		.body(createOrderJson).when().get(CommonData.ORDER_LIST_API).then().extract().as(OrdersListGetBodyResponsPojo.class);
        assertFalse(response.getOrders().length==0);
	}
	
	public ExistCourierPostResponsePojo getCourier() {
		return given().header("Content-type", "application/json")
			.body(getJson()).post(CommonData.LOGIN_COURIER_API).then().extract().as(ExistCourierPostResponsePojo.class);
	}
	public CreateCourierPostBodyRequestPojo getJson() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
	}
}
