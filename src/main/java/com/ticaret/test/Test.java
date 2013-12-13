package com.ticaret.test;

import com.ticaret.helpers.Validate;

public class Test {

	public static void main(String[] args) {
		Validate v = new Validate();
		System.out.println(v.isText("zwscsd, -1 _", false));

	}

}
