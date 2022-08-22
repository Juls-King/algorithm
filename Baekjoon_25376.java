package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_25376 {
	static boolean visited[];
	static int min_cnt = Integer.MAX_VALUE;
	static int N;
	static int[] switch_status;
	static ArrayList<Integer>[] related_switch;
	static StringBuilder sb;
	static StringBuilder max_bit;
	static int sb_int;
	static int max_bit_int;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		max_bit = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		visited = new boolean[N];

		switch_status = new int[N + 1];
		related_switch = new ArrayList[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {

			related_switch[i] = new ArrayList<Integer>();

			sb.append(st.nextToken());
			max_bit.append("1");
		}

		sb_int = Integer.parseInt(sb.toString(), 2);
		max_bit_int = Integer.parseInt(max_bit.toString(), 2);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= m; j++) {
				related_switch[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		if (sb_int == max_bit_int) {
			System.out.println(0);
			return;
		}

		solution(0);

		if (min_cnt == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min_cnt);
		}

	}

	public static void solution(int count) {
		for (int i = 1; i <= N; i++) { // 완전 탐색

			String test1 = Integer.toBinaryString(sb_int);
//			test1 = String.format("%0" + N + "d", Long.parseLong(test1));

			StringBuilder sb_ = new StringBuilder();
			int test_len = N - test1.length();
			for (int j = 0; j < test_len; j++) {
				sb_.append("0");
			}

			test1 = sb_ + test1;

			if (visited[i - 1] == false && test1.charAt(i - 1) == '0') {
				visited[i - 1] = true; // 방문 처리

				int sb_bak = sb_int;

				switchOn_(i);
				count++;

				if (sb_int == max_bit_int) {
					if (min_cnt > count) {
						min_cnt = count;
					}

					return;
				}

				solution(count);

				visited[i - 1] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다

				sb_int = sb_bak;
			}
		}
	}

	public static void switchOn_(int k) {
		sb_int ^= (1 << (N - k));

		int len = related_switch[k].size();

		for (int i = 0; i < len; i++) {
			int val = related_switch[k].get(i);

			sb_int ^= (1 << (N - val));
		}
	}
}
