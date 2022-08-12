import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Boolean[] visited; // 방문여부 확인 배열
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		StringTokenizer st = new StringTokenizer(s);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new Boolean[N + 1];
		Arrays.fill(visited, false);

		int[] ary = new int[M];

		dfs(0, ary); // 함수 호출
	}

	public static void dfs(int depth, int[] ary) {
		if (depth == M) { // 입력한 depth에 도달 시 출력
			for (int a = 0; a < M; a++) {
				System.out.print(ary[a] + " ");
			}
			System.out.println();

			return;
		}

		for (int j = 1; j <= N; j++) { // 완전 탐색
			if (visited[j] == false) {
                if(depth > 0 && ary[depth-1] > j) {	// 2번째 depth 이상일 때, 두번째 값이 첫번째 값보다 작은 경우 continue  
					continue;
				}
                
				visited[j] = true; // 방문 처리

				ary[depth] = j; // 현재의 index값을 현재의 depth 자리에 대입

				dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}