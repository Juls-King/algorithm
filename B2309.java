package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2309 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = 9;
		
		int[] ary = new int[N];
		
		for(int i=0 ; i<N ; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ary);
		
		System.out.println(Arrays.toString(ary));
	}
	
	public static void dfs(int ary, int num) {
		if(num > 100) {
			return;
		}
		
		if(num == 100) {
			
		}
	}

}