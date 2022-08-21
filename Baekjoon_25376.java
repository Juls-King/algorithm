package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
//			switch_status[i] = Integer.parseInt(st.nextToken());

			related_switch[i] = new ArrayList<Integer>();
			
//			sb.append(switch_status[i]);
			
			sb.append(st.nextToken());
			max_bit.append("1");
		}
		
		sb_int = Integer.parseInt(sb.toString(), 2);
		max_bit_int = Integer.parseInt(max_bit.toString(), 2);
//		System.out.println("sb : " + sb);
//		System.out.println("max_bit : " + max_bit);
//		System.out.println("sb_int : " + sb_int);
//		System.out.println("max_bit_int : " + max_bit_int);
		
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
			test1 = String.format("%0" + N + "d", Integer.parseInt(test1));
//			System.out.println("test1 : " + test1);
			
//			System.out.println("test : " + test);
//			System.out.println("test == '0' : " + (test == '0'));
			
			if (visited[i - 1] == false && test1.charAt(i-1) == '0') {
				visited[i - 1] = true; // 방문 처리

//				int[] temp = switch_status.clone();
//				StringBuilder sb_bak = sb;
				int sb_bak = sb_int;
				
				switchOn_(i);
				
				count++;
				
//				print();
//				System.out.println(Integer.toBinaryString(sb_int));

//				if (lightAll()) {
				if (sb_int == max_bit_int) {
					if (min_cnt > count) {
						min_cnt = count;
					}
					
//					System.out.println("lightAll : " + count);
					return;
				}

				solution(count);

				visited[i - 1] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
				
//				switch_status = temp.clone();
				sb_int = sb_bak;
			}
		}
	}

	public static boolean lightAll() {
		int count = 0;

		for (int i = 1; i <= N; i++) {
			if (switch_status[i] == 1) {
				count++;
			}
		}

		if (count == N) {
			return true;
		}

		return false;
	}

//	public static void switchOn(int k) {
//		switch_status[k] = 1;
//
//		int len = related_switch[k].size();
//		for (int i = 0; i < len; i++) {
//			int val = related_switch[k].get(i);
//
//			switch_status[val] = (switch_status[val] == 0) ? 1 : 0;
//		}
//	}
	
	public static void switchOn_(int k) {
		sb_int ^= (1 << (N-k));

		int len = related_switch[k].size();
		
		for (int i = 0; i < len; i++) {
			int val = related_switch[k].get(i);
			
			sb_int ^= (1 << (N-val));
		}
	}

//	public static void print() {
//		for (int i = 1; i <= N; i++) {
//			System.out.print(switch_status[i] + " ");
//		}
//		System.out.println();
//	}
}
