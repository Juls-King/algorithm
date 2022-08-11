/*
문제
알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.

길이가 짧은 것부터
길이가 같으면 사전 순으로
입력
첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.

출력
조건에 따라 정렬하여 단어들을 출력한다. 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.

예제 입력 1 
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours

예제 출력 1 
i
im
it
no
but
more
wait
wont
yours
cannot
hesitate
*/
package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Baekjoon_1181 {
	
	public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
        	
			if (s1.length() > s2.length()) {
				return 1;
			} else if (s1.length() < s2.length()) {
				return -1;
			}
			
			return s1.compareTo(s2);
        }
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Set<String> set = new HashSet<String>();
		
		for(int i=0 ; i<N ; i++) {
			set.add(br.readLine());
		}
		
		ArrayList<String> alist = new ArrayList<>(set);	// set에는 sort 없어서 list사용
		
		alist.sort(new MyComparator());
		
		Iterator<String> iter = alist.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}