package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B17298 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] ary = new int[N];
		ary[N-1] = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		for(int i=0 ; i<N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(i == 0) {
				stack.push(i);
				stack2.push(num);
				continue;
			}
			
			if(stack.isEmpty() == true) {
				stack.push(i);
				stack2.push(num);
				continue;
			}
			else if(stack2.peek() > num) {
				stack.push(i);
				stack2.push(num);
			}
			else if (stack.peek() < num) {
				while(!stack.isEmpty()) {
					ary[stack.pop()] = num;
					stack2.pop();
				}
			}
			
			stack.push(i);
			stack2.push(num);
		}
		
		for(int i=0 ; i<N ; i++) {
			System.out.print(ary[i] + " ");
		}
	}
}
