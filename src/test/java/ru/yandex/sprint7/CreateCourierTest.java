package ru.yandex.sprint7;

import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateCourierPostBodyData;
import ru.yandex.sprint7.pojo.CreateCourierPostBodyResponsePojo;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class CreateCourierTest {
	CourierApi courier = new CourierApi();
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
	}

	@Test
	@DisplayName("Check that courier can create")
	@Description("проверка что курьера можно создать")
	public void canCreateCourierTest() {
		given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPasswordAndName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_CREATED);
		courier.deleteCourier(courier.getJsonWithLoginAndPassword());
	}
	
	@Test
	@Description("проверка текста сообщения о создании курьера")
	public void checkMessageCreateCourierTest() {
		CreateCourierPostBodyResponsePojo response = given().header("Content-type", "application/json")
		.body(courier.getJsonWithLoginAndPasswordAndName()).when()
		.post(CommonData.CREATE_COURIER_API).then().extract().as(CreateCourierPostBodyResponsePojo.class);
		assertEquals(CreateCourierPostBodyData.SUCCESS_CREATE_COURIER_MASSAGE, response.getOk());
		courier.deleteCourier(courier.getJsonWithLoginAndPassword());
	}
	
	@Test
	@Description("проверка что нельзя создать 2х курьеров с одинаковыми данными")
	public void cannotCreateDiplicateCourierTest() {
		//создаем курьера и проверяем что статус успешного создания курьера
		given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPasswordAndName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_CREATED);
		// создаем еще одного куртьера с теми же данными и проверяем, что приходит ошибка
		given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPasswordAndName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_CONFLICT);
		courier.deleteCourier(courier.getJsonWithLoginAndPassword());	
	}
	
	@Test
	@Description("проверка что нельзя создать курьеров с одинаковыми логинами")
	public void cannotCreateCourierWithDuplicateNameTest() {
		given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPasswordAndName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_CREATED);
		// создаем еще одного куртьера с теми же данными и проверяем, что приходит ошибка
		given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPasswordnewAndNamenew()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_CONFLICT);
		courier.deleteCourier(courier.getJsonWithLoginAndPassword());	
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без логина")
	public void cannotCreateCourierWithoutFieldLoginTest() {
		given().header("Content-type", "application/json").body(courier.getJsonWithoutLoginWithName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без пароля")
	public void cannotCreateCourierWithoutFieldPasswordTest() {
		given().header("Content-type", "application/json").body(courier.getJsonWithoutPasswordWithName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без логина и пароля")
	public void cannotCreateCourierWithoutFieldLoginAndPasswordTest() {
		given().header("Content-type", "application/json").body(courier.getJsonWithoutLoginAndPasswordWithName()).when()
		.post(CommonData.CREATE_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверка что можно создать курьера без имени")
	public void canCreateCourierWithoutFieldNameTest() {
		given().header("Content-type", "application/json")
		.body(courier.getJsonWithLoginAndPasswordWithoutName()).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
		courier.deleteCourier(courier.getJsonWithLoginAndPassword());
	}
}
