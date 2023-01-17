import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> q = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		int memo = 0;
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if(cmd.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				
				q.offer(num);
				memo = num;
			}
			else if(cmd.equals("pop")) {
				if(q.isEmpty() == true) {
					System.out.println("-1");
				}
				else {
					System.out.println(q.poll());
				}
			}
			else if(cmd.equals("size")) {
				System.out.println(q.size());
			}
			else if(cmd.equals("empty")) {
				if(q.isEmpty() == true) {
					System.out.println("1");
				}
				else {
					System.out.println("0");
				}
			}
			else if(cmd.equals("front")) {
				if(q.isEmpty() == true) {
					System.out.println("-1");
				}
				else {
					System.out.println(q.peek());
				}
			}
			else if(cmd.equals("back")) {
				if(q.isEmpty() == true) {
					System.out.println("-1");
				}
				else {
					System.out.println(memo);
				}
			}
		}
	}
}