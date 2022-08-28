package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_25376 {
	static boolean visited[];
	static int min_cnt = Integer.MAX_VALUE;
	static int N;
	static int[] related_switch;
	static int bit = 0;
	static int max_bit = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st, st2;

		N = Integer.parseInt(br.readLine());

		visited = new boolean[N];

		related_switch = new int[N + 1];

		st = new StringTokenizer(br.readLine());
//		for (int i = 1; i <= N; i++) {
//			
//		}

		max_bit = (max_bit | (1 << N)) - 1;

		for (int i = 1; i <= N; i++) {
			if (st.nextToken().equals("1")) {
				bit |= (1 << (N - i));
			}
			
			st2 = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st2.nextToken());

			int temp_bit = 0;

			for (int j = 1; j <= m; j++) {
				int idx = Integer.parseInt(st2.nextToken());
				temp_bit |= (1 << (N - idx));
			}

			related_switch[i] = temp_bit;
		}

		if (bit == max_bit) {
			System.out.println(0);
			return;
		}

		solution(0);

		if (min_cnt == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min_cnt);
	}

	public static void solution(int count) {
		for (int i = 1; i <= N; i++) { // 완전 탐색

			int bit_check = bit & (1 << (N - i));

			if (visited[i - 1] == false && bit_check == 0) {
				visited[i - 1] = true; // 방문 처리

				int bit_bak = bit;

				switchOn(i);
				count++;

				if (count > min_cnt) {
					return;
				}

				if (bit == max_bit) {
					if (min_cnt > count) {
						min_cnt = count;
					}

					return;
				}

				solution(count);

				visited[i - 1] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다

				bit = bit_bak;
			}
		}
	}

	public static void switchOn(int k) {
		bit |= (1 << (N - k));
		bit ^= related_switch[k];
	}
}
