package ru.yandex.sprint7;

public class ExistCourierPostRequestPojo {
	private String login;
	private String password;
	public ExistCourierPostRequestPojo(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public ExistCourierPostRequestPojo() {
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
