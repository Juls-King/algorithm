package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_25376 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] switch_status = new int[N + 1];
		ArrayList<Integer>[] related_switch = new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			switch_status[i] = Integer.parseInt(st.nextToken());
			
			related_switch[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			
			for(int j=1 ; j<=m ; j++) {
				related_switch[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		if(lightAll(N, switch_status) == true) {
			System.out.println(0);
			return;
		}
		
		int result;
		
		result = solution(1, N, switch_status, related_switch);

		System.out.println(result);
	}
	
	public static int solution(int i, int N, int[] switch_status, ArrayList<Integer>[] related_switch) {
		Queue<Integer> q = new LinkedList<>();
		int count = 0;

		q.offer(i);
//		visited[i - 1] = true;

		while (!q.isEmpty()) {
			int temp = q.poll();
//			System.out.print(temp + " ");

			for (int j = 1; j <= N; j++) {
				if (switch_status[j] == 0) {
					q.offer(j);
					switchOn(j, switch_status, related_switch);
					count++;
//					print(N, switch_status);
//					visited[j] = true;
				}
				
				if(count > N) {
					return -1;
				}
			}
		}
		
		if(count == 0) {
			return -1;
		}
		
		return count;
	}
	
	public static boolean lightAll(int N, int[] switch_status) {
		int count = 0;
		
		for(int i=1 ; i<=N ; i++) {
			if(switch_status[i] == 1) {
				count++;
			}
		}
		
		if(count == N) {
			return true;
		}
		
		return false;
	}
	
	public static void switchOn(int k, int[] switch_status, ArrayList<Integer>[] related_switch) {
		switch_status[k] = 1;
		
		int len = related_switch[k].size();
		for(int i=0 ; i<len ; i++) {
			int val = related_switch[k].get(i);
			
			switch_status[val] = (switch_status[val] == 0) ? 1 : 0;
		}
	}
	
	public static void print(int N, int[] switch_status) {
		for(int i=1 ; i<=N ; i++) {
			System.out.print(switch_status[i] + " ");
		}
		System.out.println();
	}
}
