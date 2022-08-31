package algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class KS1 {

	public static void main(String[] args) {
		int[] coins = { 1, 5, 10, 50, 100, 500 };

		int[] costs = { 1, 4, 99, 35, 50, 1000 };
//      int[] costs = { 2, 11, 20, 100, 200, 600 };

		int money = 4579;
//      int money = 1999;

		int result = 0;

		// result : 2308, 2798
		// cate[i]-costs[i] = 0, 1, -89, 15, 50, -500
		// 0, 100, -4450, 150, 250, -500

		int[][] ary = new int[6][3];

		for (int i = 0; i < ary.length; i++) {
			ary[i][0] = coins[i];
			ary[i][1] = costs[i];
			ary[i][2] = (coins[i] - costs[i]) * (coins[5] / coins[i]); // 1. coin에서 cost를 빼서 각 코인의 생산 효율을 구한다.
																	   // 2. 각 코인의 생산효율을 500원과 같은 가치일 때 값을 구한다.
																	   // 3. 최종값의 크기순으로 최소비용을 쉽게 계산할 수 있다.
		}

		Arrays.sort(ary, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) { // sort함수 재정의. 2번 인덱스의 값으로 정렬하도록
				return o2[2] - o1[2];
			}
		});

		for (int i = 0; i < ary.length; i++) {
			int a = money / ary[i][0]; // 몫
			int b = money % ary[i][0]; // 나머지

			result += a * ary[i][1]; // 몫 x 동전가치

			money = b; // 나머지 값으로 초기화

			if (money == 0) {
				break;
			}
		}

		System.out.println(result);
	}
}