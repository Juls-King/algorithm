package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_9114 {
	static int N;
	static char[][][] map;
	static int[][][] pos;
	static int[] king;
	static int[] pawn;
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new char[N][9][9];
		pos = new int[N][2][2];
		king = new int[2];
		pawn = new int[2];

		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= 8; j++) {
				String str = br.readLine();

				for (int k = 1; k <= 8; k++) {
					map[i][9 - j][k] = str.charAt(k - 1);
				}
			}

			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());

				pos[i][j][0] = Integer.parseInt(st.nextToken());
				pos[i][j][1] = Integer.parseInt(st.nextToken());
			}
		}

		pawn[0] = pos[0][1][0];
		pawn[1] = pos[0][1][1];

		for (int i = 0; i < 4; i++) {
			print(0);
			movePawn();
		}

	}

	public static void movePawn() {
		pawn[0] = pawn[0] + dx[4];
		pawn[1] = pawn[1] + dy[4];
	}

	public static void print(int i) {
		for (int j = 1; j <= 8; j++) {
			for (int k = 1; k <= 8; k++) {
				if ((9-j) == pawn[1] && k == pawn[0]) {
					System.out.print("P");
					continue;
				}

				System.out.print(map[i][9 - j][k]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					System.out.print(map[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					System.out.print(pos[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
