import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static Boolean[] visited; // 방문여부 확인 배열
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] inputVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		StringTokenizer st = new StringTokenizer(s);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new Boolean[N + 1];
		Arrays.fill(visited, false);

		int[] ary = new int[M];		// 출력을 위한 배열
		inputVal = new int[N + 1];		// 입력값을 담기위한 배열
		
		s = br.readLine();
		st = new StringTokenizer(s);
		
		for(int i=1 ; i<=N ; i++) {
			inputVal[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(inputVal);	// 오름차순 정렬
		
		dfs(0, ary); // 함수 호출

		bw.flush();
		bw.close();
	}

	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력
			for (int a = 0; a < M; a++) {
				bw.write(ary[a] + " ");
			}
			bw.write("\n");

			return;
		}

		for (int j = 1; j <= N; j++) { // 완전 탐색
			if (visited[j] == false) {
				visited[j] = true; // 방문 처리

				ary[depth] = inputVal[j]; // 현재의 inputVal[index]값을 현재의 depth 자리에 대입

				dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}