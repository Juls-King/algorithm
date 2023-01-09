package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		sb.append(br.readLine());
		
		int M = Integer.parseInt(br.readLine());
		
		int cur = sb.length();
		
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			if(cmd.equals("L") && cur > 0) {
				cur--;
			}
			else if(cmd.equals("D") && cur < sb.toString().length()) {
				cur++;
			}
			else if(cmd.equals("B") && cur > 0) {
				sb.deleteCharAt(cur - 1);
				cur--;
			}
			else if(cmd.equals("P")) {
				String word = st.nextToken();
				
				sb.insert(cur, word);
				cur++;
			}
		}
		
		System.out.println(sb.toString());
	}
}
