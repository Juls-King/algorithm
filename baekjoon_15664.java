package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15664 {
	static Boolean[] visited; // 방문여부 확인 배열
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] inputVal;
	static int[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		StringTokenizer st = new StringTokenizer(s);

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new Boolean[N + 1];
		Arrays.fill(visited, false);

		int[] ary = new int[M + 1]; // 출력을 위한 배열
		inputVal = new int[N + 1]; // 입력값을 담기위한 배열
		memo = new int[M + 1];

		s = br.readLine();
		st = new StringTokenizer(s);

		for (int i = 1; i <= N; i++) {
			inputVal[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(inputVal); // 오름차순 정렬

		dfs(0, ary); // 함수 호출

		bw.flush();
		bw.close();
	}

	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력

			String a = Arrays.toString(ary); // 현재 출력할 배열
			String b = Arrays.toString(memo); // 이전에 출력한 배열

			if (a.equals(b)) { // 같다면 탈출(중복처리)
				return;
			}

			for (int i = 1; i <= M; i++) {
				bw.write(ary[i] + " "); // 값 출력
			}
			bw.write("\n");

			memo = Arrays.copyOf(ary, ary.length); // 현재 배열을 memo배열에 저장한

			return;
		}

		for (int j = 1; j <= N; j++) { // 완전 탐색
			if (depth == 0 && inputVal[j - 1] == inputVal[j]) { // 첫번째 값이 중복되지 않도록 처리
				continue;
			}

			if (visited[j] == false) {
				if (depth > 0 && ary[depth] > inputVal[j]) { // 2번째 depth 이상일 때, 두번째 값이 첫번째 값보다 작은 경우 continue
					continue;
				}

				visited[j] = true; // 방문 처리

				ary[depth + 1] = inputVal[j]; // 현재의 inputVal[index]값을 현재의 depth 자리에 대입

				dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다

				visited[j] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}
}