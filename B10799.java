package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class B10799 {
	public static class Stick {
		public int index;
		
		public Stick(int index) {
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<String> stack = new Stack<String>();
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		String input = br.readLine();
		
		String[] strAry = input.split("");
		
		int count = 0;
		
		for(String s : strAry) {
			switch(s) {
			case "(":
				stack.push(s);
				
				
				
				alist.add(0);
				break;
			case ")":
				if(stack.peek().equals("(")) {
					stack.pop();
				}
				break;
			}
		}
		


	}
}
