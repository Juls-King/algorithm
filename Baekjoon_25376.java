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
	
	public static class Node {
		public int bit;
		public int cnt;
		
		public Node(int bit, int cnt) {
			this.bit = bit;
			this.cnt = cnt;
		}
	}

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

			int M = Integer.parseInt(st2.nextToken());

			int temp_bit = 0;

			for (int j = 1; j <= M; j++) {
				int idx = Integer.parseInt(st2.nextToken());
				temp_bit |= (1 << (N - idx));
			}

			related_switch[i] = temp_bit;
		}

		if (bit == max_bit) {
			System.out.println(0);
			return;
		}

		bfs(bit);

		if (min_cnt == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min_cnt);
	}
	
	public static void bfs(int bit) {
		Queue<Node> q = new LinkedList<>();

		q.offer(new Node(bit, 0));
//		String test3 = Integer.toBinaryString(bit);
//		test3 = String.format("%0" + N + "d", Long.parseLong(test3));
//		System.out.println("ofer : " + test3);

		while (!q.isEmpty()) {
			Node node = q.poll();
			int l_bit = node.bit;
			int l_cnt = node.cnt;
			
//			String test = Integer.toBinaryString(l_bit);
//			test = String.format("%0" + N + "d", Long.parseLong(test));
//			System.out.println("poll : " + test);
			
//			if (l_bit == max_bit) {
//				min_cnt = l_cnt;
//				break;
//			}

			int t_bit = 0;
			
			for (int i = 1; i <= N; i++) {
				int bit_chk = l_bit & (1 << (N - i));
				
				if (bit_chk == 0) {
					t_bit = switchOn(i, l_bit);
					
					if(visited[t_bit] == false) {
						visited[t_bit] = true;
						
						if (t_bit == max_bit) {
							min_cnt = l_cnt + 1;
							return;
						}
						
						q.offer(new Node(t_bit, l_cnt + 1));
//						String test2 = Integer.toBinaryString(t_bit);
//						test2 = String.format("%0" + N + "d", Long.parseLong(test2));
//						System.out.println("ofer : " + test2);
					}
				}
			}
		}
	}

	public static int switchOn(int k, int bit) {
		bit |= (1 << (N - k));
		bit ^= related_switch[k];
		
		return bit;
	}
}