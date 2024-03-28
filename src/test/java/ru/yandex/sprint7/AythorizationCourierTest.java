package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateCourierPostBodyData;
import ru.yandex.sprint7.data.LoginCourierPostBodyData;

public class AythorizationCourierTest {
	
	@Before
	public void setUp() {
		RestAssured.baseURI = CommonData.SITE_ADRESS;
	}

	@Test
	@DisplayName("Check that contains ID number")
	@Description("проверяем что ответ содердит цифровое значение id")
	public void checkResponseContainIntegerIdCourierTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
		LoginCourierPostBodyResponsePojo response = given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.LOGIN_COURIER_API).then()
		.extract().as(LoginCourierPostBodyResponsePojo.class);
		assertTrue(response.getId().matches("-?\\d+(\\.\\d+)?"));
		deleteCourier(json);
	}
	
	@Test
	@DisplayName("Check that exist courier can authorization")
	@Description("проверяем что курьер может авторизоваться, ответ содердит статус 200")
	public void canAuthorizationCourierTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(200);
        deleteCourier(json);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без логина")
	public void cannotAuthorizationCourierWithoutLoginTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(LoginCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(400);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без пароля")
	public void cannotAuthorizationCourierWithoutPasswordTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				LoginCourierPostBodyData.EMPTY_STRING);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(400);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без логина и пароля")
	public void cannotAuthorizationCourierWithoutLoginAndPasswordTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(LoginCourierPostBodyData.EMPTY_STRING, 
				LoginCourierPostBodyData.EMPTY_STRING);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(400);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным логином")
	public void wrongLoginTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(LoginCourierPostBodyData.WRONG_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(404);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным паролем")
	public void wrongPasswordTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				LoginCourierPostBodyData.WRONG_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(404);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным логином и паролем")
	public void wrongLoginAndPasswordTest() {
		createCourier();
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(LoginCourierPostBodyData.WRONG_LOGIN, 
				LoginCourierPostBodyData.WRONG_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(404);
        deleteCourier(new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD));
	}
	
	@Test
	@Description("проверяем что Ошибка если курьер не создан")
	public void cannotAuthorizationWithoutCreateCourierTest() {
		LoginCourierPostBodyRequestPojo json = new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
        given().header("Content-type", "application/json").body(json).when().post(CommonData.LOGIN_COURIER_API)
        .then().statusCode(404);
	}
	
	public void createCourier() {
		CreateCourierPostBodyRequestPojo json = new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD, CreateCourierPostBodyData.COURIER_NAME);
		given().header("Content-type", "application/json")
		.body(json).when().post(CommonData.CREATE_COURIER_API).then().statusCode(201);
	}
	
	public void deleteCourier(LoginCourierPostBodyRequestPojo json) {
		LoginCourierPostBodyResponsePojo response =  given().header("Content-type", "application/json")
				.body(json).post(CommonData.LOGIN_COURIER_API).then().extract().as(LoginCourierPostBodyResponsePojo.class);
		given().delete(CommonData.CREATE_COURIER_API + "/" + response.getId().toString()).then().statusCode(200);
	}
}
