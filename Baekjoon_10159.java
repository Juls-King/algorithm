package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Baekjoon_10159 {
	static int[][] input_ary;	// 입력값 저장 배열
	static int ary_len;			// 입력값 저장 배열의 길이
	static HashSet[] info;		// 해시셋으로 만든 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		input_ary = new int[M][2];
		info = new HashSet[N + 1];
		
		ary_len = input_ary.length;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			input_ary[i][0] = Integer.parseInt(st.nextToken());
			input_ary[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			info[i] = new HashSet<Integer>();
		}

		for (int i = 1; i <= N; i++) {	// i가 비교할 수 있는 모든 수를 찾는다
			findSmaller(i, i);
			findBigger(i, i);
		}

		for (int i = 1; i <= N; i++) {
			System.out.println((N - 1) - info[i].size());	// 최종결과 출력
		}
	}

	@SuppressWarnings("unchecked")
	public static void findSmaller(int m, int n) {	// m: 가벼운 수로 갱신, n: 고정된 값
		for (int i = 0; i < ary_len; i++) {
			int bigger = input_ary[i][0];
			int smaller = input_ary[i][1];
			
			if (bigger == m && !info[n].contains(smaller)) {	// bigger 중 m인 값을 찾았을 때, 동일한 값 없을 때
				info[n].add(smaller);	// set에 추가

				findSmaller(smaller, n);	// 재귀호출
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void findBigger(int m, int n) {	// m: 무거운 수로 갱신, n: 고정된 값
		for (int i = 0; i < ary_len; i++) {
			int bigger = input_ary[i][0];
			int smaller = input_ary[i][1];
			
			if (smaller == m && !info[n].contains(bigger)) {	// smaller 중 m인 값을 찾았을 때, 동일한 값 없을 때
				info[n].add(bigger);	// set에 추가

				findBigger(bigger, n);	//재귀호출
			}
		}
	}
}