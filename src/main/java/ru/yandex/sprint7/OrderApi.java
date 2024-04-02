package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateOrderPostBodyData;
import ru.yandex.sprint7.pojo.CreateOrderPostBodyRequestPojo;

public class OrderApi {
	
	public Response getOrderResponse(CreateOrderPostBodyRequestPojo json) {
		return given().header("Content-type", "application/json").body(json).when().post(CommonData.CREATE_ORDER_API);
	}
	
	public Response getOrderListResponse(CreateOrderPostBodyRequestPojo json) {
		return given().header("Content-type", "application/json").body(json).when().get(CommonData.ORDER_LIST_API);
	}
	
	public CreateOrderPostBodyRequestPojo getOrderJsonWithOneColor () {
		return new CreateOrderPostBodyRequestPojo(CreateOrderPostBodyData.FIRSTNAME,CreateOrderPostBodyData.LASTNAME,
				CreateOrderPostBodyData.ADDRESS, CreateOrderPostBodyData.METRO_STATION,CreateOrderPostBodyData.PHONE,
				CreateOrderPostBodyData.RENT_TIME, CreateOrderPostBodyData.DELIVERY_DATE, CreateOrderPostBodyData.COMMENT,
				CreateOrderPostBodyData.COLOUR_BLACK);
	}

}
