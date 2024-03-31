package ru.yandex.sprint7;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.pojo.CreateOrderPostBodyResponsePojo;

public class CreateOrderTest {
	OrderApi order = new OrderApi();
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
	}
	
	@Test
	@Description("Проверка что создании заказа в теле ответа содержится числовой номер трека")
	public void checkResponseContainTrackNumberTest() {
		CreateOrderPostBodyResponsePojo response = given().header("Content-type", "application/json").body(order.getOrderJsonWithOneColor())
		 .when().post(CommonData.CREATE_ORDER_API).then().extract().as(CreateOrderPostBodyResponsePojo.class);
		assertTrue(response.getTrack().matches(CommonData.REGEX_DIGITAL));
	}

}
