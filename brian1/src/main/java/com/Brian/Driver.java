package com.Brian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.Brian.client.AppUI;
import com.Brian.client.AppUIEmployee;
import com.Brian.model.Customer;
import com.Brian.model.Employee;
import com.Brian.model.Hotdog;
import com.Brian.model.HotdogTemplate;
import com.Brian.model.User;
import com.Brian.respository.HotdogRespositoryImp;


public class Driver {
	
	
	public static void main(String[] args) {
		AppUI.appStart();
	}
}
