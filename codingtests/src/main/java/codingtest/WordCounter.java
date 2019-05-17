package codingtest;

class Program {

	String exec(String command, String text) { // keep this method
		if (!"CountWords".equals(command)) {
			throw new IllegalArgumentException(command);
		}

		return WordCounter.count(text);
	}
}

class Program2 {
	Program p = new Program();

	String exec(String command, String text) { // keep this method
		if ("CountWords".equals(command)) {
			return p.exec(command, text);
		}

		return WordCounter.count(text);
	}
}

class ProgramTester {
	Program2 p;

	void init() {
		p = new Program2();
	}

	void testCountWords() {
		String r = p.exec("CountWords", "Yes we code");
		assert (r.equals("3"));
	}
}

public class WordCounter {
	public static String count(String str) {
		return String.valueOf(str.split(" ").length);
	}
}
