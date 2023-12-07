package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15654 {

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
		ary = new int[M * 2];
		nums = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		int i=0;
		for(i=1 ; i<=N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);

		dfs(0);

		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) {
//			ary[2 * M - 1] = "\n";
			sb.append(Arrays.toString(ary));
			sb.append("\n");
			return;
		}

		int i = 0;
		for (i = 1; i <= N; i++) {
			if (visited[i] == false) {
				visited[i] = true;

//				ary[2 * depth] = Integer.toString(nums[i]);
//				ary[2 * depth + 1] = "";
				ary[2 * depth] = nums[i];

				dfs(depth + 1);

				visited[i] = false;
			}
		}
	}
}
