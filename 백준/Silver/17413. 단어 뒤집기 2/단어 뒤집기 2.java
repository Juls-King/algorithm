import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String[] strAry = str.split("");
		Stack<String> stack = new Stack<String>();

		boolean flag = false;

		for (String s : strAry) {
			if (s.equals(">")) {
				flag = false;
				System.out.print(s);
				continue;
			} else if (s.equals("<") || flag == true) {
				while (!stack.isEmpty()) {
					System.out.print(stack.pop());
				}

				flag = true;
				System.out.print(s);
				continue;
			}

			if (s.equals(" ")) {
				while (!stack.isEmpty()) {
					System.out.print(stack.pop());
				}
				System.out.print(" ");
				continue;
			}

			stack.push(s);
		}

		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}
}