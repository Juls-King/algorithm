import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N;
	static int M;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = 9;
		M = Integer.parseInt(br.readLine());

		int[] ary = new int[M];

		dfs(0, ary); // 함수 호출
		
		System.out.println(set.size());
	}

	public static void dfs(int depth, int[] ary) throws IOException {
		if (depth == M) { // 입력한 depth에 도달 시 출력
			int temp = 1;
			
			for (int i = 0; i < M; i++) {
				temp *= ary[i];
			}
			
			set.add(temp);

			return;
		}

		for (int i = 1; i <= N; i++) { // 완전 탐색
			if (depth > 0 && ary[depth - 1] > i) { // 2번째 depth 이상일 때, 두번째 값이 첫번째 값보다 작은 경우 continue
				continue;
			}

			ary[depth] = i; // 현재의 index값을 현재의 depth 자리에 대입

			dfs(depth + 1, ary); // 재귀호출, 다음 depth로 들어간다
		}
	}
}