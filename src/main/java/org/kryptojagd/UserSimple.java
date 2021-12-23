package org.kryptojagd;

public class UserSimple {  
    private String name;
    private String email;
    private int age;
    private boolean isDeveloper;
    
    public UserSimple (String name, String email, int age, boolean isDeveloper) {
    	this.name = name;
    	this.email = email;
    	this.age = age;
    	this.isDeveloper = isDeveloper;
    }
    
   public String sagHallo() {
	   return "Hallo, ich bin " + name + ". Meine E-Mail ist " + email + ". Ich bin " + age + " Jahre alt."
			   + "Ich bin " + isDeveloper + " Entwickler.";
   }
}