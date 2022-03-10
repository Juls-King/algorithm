package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_15651 {
	static Boolean[] visited; // 방문여부 확인 배열
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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

		bw.flush();
		bw.close();
	}

	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력
			for (int a = 0; a < M; a++) {
//				System.out.print(ary[a] + " ");		// System.out 사용 시 속도가 느려 본 문제에 시간초과 발생
				bw.write(ary[a] + " "); // BufferedWriter 사용 시 통과
			}
			bw.write("\n");

			return;
		}

		for (int j = 1; j <= N; j++) { // 완전 탐색
			ary[depth] = j; // 현재의 index값을 현재의 depth 자리에 대입

			dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다
		}
	}
}
