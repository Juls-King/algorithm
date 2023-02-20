import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
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
			else if(stack2.peek() < num){
				while(!stack2.isEmpty() && stack2.peek() < num) {
					int temp = stack2.pop();
					
					if(temp < num) {
						ary[stack.pop()] = num;
					}
					else {
						ary[stack.pop()] = -1;
					}
				}
				
//				continue;
			}
			
			if(i == N-1) {
				while(!stack.isEmpty()) {
					ary[stack.pop()] = -1;
					stack2.pop();
				}
			}
			else {
				stack.push(i);
				stack2.push(num);
				continue;
			}
			
			
			stack.push(i);
			stack2.push(num);
		}
		
		for(int i=0 ; i<N ; i++) {
//			System.out.print(ary[i] + " ");
//			bw.write(ary[i] + " ");
			if(i == N-1) {
				sb.append(ary[i]);
				continue;
			}
			sb.append(ary[i] + " ");
		}
		
//		bw.flush();
//		bw.close();
		System.out.print(sb.toString());
	}
}