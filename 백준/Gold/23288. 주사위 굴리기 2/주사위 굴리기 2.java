import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;             // 입력 맵 저장할 배열
	static Boolean[][] visited;		// 방문여부 기록 배열
	static int dx[] = { 1, 0, -1, 0 }; // 배열 위아래 탐색
	static int dy[] = { 0, 1, 0, -1 }; // 배열 좌우 탐색
	
	// 주사위 클래스
	public static class Dice {
		public int[] dice = {1, 2, 3, 4, 5, 6};	// 위, 북, 동, 서, 남, 아래
		
		public int[] idx = {0, 0};	// 좌표
		
		public int dir = 0;	// 방향을 나태낼 정수
		
		public int n;	// 입력값 N,M
		public int m;
		
		public int result = 0;	// 최종 결과값

		// 생성자
		public Dice(int n, int m) {
			this.n = n;
			this.m = m;
		}
		
		// 주사위를 이동시키는 함수
		public void move() {
			int[] dice = this.dice.clone();	// 배열 깊은 복사
			
			if(this.dir == 0 && this.idx[1] + 1 >= this.m 	// 주사위가 지도를 벗어나지 않게 하기위한 처리
			|| this.dir == 1 && this.idx[0] + 1 >= this.n
			|| this.dir == 2 && this.idx[1] - 1 < 0 
			|| this.dir == 3 && this.idx[0] - 1 < 0) {
				
				this.dir = (this.dir + 2) % 4;	// 진행 방향의 반대 방향으로 바꿔준다
			}
			
			switch(dir) {
				case 0: 	// 동
					this.dice[2] = dice[0];
					this.dice[3] = dice[5];
					this.dice[0] = dice[3];
					this.dice[5] = dice[2];
					
					this.idx[1]++;	// 열 값 증가
					
					break;
				case 1: 	// 남
					this.dice[4] = dice[0];
					this.dice[1] = dice[5];
					this.dice[0] = dice[1];
					this.dice[5] = dice[4];
					
					this.idx[0]++;	// 행 값 증가
					
					break;
				case 2: 	// 서
					this.dice[2] = dice[5];
					this.dice[3] = dice[0];
					this.dice[0] = dice[2];
					this.dice[5] = dice[3];
					
					this.idx[1]--;	// 열 값 감소
					
					break;
				case 3: 	// 북
					this.dice[4] = dice[5];
					this.dice[1] = dice[0];
					this.dice[0] = dice[4];
					this.dice[5] = dice[1];
					
					this.idx[0]--;	// 행 값 감소
					
					break;
			}
			
			turnDir();	// 방향 변경함수 호출
		}
		
		// 방향을 바꿀지 확인하는 함수
		public void turnDir() {
			int A = this.dice[5];	// 주사위의 아랫면
			int B = map[this.idx[0]][this.idx[1]];
			
			if(A > B) {
				this.dir = (this.dir + 1) % 4;	// 진행 방향을 90도 시계 방향으로 바꿔준다
			}
			else if(A < B) {
				this.dir = (this.dir + 3) % 4;	// 진행 방향을 90도 반시계 방향으로 바꿔준다
			}
		}
		
        // 같은 값을 BFS로 찾는 함수
		public void bfs() {
			// 배열 인덱스 i,j를 한쌍으로 관리할 큐
			Queue<Integer> q_i = new LinkedList<>();
			Queue<Integer> q_j = new LinkedList<>();
			
			int i = this.idx[0];
			int j = this.idx[1];
			
			int B = map[i][j];	// 칸에 있는 정수
			int C = 0;			// 연속으로 이동할 수 있는 칸의 수
			
			q_i.offer(i);
			q_j.offer(j);
			visited[i][j] = true;
			
			C++;	// 첫번째니깐 일딴 카운트 증가
			
			while(!q_i.isEmpty()) {
				int x = q_i.poll();
				int y = q_j.poll();

				// 아래쪽 확인
				if(x + dx[0] < this.n) {		//out of index 체크
					if(visited[x + dx[0]][y + dy[0]] == false 			// 방문한 타일인지 체크
							&& B == map[x + dx[0]][y + dy[0]]) { 	// 현재 B값과 동일한지 확인
						q_i.offer(x + dx[0]);
						q_j.offer(y + dy[0]);
						visited[x + dx[0]][y + dy[0]] = true;
						
						C++;	// 같은 값 일때 카운트 증가
					}
				}
				// 오른쪽 확인
				if(y + dy[1] < this.m) {		//out of index 체크
					if(visited[x + dx[1]][y + dy[1]] == false 			// 방문한 타일인지 체크
							&& B == map[x + dx[1]][y + dy[1]]) { 	// 현재 B값과 동일한지 확인
						q_i.offer(x + dx[1]);
						q_j.offer(y + dy[1]);
						visited[x + dx[1]][y + dy[1]] = true;
						
						C++;	// 같은 값 일때 카운트 증가
					}
				}
				// 위쪽 확인
				if(x + dx[2] > -1) {	//out of index 체크
					if(visited[x + dx[2]][y + dy[2]] == false 			// 방문한 타일인지 체크
							&& B == map[x + dx[2]][y + dy[2]]) { 	// 현재 B값과 동일한지 확인
						q_i.offer(x + dx[2]);
						q_j.offer(y + dy[2]);
						visited[x + dx[2]][y + dy[2]] = true;
						
						C++;	// 같은 값 일때 카운트 증가
					}
				}
				// 왼쪽 확인
				if(y + dy[3] > -1) {	//out of index 체크
					if(visited[x + dx[3]][y + dy[3]] == false 			// 방문한 타일인지 체크
							&& B == map[x + dx[3]][y + dy[3]]) { 	// 현재 B값과 동일한지 확인
						q_i.offer(x + dx[3]);
						q_j.offer(y + dy[3]);
						visited[x + dx[3]][y + dy[3]] = true;
						
						C++;	// 같은 값 일때 카운트 증가
					}
				}
			}
			
			this.result += B * C;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());	// 세로 크기
		int M = Integer.parseInt(st.nextToken());	// 가로 크기
		
		int K = Integer.parseInt(st.nextToken());	// 명령의 개수
		
		visited = new Boolean[N][M];
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				visited[i][j] = false;
			}
		}
		
		map = new int[N][M];	// 지도 배열
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0 ; j<M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 지도 배열 초기화
			}
		}
		
		Dice dice = new Dice(N, M);	// 객체 초기화
		
		for(int i=0 ; i<K ; i++) {
			dice.move();	// 객체 내장함수 호출(주사위 굴리기)
			dice.bfs();		// 객체 내장함수 호출(사방으로 같은 값 찾기)
			
			for(int x=0 ; x<N ; x++) {	// 방문확인배열 초기화
				for(int y=0 ; y<M ; y++) {
					visited[x][y] = false;
				}
			}
		}
		
		System.out.println(dice.result);	// 최종결과 출력
	}
}
