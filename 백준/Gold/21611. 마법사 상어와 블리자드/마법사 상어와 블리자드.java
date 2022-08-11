import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = { 1, 0, -1, 0 };	// 배열 위아래 탐색
	static int dy[] = { 0, 1, 0, -1 }; 	// 배열 좌우 탐색
	static int result = 0;				// 최종결과 출력 변수

	public static class Node {	// 구슬 숫자와 칸의 번호 정보를 함께 저장하는 배열을 만들기 위해 클래스 사용
		public int bead;	// 구슬 숫자
		public int order;	// 칸의 번호

		public Node(int bead, int order) {
			this.bead = bead;
			this.order = order;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Node[][] map = new Node[N][N];	// 구술 숫자 및 칸의 번호가 저장될 2차원 배열
		int[][] magic = new int[M][2];	// 블리자드 마법 정보를 담을 2차원 배열
		int[] idx = new int[2];			// 배열의 인덱스를 저장할 배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < N; j++) {
				int bead = Integer.parseInt(st.nextToken());

				map[i][j] = new Node(bead, 0);	// map 초기화
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			magic[i][0] = Integer.parseInt(st.nextToken());
			magic[i][1] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> list = new ArrayList<Integer>();	// 칸의 번호 순서대로 구슬을 저장할 리스트
		int order = 1;
		
		idx[0] = N / 2;
		idx[1] = N / 2;

		for (int i = 0; i < N / 2; i++) {	// 칸의 번호를 세팅하기 위한 작업
			idx[1] -= 1;

			map[idx[0]][idx[1]].order = order++;	// 칸의 번호 초기화

			if (map[idx[0]][idx[1]].bead > 0) {
				list.add(map[idx[0]][idx[1]].bead);	// map의 해당 인덱스에 구슬이 있으면 리스트에 해당 구슬 추가
			}

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < (i + 1) * 2; k++) {
					if (j == 0 && k == ((i + 1) * 2) - 1) {
						continue;
					}

					idx[0] += dx[j];
					idx[1] += dy[j];

					map[idx[0]][idx[1]].order = order++;	// 칸의 번호 초기화

					if (map[idx[0]][idx[1]].bead > 0) {
						list.add(map[idx[0]][idx[1]].bead);	// map의 해당 인덱스에 구슬이 있으면 리스트에 해당 구슬 추가
					}
				}
			}
		}

		for (int i = 0; i < magic.length; i++) {
			blizzard(idx, N, i, magic, map, list);	// 블리자드 마법 호출

			explode(list);	// 구슬 폭발 호출

			convert(N, list);	// 구슬 번환 호출
		}

		System.out.println(result);	// 최종결과 출력
	}

	// 블리자드 마법 구현 함수
	public static void blizzard(int[] idx, int N, int i, int[][] magic, Node[][] map, ArrayList<Integer> list) {
		idx[0] = N / 2;
		idx[1] = N / 2;
		int count = 1;

		for (int j = 0; j < magic[i][1]; j++) {

			switch (magic[i][0]) {
			case 1:	// 북
				idx[0] = idx[0] + dx[2];
				idx[1] = idx[1] + dy[2];
				break;
			case 2:	// 남
				idx[0] = idx[0] + dx[0];
				idx[1] = idx[1] + dy[0];
				break;
			case 3:	// 서
				idx[0] = idx[0] + dx[3];
				idx[1] = idx[1] + dy[3];
				break;
			case 4:	// 동
				idx[0] = idx[0] + dx[1];
				idx[1] = idx[1] + dy[1];
				break;
			}

			int m_order = map[idx[0]][idx[1]].order;	// 현재 인덱스에 해당하는 칸의 순서 값

			if (list.size() > m_order - 1) {
				list.remove(m_order - count++);		// 리스트에서 구슬을 하나씩 제거할 때 마다 리스트는 당겨지므로 삭제할 인덱스를 잘 계산해 줘야함
			}
		}
	}

	// 구슬 폭발 구현 함수
	public static void explode(ArrayList<Integer> list) {
		if (list.size() < 1) {	// 리스트에 값이 없을 때 함수 종료
			return;
		}

		int count = 1;			// 같은 구슬이 얼마나 연속됬는지 기억하기 위한 변수
		int bead = list.get(0);	// 첫번째 구슬
		int index = 0;			// 구슬의 종류가 바뀌는 때를 기억하기 위한 변수
		int isExploded = 0;		// 폭발이 있었는지 확인하기 위한 변수

		for (int i = 1; i < list.size(); i++) {
			if (bead == list.get(i)) {	// 같은 구슬일 때
				count++;
			} 
			else {	// 다른 구슬일 때
				if (count >= 4) {	// 직전까지 같은 구슬이 4번 이상 연속됬을 때
					for (int j = 0; j < count; j++) {
						list.remove(index);	// 연속된 만큼 리스트에서 삭제
					}

					isExploded++;	// 폭발 시 증분

					result += bead * count;	// 최종결과값에 계산 - N × (폭발한 N번 구슬의 개수)

					i = index;	// 폭발로 리스트에 변화가 생겼으므로 반복문의 인덱스를 index(폭발 시작지점)으로 변경해 주어야함

					bead = list.get(index);	// 구슬 또한 리스트의 index(폭발 시작지점) 값 으로 초기화
				} 
				else {	// 직전까지 같은 구슬이 4번 미만 연속됬을 때
					index = i;	// 반복문 현재 인덱스로 초기화

					bead = list.get(index);	// 현재 인덱스의 구슬 저장
				}

				count = 1;
			}
		}
		
		if (isExploded > 0) {	// 폭발이 있었으면 더 이상 폭발하는 구슬이 없을때까지 반복
			explode(list);
		}

		if (list.size() >= 4 && count >= 4) {	// 같은 구슬로 끝날 경우를 체크해주기 위해 추가
			for (int j = 0; j < count; j++) {
				list.remove(index);
			}

			result += bead * count;	// 최종결과값에 계산 - N × (폭발한 N번 구슬의 개수)
		}
	}

	// 구슬 변환 구현 함수
	public static void convert(int N, ArrayList<Integer> list) {
		if (list.size() < 1) {	// 리스트에 값이 없을 때 함수 종료
			return;
		}

		ArrayList<Integer> local_list = new ArrayList<Integer>();	// 함수 내부에서 사용될 리스트
		int count = 1;
		int bead = list.get(0);	// 첫번째 구슬

		for (int i = 1; i < list.size(); i++) {
			if (bead == list.get(i)) {	// 같은 구슬일 때
				count++;
			}
			else {	// 다른 구슬일 때
				local_list.add(count);	// 구슬 A
				local_list.add(bead);	// 구슬 B

				count = 1;
				bead = list.get(i);
			}
		}
		local_list.add(count);	// 구슬 A
		local_list.add(bead);	// 구슬 B

		int max_size = (N * N) - 1;		// 칸의 최대 길이

		if (local_list.size() > max_size) {	// 구슬이 칸의 수보다 많은 경우
			int temp = local_list.size() - max_size;

			for (int i = 0; i < temp; i++) {
				local_list.remove(max_size);	// 칸을 넘어선 구슬은 제거
			}
		}

		list.clear();	// 리스트 초기화
		list.addAll(local_list);	// 리스트 복사
	}
}