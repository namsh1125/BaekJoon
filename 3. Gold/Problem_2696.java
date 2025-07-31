import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    /**
     * 입력된 수열에서 홀수 번째 수를 읽을 때마다 중앙값을 출력하는 메서드
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int m = Integer.parseInt(br.readLine()); // 수열 길이
            int[] input = readInput(br, m);
            List<Integer> medians = calculateMedians(input);

            printMedians(sb, medians);

            if (t != 0) sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    /**
     * 주어진 수열 길이만큼 정수를 읽어 배열에 저장하는 메서드
     * 수는 한 줄에 10개씩 제공되며, 마지막 줄은 10개 미만일 수 있다.
     *
     * @param br    BufferedReader for input
     * @param size  수열의 길이
     * @return      입력된 정수 배열
     */
    public static int[] readInput(BufferedReader br, int size) throws IOException {
        int[] input = new int[size];
        int readCount = 0;

        while (readCount < size) {
            String[] tokens = br.readLine().split(" ");
            for (String token : tokens) {
                input[readCount++] = Integer.parseInt(token);
                if (readCount == size) break;
            }
        }

        return input;
    }

    /**
     * 입력된 정수 배열을 바탕으로 홀수 번째 수를 읽을 때마다 중앙값을 구하는 메서드
     *
     * @param input 정수 배열
     * @return      중앙값 리스트
     */
    public static List<Integer> calculateMedians(int[] input) {
        List<Integer> medians = new ArrayList<>();
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        PriorityQueue<Integer> right = new PriorityQueue<>(); // 최소 힙
        
        for (int i = 0; i < input.length; i++) {
            int num = input[i];

            // 1. left와 right의 크기가 같을 때 -> left에 추가
            if (left.size() == right.size()) {
                if (right.isEmpty() || num <= right.peek()) {
                    left.add(num);

                } else {
                    left.add(right.poll());
                    right.add(num);
                }

            // 2. left가 더 많을 때 → right에 추가
            } else {
                if (num >= left.peek()) {
                    right.add(num);
                } else {
                    right.add(left.poll());
                    left.add(num);
                }
            }

            // 현재까지 홀수 개의 수를 읽은 경우 → 중앙값 저장
            if (i % 2 == 0) {
                medians.add(left.peek());
            }
        }

        return medians;
    }

    /**
     * 중앙값 리스트를 출력 포맷에 맞게 StringBuilder에 저장하는 메서드
     * - 첫 줄: 중앙값의 개수
     * - 다음 줄들: 10개씩 끊어서 출력
     *
     * @param sb       출력용 StringBuilder
     * @param medians  중앙값 리스트
     */
    public static void printMedians(StringBuilder sb, List<Integer> medians) {
        sb.append(medians.size()).append("\n");

        for (int i = 0; i < medians.size(); i++) {
            sb.append(medians.get(i));

            if (i % 10 == 9) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
    }

}
