import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int N, M;
	static int[] ary;
	static StringBuilder sb = new StringBuilder();
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		ary = new int[M];
		nums = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		int i = 0;
		for (i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		dfs(0);

		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) {
			for (int i : ary) {
				sb.append(i).append(" ");
			}

			sb.append("\n");

			return;
		}

		int i = 0;
		for (i = 1; i <= N; i++) {
			if (visited[i] == false) {
				if (depth > 0 && ary[depth - 1] > nums[i]) {
					continue;
				}

				ary[depth] = nums[i];

				dfs(depth + 1);
			}
		}
	}
}