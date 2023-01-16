import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		Stack<Character> stack1 = new Stack<Character>();
		Stack<Character> stack2 = new Stack<Character>();
		
		String str = br.readLine();
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0 ; i<str.length() ; i++) {
			stack1.push(str.charAt(i));
		}
		
		for(int i=0 ; i<M ; i++) {
			String cmd = br.readLine();
			
			if(cmd.charAt(0) == 'L' && stack1.size() > 0) {
				stack2.push(stack1.pop());
			}
			else if(cmd.charAt(0) == 'D' && stack2.size() > 0) {
				stack1.push(stack2.pop());
			}
			else if(cmd.charAt(0) == 'B' && stack1.size() > 0) {
				stack1.pop();
			}
			else if(cmd.charAt(0) == 'P') {
				stack1.push(cmd.charAt(2));
			}
		}
		
		while(!stack1.empty()) {
			sb.append(stack1.pop());
		}
		while(!stack2.empty()) {
			sb2.append(stack2.pop());
		}
		
		sb.reverse();
		
		System.out.print(sb.toString());
		System.out.print(sb2.toString());
	}
}