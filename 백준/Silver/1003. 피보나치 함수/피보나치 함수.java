import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] memo = new int[41][2];	//메모이제이션으로 사용할 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] ary_T = new int[N];
		
		for(int i=0 ; i<N ; i++) {
			ary_T[i] = Integer.parseInt(br.readLine());
		}
		
		// 배열 첫번째 인덱스의 값 세팅
		memo[0][0] = 1;
		memo[0][1] = 0;

		for(int i=1 ; i<memo.length ; i++) {
			// 피보나치 함수 실행결과 다음과 같은 규칙으로 0과 1의 개수가 증가한다
			// memo배열 전체를 먼저 한번 세팅해 주는것이 더 효율적이다
			memo[i][0] = memo[i-1][1];
			memo[i][1] = memo[i-1][0] + memo[i][0];
		}
		
		for(int i=0 ; i<N ; i++) {
			System.out.println(memo[ary_T[i]][0] + " " + memo[ary_T[i]][1]);
		}
	}
}
