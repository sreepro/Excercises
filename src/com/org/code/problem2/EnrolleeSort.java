package com.org.code.problem2;

import java.util.Comparator;

public class EnrolleeSort implements Comparator<Enrollee> {

	@Override
	public int compare(Enrollee e1, Enrollee e2) {
		if (e1.getFirstName().compareTo(e2.getFirstName()) == 0) {
			return e1.getLastName().compareTo(e2.getLastName());
		} else {
			return e1.getFirstName().compareTo(e2.getFirstName());
		}
	}
}
