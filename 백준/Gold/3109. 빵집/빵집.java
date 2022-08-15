import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] visited;
	static char[][] map;

	static int R;
	static int C;

	static int count = 0;

	static int dx[] = { -1, 0, 1 }; // 배열 상하 탐색
	static int dy[] = { 1, 1, 1 }; // 배열 좌우 탐색

	static int flag = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new int[R][C];

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			dfs(i, 0);

			flag = 0;
		}

		System.out.println(count);
	}

	public static void dfs(int i, int j) {
//		System.out.println(i + "," + j + " ");

		visited[i][j] = 1;

		if (j == C - 1) {
			count++;
//			System.out.println("count up");

			flag = 1;

			return;
		}

		if (i - 1 >= 0 && visited[i + dx[0]][j + dy[0]] == 0 && map[i + dx[0]][j + dy[0]] != 'x' && flag != 1) {
			dfs(i + dx[0], j + dy[0]);
		}

		if (visited[i + dx[1]][j + dy[1]] == 0 && map[i + dx[1]][j + dy[1]] != 'x' && flag != 1) {
			dfs(i + dx[1], j + dy[1]);
		}

		if (i + 1 < R && visited[i + dx[2]][j + dy[2]] == 0 && map[i + dx[2]][j + dy[2]] != 'x' && flag != 1) {
			dfs(i + dx[2], j + dy[2]);
		}

//		if (flag != 1) {
//			map[i][j] = 'x';
//		}
	}
}
