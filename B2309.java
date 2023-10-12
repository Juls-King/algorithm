package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2309 {
	
//	static int sum = 0;
	static boolean visited[];
	static int N;
	static boolean ended;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = 9;
		
		int[] ary = new int[N];
		
		for(int i=0 ; i<N ; i++) {
			ary[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(ary);
		
		dfs(ary, 0, 0);
	}
	
	public static void dfs(int[] ary, int i, int sum) {
		
		if(ended == true) {
			return;
		}
		else if(sum > 100) {
			return;
		}
		else if(sum == 100) {
			
		}
		
		int num = ary[i];
		
		for (int j=0 ; j<N ; j++) {
			if (visited[j] == false) {
				visited[j] = true;
				
				dfs(ary, i+1);
				
				visited[j] = false;
			}
		}
	}
	
}