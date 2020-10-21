package com.synergisticit.utilities;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(int id) {
		super("No User with id "+ id);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
