import java.util.Scanner;
import java.util.stream.Stream;

public class Problem_1009 {
    public static void main(String[] args){
        int a;
        int b;
        int n; //test case
        int result;

        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        for(int i=0;i<n;i++){
            a = s.nextInt();
            b = s.nextInt();

            // 모든 숫자는 자신의 숫자를 여러번 곱하더라도 최대 4주기마다 똑같은 숫자가 반복된다
            b = b % 4;

            // 결과 값 계산
            result = (int)Math.pow(a, b)%10;

            // 결과 출력
            System.out.println(result);
        }
    }

}
