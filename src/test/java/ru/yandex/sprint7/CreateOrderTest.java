package ru.yandex.sprint7;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
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
		assertTrue(order.getOrderResponse(order.getOrderJsonWithOneColor()).as(CreateOrderPostBodyResponsePojo.class)
				.getTrack().matches(CommonData.REGEX_DIGITAL));
	}

}
