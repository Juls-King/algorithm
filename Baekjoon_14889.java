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
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Boolean[] visited; 	// dfs 함수에서 사용할 방문여부 확인 배열 
	static Boolean[] visited2; 	// dfs2 함수에서 사용할 방문여부 확인 배열
	static int N;				// 직원 수 입력 
	static int M;				// 팀원 수 입력(직원수/2)
	static int[][] map;			// 능력치 표를 담을 테이블 
	static int sum = 0;			// 능력치 합을 계산할 때 사용할 변수
	static int min = 9999999;	// 최소값을 계산할 때 사용할 변수, 임의의 MAX값으로 초기화

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

		int[] ary = new int[M];	// 출력을 위한 배열

		dfs(0, ary); // 함수 호출
		
		bw.write(min + "\n");	// 결과 출력
		
		bw.flush();
		bw.close();
	}

	// 두 팀의 능력치 차이의 최소값을 구하는 함수, N과 M(2) 활용
	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력

			int[] ary2 = new int[M];	// ary에 담겨있지 않은 숫자를 담기위한 배열 
			int index = 0;	// ary에 순서대로 값을 담기위한 index변수
			
			for (int i = 1; i <= N; i++) {
				int count = 0;

				for (int j : ary) {
					if (i == j) {
						count++;
					}
				}

				if (count > 0) {	// count가 0보다 크면 ary에 일치하는 수가 있다는 뜻이므로 continue
					continue;
				}
				
				ary2[index] = i;	// ary에 일치하는 수가 없으면 ary2에 값을 넣는다
				index++;
			}
			
			int[] prt_arr = new int[2];	// 출력을 위한 배열
			
			dfs2(0, prt_arr, M, ary);	// 스타트팀 능력치 계산
			
			int sum_start = sum;	// 스타트팀 능력치 합
			sum = 0;
			
			dfs2(0, prt_arr, M, ary2);	// 링크팀 능력치 계산
			
			int sum_link = sum;		// 링크팀 능력치 합
			sum = 0;
			
			int diff = Math.abs(sum_start - sum_link);	// 두 팀 점수 차이 계산
			
			if(min > diff) {
				min = diff;		// 팀 능력치 차이 최소값 갱신
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
	
	// 팀내 능력치 계산을 위해 두명씩 짝짓는 모든 경우의 수를 구해가며 능력치의 합을 구하는 함수, N과 M(1) 활용
	public static void dfs2(int depth, int[] ary, int n, int[] inputVal) throws IOException {
		if (depth == 2) { // 입력한 depth에 도달 시 출력
			int i = ary[0];
			int j = ary[1];
			
			sum += map[i][j];	// 해당 인덱스로 구한 점수를 sum에 계속 더한다

			return;
		}

		for (int j = 1; j <= n; j++) { // 완전 탐색
			if (visited2[j] == false) {
				visited2[j] = true; // 방문 처리

				ary[depth] = inputVal[j-1]; // 현재의 inputVal[index]값을 현재의 depth 자리에 대입

				dfs2(depth + 1, ary, n, inputVal); // 재귀호출, 다음 depth로 들어간다

				visited2[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}