package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baekjoon_17837 {
	static int[] dx = { 0, 0, 0, -1, 1 }; // 배열 상하 탐색
	static int[] dy = { 0, 1, -1, 0, 0 }; // 배열 좌우 탐색

	static Horse[] horse; // 말 객체를 담을 배열

	public static class Horse { // 말 객체
		public int x; // 행 좌표
		public int y; // 열 좌표
		public int dir; // 방향

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

		int[][] map = new int[N + 1][N + 1]; // 체스판 초기화

		horse = new Horse[K];

		@SuppressWarnings("unchecked")
		ArrayList<Integer>[][] alist = new ArrayList[N + 1][N + 1]; // 말의 위치를 다룰 2차원 리스트

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				alist[i][j] = new ArrayList<Integer>(); // 2차원 리스트의 원소로 다시 리스트 선언
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			horse[i] = new Horse(x, y, dir); // 말 객체 생성

			alist[x][y].add(i + 1); // 리스트에 말 추가
		}

		int count = 0; // 턴 진행횟수

		for (int i = 0; i < 1000; i++) { // 1000번 진행
			count++;

			if (move(N, K, map, alist)) { // move함수 호출
				break;
			}
		}

		if (count < 1000) {
			System.out.println(count); // 현재 count값 출력
		} else {
			System.out.println(-1);
		}
	}

	// 모든 말을 한 턴 이동시키는 함수
	static public boolean move(int N, int K, int[][] map, ArrayList<Integer>[][] alist) {
		for (int i = 0; i < K; i++) { // 모든 말의 수 K 만큼 반복
			int x = horse[i].x;
			int y = horse[i].y;
			int dir = horse[i].dir;

			int index = alist[x][y].indexOf(i + 1); // 현재 좌표에서 말의 쌓여있는 순서

			int size = alist[x][y].size(); // 현재 좌표의 리스트 길이

			ArrayList<Integer> l_list = new ArrayList<Integer>(alist[x][y].subList(index, size)); // 현재 말과 위의 모든 말을 따로 저장

			if (index == 0) { // 말이 가장아래 일 때
				alist[x][y].clear();
			} else {
				alist[x][y] = new ArrayList<Integer>(alist[x][y].subList(0, index));
			}

			boolean isMoved = false; // 말이 이동했는지 체크하는 변수

			if ((x + dx[dir] < 1 || x + dx[dir] > N || y + dy[dir] < 1 || y + dy[dir] > N)
					|| map[x + dx[dir]][y + dy[dir]] == 2) { // 이동할 위치가 체스판 외부 또는 파란색일 때

				if (dir == 1 || dir == 3) { // 방향 바꿔주기
					dir += 1;
				} else {
					dir -= 1;
				}

				horse[i].dir = dir; // 말의 방향 갱신

				if (!((x + dx[dir] < 1 || x + dx[dir] > N || y + dy[dir] < 1 || y + dy[dir] > N)
						|| map[x + dx[dir]][y + dy[dir]] == 2)) { // 이동할 위치가 체스판 내부 또는 체스판이 파란색이 아닐 때

					x += dx[dir]; // 좌표값 변경
					y += dy[dir];

					isMoved = true; // 말 이동 표시
				}
			} else { // 이동할 위치가 체스판 내부 또는 체스판이 파란색이 아닐 때
				x += dx[dir]; // 좌표값 변경
				y += dy[dir];

				isMoved = true; // 말 이동 표시
			}

			if (isMoved) { // 말이 이동했을 때 현재 말과 위의 모든 말 이동
				for (int j = 0; j < l_list.size(); j++) {
					int element = l_list.get(j);

					horse[element - 1].x = x; // 말의 좌표 갱신
					horse[element - 1].y = y;
				}
			}

			if (map[x][y] == 1 && isMoved == true) { // 말이 이동하고 체스판이 빨간색일 때
				Collections.reverse(l_list); // 이동할 말들 순서 뒤집기
			}

			alist[x][y].addAll(l_list); // 이동할 말을 이동할 위치에 있는 말 위에 쌓는다

			if (alist[x][y].size() > 3) { // 말이 3개보다 많이 쌓일 때 true 반환
				return true;
			}
		}

		return false;
	}
}