package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SWEA_12004 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] input = new int[N];			// 입력값을 담을 배열
		String[] check = new String[N];		// 결과값을 담을 배열
		Arrays.fill(check, "No");			// 전체를 "No"로 초기화

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= 9; i++) {		// 모든 경우의 수를 탐색
			for (int j = 1; j <= 9; j++) {
				for (int k = 0; k < N; k++) {	// 입력배열의 모든 index를 검사한다 
					if (input[k] == i * j) {	// 두 수의 곱이 입력값과 일치할 때 결과 배열의 값을 "Yes"로 변경한다
						check[k] = "Yes";
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			bw.write("#" + (i+1) + " " + check[i] + "\n");
		}

		bw.flush();
		bw.close();
	}
}