// 우주탐사선
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_17182 {
	static int N;
	static int K;
	static int[][] map;
	static boolean[] visited;
	static int min_cost = Integer.MAX_VALUE;

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
		
		// 플로이드-워셜 알고리즘 사용해 모든 정점에서 다른 모든 정점으로의 최소비용 계산
		for(int k=0 ; k<N ; k++) {
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		visited[K] = true;	// 시작점 방문 처리
		
		dfs(K, 1, 0);	// 시작점, 시작뎁스, 비용
		
		System.out.println(min_cost);
		
	}
	
	// dfs를 활용, 모든 경로를 탐색하며 최소비용을 찾는 함수
	public static void dfs(int start, int depth, int cost) throws IOException {
		if (depth == N) { // 최종 뎁스 도달 시
			if(cost < min_cost) {
				min_cost = cost;	// 최소비용으로 갱신
			}
			
			return;
		}

		for (int j = 0; j < N; j++) { // 완전 탐색
			if (visited[j] == false) {
				visited[j] = true; // 방문 처리

				dfs(j, depth+1, cost + map[start][j]); // 도착점, 다음뎁스, 비용 합

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}
