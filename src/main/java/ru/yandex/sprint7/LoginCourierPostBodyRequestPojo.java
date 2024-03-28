package ru.yandex.sprint7;

public class LoginCourierPostBodyRequestPojo {
	private String login;
	private String password;
	public LoginCourierPostBodyRequestPojo(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public LoginCourierPostBodyRequestPojo() {
		super();
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
