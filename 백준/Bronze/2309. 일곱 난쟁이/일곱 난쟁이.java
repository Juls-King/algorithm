import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = 9;

		int[] ary = new int[N];

		int sum = 0;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			ary[i] = num;
			sum += num;
		}

		Arrays.sort(ary);

		for (int i = 0; i < N - 1; i++) {
			for (int j = 1; j < N; j++) {
				if (sum - ary[i] - ary[j] == 100) {
					ary[i] = 0;
					ary[j] = 0;

					Arrays.sort(ary);

					for (int k = 2; k < N; k++) {
						System.out.println(ary[k]);
					}
					return;
				}
			}
		}
	}
}