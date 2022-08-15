import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[][] map;
	static boolean[] visited;
	static int result = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0 ; k<N ; k++) {
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		visited[K] = true;
		
//		printMap(map);
		
		dfs(K, 1, 0);
		
		System.out.println(result);
		
	}
	
	public static void dfs(int i, int depth, int temp) throws IOException {
		if (depth == N) { // 최종depth 도달 시
//			System.out.println("temp : " + temp);
			
			if(temp < result) {
				result = temp;
//				System.out.println("result is updated");
			}
			
			return;
		}

		for (int j = 0; j < N; j++) { // 완전 탐색
			if (visited[j] == false) {
				visited[j] = true; // 방문 처리

//				System.out.println(i + " -> " + j + " : " + map[i][j]);
				
				dfs(j, depth+1, temp + map[i][j]); // 재귀호출, 다음 depth로 들어간다

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
	
	public static void printMap(int[][] map) {
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
