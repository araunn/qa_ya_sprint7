package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.pojo.LoginCourierPostBodyResponsePojo;

public class AythorizationCourierTest {
	CourierApi courier = new CourierApi();
	
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
	@DisplayName("Check that contains ID number")
	@Description("проверяем что ответ содердит цифровое значение id")
	public void checkResponseContainIntegerIdCourierTest() {
		LoginCourierPostBodyResponsePojo response = given().header("Content-type", "application/json")
		.body(courier.getJsonWithLoginAndPassword()).when().post(CommonData.LOGIN_COURIER_API).then()
		.extract().as(LoginCourierPostBodyResponsePojo.class);
		assertTrue(response.getId().matches(CommonData.REGEX_DIGITAL));
	}
	
	@Test
	@DisplayName("Check that exist courier can authorization")
	@Description("проверяем что курьер может авторизоваться, ответ содердит статус 200")
	public void canAuthorizationCourierTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithLoginAndPassword()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_OK);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без логина")
	public void cannotAuthorizationCourierWithoutLoginTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithoutLogin()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без пароля")
	public void cannotAuthorizationCourierWithoutPasswordTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithoutPassword()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться без логина и пароля")
	public void cannotAuthorizationCourierWithoutLoginAndPasswordTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithoutLoginAndPassword()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_BAD_REQUEST);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным логином")
	public void wrongLoginTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithWrongLogin()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_NOT_FOUND);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным паролем")
	public void wrongPasswordTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithWrongPaswword()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_NOT_FOUND);
	}
	
	@Test
	@Description("проверяем что курьер не может авторизоваться с не верным логином и паролем")
	public void wrongLoginAndPasswordTest() {
        given().header("Content-type", "application/json").body(courier.getJsonWithWrongLoginAndPaswword()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_NOT_FOUND);
	}
	
	@Test
	@Description("проверяем что Ошибка если курьер не создан")
	public void cannotAuthorizationWithoutCreateCourierTest() {
        given().header("Content-type", "application/json").body(courier.getJsonCourierNotExist()).when()
        .post(CommonData.LOGIN_COURIER_API).then().statusCode(SC_NOT_FOUND);
	}
}
