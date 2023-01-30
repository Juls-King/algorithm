package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class B10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<String> stack = new Stack<String>();
		
		String input = br.readLine();
		
		String[] strAry = input.split("");
		
		int count = 0;
		
		for(String s : strAry) {
			if(!stack.isEmpty()) {
				if(stack.peek().equals("(") && s.equals(")")) {
					count++;
				}
			}
			
			stack.push(s);
		}
		System.out.println(count);
	}
}
