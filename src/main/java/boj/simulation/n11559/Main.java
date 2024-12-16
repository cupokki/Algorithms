package boj.simulation.n11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/11559
 * PuyoPuyo
 * 12 * 6의 게임판에 5색의 뿌요(R,G,B,P,Y)가 배치된다.
 * 뿌요는 아래부터 채워져 있다. 앞으로 몇번의 연쇄가 일어나는가?
 * 상하좌우로 연결된 뿌요,
 * 같은 뿌요가 4개 이상 연결되면 터짐 (1연쇄)
 * 중력으로 인해 아래로 정렬, 다시 연결된 것이 있다면 터짐 (2연쇄)
 * 한 번에 여러 그룹이 터져도 1연쇄다.
 */
public class Main {
    static char[][] field = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int combo = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 뿌요 입력
        for(int i = 0; i < field.length; i++) {
            field[i] = br.readLine().toCharArray();
        }

        while(true) {
            int isChanged = 0;
            for(int r = 12 - 1; r >= 0; r--) {
                for(int c = 0; c < 6; c++) {
                    if(field[r][c] == '.' || visited[r][c])
                        continue;
                    isChanged += removePuyo(r,c);
                }
            }
            visited = new boolean[12][6];// 최적화 가능 같은 그룹 지우기 시도 계속함
            if(isChanged < 0){
                combo++;
                dropPuyo();
            }else {
                break;
            }

        }

        System.out.print(combo);
    }
    static int removePuyo(int r, int c) {
        //bfs로 뿌요 제거
        char puyo = field[r][c];
        int[] root = new int[]{r, c};
        Queue<int[]> q = new LinkedList<>();
        Set<int[]> selected = new HashSet<>();
        q.add(root);
        visited[r][c] = true;
        selected.add(root);

        int[] dx = {1, -1 ,0, 0};
        int[] dy = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int dir = 0; dir < 4; dir++){
                int nr = p[0] + dx[dir];
                int nc = p[1] + dy[dir];
                if(nr < 0 || nc < 0 || nr >= 12 || nc >= 6)
                    continue;
                if (!visited[nr][nc] && field[nr][nc] == puyo) {
                    int[] cur = new int[]{nr,nc};
                    selected.add(cur);
                    q.add(cur);
                    visited[nr][nc] = true;
                }
            }
        }
        if(selected.size() >= 4){
            for(int[] p : selected) {
                field[p[0]][p[1]] = '.';
            }
            return -1;
        }else {
            return 0;
        }
    }
    static void dropPuyo() {
        // 뿌요를 아래로 정렬
        for(int c = 0; c < 6; c++) {
            for(int r = 12 - 1; r >= 0; r--){
                if(field[r][c] != '.')
                    continue;
                for(int nr = r - 1; nr >= 0; nr--){
                    if(field[nr][c] != '.' && field[r][c] != field[nr][c]){
                        field[r][c] = field[nr][c];
                        field[nr][c] = '.';
                        break;
                    }
                }
            }
        }
    }
}
