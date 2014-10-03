package com.redhat.masvida.cobranzas.medicas.common.util;

import java.util.ArrayList;
import java.util.List;

public class RutDigitoVerificadorUtil {
	private static final int DIGITS_AMOUNT = 11;

	private RutDigitoVerificadorUtil() {

	}

	public static int getVerificationDigit(String rut) {

		List<Integer> digits = new ArrayList();
		int beginIndex = rut.length() - 1;
		int digit, obtainedVerificationDigit;
		int total = 0;

		while (rut.length() > 1) {
			digit = Integer.parseInt(rut.substring(beginIndex));
			digits.add(digit);
			rut = rut.substring(0, beginIndex);
			beginIndex--;

		}

		digit = Integer.parseInt(rut);
		digits.add(digit);

		for (int i = 0; i < digits.size(); i++) {
			total += digits.get(i) * ((i % 6) + 2);
		}

		obtainedVerificationDigit = DIGITS_AMOUNT - (total % DIGITS_AMOUNT);

		return obtainedVerificationDigit;
	}
}
