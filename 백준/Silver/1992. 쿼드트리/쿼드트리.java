import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static char[][] image;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		image = new char[N][N];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			for (int j = 0; j < N; j++) {
				image[i][j] = str.charAt(j);
			}
		}

		compress(N, 0, 0);

		bw.flush();
		bw.close();
	}

	public static void compress(int N, int x, int y) throws IOException {
		char first_val = image[x][y];

		for (int i = x; i < (N + x); i++) {
			for (int j = y; j < (N + y); j++) {
				if (first_val != image[i][j]) {
					bw.write("(");
					
					compress(N / 2, x, y);
					compress(N / 2, x, y + (N / 2));
					compress(N / 2, x + (N / 2), y);
					compress(N / 2, x + (N / 2), y + (N / 2));
					
					bw.write(")");
					
					return;
				}
			}
		}

		bw.write(first_val);
	}
}