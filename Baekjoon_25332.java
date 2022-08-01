package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_25332 {
	static int[] A;
	static int[] B;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;
		
		int N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		B = new int[N];
		
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		
		for(int i=0 ; i<N ; i++) {
			A[i] = Integer.parseInt(st1.nextToken());
			B[i] = Integer.parseInt(st2.nextToken());
		}
		
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				if(i > j) {
					continue;
				}
				
				solution(i, j);
			}
		}
		
		System.out.println(count);
	}
	
	public static void solution(int i, int j) {
		int sum_A = 0;
		int sum_B = 0;
		
		for(int a=i ; a<=j ; a++) {
			sum_A += A[a];
			sum_B += B[a];
		}
		
		if(sum_A == sum_B) {
			count++;
		}
	}

}
