import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		ArrayList<Integer> alist = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			alist.add(i + 1);
		}
		
		int index = K;
		
		for (int i = 0; i < N; i++) {
			int size = alist.size();
			index = (index - 1) % size;

			if(i == 0) {
				System.out.print("<");
			}
			
			System.out.print(alist.get(index));
			
			if(i == N - 1) {
				System.out.print(">");
			}
			else {
				System.out.print(", ");
			}
			
			alist.remove(index);
			
			index += K;
		}
	}
}