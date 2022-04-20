package appuitest;
import java.util.ArrayList;
import java.util.Scanner;

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


import com.Brian.client.AppUI;

import uk.org.webcompere.systemstubs.SystemStubs;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AppUITest {
	private AppUI appUI;
	
	@BeforeAll
	public void startup() {
		AppUI appUI = new AppUI();
	}
	
	@Test
	public void testhandleUserSelection() throws Exception {
		SystemStubs.withTextFromSystemIn("1").execute(() ->{
			Scanner scanner = new Scanner(System.in);
			int userSelection = AppUI.handleUserSelection(scanner);
			Assertions.assertEquals(1, userSelection);
		});
		
	}
}
