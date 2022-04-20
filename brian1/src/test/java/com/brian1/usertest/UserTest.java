package com.brian1.usertest;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Brian.model.Customer;
import com.Brian.model.User;
import com.Brian.service.UserService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserTest {
	
	// object under test
	private User user;
	
	@InjectMocks
	private UserService userService;

	private ArrayList<User> mockUsers;
	/*
	 * Basic set up before running any tests. Need to create an instance
	 */
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.userService = new UserService();
		this.user = new User(1, "testuser", "password", "Joe", "Dirt", "432 Ballard blvd", "Los Angeles", "CA", 32425);
		this.mockUsers = new ArrayList<User>();
		this.mockUsers.add(new User(1, "testuser", "password", "john", "doe", "432 nowhere", "new york", "ny", 32425));
		this.mockUsers.add(new User(2, "testuser2", "password1", "dan", "smith", "440 here", "new york", "il", 32425));
		this.mockUsers.add(new User(3, "testuser3", "password2", "edward", "lee", "670 there", "new york", "ky", 32425));
	}
	
	@Test
	@Disabled
	public void testFindAllUsers() {
		Mockito.when(User.findAll()).thenReturn(this.mockUsers);
		ArrayList<User> testUsers = userService.findAllUsers();
		Assertions.assertEquals(3, testUsers.size());
	}
	
	@Test
	public void testChangeUserName() {
		user.changeUserName("Thor");
		Assertions.assertEquals("Thor", user.getUserName());
	}
	@Test
	public void testChangePassword() {
		user.changePassword("secret!");
		Assertions.assertEquals("secret!", user.getPassword());
	}
	@Test
	public void testChangeFirstName() {
		user.changeFirstName("John");
		Assertions.assertEquals("John", user.getFirstName());
	}
	@Test
	public void testChangelastName() {
		user.changeLastName("Smith");
		Assertions.assertEquals("Smith", user.getLastName());
	}
	@Test
	public void testChangeStreet() {
		user.changeStreet("537 Dee Rd");
		Assertions.assertEquals("537 Dee Rd", user.getStreet());
	}
	@Test
	public void testChangeCity() {
		user.changeCity("Des Plaines");
		Assertions.assertEquals("Des Plaines", user.getCity());
	}
	@Test
	public void testChangeUserState() {
		user.changeState("Washington");
		Assertions.assertEquals("Washington", user.getState());
	}
	@Test
	public void testChangeUserZip() {
		user.changeZip(55501);
		Assertions.assertEquals(55501, user.getZip());
	}
	@Test
	public void testChangeAccessLevel() {
		user.changeAccessLevel(3);
		Assertions.assertEquals(3, user.getAccessLevel());
	}
	@Test
	public void testTrimString() {
		String testString = userService.trimString(" ");
		Assertions.assertEquals("", testString);
	}
	@Test
	public void testTrimString2() {
		String testString = userService.trimString(" hello");
		Assertions.assertEquals("hello",testString);
	}
	@Test
	public void testTrimString3() {
		String testString = userService.trimString("hello ");
		Assertions.assertEquals("hello",testString);
	}
	
	
}
