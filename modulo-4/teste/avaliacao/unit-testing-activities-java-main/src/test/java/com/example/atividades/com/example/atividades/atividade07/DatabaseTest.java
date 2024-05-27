package com.example.atividades.atividade07;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

class DatabaseTest {
	
    // Verify that the saveUser method of Database is called correctly
    // when a valid user is passed to UserService.

	@Test
    void testSaveUser() {
        Faker faker = new Faker();
        Database mockDatabase = mock(Database.class);
        
        UserService userService = new UserService(mockDatabase);
        
        userService.saveUser(new User(faker.name().fullName(), faker.internet().emailAddress()));
        
        verify(mockDatabase, times(1)).saveUser(any(User.class));
    }

}
