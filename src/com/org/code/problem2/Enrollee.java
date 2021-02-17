package com.org.code.problem2;

public class Enrollee {

	private String userId;

	private String firstName;

	private String lastName;

	private String version;

	private String insCompany;

	public Enrollee(String userId, String firstName, String lastName, String version, String insCompany) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.version = version;
		this.insCompany = insCompany;
	}

	public String getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getVersion() {
		return version;
	}

	public String getInsCompany() {
		return insCompany;
	}

	public String toString() {
		String format = "%s,%s,%s,%s,%s";
		return String.format(format, userId, firstName, lastName, version, insCompany);
	}

}
