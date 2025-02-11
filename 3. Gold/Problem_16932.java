/*
 * 백준 16932번. 모양 만들기
 * https://www.acmicpc.net/problem/16932
 */

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final int[] di = {-1, 1, 0, 0}; // 상하좌우
    private static final int[] dj = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M을 입력받는다.
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // N * M 크기의 2차원 배열을 입력받는다.
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 1이 연결된 위치를 찾아서 그룹을 만든다.
        HashMap<Integer, Integer> groupSizeMap = grouping(arr, N, M);

        // 0인 위치에 대해 인접한 그룹의 크기를 구하고, 가장 큰 모양의 크기를 구한다.
        int max = getMaxShapeSize(N, M, arr, groupSizeMap);

        // 결과를 출력한다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static HashMap<Integer, Integer> grouping(int[][] arr, int N, int M) {
        HashMap<Integer, Integer> groupMap = new HashMap<>();

        boolean[][] visited = new boolean[N][M];
        int groupNum = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    int size = dfs(arr, visited, i, j, groupNum);
                    groupMap.put(groupNum, size);
                    groupNum++;
                }
            }
        }

        return groupMap;
    }


    private static int dfs(int[][] arr, boolean[][] visited, int i, int j, int groupNum) {
        int size = 1;
        visited[i][j] = true;
        arr[i][j] = groupNum;

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            if (ni < 0 || ni >= arr.length || nj < 0 || nj >= arr[0].length) {
                continue;
            }

            if (arr[ni][nj] == 1 && !visited[ni][nj]) {
                size += dfs(arr, visited, ni, nj, groupNum);
            }
        }

        return size;
    }

    private static int getMaxShapeSize(int N, int M, int[][] arr, Map<Integer, Integer> groupSizeMap) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    int sum = 1;

                    Set<Integer> groupSet = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int ni = i + di[k];
                        int nj = j + dj[k];

                        if (!isInRange(ni, nj, arr)) {
                            continue;
                        }

                        if (arr[ni][nj] != 0) {
                            int groupNum = arr[ni][nj];
                            groupSet.add(groupNum);
                        }
                    }

                    for (int groupNum : groupSet) {
                        sum += groupSizeMap.get(groupNum);
                    }

                    max = Math.max(max, sum);
                }
            }
        }

        return max;
    }

    private static boolean isInRange(int i, int j, int[][] arr) {
        return i >= 0 && i < arr.length && j >= 0 && j < arr[0].length;
    }


}
