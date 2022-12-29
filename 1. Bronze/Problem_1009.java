/*
* 백준 1009번. 분산처리.
* https://www.acmicpc.net/problem/1009
* */
import java.util.Scanner;
//Todo: Fix Error
public class Problem_1009 {
    public static void main(String[] args){
        int a;
        int b;
        int n; // test case

        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        for(int i=0;i<n;i++){
            a = s.nextInt();
            b = s.nextInt();

            // 모든 숫자는 자신의 숫자를 여러 번 곱할 때 최대 4주기를 가진다.
            // N^4와 N^0은 다르므로 4를 추가
            b = b % 4 + 4;

            // 결과를 계산하고 저장
            // 0번 컴퓨터는 없다. % 했을 때 0이면 10으로 바꿔주자.
            int res = (int)Math.pow(a, b)%10;
            if(res == 0)
                System.out.println(10);
            else
                System.out.println(res);

        }

    }

}
