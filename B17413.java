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
		
		StringTokenizer st = new StringTokenizer(str);
		
		boolean flag = false;
		
//		while(st.hasMoreTokens()) {
//			StringBuilder sb = new StringBuilder();
//			sb.append(st.nextToken());
//			sb.reverse();
//			System.out.print(sb.toString() + " ");
//		}
		int index = 0;
		
		for(String s : strAry) {
			index++;
			
			if(s.equals("<") || flag == true) {
				flag = true;
				System.out.print(s);
				continue;
			}
			else if (s.equals(">")) {
				flag = false;
				System.out.print(s);
				continue;
			}
			
			sb.append(s);
			

//			System.out.print(s);
		}
		
		
	}
}
