// 적록색약
package algorithm;

import java.util.*;
import java.io.*;

public class Baekjoon_10026 {
	static Boolean[][] visited;		// 방문여부 기록 배열
	static char[][] image;			// 입력 배열
	static int N;					// 입력 N
	static int dx[]={1, 0, -1, 0};	// 배열 위아래 탐색
	static int dy[]={0, 1, 0, -1};	// 배열 좌우 탐색
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = 0;	// 구역 수
		N = Integer.parseInt(br.readLine());
		
		image = new char[N][N];
		
		for(int i=0 ; i<N ; i++) {
			String s = br.readLine();
			
			for(int j=0 ; j<N ; j++) {
				image[i][j] = s.charAt(j);
			}
		}
		
		visited = new Boolean[N][N];
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				visited[i][j] = false;
			}
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(visited[i][j] == false) {
					bfs(i,j);	// 함수 호출
					count++;
//					System.out.println("count : " + count);
				}
			}
		}
		
		System.out.print(count + " ");
		
		count = 0;	// 변수 초기화

		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				visited[i][j] = false;	// 배열 초기화
				
				// 적록색맹이므로 'G'를 'R'로 치환
				if(image[i][j] == 'G') {
					image[i][j] = 'R';
				}
			}
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(visited[i][j] == false) {
					bfs(i,j);	// 함수 호출
					count++;
//					System.out.println("count : " + count);
				}
			}
		}
		
		System.out.print(count + " ");
	}
	
	public static void bfs(int i, int j) {
		// 배열 인덱스 i,j를 한쌍으로 관리할 큐
		Queue<Integer> q_i = new LinkedList<>();
		Queue<Integer> q_j = new LinkedList<>();
		
		char rgb = image[i][j];
		
		q_i.offer(i);
		q_j.offer(j);
		visited[i][j] = true;
		
		while(!q_i.isEmpty()) {
			int x = q_i.poll();
			int y = q_j.poll();

//			System.out.println("image["+x+"]["+y+"] : " + image[x][y]);
			
			// 아래쪽 확인
			if(x + dx[0] < N) {		//out of index 체크
				if(visited[x + dx[0]][y + dy[0]] == false 			// 방문한 타일인지 체크
						&& rgb == image[x + dx[0]][y + dy[0]]) { 	// 현재 RGB값과 동일한지 확인
					q_i.offer(x + dx[0]);
					q_j.offer(y + dy[0]);
					visited[x + dx[0]][y + dy[0]] = true;
				}
			}
			// 오른쪽 확인
			if(y + dy[1] < N) {		//out of index 체크
				if(visited[x + dx[1]][y + dy[1]] == false 			// 방문한 타일인지 체크
						&& rgb == image[x + dx[1]][y + dy[1]]) { 	// 현재 RGB값과 동일한지 확인
					q_i.offer(x + dx[1]);
					q_j.offer(y + dy[1]);
					visited[x + dx[1]][y + dy[1]] = true;
				}
			}
			// 위쪽 확인
			if(x + dx[2] > -1) {	//out of index 체크
				if(visited[x + dx[2]][y + dy[2]] == false 			// 방문한 타일인지 체크
						&& rgb == image[x + dx[2]][y + dy[2]]) { 	// 현재 RGB값과 동일한지 확인
					q_i.offer(x + dx[2]);
					q_j.offer(y + dy[2]);
					visited[x + dx[2]][y + dy[2]] = true;
				}
			}
			// 왼쪽 확인
			if(y + dy[3] > -1) {	//out of index 체크
				if(visited[x + dx[3]][y + dy[3]] == false 			// 방문한 타일인지 체크
						&& rgb == image[x + dx[3]][y + dy[3]]) { 	// 현재 RGB값과 동일한지 확인
					q_i.offer(x + dx[3]);
					q_j.offer(y + dy[3]);
					visited[x + dx[3]][y + dy[3]] = true;
				}
			}
		}
	}
}