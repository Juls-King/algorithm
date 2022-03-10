package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_1753 {
	
	static Boolean[] visited;
	static int[][] map;
	static int[] info;
	static int V;
	static int E;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[V+1][V+1];
		info = new int[V+1];
		visited = new Boolean[V+1];
		Arrays.fill(visited, false);
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0 ; i<E ; i++) {
			s = br.readLine();
			
			st = new StringTokenizer(s);
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map[u][v] = w;
		}
		
		bfs(K);
		
//		for(int i=1 ; i<=V ; i++) {
//			for(int j=1 ; j<=V ; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	public static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
//		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		q.offer(i);
		visited[i-1] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			
			for (int j=0 ; j<V ; j++) {
				if (map[temp-1][j] == 1 && visited[j] == false) {
					q.offer(j+1);
					visited[j] = true;
				}
			}
		}
	}

}
