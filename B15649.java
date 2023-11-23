package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15649 {
	
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];
		
		dfs(N, M, 0);
		
//		System.out.println(N + M);
		
	}
	
	public static void dfs(int n, int m, int depth) {
		if(depth == m) {
			System.out.println();
			return;
		}
		
		
		
		for(int i=0 ; i<n ; i++) {
			if(visited[i] == false) {
				
				visited[i] = true;
				
				dfs(n, m, depth + 1);
				
				visited[i] = false;
			}
		}
		
		
	}

}
