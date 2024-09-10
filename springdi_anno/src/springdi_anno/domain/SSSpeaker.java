package springdi_anno.domain;

public class SSSpeaker implements Speaker {

	@Override
	public void printName() {
		System.out.println("Samsung 스피커");
	}

}
