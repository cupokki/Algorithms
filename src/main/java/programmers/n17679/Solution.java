package programmers.n17679;

import java.util.*;

public class Solution {
    // 2x2로 같은 블록이 위치하면 블록을 제거한다.
    // 제거된 블록은 아래방향으로 떨어진다.
    // 다시 제거를 반복하여 제거할 것이 없을때까지 수행
    // 제거된 블록의 수를 구하여라
    static char[][] gameBoard;
    static int M;
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static int solution(int m, int n, String[] board) {
        gameBoard = new char[m][n];
        M = m;
        N = n;

        int answer = 0;

        //initiate board
        for (int i = 0; i < M; i++) {
            gameBoard[i] = board[i].toCharArray();
        }

        while(true) {
            boolean[][] removed = new boolean[M][N];
            for (int i = 0; i < M - 1; i++) {
                for (int j = 0; j < N - 1; j++) {
                    char c = gameBoard[i][j];
                    if (c == ' ') {
                        continue;
                    }
                    if (
                            c == gameBoard[i][j + 1] &&
                            c == gameBoard[i + 1][j] &&
                            c == gameBoard[i + 1][j + 1]
                    )  {
                        removed[i][j] = true;
                        removed[i][j + 1] = true;
                        removed[i + 1][j] = true;
                        removed[i + 1][j + 1] = true;
                    }
                    //
                }
            }

            int cnt = 0;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if(removed[i][j]) {
                        gameBoard[i][j] = ' ';
                        cnt++;
                    }
                }
            }

            dropBlock();

            if (cnt == 0) {
                break;
            }
            answer += cnt;
        }

        return answer;
    }

    static void dropBlock() {

        for (int i = 0; i < N; i++) {
            Stack<Character> line = new Stack<>();
            for (int j = 0; j < M; j++) {
                if(gameBoard[j][i] != ' ') {
                    line.push(gameBoard[j][i]);
                }
                gameBoard[j][i] = ' ';

            }
            int row = M - 1;
            while(!line.isEmpty()) {
                gameBoard[row--][i] = line.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 2, new String[]{"CB", "CB"}));
        System.out.println(solution(2, 2, new String[]{"CC", "CC"}));
//        System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
//        System.out.println(solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}));
    }
}

