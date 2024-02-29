package com.ruby.java.ch14.method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class Test16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<Date> date = (d) -> {
			String s = new SimpleDateFormat("YY-MM-dd").format(d);
			System.out.println(s);
		};
		
		date.accept(new Date());
	}

}
