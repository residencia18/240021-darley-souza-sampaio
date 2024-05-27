package com.example.atividades.atividade07;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.javafaker.Faker;

class UserServiceTest {

    @Mock
    private Database db;

    @InjectMocks
    private UserService userService;
    
	String name;
	String email;
	Faker faker;
	
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

		faker = new Faker();
		name = faker.name().fullName();
		email = faker.internet().emailAddress();
    }

    @Test
    void testSaveUserWithValidUser() {
        User user = new User(name, email);
        
        userService.saveUser(user);
        
        verify(db, times(1)).saveUser(user);
		verifyNoMoreInteractions(db);
    }

    @Test
    void testSaveUserWithNullName() {
        User user = new User(null, email);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user);
        });
        
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains("User must have a name and email"));
        verify(db, never()).saveUser(user);
		verifyNoMoreInteractions(db);
    }

    @Test
    void testSaveUserWithEmptyName() {
        User user = new User("", email);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user);
        });
        
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains("User must have a name and email"));
        verify(db, never()).saveUser(user);
		verifyNoMoreInteractions(db);
    }

    @Test
    void testSaveUserWithNullEmail() {
        User user = new User(name, null);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user);
        });
        
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains("User must have a name and email"));
        verify(db, never()).saveUser(user);
		verifyNoMoreInteractions(db);
    }

    @Test
    void testSaveUserWithEmptyEmail() {
        User user = new User(name, "");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user);
        });
        
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains("User must have a name and email"));
        verify(db, never()).saveUser(user);
		verifyNoMoreInteractions(db);
    }
}
