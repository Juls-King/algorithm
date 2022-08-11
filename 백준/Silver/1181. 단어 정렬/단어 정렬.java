import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
	
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
		
//		System.out.println(set);
		
		ArrayList<String> alist = new ArrayList<>(set);
		
		alist.sort(new MyComparator());
		
//		System.out.println(alist);
		
		Iterator<String> iter = alist.iterator(); // set을 Iterator 안에 담기
		while(iter.hasNext()) { // iterator에 다음 값이 있다면
			System.out.println(iter.next()); // iter에서 값 꺼내기
		}
	}

}
