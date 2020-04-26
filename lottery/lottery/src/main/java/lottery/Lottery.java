package lottery;

public class Lottery {

	/*
	 * execute() - method (run loto once)
	 */
	private void execute() {

		LotoFactory lotoFactory = new LotoFactory();

		LotoNumbers lotoNumbers1 = lotoFactory.getNumbers("regular");
		lotoNumbers1.generate();

		LotoNumbers lotoStrong1 = lotoFactory.getNumbers("strong");
		lotoStrong1.generate();
	}

//	/*
//	 * Lotto() --> constructor. (run once)
//	 */
//	public Lottery() {
//		LotoFactory lotoFactory = new LotoFactory();
//		
//		LotoNumbers lotoNumbers1 = lotoFactory.getNumbers("regular");
//		lotoNumbers1.generate();
//		
//		LotoNumbers lotoStrong1 = lotoFactory.getNumbers("strong");
//		lotoStrong1.generate();
//	}

	/*
	 * main() - lets start.
	 * 
	 * Lotto program uses the technology : design pattern - "Factory Pattern".
	 */
	public static void main(String[] args) {

		Lottery lottery = new Lottery();
		String add = "0";
		System.out.println("Israel lottery - Generate a Random Numbers: ");


//		System.out.println("");
		for (int i = 0; i < 14; i++) {
			System.out.println("");
			System.out.printf("..%s%d..", add, (i + 1)); //index number: ..XX.. //
			lottery.execute(); 

			// add "0" to number smaller then 10.
			if ((i + 1) < 9)
				add = "0";
			else
				add = "";
		}

	}

}
