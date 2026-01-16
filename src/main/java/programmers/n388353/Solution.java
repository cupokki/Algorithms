package programmers.n388353;

import java.util.*;

public class Solution {
    /*
    물류창고, n*m 공간(50 * 50 최대)
    요청시 "접근 가능한" 같은 종류의 물건을 모두 꺼낸다.
    접근이 가능한 컨테이너란 창고는 최소 한 면이 외우와 닿아 있는 것

    최근 크레인 도입, 모든 요청을 꺼낼 수 있음

    요청은 알파벳 문자열이며, 한글자는 지게차를 이용하고, 한글자가 연속시엔 크레인을 이용한다.

    요청을 모두 수행한 후 남은 컨테이너의 수를 반환하라.

    bfs가 생각나지만 테두리부터 벗겨가는 느낌으로 하면 어떨까?
    bfs는 매 수행 마다. 공백부터 시작해서 하니까 필요없는 영역을 훑는다.
    그냥 초기 테두리의 좌표를 배열에 넣고 돌리면 되지않을까?
     */
//    static int n;
//    static int m;
//    static int remain;
//    static char[][] map;
//    public static int solution(String[] storage, String[] requests) {
//        n = storage.length + 2;
//        m = storage[0].length() + 2;
//        map = new char[n][m];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(map[i], ' ');
//        }
//        remain = storage.length * storage[0].length();
//        for (int i = 1; i <= storage.length; i++) {
//            for (int j = 1; j <= storage[0].length(); j++) {
//                map[i][j] = storage[i - 1].charAt(j - 1);
//            }
//        }
//
//        int answer = 0;
//        for (String req : requests) {
//            char target = req.charAt(0);
//            if (req.length() == 2) {
//                // 컨테이너
//                for (int i = 1; i < n - 1; i++) {
//                    for (int j = 1; j < m - 1; j++) {
//                        if (map[i][j] == target) {
//                            map[i][j] = ' ';
//                            remain--;
//                        }
//                    }
//                }
//            } else {
//                // 지게차
//                bfs(0, 0, target);
//            }
//        }
//        answer = remain;
//        return answer;
//    }
//
//    static int[] dr = {1, -1, 0, 0};
//    static int[] dc = {0, 0, 1, -1};
//    static void bfs(int sr, int sc, char target) {
//        Queue<int[]> q = new LinkedList<>();
//        boolean[][] visited = new boolean[n][m];
//        visited[sr][sc] = true;
//        q.offer(new int[]{sr, sc});
//        while(!q.isEmpty()) {
//            int[] state = q.poll();
//            for (int d = 0; d < 4; d++) {
//                int nr = state[0] + dr[d];
//                int nc = state[1] + dc[d];
//                if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
//                    continue;
//                }
//                if (visited[nr][nc]) {
//                    continue;
//                }
//                if (map[nr][nc] == ' ') {
//                    visited[nr][nc] = true;
//                    q.offer(new int[]{nr, nc});
//                }
//                if (map[nr][nc] == target) {
//                    remain--;
//                    map[nr][nc] = ' ';
//                    visited[nr][nc] = true;
//                }
//            }
//        }
//    }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        char[][] containers = new char[n][m];
        int answer = n * m;
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[0].length(); j++) {
                containers[i][j] = storage[i].charAt(j);
            }
        }
        boolean[][] candidates = new boolean[n][m]; // Set을 생각했는데 굳이 Pos를 구현할 필요없다.
        for (int i = 0; i < n; i++) {
            candidates[i][0] = true;
            candidates[i][m - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            candidates[0][i] = true;
            candidates[n - 1][i] = true;
        }

        for (String req : requests) {
            char target = req.charAt(0);
            List<int[]> temp = new ArrayList<>();
            if (req.length() == 2) {
                // 컨테이너
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (containers[i][j] == target) {
                            containers[i][j] = ' ';
                            answer--;
                            temp.add(new int[]{i, j});
                            // 한번에 지워지고 외부와 닿는게 있는지 수정해야한다.
                            // 근데 길고 좁게 연결되어 있다면? 결국 bfs가 필요하구나
                        }
                    }
                }
            } else {
                // 지게차
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!candidates[i][j]) {
                            continue;
                        }
                        if (containers[i][j] == target) {
                            containers[i][j] = ' ';
                            answer--;
                            temp.add(new int[]{i, j});
                        }
                    }
                }
            }
            for (int[] pos : temp) {
                candidates[pos[0]][pos[1]] = false;
                for (int d = 0; d < 4; d++) {
                    int nr = pos[0] + dr[d];
                    int nc = pos[1] + dc[d];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                        continue;
                    }
                    candidates[nr][nc] = true;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"}, new String[]{"A", "BB", "A"}));
        System.out.println(solution(new String[]{"HAH", "HBH", "HHH", "HAH", "HBH"}, new String[]{"C", "B", "B", "B", "B", "H"}));
    }
}
