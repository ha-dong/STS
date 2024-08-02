package simpledi.service;

import org.springframework.stereotype.Component;

public class ConsolePrinter implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}//class
