package ru.epatko.startui;

//import ru.epatko.startui.StartUI;
import ru.epatko.models.Order;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {

/**
* Testing method: main().
*/	
	@Test
	public void wenCreateAnOrderThenCanPrintOrderName() {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut (new PrintStream(output));

		StartUI start = new StartUI();
		Order order = new Order();
		start.printName(order);
		assertThat(output.toString(), is("default\n"));
	}
}