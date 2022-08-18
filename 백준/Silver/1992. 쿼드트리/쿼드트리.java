import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[][] image = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			for (int j = 0; j < N; j++) {
				image[i][j] = str.charAt(j);
			}
		}

		compress(N, image);
	}

	public static void compress(int N, char[][] image) {
		char first_val = image[0][0];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (first_val != image[i][j]) {
					System.out.print("(");
					
					for (int a = 0; a < 2; a++) {
						for (int b = 0; b < 2; b++) {
							char[][] ary = new char[N / 2][N / 2];

							if (N > 2) {
								for (int c = 0; c < N / 2; c++) {
									for (int d = 0; d < N / 2; d++) {
										ary[c][d] = image[c + (a * (N / 2))][b * (N / 2) + d];
									}
								}
							} else {
								ary[0][0] = image[a][b];
							}

//							System.out.print("(");
							compress(N / 2, ary);
//							System.out.print(")");
						}
					}
					
					System.out.print(")");

					return;
				}
			}
		}

		System.out.print(first_val);

	}

}