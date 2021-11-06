package com.listou.listou.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		/*System.out.println(passwordEncoder.encode("usuario123"));
		System.out.println(passwordEncoder.encode("adm123"));
	*/
		 String password = "adm123";
		    String encodedPassword = passwordEncoder.encode(password);

		    System.out.println();
		    System.out.println("Password is         : " + password);
		    System.out.println("Encoded Password is : " + encodedPassword);
		    System.out.println();

		    boolean isPasswordMatch = passwordEncoder.matches(password, "$2a$10$2X6YkF5C6HvJq3XxKxkH4u3HyvQusfUgI4A6/hW63GUBqQuAwV0iy");
		    System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
	}
	
}
