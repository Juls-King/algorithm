import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0 ; i<T ; i++) {
			String str = br.readLine();
			int str_len = str.length();
			
			if(str_len % 2 != 0 || str.charAt(0) == ')') {
				System.out.println("NO");
				continue;
			}
			
			Stack<Character> stack = new Stack<Character>();
			
			for(int j=0 ; j<str_len ; j++) {
				if(str.charAt(j) == '(') {
					stack.push('(');
				}
				else {
					if(stack.size() == 0) {
						stack.push(')');
						break;
					}
					else {
						if(stack.peek() == '(') {
							stack.pop();
						}
						else {
							stack.push(')');
						}
					}
				}
			}
			
			if(stack.size() == 0) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}
}