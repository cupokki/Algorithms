package programmers.n81303;

import java.util.*;

public class Solution {
    /*
    U X : 현재서 x칸 위
    D X : 현재서 X칸 밑
    C: 현재 행 삭제후 아래행 선택(마지막 행이라면 첫번쨰로 이동)
    Z: 선택행 유지한채 최근 삭제 복구
    
    원래 테이블에서 삭제된 테이블을 표시하는 문자열을 출력하라.
    명령은 1000개, 행은 1000개

    */
    public static String solution(int n, int k, String[] cmd) {
        String answer = "";

        int[][] nodes = new int[n][2];

        for (int i = 0; i < n; i++) {
            nodes[i][0] = i - 1;
            nodes[i][1] = i + 1;
        }

        nodes[n - 1][1] = -1; // 마지막노드는 아무것도 가리키지 않는다.


        Stack<Integer> stack = new Stack<>();
        int cur = k;

        for (String s : cmd) {
            String[] arg = s.split(" ");
            int delta = 0;
            if (arg.length == 2) delta = Integer.parseInt(arg[1]);
            switch (arg[0]) {
                case "U":
                    while(delta-- > 0) cur = nodes[cur][0];
                    break;
                case "D":
                    while(delta-- > 0) cur = nodes[cur][1];
                    break;
                case "C":
                    stack.push(cur);
                    int prev = nodes[cur][0];
                    int next = nodes[cur][1];
                    // 연결
                    if (prev != -1) nodes[prev][1] = next;
                    if (next != -1) nodes[next][0] = prev;
                    // 인덱스 이동
                    if (next != -1) cur = next;
                    else cur = prev;
                    break;
                case "Z":
                    int popped = stack.pop();

                    if (nodes[popped][0] != -1) nodes[nodes[popped][0]][1] = popped;
                    if (nodes[popped][1] != -1) nodes[nodes[popped][1]][0] = popped;
                    break;
                default:    break;

            }
        }
        char[] temp = new char[n];
        Arrays.fill(temp, 'O');

        while (!stack.isEmpty()) temp[stack.pop()] = 'X';

        answer = String.valueOf(temp);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
        System.out.println(solution(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }
}