import java.util.*;

public class Main { 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.next();
        
        int answer;

        String[] array = str.split("-");
        
        for(int i=0 ; i<array.length ; i++){
            String[] array2 = array[i].split("\\+");
            int temp = 0;
            
            for(int j=0 ; j<array2.length ; j++){
                temp += Integer.parseInt(array2[j]);
            }
            
            array[i] = Integer.toString(temp);
        }
        
        answer = Integer.parseInt(array[0]);
        
        for(int i=1 ; i<array.length ; i++){
            int num = Integer.parseInt(array[i]);
            answer = answer - num;
        }
        
        System.out.println(answer);
    } 
}
