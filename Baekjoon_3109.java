package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_3109 {
	static int[][] visited;	// 방문여부 확인 배열
	static char[][] map;	// 맵 정보 담을 배열

	static int R;	// 행
	static int C;	// 열

	static int count = 0;	// 최종 결과 값

	static int dx[] = { -1, 0, 1 }; // 배열 상하 탐색
	static int dy[] = { 1, 1, 1 }; // 배열 좌우 탐색

	static int flag = 0;	// 파이프가 연결되었는지 판별하는 flag

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new int[R][C];
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			dfs(i, 0);	// 함수 호출

			flag = 0;	// flag 초기화
		}

		System.out.println(count);
	}

	// dfs 알고리즘을 기반한 함수
	public static void dfs(int i, int j) {
		visited[i][j] = 1;	// 방문 표시

		if (j == C - 1) {	// 파이프 연결 되었을 때
			count++;	// 최종 결과 count 증가

			flag = 1;	// 파이프 연결 flag 변경

			return;
		}

		// map 인덱스 벗어났는지 체크, 이미 방문한 곳인지 체크, 방문할 곳에 건물이 있는지 체크, 현재 dfs에서 파이프가 연결되었었는지 체크 
		if (i - 1 >= 0 && visited[i + dx[0]][j + dy[0]] == 0 && map[i + dx[0]][j + dy[0]] != 'x' && flag != 1) {
			dfs(i + dx[0], j + dy[0]);
		}

		// 이미 방문한 곳인지 체크, 방문할 곳에 건물이 있는지 체크, 현재 dfs에서 파이프가 연결되었었는지 체크 
		if (visited[i + dx[1]][j + dy[1]] == 0 && map[i + dx[1]][j + dy[1]] != 'x' && flag != 1) {
			dfs(i + dx[1], j + dy[1]);
		}

		// map 인덱스 벗어났는지 체크, 이미 방문한 곳인지 체크, 방문할 곳에 건물이 있는지 체크, 현재 dfs에서 파이프가 연결되었었는지 체크 
		if (i + 1 < R && visited[i + dx[2]][j + dy[2]] == 0 && map[i + dx[2]][j + dy[2]] != 'x' && flag != 1) {
			dfs(i + dx[2], j + dy[2]);
		}
	}
}
