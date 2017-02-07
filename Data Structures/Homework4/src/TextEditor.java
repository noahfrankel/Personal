import java.util.Scanner;

public class TextEditor {

	public static void main(String[] args) {
		StackArray textStack = new StackArray();
		boolean isOpen = true;
		while (isOpen == true) {
			Scanner scannerInput = new Scanner(System.in);
			String text = scannerInput.nextLine();
			if (text.equalsIgnoreCase("#exit")) {
				isOpen = false;
			}
			else if (text.equalsIgnoreCase("#print")) {
				textStack.print();
			}
			else if (text.contains("#delete") && text.length() > 7) {
				textStack.deleteX(findLine(text));
			}
			else if (text.contains("#print") && text.length() > 6) {
				textStack.printX(findLine(text));
			}
			else {
				textStack.push(text);
			}
		}
	}
	public static int findLine(String text) {
		String result = "";
		for (int i=0; i<text.length(); i++) {
			Character chars = text.charAt(i);
			if (Character.isDigit(chars)) {
				result += chars;
			}
		}
		return Integer.parseInt(result);
	}
	
}
