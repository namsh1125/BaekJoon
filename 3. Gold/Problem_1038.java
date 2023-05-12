import java.util.*;
import java.io.*;

/*
 * 백준 1038번. 감소하는 수
 * https://www.acmicpc.net/problem/1038
 */

public class Main {
    private static int n;
    private static ArrayList<Long> downNum = new ArrayList();

    public static void main(String[] args) throws IOException {
        initVariable();
        long result = getResult();
        printResult(result);
    }

    private static void initVariable() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        br.close();
    }

    private static long getResult() {
        getDownNum();

        if (downNum.size() <= n) return -1L;
        else return downNum.get(n);
    }

    private static void getDownNum() {
        combination(new ArrayList(), 0);
        Collections.sort(downNum);
    }

    private static void combination(ArrayList<Integer> used, int num) {
        if (num == 10) {
            if (used.size() != 0) {
                long value = 0;

                for (int i = used.size() - 1; i >= 0; i--) {
                    value = value * 10 + used.get(i);
                }

                downNum.add(value);
            }

            return;
        }

        used.add(num);
        combination(used, num + 1);

        used.remove(used.size() - 1);
        combination(used, num + 1);
    }

    private static void printResult(long result) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
