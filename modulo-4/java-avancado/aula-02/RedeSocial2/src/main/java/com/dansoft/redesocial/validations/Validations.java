package com.dansoft.redesocial.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
	public boolean keyValidation(String key) {
        if (key.length() < 8) {
            return false;
        }
        
        Pattern digitPattern = Pattern.compile("\\d");
        Matcher digitMatcher = digitPattern.matcher(key);
        if (!digitMatcher.find()) {
            return false;
        }
        
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher specialCharMatcher = specialCharPattern.matcher(key);
        if (!specialCharMatcher.find()) {
            return false;
        }
        
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(key);
        if (!uppercaseMatcher.find()) {
            return false;
        }
        
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Matcher lowercaseMatcher = lowercasePattern.matcher(key);
        if (!lowercaseMatcher.find()) {
            return false;
        }
        
        return true;
    }
	
	public boolean emailValidation(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        
        return emailMatcher.matches();
    }
}
