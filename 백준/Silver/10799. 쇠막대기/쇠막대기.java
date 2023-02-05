
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
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
		Stack<Integer> stack2 = new Stack<Integer>();
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		String input = br.readLine();
		
		String[] strAry = input.split("");
		
		int count = 1;
		int sticks = 0;
		int total = 0;
		
		for(int i=0 ; i<strAry.length ; i++) {
			switch(strAry[i]) {
			case "(":
				stack.push(strAry[i]);
				
				if(i < strAry.length-1 && !strAry[i+1].equals(")")) {
					stack2.push(count);
//					System.out.println("stick" + count + " start");
					count++;
					sticks++;
				}
				else if(i < strAry.length-1 && strAry[i+1].equals(")")) {
//					System.out.println("raiser");
					total += sticks;
				}
				
				break;
			case ")":
				if(i > 0 && !strAry[i-1].equals("(")) {
//					System.out.println("stick" + stack2.pop() + " end");
					sticks -= 1;
					total += 1;
				}
				
				if(stack.peek().equals("(")) {
					stack.pop();
					
					
				}
				break;
			}
		}
		
		System.out.println(total);

	}
}