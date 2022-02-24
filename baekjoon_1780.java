package algorithm;

import java.io.*;
import java.util.*;

public class baekjoon_1780 {
    static int a = 0;    // -1 개수
	static int b = 0;    // 0 갯수
	static int c = 0;    // 1 갯수
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] paper = new int[N][N];
				
		for(int i=0 ; i<N ; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			for(int j=0 ; j<N ; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		check(N, paper);    // 함수 호출
		
		// 결과 출력
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
    }
    
    public static void check(int n, int[][] paper) {
		int temp = paper[0][0];	// 배열의 첫번째 값 대입
		
        // 재귀실행 중 배열 원소가 하나일 때 처리
		if(n == 1) {
			if(temp == -1) {
				a++;
			}
			else if(temp == 0) {
				b++;
			}
			else if(temp == 1){
				c++;
			}
			
			return;
		}
		
		//전체탐색
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
                
                // 배열에 다른 원소가 있을 때 종이를 9등분 한다
				if(temp != paper[i][j]) {
					for(int a=0 ; a<3 ; a++) {
						for(int b=0 ; b<3 ; b++) {
							int[][] ary = new int[n/3][n/3];    // 9등분 한 후의 하나의 종이 배열
							
                            // 잘려진 배열에 원소를 넣어준다
							if(n>3) {
								for(int c=0 ; c<n/3 ; c++) {
									for(int d=0 ; d<n/3 ; d++) {
										ary[c][d] = paper[c+(a*(n/3))][b*(n/3)+d];
									}
								}
							}
							else {    // n이 3이하일 때 처리
								ary[0][0] = paper[a][b];
							}
							
							check(n/3, ary); // 함수 재귀 호출
						}
					}
					return;
				}
			}
		}
		
        // 배열의 원소가 모두 같을 때
		if(temp == -1) {
			a++;
		}
		else if(temp == 0) {
			b++;
		}
		else if(temp == 1){
			c++;
		}
	}
}
