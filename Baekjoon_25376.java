package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_25376 {
	static boolean visited[];
	static int min_cnt = Integer.MAX_VALUE;
	static int N;
	static int[] related_switch;
	static int max_bit = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st, st2;

		int bit = 0;
		
		N = Integer.parseInt(br.readLine());

		visited = new boolean[(int) Math.pow(2, N)];

		related_switch = new int[N + 1];

		st = new StringTokenizer(br.readLine());

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

//		solution(0, bit);
		bfs(bit);

		if (min_cnt == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min_cnt);
	}
	
	public static void bfs(int bit) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(bit);
//		visited[bit] = true;

		while (!q.isEmpty()) {
			int count = 0;
			int temp = q.poll();
//			System.out.print(temp + " ");

			for (int i = 1; i <= N; i++) {
				int bit_check = bit & (1 << (N - i));
				
				if (visited[bit] == false && bit_check == 0) {
					visited[bit] = true;
					
					bit = switchOn(i, bit);
					count++;
					
					if (count > min_cnt) {
						break;
					}

					if (bit == max_bit) {
						if (min_cnt > count) {
							min_cnt = count;
						}

						break;
					}
					
					q.offer(bit);
				}
			}
		}
	}

	public static void solution(int count, int bit) {
		
		for (int i = 1; i <= N; i++) { // 완전 탐색

			int bit_check = bit & (1 << (N - i));
			
			if(visited[bit] == true) {
				return;
			}

			if (visited[bit] == false && bit_check == 0) {
				visited[bit] = true; // 방문 처리

				bit = switchOn(i, bit);
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

				solution(count, bit);

//				visited[bit] = false; // 다음 depth 완료 후 방문여부를 초기화 해줘야 모든 경우에수 탐색 할 수 있다
			}
		}
	}

	public static int switchOn(int k, int bit) {
		bit |= (1 << (N - k));
		bit ^= related_switch[k];
		
		return bit;
	}
}
