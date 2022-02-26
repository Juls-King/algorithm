package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_2805 {
	static int max_mid = 0;	// 절단기의 최대값을 담을 변수

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		s = br.readLine();
		st = new StringTokenizer(s);
		
		int[] ary = new int[N];		// 입력값 담을 배열
		
		for(int i=0 ; i<N ; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		} 
		
		Arrays.sort(ary);	// 알고리즘 해결을 위해 오름차순 정렬
		
		int start = 0;
		int end = ary[N-1];	// 배열 마지막 값이 나무의 최대 길이
		
		b_search(ary, M, start, end);	// 함수 호출
		
		System.out.println(max_mid);	// 결과 출력
	}
	
	public static void b_search(int[] ary, int M, int start, int end) {
		if(start > end) {	// 이진탐색 종료조건, 탐색 완료 시 종료
			return;
		}
		
		int mid = (start + end) / 2;	// 절단기의 높이
		
		long sum_tree = 0;	// 잘려진 나무들의 길이 합
		
		for(int i=0 ; i<ary.length ; i++) {
			if(ary[i] > mid) {		// 나무길이가 절단기보다 높아야 값에 더한다
				sum_tree += ary[i] - mid;
			}
		}
		
		if(sum_tree >= M) {	// 최소 가져가야할 나무길이보다 잘려진 나무길이가 같거나 클 때 
			if(mid > max_mid) {
				max_mid = mid;	// 절단기 높이가 높을수록 나무를 최소한으로 가져간다
			}
			
			b_search(ary, M, mid+1, end);	// mid+1, end 범위로 재귀호출
		}
		else {
			b_search(ary, M, start, mid-1);	// start, mid-1 범위로 재귀호출
		}
	}
}