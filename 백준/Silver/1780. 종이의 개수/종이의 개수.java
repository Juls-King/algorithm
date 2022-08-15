import java.io.*;
import java.util.*;

public class Main {
    static int a = 0;
	static int b = 0;
	static int c = 0;
    
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
		
		check(N, paper);
		
		// 결과 출력
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
    }
    
    public static void check(int n, int[][] paper) {
		int temp = paper[0][0];	// 배열의 첫번째 값 대입
		
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
				if(temp != paper[i][j]) {
					//종이 잘라야됨
					
					for(int a=0 ; a<3 ; a++) {
						for(int b=0 ; b<3 ; b++) {
							int[][] ary = new int[n/3][n/3];
							
							if(n>3) {
								for(int c=0 ; c<n/3 ; c++) {
									for(int d=0 ; d<n/3 ; d++) {
										ary[c][d] = paper[c+(a*(n/3))][b*(n/3)+d];
									}
								}
							}
							else {
								ary[0][0] = paper[a][b];
							}
							
							check(n/3, ary);
						}
					}
					return;
				}
			}
		}
		
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
