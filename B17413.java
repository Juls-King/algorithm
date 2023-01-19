package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17413 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String[] strAry = str.split("");
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean flag = false;
		
		for(String s : strAry)  {
			if(s.equals("<")) {
				if(sb.length() != 0) {
					st = new StringTokenizer(sb.toString());
					
					while(st.hasMoreTokens()) {
						StringBuilder sb2 = new StringBuilder();
						
						sb2.append(st.nextToken());
						sb2.reverse();
						System.out.print(sb2.toString());
						
						if(st.hasMoreTokens() == true) {
							System.out.print(" ");
						}
					}
					
//					System.out.print(sb.toString());
					sb.setLength(0);
				}
				
				System.out.print(s);
				
				flag = true;
			}
			else if(s.equals(">")) {
//				sb.append(s);
				System.out.print(s);
				
				flag = false;
			}
			else if (flag == true) {
				System.out.print(s);
			}
			else if (flag == false) {
				sb.append(s);
			}
			
			
			
		}
	}
}
