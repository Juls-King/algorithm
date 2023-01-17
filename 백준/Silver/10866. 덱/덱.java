import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> dq = new ArrayDeque<Integer>();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if(cmd.equals("push_front")) {
				int num = Integer.parseInt(st.nextToken());
				dq.offerFirst(num);
			}
			else if(cmd.equals("push_back")) {
				int num = Integer.parseInt(st.nextToken());
				dq.offerLast(num);
			}
			else if(cmd.equals("pop_front")) {
				if(dq.isEmpty()) {
					System.out.println(-1);
				}
				else {
					System.out.println(dq.pollFirst());
				}
			}
			else if(cmd.equals("pop_back")) {
				if(dq.isEmpty()) {
					System.out.println(-1);
				}
				else {
					System.out.println(dq.pollLast());
				}
			}
			else if(cmd.equals("size")) {
				System.out.println(dq.size());
			}
			else if(cmd.equals("empty")) {
				if(dq.isEmpty()) {
					System.out.println(1);
				}
				else {
					System.out.println(0);
				}
			}
			else if(cmd.equals("front")) {
				if(dq.isEmpty()) {
					System.out.println(-1);
				}
				else {
					System.out.println(dq.peekFirst());
				}
			}
			else if(cmd.equals("back")) {
				if(dq.isEmpty()) {
					System.out.println(-1);
				}
				else {
					System.out.println(dq.peekLast());
				}
			}
		}
	}
}