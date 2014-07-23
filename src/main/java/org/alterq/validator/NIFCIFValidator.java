package org.alterq.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIFCIFValidator {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public boolean isValidCIF(String cif) {

		if(StringUtils.isBlank(cif))
			return false;
		
		Pattern cifPattern = Pattern.compile("([ABCDEFGHJNPQRSUVWabcdefghjnpqrsuvw])(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)([abcdefghijABCDEFGHIJ0123456789])");

		Matcher m = cifPattern.matcher(cif);

		if (m.matches()) {

			if (log.isDebugEnabled())

				log.debug("CIF match the pattern:");

			// Add positions 3, 5, and 7

			int addEven = Integer.parseInt(m.group(3)) + Integer.parseInt(m.group(5)) + Integer.parseInt(m.group(7));

			if (log.isDebugEnabled())

				log.debug("Even sum: " + addEven);

			// Multiply by 2 positions 2,4,6,8 and add the digits (/10 and %10)

			int add2 = ((Integer.parseInt(m.group(2)) * 2) % 10) + ((Integer.parseInt(m.group(2)) * 2) / 10);

			int add4 = ((Integer.parseInt(m.group(4)) * 2) % 10) + ((Integer.parseInt(m.group(4)) * 2) / 10);

			int add6 = ((Integer.parseInt(m.group(6)) * 2) % 10) + ((Integer.parseInt(m.group(6)) * 2) / 10);

			int add8 = ((Integer.parseInt(m.group(8)) * 2) % 10) + ((Integer.parseInt(m.group(8)) * 2) / 10);

			// Add positions 2,4,6,7

			int addOdd = add2 + add4 + add6 + add8;

			if (log.isDebugEnabled())

				log.debug("Odd sum: " + addOdd);

			int addition = addEven + addOdd;

			int control = 10 - (addition % 10);

			// The string starts with 0, J is 0 (not 10)

			if (control == 10)

				control = 0;

			String characters = "JABCDEFGHI";

			if (log.isDebugEnabled())

				log.debug("Control: " + control + " or " + characters.substring(control, control + 1));

			// Control Digit is a char

			if (m.group(1).equalsIgnoreCase("N") || m.group(1).equalsIgnoreCase("P")

			|| m.group(1).equalsIgnoreCase("Q") || m.group(1).equalsIgnoreCase("R")

			|| m.group(1).equalsIgnoreCase("S") || m.group(1).equalsIgnoreCase("W")) {

				if (log.isDebugEnabled())

					log.debug("Have to be a character");

				if (m.group(9).equalsIgnoreCase(characters.substring(control, control + 1)))

					return true;

				else

					return false;

			}

			// Control Digit is an integer

			else if (m.group(1).equalsIgnoreCase("H") || m.group(1).equalsIgnoreCase("J")

			|| m.group(1).equalsIgnoreCase("U") || m.group(1).equalsIgnoreCase("V")) {

				if (log.isDebugEnabled())

					log.debug("Have to be a number");

				if (m.group(9).equalsIgnoreCase("" + control))

					return true;

				else

					return false;

			}

			// Control Digit is an integer or a char

			else {

				if (m.group(9).equalsIgnoreCase(characters.substring(control, control + 1))

				|| m.group(9).equalsIgnoreCase("" + control))

					return true;

				else

					return false;

			}

		} else

			return false;

	}

	/**
	 * 
	 * Checks whether the string is a valid DNI
	 * 
	 * 
	 * 
	 * @param nif
	 * 
	 * @return
	 */

	public boolean isValidNIF(String nif) {
		
		if(StringUtils.isBlank(nif))
			return false;

		Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher m = nifPattern.matcher(nif);

		if (m.matches()) {
			String[] nifArray = new String[2];
			nifArray[0] = nif.substring(0, nif.length() - 1);
			nifArray[1] = nif.substring(nif.length() - 1, nif.length());
			int character = (Integer.valueOf(nifArray[0]).intValue()) % 23;
			String[] validChars = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E", "T" };
			if (validChars[character].compareToIgnoreCase(nifArray[1]) == 0)
				return true;
		}

		return false;

	}

}
