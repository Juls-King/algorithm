import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		
		StringBuilder sb = new StringBuilder();
		
		int j=1;
		
		for(int i=0 ; i<n ; i++) {
			int num = Integer.parseInt(br.readLine());
			
			while(true) {
				if(stack.size() > 0 && stack.peek() == num) {
					sb.append("-\n");
					
					stack.pop();
					break;
				}
				
				if(j == n+1 && stack.size() > 0) {
					System.out.println("NO");
					return;
				}

				stack.push(j);
				j++;
				sb.append("+\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}