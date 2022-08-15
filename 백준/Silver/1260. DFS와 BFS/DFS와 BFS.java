import java.io.*;
import java.util.*;

public class Main {
    static Boolean[] visited;
	static int[][] map;
	static int N;
    
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		visited = new Boolean[N];
		Arrays.fill(visited, false);
		
		map = new int[N][N];
		
		for(int i=0 ; i<map.length ; i++) {
			Arrays.fill(map[i], 0);
		}
		
		for(int i=0 ; i<M ; i++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x-1][y-1] = 1;
			map[y-1][x-1] = 1;
		}
		
		dfs(V);
		System.out.println();
		
		Arrays.fill(visited, false);
		
		bfs(V);
    }
    
    public static void dfs(int i) {
		visited[i-1] = true;
		System.out.print(i + " ");
		
		for (int j=0 ; j<N ; j++) {
			if (map[i-1][j] == 1 && visited[j] == false) {
				dfs(j+1);
			}
		}
	}
	
	public static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(i);
		visited[i-1] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			
			for (int j=0 ; j<N ; j++) {
				if (map[temp-1][j] == 1 && visited[j] == false) {
					q.offer(j+1);
					visited[j] = true;
				}
			}
		}
	}
}