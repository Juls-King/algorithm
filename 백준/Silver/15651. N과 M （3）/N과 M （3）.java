import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static int N, M;
	static char[] ary;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		ary = new char[M * 2];

		dfs(0);

		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) {
			ary[2 * M - 1] = '\n';
			sb.append(ary);
			return;
		}

		int i = 0;
		for (i = 1; i <= N; i++) {
			ary[2 * depth] = (char) (i + '0');
			ary[2 * depth + 1] = ' ';

			dfs(depth + 1);
		}
	}
}