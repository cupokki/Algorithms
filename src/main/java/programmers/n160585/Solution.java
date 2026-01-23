package programmers.n160585;

public class Solution {
    /*
    혼자서 틱택토를 한다.
    O, X 순으로 번갈아 수행한다.

    실수를 하여, 순서를 잘못적거나, 게임이 종료되었는데 진행하는 경우가 있다.
    Board의 현상태가 주어질때, 정상적인 상태읹 판별하는 프로그램을 작성한다. (정상: 1, 비정상: 0)
    f, s는 각각 선공, 후공의 문자 개수. f = s이거나, f = s - 1을 항상 만족한다.
    선공, 후공이 둘다 게임 이긴 경우가 존재하는 경우.
    */
    public int solution(String[] board) {
        int answer = 1;
        int f = 0, s = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') f++;
                if (board[i].charAt(j) == 'X') s++;
            }
        }

        boolean fWon = false, sWon = false;
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                if (board[i].charAt(0) == 'O') fWon = true;
                else if (board[i].charAt(0) == 'X') sWon = true;
            }
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (board[0].charAt(i) == 'O') fWon = true;
                else if (board[0].charAt(i) == 'X') sWon = true;
            }
        }
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if (board[1].charAt(1) == 'O') fWon = true;
            else if (board[1].charAt(1) == 'X') sWon = true;
        }
        if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
            if (board[1].charAt(1) == 'O') fWon = true;
            else if (board[1].charAt(1) == 'X') sWon = true;
        }
        if (f < s || f > (s + 1))   answer = 0;
        if (fWon && sWon)           answer = 0;
        if (fWon && f != s + 1)     answer = 0;
        if (sWon && s != f)         answer = 0;

        return answer;
    }
}
