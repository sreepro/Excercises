package com.org.code.problem1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class ValidateParentheses {

	/**
	 * <pre>
	 * Solution 1: 
	 * List1 contains all the opening parentheses 
	 * List2 contains all the closing parentheses
	 * read the list1 contents with list2 reversed contents and match the opening 
	 * parentheses to the closing parentheses
	 * </pre>
	 * 
	 * @param expr
	 * @return
	 */
	private static boolean validate1(String expr) {
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();

		for (int i = 0; i < expr.length(); i++) {
			char x = expr.charAt(i);

			if (x == '(' || x == '[' || x == '{') {
				l1.add(x + "");
				// continue;
			}
			if (x == ')' || x == ']' || x == '}') {
				l2.add(x + "");
			}
		}

		if (l1.size() != l2.size()) {
			return false;
		}

		Collections.reverse(l2);
		System.out.println(l1.toString() + ":" + l2.toString());
		for (String s1 : l1) {
			String check = "";
			switch (s1) {
			case "(":
				if (check == "}" || check == "]")
					return false;
				break;

			case "{":
				if (check == ")" || check == "]")
					return false;
				break;

			case "[":
				if (check == ")" || check == "}")
					return false;
				break;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * Solution2:
	 * create a stack with storing all the open and close parentheses and 
	 * whenever you find any close parentheses, check the recent stack last value
	 * to check if it is the parentheses are a pair of open and close and exit as soon
	 * as you find it not the case. If you find the match, take out the matched pair from the stack
	 * </pre>
	 * 
	 * @param expr
	 * @return
	 */
	private static boolean validate(String expr) {
		Deque<Character> stack = new ArrayDeque<Character>();

		for (int i = 0; i < expr.length(); i++) {
			char x = expr.charAt(i);

			if (x == '(' || x == '[' || x == '{') {
				stack.push(x);
				continue;
			}

			if (stack.isEmpty())
				return false;
			char check;
			switch (x) {
			case ')':
				check = stack.pop();
				if (check == '{' || check == '[')
					return false;
				break;

			case '}':
				check = stack.pop();
				if (check == '(' || check == '[')
					return false;
				break;

			case ']':
				check = stack.pop();
				if (check == '(' || check == '{')
					return false;
				break;
			}
		}

		return (stack.isEmpty());
	}

	public static void main(String[] args) {
		String expr = "(tst[sesrer{132324 }4 2]3423])";

		// Function call
		if (validate(expr))
			System.out.println("Balanced ");
		else
			System.out.println("Not Balanced ");
	}
}