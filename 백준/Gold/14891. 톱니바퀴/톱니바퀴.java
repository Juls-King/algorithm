import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Cogwheel[] cogwheel;	// 톱니바퀴 객체를 담을 배열

	// 톱니바퀴 클래스
	public static class Cogwheel {
		public int north;		// 북
		public int east_north;	// 북동
		public int east;		// 동
		public int east_south;	// 남동
		public int south;		// 남
		public int west_south;	// 남서
		public int west;		// 서
		public int west_north;	// 북서

		public Cogwheel(int north, int east_north, int east, int east_south, 
						int south, int west_south, int west, int west_north) {
			this.north = north;
			this.east_north = east_north;
			this.east = east;
			this.east_south = east_south;
			this.south = south;
			this.west_south = west_south;
			this.west = west;
			this.west_north = west_north;
		}
		
		// 톱니바퀴를 방향(dir)에 따라 돌리는 함수
		public void rotate(int dir) {
			int north = this.north;
			int east_north = this.east_north;
			int east = this.east;
			int east_south = this.east_south;
			int south = this.south;
			int west_south = this.west_south;
			int west = this.west;
			int west_north = this.west_north;

			if (dir == 1) {		// 시계방향 회전
				this.north = west_north;
				this.east_north = north;
				this.east = east_north;
				this.east_south = east;
				this.south = east_south;
				this.west_south = south;
				this.west = west_south;
				this.west_north = west;
			} else if (dir == -1) {		// 반시계방향 회전
				this.north = east_north;
				this.east_north = east;
				this.east = east_south;
				this.east_south = south;
				this.south = west_south;
				this.west_south = west;
				this.west = west_north;
				this.west_north = north;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] cogwheel_info = new int[4+1][8+1];	// 톱니바퀴 정보(N or S)를 담을 배열

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();

			for (int j = 0; j < 8; j++) {
				cogwheel_info[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		int K = Integer.parseInt(br.readLine());	// 회전 횟수

		int[][] rotate_info = new int[K][2];		// 회전 정보를 담을 배열

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			rotate_info[i][0] = Integer.parseInt(st.nextToken());	// 톱니바퀴 번호
			rotate_info[i][1] = Integer.parseInt(st.nextToken());	// 방향
		}

		cogwheel = new Cogwheel[4+1];

		// 톱니바퀴 객체 생성
		for (int i = 1; i <= 4; i++) {
			cogwheel[i] = new Cogwheel(cogwheel_info[i-1][0], cogwheel_info[i-1][1], cogwheel_info[i-1][2], cogwheel_info[i-1][3],
									cogwheel_info[i-1][4], cogwheel_info[i-1][5], cogwheel_info[i-1][6], cogwheel_info[i-1][7]);
		}

		for (int i = 0; i < K; i++) {
			int number = rotate_info[i][0];	// 톱니바퀴 번호
			int dir = rotate_info[i][1];	// 방향

			execute(number, dir);	// 함수 호출
		}

		int sum = 0;

		if (cogwheel[1].north == 1) {
			sum = sum + 1;
		}
		if (cogwheel[2].north == 1) {
			sum = sum + 2;
		}
		if (cogwheel[3].north == 1) {
			sum = sum + 4;
		}
		if (cogwheel[4].north == 1) {
			sum = sum + 8;
		}

		System.out.println(sum);	// 결과 출력
	}

	// 시작 톱니바퀴를 돌리는 함수
	public static void execute(int number, int dir) {
		int now_west = cogwheel[number].west;	// 현재 톱니바퀴의 왼쪽 값
		int now_east = cogwheel[number].east;	// 현재 톱니바퀴의 오른쪽 값

		cogwheel[number].rotate(dir);	// 톱니바퀴 회전

		// 왼쪽에 톱니바퀴가 더 있을 때
		if (number > 1) {
			int next_east = cogwheel[number-1].east;

			if (now_west != next_east) {	// 맞닿은 부분이 다른 극일 때
				execute_left(number-1, (dir*-1));	// 재귀 호출
			}
		}
		
		// 오른쪽에 톱니바퀴가 더 있을 때
		if (number < 4) {
			int next_west = cogwheel[number+1].west;

			if (now_east != next_west) {	// 맞닿은 부분이 다른 극일 때
				execute_right(number+1, (dir*-1));	// 재귀 호출
			}
		}
	}

	// 왼쪽 톱니바퀴를 돌리는 함수
	public static void execute_left(int number, int dir) {
		if (number < 1) {	// 왼쪽에 톱니바퀴가 더 없을 때
			return;
		}

		int now_west = cogwheel[number].west;

		cogwheel[number].rotate(dir);	// 톱니바퀴 회전

		// 왼쪽에 톱니바퀴가 더 있을 때
		if (number > 1) {
			int next_east = cogwheel[number-1].east;

			if (now_west == next_east) {	// 맞닿은 부분이 같은 극일 때
				return;
			} else {
				execute_left(number-1, (dir*-1));	// 재귀 호출
			}
		}
	}

	// 오른쪽 톱니바퀴를 돌리는 함수
	public static void execute_right(int number, int dir) {
		if (number > 4) {	// 오른쪽에 톱니바퀴가 더 없을 때
			return;
		}

		int now_east = cogwheel[number].east;

		cogwheel[number].rotate(dir);	// 톱니바퀴 회전

		// 오른쪽에 톱니바퀴가 더 있을 때
		if (number < 4) {
			int next_west = cogwheel[number+1].west;

			if (now_east == next_west) {	// 맞닿은 부분이 같은 극일 때
				return;
			} else {
				execute_right(number+1, (dir*-1));	// 재귀 호출
			}
		}
	}
}