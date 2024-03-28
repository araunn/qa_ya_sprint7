package ru.yandex.sprint7;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateOrderPostBodyData;

public class CreateOrderTest {
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
	}
	
	@Test
	@Description("Проверка что создании заказа в теле ответа содержится числовой номер трека")
	public void checkResponseContainTrackNumberTest() {
		CreateOrderPostBodyRequestPojo json = new CreateOrderPostBodyRequestPojo(CreateOrderPostBodyData.FIRSTNAME,CreateOrderPostBodyData.LASTNAME,
				CreateOrderPostBodyData.ADDRESS, CreateOrderPostBodyData.METRO_STATION,CreateOrderPostBodyData.PHONE,
				CreateOrderPostBodyData.RENT_TIME, CreateOrderPostBodyData.DELIVERY_DATE, CreateOrderPostBodyData.COMMENT,
				CreateOrderPostBodyData.COLOUR_BLACK);
		CreateOrderPostBodyResponsePojo response = given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_ORDER_API).then().extract().as(CreateOrderPostBodyResponsePojo.class);
		assertTrue(response.getTrack().matches("-?\\d+(\\.\\d+)?"));
	}

}
