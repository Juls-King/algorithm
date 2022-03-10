// 스타트와 링크
package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_14889 {
	static Boolean[] visited; // 방문여부 확인 배열
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int[][] map;
	
	static int sum = 0;
	static Boolean[] visited2; // 방문여부 확인 배열
	
	static int min = 9999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = N / 2;
		
		map = new int[N+1][N+1];
		
		for(int i=1 ; i<=N ; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			for(int j=1 ; j<=N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new Boolean[N + 1];
		Arrays.fill(visited, false);
		
		visited2 = new Boolean[N + 1];
		Arrays.fill(visited2, false);

		int[] ary = new int[M];

		dfs(0, ary); // 함수 호출
		
		bw.write(min + "\n");
		
		bw.flush();
		bw.close();
	}

	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력

			int[] ary2 = new int[M];
			int index = 0;
			
			for (int a = 1; a <= N; a++) {
				int temp = 0;

				for (int b : ary) {
					if (b == a) {
						temp++;
					}
				}

				if (temp > 0) {
					continue;
				}
				
				ary2[index] = a;
				index++;
			}
			
			int[] arry = new int[2];
			calc(0, arry, M, ary);
			
			int sum_a = sum;
			sum = 0;
			
			calc(0, arry, M, ary2);
			
			int sum_b = sum;
			sum = 0;
			
			int aa = Math.abs(sum_a - sum_b);
			
			if(min > aa) {
				min = aa;
			}
			
			return;
		}

		for (int j = 1; j <= N; j++) { // 완전 탐색
			if (visited[j] == false) {
				if (depth > 0 && ary[depth - 1] > j) { // 2번째 depth 이상일 때, 두번째 값이 첫번째 값보다 작은 경우 continue
					continue;
				}

				visited[j] = true; // 방문 처리

				ary[depth] = j; // 현재의 index값을 현재의 depth 자리에 대입

				if (ary[0] > 1) {	// 1로 시작하는 순열만 필요하다
					return;
				}

				dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
	
	public static void calc(int depth, int[] ary, int n, int[] inputVal) throws IOException {
		if (depth == 2) { // 입력한 depth에 도달 시 출력
			int i = ary[0];
			int j = ary[1];
			
			sum += map[i][j];

			return;
		}

		for (int j = 1; j <= n; j++) { // 완전 탐색
			if (visited2[j] == false) {
				visited2[j] = true; // 방문 처리

				ary[depth] = inputVal[j-1]; // 현재의 inputVal[index]값을 현재의 depth 자리에 대입

				calc(depth + 1, ary, n, inputVal); // 재귀호출, 다음 depth로 들어간다

				visited2[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}