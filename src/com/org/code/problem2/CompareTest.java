package com.org.code.problem2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CompareTest {

	public static void main(String[] args) {
		List<Enrollee> list = new ArrayList<Enrollee>();

		Enrollee e1 = new Enrollee("102", "sreedahr", "j1", "1", "1");
		Enrollee e2 = new Enrollee("103", "john", "smith", "1", "1");
		Enrollee e3 = new Enrollee("101", "andrew", "l21", "1", "1");
		Enrollee e4 = new Enrollee("105", "andrew", "alpha", "1", "1");
		Enrollee e5 = new Enrollee("106", "andrew", "beta", "1", "1");
		Enrollee e6 = new Enrollee("104", "mark", "l10", "1", "1");
		Enrollee e7 = new Enrollee("104", "mark", "alpha", "1", "1");
		Enrollee e8 = new Enrollee("103", "john", "alpha", "1", "1");

		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		list.add(e5);
		list.add(e6);
		list.add(e7);
		list.add(e8);
//		Collections.sort(list, new EnrolleeSort());
		
		List<Enrollee> list1 = list.stream().sorted(new EnrolleeSort()).collect(Collectors.toList());


		for (Enrollee p : list1) {
			System.out.println(p.getFirstName() + ":" + p.getLastName());
		}
		
		
	}
}
