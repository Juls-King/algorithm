import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a,b,c,d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		
		int[][] pants = new int[N][2];
		
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			
			pants[i][0] = Integer.parseInt(st.nextToken());
			pants[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0 ; i<N ; i++) {
			int u_i = pants[i][0];
			int v_i = pants[i][1];
			
			int u = func(v_i);
//			System.out.print(v_i + " ");
//			System.out.print(u_i + " ");
//			System.out.println(u);
			
			if(u == u_i) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static int func (int x) {
		return (int) Math.max(a * Math.pow((x-b), 2) + c, d);
	}
}