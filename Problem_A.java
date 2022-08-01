package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_A {
	static int N;	// 몬스터의 수
	static int[] powers;	// 몬스터의 공격력 저장 배열
	static int[] residents;		// 마을의 주민 수 저장 배열
	static boolean visited[];	// 마을 방문여부 저장 배열

	static int max_save = 0;	// 최대값 저장 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	// 시루의 초기 체력

		powers = new int[N];
		residents = new int[N];
		visited = new boolean[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			powers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			residents[i] = Integer.parseInt(st.nextToken());
		}

		int min_power = Arrays.stream(powers).min().getAsInt();	// 배열의 최소값 구하는 신박한 방법
		if (min_power > K) {
			System.out.println(0);
			return;
		}

		solution(K, 0, 0, 0);	// 초기체력, 몬스터의 공격력, 주민 수, 뎁스

		System.out.println(max_save);

	}

	public static void solution(int life, int power, int save, int depth) {
		if (depth == N || life <= 0) {	// 모든마을 다 방문 or 체력이 0이하
			if (save > max_save) {
				max_save = save;	// 최대값으로 갱신
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] == false) {
				visited[i] = true;

				int temp_power = power + powers[i];	// 이전 마을 몬스터들의 공격력이 계속 더해짐
				
				int temp_life = life - temp_power;	// 남은 체력

				int temp_save = save + residents[i];	// 구한 주민 수

				if (temp_life < 0) {	// 체력이 0보다 작을경우 구한 주민 수 이전로 돌림
					temp_save = save;
				}

				solution(temp_life, temp_power, temp_save, depth + 1);

				visited[i] = false;
			}
		}
	}
}