package boj.simulation.n3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/3190
 * 뱀
 * 도스게임 더미.
 * 사과를 먹으면 뱀이 길어짐, 벽이나 자기몸에 머리가 닿으면 끝
 * N By N 크기의 보드위에 가장자리는 벽이 있다.
 * 시작위치 1,1. 뱀의 초기 길이는 1, 오른쪽으로움직임
 *
 */
public class Main {
    static int N, K, L;
    static char[][] board;
    static int time = 0;
    static Deque<int[]> snake = new LinkedList<>();
    static int dir = 1;
    static int r = 0;
    static int c = 0;
    static Map<Integer, Character> commands = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine()); // 사과 수
        board = new char[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(board[i], '_');
        }
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            board[r][c] = '*';
        }

        L = Integer.parseInt(br.readLine());
        for (int l = 0; l < L; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            commands.put(r,c);
        }

        snake.offer(new int[]{r, c});
        board[r][c] = 's';
        runGame(); // dir
        System.out.println(time);
    }
    static void runGame() {
        while (true) {
            Character c = commands.get(time);
            if (c != null) {
                if (c == 'L') dir = (dir - 1 + 4) % 4;
                else if (c == 'D') dir = (dir + 1) % 4;
            }
            time++;
            if(!canMove()) break;
            moveSnake();
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = { 0, 1, 0, -1};
    static void moveSnake() {
        r += dr[dir];
        c += dc[dir];
        snake.offerFirst(new int[]{r, c}); // 머리추가
        if(board[r][c] != '*') {
            int[] p = snake.pollLast();
            board[p[0]][p[1]] = '_';
        }
        board[r][c] = 's';

    }
    static boolean canMove() {
        int nr = r + dr[dir]; // 머리가 벽이나 몸에 닿으면
        int nc = c + dc[dir];

        if(nr < 0 || nc < 0 || nr >= N || nc >= N)
            return false;
        for (int[] p : snake) {
            if (p[0] == nr && p[1] == nc)
                return false;
        }
        return true;
    }
}
