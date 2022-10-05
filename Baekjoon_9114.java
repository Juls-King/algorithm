package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_9114 {
	static int N;
	static char[][][] map;
	static int[][][] pos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		map = new char[N][8][8];
		pos = new int[N][2][2];
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<8 ; j++) {
				String str = br.readLine();
				
				for(int k=0 ; k<8 ; k++) {
					map[i][j][k] = str.charAt(k);
				}
			}
			
			for(int j=0 ; j<2 ; j++) {
				st = new StringTokenizer(br.readLine());
				
				pos[i][j][0] = Integer.parseInt(st.nextToken());
				pos[i][j][1] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0 ; i<5 ; i++) {
			
		}
		
		print(0);
		
		
		
	}
	
	public static void print(int i) {
		for(int j=0 ; j<8 ; j++) {
			for(int k=0 ; k<8 ; k++) {
				System.out.print(map[i][j][k]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void print() {
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<8 ; j++) {
				for(int k=0 ; k<8 ; k++) {
					System.out.print(map[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<2 ; j++) {
				for(int k=0 ; k<2 ; k++) {
					System.out.print(pos[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
