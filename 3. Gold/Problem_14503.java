import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    private static final int WALL = 1;

    private static final int[] di = new int[]{-1, 0, 1, 0}; // 북, 동, 남, 서
    private static final int[] dj = new int[]{0, 1, 0, -1}; // 북, 동, 남, 서

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번쨰 줄에 (n, m)
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        // 둘째 줄에 현재 위치 (r, c)와 로봇 청소기가 바라보는 방향 d
        s = br.readLine().split(" ");
        int r = Integer.parseInt(s[0]);
        int c = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);

        // 세번째 줄부터 N개의 줄에 해당하는 map
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        // 이제 그래프 탐색을 하면서 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 구함
        int count = 0;
        boolean[][] cleaned = new boolean[n][m];
        while (true) {
            // 1) 현재 칸이 아직 청소되지 않은 경우
            if (!cleaned[r][c]) {
                cleaned[r][c] = true;
                count++;
            }

            // 현재 칸의 주변 4칸이 모두 청소된 경우 확인
            boolean alreadyCleaned = true;
            for (int k = 0; k < 4; k++) {
                int ni = r + di[k];
                int nj = c + dj[k];

                if (!isMovable(ni, nj, map)) {
                    continue;
                }

                if (!cleaned[ni][nj]) { // 3) 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                    alreadyCleaned = false;
                    break;
                }
            }

            if (alreadyCleaned) { // 2) 현재 칸의 주변 4칸이 모두 청소된 경우
                int ni = r + di[(d + 2) % 4];
                int nj = c + dj[(d + 2) % 4];

                if (isMovable(ni, nj, map)) { // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있는지 여부
                    // 한 칸 후진하고 1번으로 돌아간다.
                    r = ni;
                    c = nj;

                } else { // 후진할 수 없는 경우
                    break; // 작동을 멈춘다.
                }

            } else { // 3) 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
                d = (d + 3) % 4; // 반시계 방향으로 90도 회전한다.
                int ni = r + di[d];
                int nj = c + dj[d];

                if (isMovable(ni, nj, map) && !cleaned[ni][nj]) {
                    r = ni;
                    c = nj;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isMovable(int i, int j, int[][] map) {
        return isInRange(i, j, map) && map[i][j] != WALL;
    }

    private static boolean isInRange(int i, int j, int[][] map) {
        return i >= 0 && i < map.length && j >= 0 && j < map[i].length;
    }
}
