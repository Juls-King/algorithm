import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();

		String[] strAry = input.split("");
		int strAry_len = strAry.length;

		int count = 0;
		int sticks = 0;

		for (int i = 0; i < strAry_len; i++) {
			switch (strAry[i]) {
			case "(":
				if (i < strAry_len - 1 && !strAry[i + 1].equals(")")) {
					count++;
				} else if (i < strAry_len - 1 && strAry[i + 1].equals(")")) {
					sticks += count;
				}

				break;
			case ")":
				if (i > 0 && !strAry[i - 1].equals("(")) {
					count -= 1;
					sticks += 1;
				}

				break;
			}
		}

		bw.write(sticks + "");
		bw.flush();
		bw.close();
	}
}