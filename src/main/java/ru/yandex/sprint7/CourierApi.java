package ru.yandex.sprint7;

import static io.restassured.RestAssured.given;

import ru.yandex.sprint7.data.CommonData;
import ru.yandex.sprint7.data.CreateCourierPostBodyData;
import ru.yandex.sprint7.pojo.CreateCourierPostBodyRequestPojo;
import ru.yandex.sprint7.pojo.LoginCourierPostBodyRequestPojo;
import ru.yandex.sprint7.pojo.LoginCourierPostBodyResponsePojo;

public class CourierApi {
	
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
	
	public LoginCourierPostBodyRequestPojo getJsonWithLoginAndPassword() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithoutLogin() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithoutPassword() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.EMPTY_STRING);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithoutLoginAndPassword() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.EMPTY_STRING);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithWrongLogin() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN_NEW, 
				CreateCourierPostBodyData.COURIER_PASSWORD);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithWrongPaswword() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD_NEW);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonWithWrongLoginAndPaswword() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN_NEW, 
				CreateCourierPostBodyData.COURIER_PASSWORD_NEW);
	}
	
	public LoginCourierPostBodyRequestPojo getJsonCourierNotExist() {
		return new LoginCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN_NEW, 
				CreateCourierPostBodyData.COURIER_PASSWORD_NEW);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithLoginAndPasswordAndName() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD,CreateCourierPostBodyData.COURIER_NAME);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithLoginAndPasswordnewAndNamenew() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD_NEW,CreateCourierPostBodyData.COURIER_NAME_NEW);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithoutLoginWithName() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.COURIER_PASSWORD,CreateCourierPostBodyData.COURIER_NAME);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithoutPasswordWithName() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.EMPTY_STRING,CreateCourierPostBodyData.COURIER_NAME);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithoutLoginAndPasswordWithName() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.EMPTY_STRING, 
				CreateCourierPostBodyData.EMPTY_STRING,CreateCourierPostBodyData.COURIER_NAME);
	}
	
	public CreateCourierPostBodyRequestPojo getJsonWithLoginAndPasswordWithoutName() {
		return new CreateCourierPostBodyRequestPojo(CreateCourierPostBodyData.COURIER_LOGIN, 
				CreateCourierPostBodyData.COURIER_PASSWORD,CreateCourierPostBodyData.EMPTY_STRING);
	}
	
}
