package simpledi.service;

import org.springframework.stereotype.Component;

public class StringPrinter implements Printer {

	private StringBuilder sb = new StringBuilder();
	
	@Override
	public void print(String message) {
		this.sb.append(message);
	}
	
	@Override
	public String toString() {
		return this.sb.toString();
	}

}//class
