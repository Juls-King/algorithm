package algorithm;

public class KS2 {
	static int[][] result; // 반환값 담을 변수
	static int dir_info[][][] = { { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }, // 시계방향 - 우, 하, 좌, 상
								  { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } } }; // 반시계방향 - 하, 우, 상, 좌

	public static void main(String[] args) {
		int n = 5; // 입력값
		result = new int[n][n];

		boolean clockwise = true; // 시계:true, 반시계:false

		// 이차원배열 네 꼭지점을 1로 초기화
		result[0][0] = 1;
		result[0][n - 1] = 1;
		result[n - 1][n - 1] = 1;
		result[n - 1][0] = 1;

		execute(n, clockwise); // 함수 실행

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}

//      System.out.println(Arrays.toString(result));
	}

	public static void execute(int n, boolean clockwise) {
		int[][] idx = new int[4][2]; // 네시작점의 인덱스를 관리할 배열
		idx[0][0] = 0; // 좌상
		idx[0][1] = 0; // 좌상
		idx[1][0] = 0; // 우상
		idx[1][1] = n - 1; // 우상
		idx[2][0] = n - 1; // 우하
		idx[2][1] = n - 1; // 우하
		idx[3][0] = n - 1; // 좌하
		idx[3][1] = 0; // 좌하
		int dir = 0; // 상하좌우 방향정보 저장 변수
		int count = 2; // 숫자를 찍기 위한 변수

		int cw = (clockwise == true) ? 0 : 1; // clockwise값에 따른 정수값을 저장할 변수

		while (true) {
			if (cw == 0) { // 시계방향
				result[idx[0][0] + dir_info[cw][dir % 4][0]][idx[0][1] + dir_info[cw][dir % 4][1]] = count;
				idx[0][0] += dir_info[cw][dir % 4][0]; // 다음 인덱스 대입
				idx[0][1] += dir_info[cw][dir % 4][1]; // 다음 인덱스 대입

				result[idx[1][0] + dir_info[cw][(dir + 1) % 4][0]][idx[1][1] + dir_info[cw][(dir + 1) % 4][1]] = count;
				idx[1][0] += dir_info[cw][(dir + 1) % 4][0];
				idx[1][1] += dir_info[cw][(dir + 1) % 4][1];

				result[idx[2][0] + dir_info[cw][(dir + 2) % 4][0]][idx[2][1] + dir_info[cw][(dir + 2) % 4][1]] = count;
				idx[2][0] += dir_info[cw][(dir + 2) % 4][0];
				idx[2][1] += dir_info[cw][(dir + 2) % 4][1];

				result[idx[3][0] + dir_info[cw][(dir + 3) % 4][0]][idx[3][1] + dir_info[cw][(dir + 3) % 4][1]] = count;
				idx[3][0] += dir_info[cw][(dir + 3) % 4][0];
				idx[3][1] += dir_info[cw][(dir + 3) % 4][1];
			} else { // 반시계방향
				result[idx[0][0] + dir_info[cw][dir % 4][0]][idx[0][1] + dir_info[cw][dir % 4][1]] = count;
				idx[0][0] += dir_info[cw][dir % 4][0];
				idx[0][1] += dir_info[cw][dir % 4][1];

				result[idx[3][0] + dir_info[cw][(dir + 1) % 4][0]][idx[3][1] + dir_info[cw][(dir + 1) % 4][1]] = count;
				idx[3][0] += dir_info[cw][(dir + 1) % 4][0];
				idx[3][1] += dir_info[cw][(dir + 1) % 4][1];

				result[idx[2][0] + dir_info[cw][(dir + 2) % 4][0]][idx[2][1] + dir_info[cw][(dir + 2) % 4][1]] = count;
				idx[2][0] += dir_info[cw][(dir + 2) % 4][0];
				idx[2][1] += dir_info[cw][(dir + 2) % 4][1];

				result[idx[1][0] + dir_info[cw][(dir + 3) % 4][0]][idx[1][1] + dir_info[cw][(dir + 3) % 4][1]] = count;
				idx[1][0] += dir_info[cw][(dir + 3) % 4][0];
				idx[1][1] += dir_info[cw][(dir + 3) % 4][1];
			}

			// 한 방향으로 가다가 0이 아닌 수를 만나면 방향을 바꾼다
			if (result[idx[0][0] + dir_info[cw][dir % 4][0]][idx[0][1] + dir_info[cw][dir % 4][1]] != 0) {
				dir += 1;
			}

			// 반복 종료 조건
			if (n % 2 == 1) { // n이 홀수일 때는 인덱스가 정가운데 일 때
				if (idx[0][0] == n / 2 && idx[0][1] == n / 2) {
					break;
				}
			} else { // n이 짝수일 때는 인덱스가 가운데 부근일 때
				if (idx[0][0] == n / 2 - 1 && idx[0][1] == n / 2 - 1 || idx[0][0] == n / 2 - 1 && idx[0][1] == n / 2
						|| idx[0][0] == n / 2 && idx[0][1] == n / 2 - 1 || idx[0][0] == n / 2 && idx[0][1] == n / 2) {
					break;
				}
			}

			count++; // 찍을 값 증분
		}
	}
}