package algorithm;

import java.io.IOException;

import java.util.Arrays;
import java.util.Stack;

public class 뒤에있는큰수찾기 {

	public static void main(String[] args) throws IOException {
		int numbers[] = { 9, 1, 5, 3, 6, 2 };
		int[] answer = {};

		int num_cnt = numbers.length;

		answer = new int[num_cnt];

		Arrays.fill(answer, -1);

		Stack<Integer> stack = new Stack<Integer>();

		for (int i = num_cnt - 1; i > -1; i--) {

			while (!stack.empty() && stack.peek() <= numbers[i]) {
				stack.pop();
			}

			if (!stack.empty()) {
				answer[i] = stack.peek();
			}

			stack.push(numbers[i]);
		}

		System.out.println(Arrays.toString(numbers));
		System.out.println(Arrays.toString(answer));
	}

}