import java.util.*;

public class Main{
	public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		String[] ary = str.split("\\s");
		
		double D = Integer.parseInt(ary[0]);
		double H_rate = Integer.parseInt(ary[1]);
		double W_rate = Integer.parseInt(ary[2]);
		
		double D_rate = Math.sqrt((H_rate * H_rate) + (W_rate * W_rate));    //대각선의 비율
		
		int H = (int) ((D * H_rate) / D_rate);    //비례식에 의해 높이 계산
		int W = (int) ((D * W_rate) / D_rate);    //비례식에 의해 너비 계산
		
		System.out.println(H + " " + W);
	}
}