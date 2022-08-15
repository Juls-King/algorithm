import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long max_mid = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		s = br.readLine();
		st = new StringTokenizer(s);
		
		int[] ary = new int[N];
		
		for(int i=0 ; i<N ; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		} 
		
		Arrays.sort(ary);
		
		int start = 0;
		int end = ary[N-1];
		
		b_search(ary, M, start, end);
		
		System.out.println(max_mid);
	}
	
	public static void b_search(int[] ary, int M, int start, int end) {
		if(start > end) {
			return;
		}
		
		int mid = (start + end) / 2;
		
		long sum_tree = 0;
		
		for(int i=0 ; i<ary.length ; i++) {
			if(ary[i] > mid) {
				sum_tree += ary[i] - mid;
			}
		}
		
		if(sum_tree >= M) {
			if(mid > max_mid) {
				max_mid = mid;
			}
			
			b_search(ary, M, mid+1, end);
		}
		else {
			b_search(ary, M, start, mid-1);
		}
	}
}