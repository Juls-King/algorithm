import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	// 주사위 클래스
	public static class Dice {
		public int east;	// 동
		public int west;	// 서
		public int south;	// 남
		public int north;	// 북
		public int top;		// 위
		public int bottom;	// 아래 위위 아래
		
		public int r;	// 행 좌표
		public int c;	// 열 좌표

		// 생성자
		public Dice(int east, int west, int south, int north, int top, int bottom, int r, int c) {
			this.east = east;
			this.west = west;
			this.south = south;
			this.north = north;
			this.top = top;
			this.bottom = bottom;
			
			this.r = r;
			this.c = c;
		}
		
		// 주사위를 이동시키는 함수
		public void move(int dir, int[][] map, int n, int m) throws IOException {
			int east = this.east;	// 객체의 변수값을 지역변수에 초기화
			int west = this.west;
			int south = this.south;
			int north = this.north;
			int top = this.top;
			int bottom = this.bottom;
			
			if(dir == 1 && this.c + 1 >= m 	// 주사위가 지도를 벗어나지 않게 하기위한 처리
				|| dir == 2 && this.c - 1 < 0 
				|| dir == 3 && this.r - 1 < 0
				|| dir == 4 && this.r + 1 >= n) {
				return;
			}
			
			switch(dir) {
				case 1: 	// 동
					this.east = top;
					this.west = bottom;
					this.top = west;
					this.bottom = east;
					
					this.c++;	// 열 값 증가
					exchange(map);	// 바닥면과 주사위 바닥 교환 함수 호출
					
					bw.write(this.top + "\n");	// 주사위 윗면 출력
					
					break;
				case 2: 	// 서
					this.east = bottom;
					this.west = top;
					this.top = east;
					this.bottom = west;
					
					this.c--;	// 열 값 감소
					exchange(map);	// 바닥면과 주사위 바닥 교환 함수 호출
					
					bw.write(this.top + "\n");	// 주사위 윗면 출력
					
					break;
				case 3: 	// 북
					this.south = bottom;
					this.north = top;
					this.top = south;
					this.bottom = north;
					
					this.r--;	// 행 값 감소
					exchange(map);	// 바닥면과 주사위 바닥 교환 함수 호출
					
					bw.write(this.top + "\n");	// 주사위 윗면 출력
					
					break;
				case 4: 	// 남
					this.south = top;
					this.north = bottom;
					this.top = north;
					this.bottom = south;
					
					this.r++;	// 행 값 증가
					exchange(map);	// 바닥면과 주사위 바닥 교환 함수 호출
					
					bw.write(this.top + "\n");	// 주사위 윗면 출력
					
					break;
			}
		}
		
		public void exchange(int[][] map) {
			if(map[this.r][this.c] == 0) {	// 바닥면이 0 일 때
				map[this.r][this.c] = this.bottom;	// 주사위 바닥이 바닥면으로 복사
			}
			else {
				this.bottom = map[this.r][this.c];	// 바닥면이 주사위 바닥으로 복사
				map[this.r][this.c] = 0;	// 바닥면은 0으로
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());	// 세로 크기
		int M = Integer.parseInt(st.nextToken());	// 가로 크기
		
		int x = Integer.parseInt(st.nextToken());	// 행 좌표
		int y = Integer.parseInt(st.nextToken());	// 열 좌표
		
		int K = Integer.parseInt(st.nextToken());	// 명령의 개수
		
		int[][] map = new int[N][M];	// 지도 배열
		int[] move_info = new int[K];	// 명령을 담은 배열
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0 ; j<M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 지도 배열 초기화
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0 ; i<K ; i++) {
			move_info[i] = Integer.parseInt(st.nextToken());	// 명령을 담은 배열 초기화
		}
		
		Dice dice = new Dice(0, 0, 0, 0, 0, 0, x, y);	// 객체 초기화
		for(int i=0 ; i<K ; i++) {
			dice.move(move_info[i], map, N, M);	// 객체 내장함수 호출(주사위 굴리기)
		}
		
		bw.flush();
		bw.close();
	}
}