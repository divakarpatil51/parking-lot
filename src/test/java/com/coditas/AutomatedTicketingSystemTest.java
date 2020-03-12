package com.coditas;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.coditas.exception.InvalidFileException;

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
		String fileName = AutomatedTicketingSystemTest.this.getClass().getResource("/parking_lot_file_inputs.txt")
				.getFile();
		AutomatedTicketingSystem.main(new String[] { fileName });
	}

	/**
	 * Tests the app behavior in case of invalid input file extension.
	 */
	@Test
	public void testInvalidInputFileExtension() {
		expectedException.expect(InvalidFileException.class);
		expectedException.expectMessage(AutomatedTicketingSystem.INVALID_FILE_FORMAT);
		AutomatedTicketingSystem.main(new String[] { "parking_lot_file_inputs_invalid" });
	}

	/**
	 * Tests the app behavior in case of invalid parameters passed at runtime.
	 */
	@Test
	public void testInvalidParameters() {
		expectedException.expect(InvalidFileException.class);
		expectedException.expectMessage(AutomatedTicketingSystem.INVALID_ARGUMENT_LIST);
		AutomatedTicketingSystem.main(new String[] {});
	}

}
