package me.coconan.mybatis.model;

public class Address {

	private Integer addressId;
	private String streetAddress;
	private Integer personId;

	public Address() {
	}

	public Address(String streetAddress, Integer personId) {
		this.streetAddress = streetAddress;
		this.personId = personId;
	}

	public Integer getPersonId() {
		return personId;
	}

	public String getPersonIdAsString() {
		return String.valueOf(personId);
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	private Person person;

	public Address(int i, String name) {
		this.streetAddress = name;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

}
