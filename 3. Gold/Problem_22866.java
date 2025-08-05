import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int MIN = -999_999_999;
    private static int MAX = 999_999_999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 건물의 개수 (1 <= N <= 100,000)
        int[] height = new int[n]; // 건물 높이를 저장하는 배열 (1 <= height[i] <= 100,000)

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(s[i]);
        }

        if (n == 1) {
            System.out.println("0");
            return;
        }

        int[] canWatch = new int[n]; // 내가 볼 수 있는 건물의 수
        int[][] neareast = new int[n][2]; // 내 주변 가장 가까운 건물의 번호 (j: 0 -> 왼쪽 / j: 1 -> 우측)

        for (int i = 0; i < n; i++) {
            neareast[i][0] = MIN;
            neareast[i][1] = MAX;
        }

        // 나를 기준으로 왼쪽 보기
        Stack<Integer> left = new Stack<>(); // i번째 건물을 기준으로 왼쪽 건물의 높이를 저장하는 스택
        for (int i = 0; i < n; i++) { // 모든 건물에 대하여
            if (i == 0) { // 첫 번째 건물인 경우
                left.push(i);
                continue;
            }

            while (!left.isEmpty()) {
                if (height[i] < height[left.peek()]) { // 내 높이보다 더 높은 경우 (볼 수 있는 경우)
                    canWatch[i] += left.size();
                    neareast[i][0] = left.peek();
                    break;
                }

                left.pop();
            }

            left.add(i);
        }

        // 나를 기준으로 우측 보기
        Stack<Integer> right = new Stack<>(); // i번째 건물을 기준으로 우측 건물의 높이를 저장하는 스택
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                right.push(i);
                continue;
            }

            while (!right.isEmpty()) {
                if (height[i] < height[right.peek()]) {
                    canWatch[i] += right.size();
                    neareast[i][1] = right.peek();
                    break;
                }

                right.pop();
            }

            right.add(i);
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(canWatch[i]);

            if (canWatch[i] > 0) {
                sb.append(" ");

                int leftLength = i - neareast[i][0];
                int rightLength = neareast[i][1] - i;

                if (leftLength == rightLength) {
                    sb.append(neareast[i][0] + 1);
                } else {
                    if (leftLength < rightLength) {
                        sb.append(neareast[i][0] + 1);
                    } else {
                        sb.append(neareast[i][1] + 1);
                    }
                }

            }

            if (i != n - 1) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}
