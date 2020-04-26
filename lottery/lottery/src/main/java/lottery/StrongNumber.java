package lottery;

import java.util.Random;

public class StrongNumber implements LotoNumbers {

	private int min = 1;
	private int max = 7;
	private int NumberGenerated = 0;

	public int getNumberGenerated() {
		return NumberGenerated;
	}

	public void setNumberGenerated(int numberGenerated) {
		NumberGenerated = numberGenerated;
	}

	@Override
	public void generate() {

		Random r = new Random();
		setNumberGenerated(r.nextInt((max - min) + 1) + min);

//		System.out.print(getNumberGenerated());
		System.out.printf("......[%2d]", getNumberGenerated());

	}

}
