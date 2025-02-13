/*
 * 백준 20438번. 출석체크
 * https://www.acmicpc.net/problem/20438
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1번째 줄 입력 받기
        int N = Integer.parseInt(st.nextToken()); // 학생의 수 (1 ≤ N ≤ 5,000)
        int K = Integer.parseInt(st.nextToken()); // 졸고 있는 학생의 수 (1 ≤ K ≤ N ≤ 5,000)
        int Q = Integer.parseInt(st.nextToken()); // 지환이가 출석 코드를 보낼 학생의 수 (1 ≤ Q ≤ N ≤ 5,000)
        int M = Integer.parseInt(st.nextToken()); // 주어질 구간의 수 (1 ≤ M ≤ 50,000)

        // 2번째 줄 입력 받기 (졸고 있는 학생의 입장 번호)
        st = new StringTokenizer(br.readLine());

        boolean[] isSleeping = new boolean[N + 3];
        for (int i = 0; i < K; i++) {
            isSleeping[Integer.parseInt(st.nextToken())] = true;
        }

        // 3번째 줄 입력 받기 (출석 코드를 받을 학생의 입장 번호)
        st = new StringTokenizer(br.readLine());

        // 출석체크를 진횅하는 학생들
        boolean[] isChecked = new boolean[N + 3];
        for (int i = 0; i < Q; i++) {
            int student = Integer.parseInt(st.nextToken());
            if (isSleeping[student]) continue;
            for (int j = student; j < N + 3; j += student) {
                if (!isSleeping[j]) {
                    isChecked[j] = true;
                }
            }
        }

        // 누적합을 통해 출석체크를 진행하지 않은 학생들을 구하기
        // 이럴 경우 추후 S ~ E 구간의 학생들을 구할 때 O(1)에 구할 수 있다.
        int[] prefixSum = new int[N + 3];
        for (int i = 3; i < N + 3; i++) {
            prefixSum[i] = prefixSum[i - 1] + (isChecked[i] ? 0 : 1);
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            // 특정 구간의 시작과 끝을 입력 받기
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // 구간의 시작 (3 ≤ S)
            int E = Integer.parseInt(st.nextToken()); // 구간의 끝 (E ≤ N + 2)

            // S부터 E까지의 구간에 대해서 출석체크를 진행하지 않은 학생들의 수를 구한다.
            int count = prefixSum[E] - prefixSum[S - 1];
            answer.append(count).append("\n");
        }

        // M개의 줄에 걸쳐서 각 구간에 대해서 출석이 되지 않은 학생들의 수를 출력하라.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer.toString());
        bw.flush();
        bw.close();

    }
}
