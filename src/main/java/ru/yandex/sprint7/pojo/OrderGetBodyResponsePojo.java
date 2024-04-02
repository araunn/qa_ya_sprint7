package ru.yandex.sprint7.pojo;

public class OrderGetBodyResponsePojo {
	private String id;
	private String courierId;
	private String firstName;
	private String lastName;
	private String address;
	private String metroStation;
	private String phone;
	private Integer rentTime;
	private String deliveryDate;
	private String track;
	private String[] color;
	private String comment;
	private String createdAt;
	private String updatedAt;
	private String status;
	
	public OrderGetBodyResponsePojo(String id, String courierId, String firstName, String lastName, String address,
			String metroStation, String phone, Integer rentTime, String deliveryDate, String track, String[] color,
			String comment, String createdAt, String updatedAt, String status) {
		super();
		this.id = id;
		this.courierId = courierId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.metroStation = metroStation;
		this.phone = phone;
		this.rentTime = rentTime;
		this.deliveryDate = deliveryDate;
		this.track = track;
		this.color = color;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}
	
	public OrderGetBodyResponsePojo() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourierId() {
		return courierId;
	}
	public void setCourierId(String courierId) {
		this.courierId = courierId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMetroStation() {
		return metroStation;
	}
	public void setMetroStation(String metroStation) {
		this.metroStation = metroStation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRentTime() {
		return rentTime;
	}
	public void setRentTime(Integer rentTime) {
		this.rentTime = rentTime;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
