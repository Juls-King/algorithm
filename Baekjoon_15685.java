package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15685 {
	static int dx[] = { 1, 0, -1, 0 }; // 배열 좌우 탐색
	static int dy[] = { 0, -1, 0, 1 }; // 배열 상하 탐색

	static int result = 0; // 최종결과 출력 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[101][101]; // 드래곤커브를 그릴 배열

		int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수

		int[][] curve_info = new int[N][4]; // 드래곤 커브 정보 담을 배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			curve_info[i][0] = Integer.parseInt(st.nextToken()); // x좌표
			curve_info[i][1] = Integer.parseInt(st.nextToken()); // y좌표
			curve_info[i][2] = Integer.parseInt(st.nextToken()); // 시작방향
			curve_info[i][3] = Integer.parseInt(st.nextToken()); // 세대
		}

		drawCurve(map, N, curve_info); // 드래곤 커브 그려주는 함수 호출

		checkMap(map); // 드래곤 커브 체크 함수 호출

		System.out.println(result); // 최종결과 출력
	}

	// 드래곤 커브 그리는 함수
	public static void drawCurve(int[][] map, int N, int[][] curve_info) {
		for (int i = 0; i < N; i++) {
			int x = curve_info[i][0]; // x좌표
			int y = curve_info[i][1]; // y좌표
			int dir = curve_info[i][2]; // 시작방향
			int gen = curve_info[i][3]; // 세대

			int move_cnt = (int) Math.pow(2, gen); // 시작점부터 끝점까지 이동한 횟수

			int[] move_info = new int[move_cnt]; // 시작점부터 끝점까지 이동 방향을 담을 배열

			move_info[0] = dir; // 배열의 첫번째 인덱스에 시작 방향을 넣는다

			for (int j = 1; j <= gen; j++) { // 세대 수만큼 반복
				int len = (int) Math.pow(2, j); // 해당 세대에서의 배열 길이

				for (int k = 0; k < len / 2; k++) { // 해당 세대에서 배열의 절반을 채우기 위해 len/2 만큼 반복

					int idx = len - k - 1; // 배열의 뒤에서 부터 채우기 위한 인덱스 계산

					move_info[idx] = (move_info[k] + 1) % 4; // ex)k번째 값에 1을 더해 n-k번째에 대입, 데칼코마니 위치
				}
			}

			map[y][x] = 1; // 드래곤 커브 위치 표시

			for (int j = 0; j < move_cnt; j++) { // 인덱스를 반복적으로 계산해준다
				x += dx[move_info[j]];
				y += dy[move_info[j]];

				map[y][x] = 1; // 드래곤 커브 위치 표시
			}
		}
	}

	// 드래곤 커브 체크 함수
	public static void checkMap(int[][] map) {
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map.length - 1; j++) {
				if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) { // 네 꼭짓점이 모두 드래곤 커브의 일부인 경우 검사
					result++;
				}
			}
		}
	}
}