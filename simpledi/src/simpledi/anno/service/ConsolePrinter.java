package simpledi.anno.service;

import org.springframework.stereotype.Component;

@Component("consolePrinter")
public class ConsolePrinter implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}//class
