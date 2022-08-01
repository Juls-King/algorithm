//가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
//다음 1초 동안 봄버맨은 아무것도 하지 않는다.
//다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
//1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
//3과 4를 반복한다.
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_16918 {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	
	public static class Bomb {
		public char bomb;
		public int sec;
		public boolean explode;
		
		public Bomb(char bomb, int sec, boolean explode) {
			this.bomb = bomb;
			this.sec = sec;
			this.explode = explode;
		}
		
		public void countDown() {
			if(this.bomb == 'O' && sec > 0) {
				this.sec--;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		Bomb[][] map = new Bomb[R][C];
		
		for(int i=0 ; i<R ; i++) {
			String str = br.readLine();
			
			for(int j=0 ; j<C ; j++) {
				char bomb = str.charAt(j);
//				int sec = (bomb == 'O') ? 3 : 0;
				int sec = 3;
				
				map[i][j] = new Bomb(bomb, sec, false);
			}
		}
		
//		printMap(R, C, map, N);
		
		// 
		for(int t=1 ; t<=N ; t++) {
			for(int i=0 ; i<R ; i++) {
				for(int j=0 ; j<C ; j++) {
					// 1. 폭탄이 있으면 1초 감소한다
					if(map[i][j].bomb == 'O') {
						map[i][j].countDown();
					}
					
					// 2. 0초된 폭탄과 주변 폭탄에 폭발할꺼란 표시
					if(map[i][j].sec == 0) {
						map[i][j].explode = true;
						
						// 현위치의 동서남북 폭발할꺼란 표시
						if(i + dx[0] >= 0 && map[i + dx[0]][j + dy[0]].bomb == 'O') {
							map[i + dx[0]][j + dy[0]].explode = true;
						}
						if(j + dy[1] >= 0 && map[i + dx[1]][j + dy[1]].bomb == 'O') {
							map[i + dx[1]][j + dy[1]].explode = true;
						}
						if(i + dx[2] < R && map[i + dx[2]][j + dy[2]].bomb == 'O') {
							map[i + dx[2]][j + dy[2]].explode = true;
						}
						if(j + dy[3] < C && map[i + dx[3]][j + dy[3]].bomb == 'O') {
							map[i + dx[3]][j + dy[3]].explode = true;
						}
					}
				}
			}
			
			for(int i=0 ; i<R ; i++) {
				for(int j=0 ; j<C ; j++) {
					// 3. 폭발 표시된 폭탄을 터트림, 초기화
					if(map[i][j].explode == true) {
						map[i][j].bomb = '.';
						map[i][j].sec = 3;
						map[i][j].explode = false;	// ??
					}
				}
			}
			
			// 4. 짝수 시간일 때 봄버맨이 빈칸에 폭탄 설치
			if(t % 2 == 0){
				for(int i=0 ; i<R ; i++) {
					for(int j=0 ; j<C ; j++) {
						if(map[i][j].bomb == '.') {
							map[i][j].bomb = 'O';
						}
					}
				}
			}
			
//			printMap(R, C, map, t);
		}
		
		printMap(R, C, map, 0);
	}
	
	public static void printMap(int R, int C, Bomb[][] map, int t) {
//		System.out.println("t : " + t);
		
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				System.out.print(map[i][j].bomb);
			}
			System.out.println();
		}
		System.out.println();
	}
}