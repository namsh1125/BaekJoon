import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/*
 * 백준 1174번. 줄어드는 수
 * https://www.acmicpc.net/problem/1174
 */

public class Main {
    private static int n = -1;
    private static TreeSet<Long> list = new TreeSet<>();

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
        combination(0, new boolean[10]);

        if (n <= list.size()) {
            return list.stream().sorted()
                    .collect(Collectors.toList())
                    .get(n - 1);
        } else {
            return -1;
        }
    }

    private static void combination(int index, boolean[] used) {
        if (index == 10) {
            long num = 0;

            for (int i = 9; i >= 0; i--) {
                if (used[i]) {
                    num = num * 10 + i;
                }
            }

            list.add(num);

            return;
        }

        used[index] = true;
        combination(index + 1, used);

        used[index] = false;
        combination(index + 1, used);
    }

    private static void printResult(long result) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

}
