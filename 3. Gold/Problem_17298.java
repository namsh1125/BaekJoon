import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n]; // 수열
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        // 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수
        int[] nge = new int[n]; // 오큰수
        nge[n - 1] = -1;

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                stack.push(arr[i]);
                continue;
            }

            // 두 번째 수부터
            while (!stack.isEmpty()) {
                if (arr[i] < stack.peek()) { // 나보다 더 큰 수인 경우
                    nge[i] = stack.peek();
                    break;
                }

                stack.pop();
            }

            if (stack.isEmpty()) {
                nge[i] = -1;
            }

            stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(nge[i]);
            if (i != n - 1)
                sb.append(" ");
        }

        System.out.println(sb.toString());
    }

}
