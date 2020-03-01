package com.coditas;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.coditas.exception.InvalidFileException;

/**
 * The Class AutomatedTicketingSystemTest.
 */
public class AutomatedTicketingSystemTest {

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
		assertThrows(InvalidFileException.class, () -> {
			AutomatedTicketingSystem.main(new String[] { "parking_lot_file_inputs_invalid" });
		}, () -> AutomatedTicketingSystem.INVALID_FILE_FORMAT);
	}
	
	/**
	 * Tests the app behavior in case of invalid parameters passed at runtime.
	 */
	@Test()
	public void testInvalidParameters() {
		assertThrows(InvalidFileException.class, () -> {
			AutomatedTicketingSystem.main(new String[] { "parking_lot_file_inputs_invalid", "test" });
		}, () -> AutomatedTicketingSystem.INVALID_ARGUMENT_LIST);
	}

}
