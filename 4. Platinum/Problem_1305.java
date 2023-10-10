/**
 * 백준 1305번. 광고
 * https://www.acmicpc.net/problem/1305
 * Reference: https://woo-niverse.tistory.com/230
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int l; // 광고의 길이, 1 <= l <= 1_000_000
    private static String ad; // 광고

    public static void main(String[] args) throws Exception {
        // 입력을 받아 변수에 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        ad = br.readLine();

        // < 로직 >
        // 전광판의 크기로 잘린 문자의 길이를 찾는다.
        // KMP 알고리즘을 사용하여 해당 광고에서 가장 길이가 긴 접미사의 길이를 찾고
        // 광고의 길이에서 빼주면 최소 광고의 길이를 알 수 있다.

        int[] pi = new int[l]; // 동일한 글자의 접두사와 접미사가 가장 긴 길이를 저장하는 배열
        int length = 0; // 이전 글자에서 가장 긴 동일한 접두사와 접미사의 길이

        for (int i = 1; i < l; i++) {
            // 접두사와 접미사가 같은 가장 긴 길이의 단어에서
            // 접두사와 접미사의 다음 글자가 다른 경우
            while (length > 0 && ad.charAt(i) != ad.charAt(length)) {
                length = pi[length - 1]; // 이전위치까지 일치한 index로 돌아간다
            }

            // 접두사와 접미사가 같은 가장 긴 길이의 단어에서
            // 접두사와 접미사의 다음 글자가 같은 경우
            if (ad.charAt(i) == ad.charAt(length)) {
                pi[i] = ++length; // 현재까지 길이에서 한 글자를 추가한다
            }
        }

        // 결과
        int result = l - pi[l - 1]; // 광고의 길이에서 해당 광고의 가장 긴 접미사의 길이를 빼준다.
        System.out.println(result); // 최소 광고의 길이를 출력한다.
    }

}
