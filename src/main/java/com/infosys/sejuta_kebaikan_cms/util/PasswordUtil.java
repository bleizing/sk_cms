package com.infosys.sejuta_kebaikan_cms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	public static String hashPassword(String password) {
		String hashedPassword = "";
		if (!password.equals("")) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			hashedPassword = passwordEncoder.encode(password);
		}
		return hashedPassword;
	}
}
