package ru.yandex.sprint7;

import ru.yandex.sprint7.data.CreateOrderPostBodyData;
import ru.yandex.sprint7.pojo.CreateOrderPostBodyRequestPojo;

public class OrderApi {
	
	public CreateOrderPostBodyRequestPojo getOrderJsonWithOneColor () {
		return new CreateOrderPostBodyRequestPojo(CreateOrderPostBodyData.FIRSTNAME,CreateOrderPostBodyData.LASTNAME,
				CreateOrderPostBodyData.ADDRESS, CreateOrderPostBodyData.METRO_STATION,CreateOrderPostBodyData.PHONE,
				CreateOrderPostBodyData.RENT_TIME, CreateOrderPostBodyData.DELIVERY_DATE, CreateOrderPostBodyData.COMMENT,
				CreateOrderPostBodyData.COLOUR_BLACK);
	}

}
