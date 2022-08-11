import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };

	static Horse[] horse;

	public static class Horse {
		public int x;
		public int y;
		public int dir;

		public Horse(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][N + 1];

		horse = new Horse[K];

		@SuppressWarnings("unchecked")
		ArrayList<Integer>[][] alist = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				alist[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			horse[i] = new Horse(x, y, dir);

			alist[x][y].add(i + 1);
		}

//      t_print(alist);

		int count = 0;

		for (int i = 0; i < 1000; i++) {
			count++;

			if (move(N, K, map, alist)) {
				break;
			}
		}

		if (count < 1000) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}
	}

	static public void t_print(ArrayList<Integer>[][] alist) {
		for (ArrayList<Integer>[] i : alist) {
			for (ArrayList<Integer> j : i) {
				if (j == null) {
					continue;
				}

				System.out.print(j + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	static public boolean move(int N, int K, int[][] map, ArrayList<Integer>[][] alist) {

		for (int i = 0; i < K; i++) {
			int x = horse[i].x;
			int y = horse[i].y;
			int dir = horse[i].dir;

			int index = alist[x][y].indexOf(i + 1);

			int size = alist[x][y].size();

			ArrayList<Integer> l_list = new ArrayList<Integer>(alist[x][y].subList(index, size));

			if (index == 0) {
				alist[x][y].clear();
			} else {
				alist[x][y] = new ArrayList<Integer>(alist[x][y].subList(0, index));
			}

			boolean isMoved = false;

			if ((x + dx[dir] < 1 || x + dx[dir] > N || y + dy[dir] < 1 || y + dy[dir] > N)
					|| map[x + dx[dir]][y + dy[dir]] == 2) { // 이동할 위치가 외부 또는 파란색
				
				if (dir == 1 || dir == 3) { // 방향 바꿔주기
					dir += 1;
				} else {
					dir -= 1;
				}

				horse[i].dir = dir;
				
				if (!((x + dx[dir] < 1 || x + dx[dir] > N || y + dy[dir] < 1 || y + dy[dir] > N)
						|| map[x + dx[dir]][y + dy[dir]] == 2)) {

					x += dx[dir];
					y += dy[dir];

					isMoved = true;
				}
			} else {
				x += dx[dir];
				y += dy[dir];

				isMoved = true;
			}
			
			if(isMoved) {
				for (int j = 0; j < l_list.size(); j++) {
					int element = l_list.get(j);

					horse[element - 1].x = x;
					horse[element - 1].y = y;
				}
			}
			      
			if (map[x][y] == 1 && isMoved == true) {
				Collections.reverse(l_list);
			}

			alist[x][y].addAll(l_list);

			if (alist[x][y].size() > 3) {
				return true;
			}
//         t_print(alist);
		}

//      t_print(alist);
		return false;
	}
}