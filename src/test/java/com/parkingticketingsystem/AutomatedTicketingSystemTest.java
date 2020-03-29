package com.parkingticketingsystem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * The Class AutomatedTicketingSystemTest.
 */
public class AutomatedTicketingSystemTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/**
	 * Tests whether the input file has valid data.
	 */
	@Test
	public void testValidInputFile() {
		String fileName = AutomatedTicketingSystemTest.class.getClass().getResource("/parking_lot_file_inputs_valid.txt")
				.getFile();
		AutomatedTicketingSystem.main(new String[] { fileName });
	}


	/**
	 * Tests the app behavior in case of invalid parameters passed at runtime.
	 */
	@Test
	public void testInvalidParameters() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(AutomatedTicketingSystem.INVALID_ARGUMENT_LIST);
		AutomatedTicketingSystem.main(new String[] {});
	}

}
