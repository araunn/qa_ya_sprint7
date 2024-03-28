package ru.yandex.sprint7;

import static org.junit.Assert.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateCourierPostBodyData;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class CreateCourierTest {
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
	}

	@Test
	@DisplayName("Check that courier can create")
	@Description("проверка что курьера можно создать")
	// проверка что курьера можно создать
	public void canCreateCourierTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
		deleteCourier();
	}
	
	@Test
	@Description("проверка текста сообщения о создании курьера")
	public void checkMessageCreateCourierTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		CreateCourierPostBodyResponsePojo response = given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then()
		.extract().as(CreateCourierPostBodyResponsePojo.class);
		assertEquals(CreateCourierPostBodyData.SUCCESS_CREATE_COURIER_MASSAGE, response.getOk());
		deleteCourier();
	}
	
	@Test
	@Description("проверка что нельзя создать 2х курьеров с одинаковыми данными")
	public void cannotCreateDiplicateCourierTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		//создаем курьера и проверяем что статус успешного создания курьера
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
		// создаем еще одного куртьера с теми же данными и проверяем, что приходит ошибка
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(409);
		deleteCourier();	
	}
	
	@Test
	@Description("проверка что нельзя создать курьеров с одинаковыми логинами")
	public void cannotCreateCourierWithDuplicateNameTest() {
		CreateCourierPostBodyRequestPojo jsonFirstCourier = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		CreateCourierPostBodyRequestPojo jsonSecondCourier = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD_NEW, CreateCourierPostBodyData.COURIER_NAME_NEW);
		given().header("Content-type", "application/json")
		.body(jsonFirstCourier).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
		// создаем еще одного куртьера с теми же данными и проверяем, что приходит ошибка
		given().header("Content-type", "application/json")
		.body(jsonSecondCourier).when().post(CommonData.CREATE_COURIER_API).then().statusCode(409);
		deleteCourier();	
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без логина")
	public void cannotCreateCourierWithoutFieldLoginTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(400);
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без пароля")
	public void cannotCreateCourierWithoutFieldPasswordTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.EMPTY_STRING, CreateCourierPostBodyData.COURIER_NAME);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(400);
	}
	
	@Test
	@Description("проверка что нельзя создать курьера без логина и пароля")
	public void cannotCreateCourierWithoutFieldLoginAndPasswordTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.EMPTY_STRING, CreateCourierPostBodyData.COURIER_NAME);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(400);
	}
	
	@Test
	@Description("проверка что можно создать курьера без имени")
	public void canCreateCourierWithoutFieldNameTest() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.EMPTY_STRING);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
		deleteCourier();
	}

	// функция для удаления существующего курьера
	public void deleteCourier() {
		ExistCourierPostRequestPojo json = new ExistCourierPostRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, CreateCourierPostBodyData.COURIER_PASSWORD);
		ExistCourierPostResponsePojo courier =  given().header("Content-type", "application/json")
			.body(json).post(CommonData.LOGIN_COURIER_API).then().extract().as(ExistCourierPostResponsePojo.class);
	given().delete(CommonData.CREATE_COURIER_API + "/" + courier.getId().toString()).then().statusCode(200);
	}
}
