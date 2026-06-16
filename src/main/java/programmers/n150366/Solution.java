package programmers.n150366;

import java.util.*;

public class Solution {
    /*
    표편집 프로그램
    Update: r,c 위치를 value로
    Uddate: 모든 value1를 value2로
    Merge r1, c1, r2, c2의 모든 셀을 r1,c1의 값으로 만든다.
        머지시에 기존값을 보관할 필욘 없고 영역에 대한 정보를 기록해야한다.
    Unmerge  r, c에 해당하는 영역의 값을 최초 상태(blank)로 돌린다.

    Print r, c 위치의 값을 출력 비어있다면 "EMPTY"


    명령은 1000회 이하.
    */
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        int[][][] roots = new int[51][51][2]; // rootR, rootC
        String[][] values = new String[51][51];

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                roots[i][j][0] = i;
                roots[i][j][1] = j;
                values[i][j] = "";
            }
        }
        for (String comm :commands) {
            String[] arg = comm.split(" ");
            switch (arg[0]) {
                case "UPDATE":
                    if (arg.length == 3) {
                        int r = Integer.parseInt(arg[0]);
                        int c = Integer.parseInt(arg[1]);

                        if (roots[r][c][0] == r && roots[r][c][1] == c) {
                            values[r][c] = arg[2];
                        } else {
                            values[roots[r][c][0]][roots[r][c][1]] = arg[2];
                        }
                    } else {
                        String value1 = arg[0];
                        String value2 = arg[1];

                        for (int i = 1; i <= 50; i++) {
                            for (int j = 1; j <= 50; j++) {
                                if (values[i][j] == value1) {
                                    values[i][j] = value2;
                                }
                            }
                        }
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(arg[0]);
                    int c1 = Integer.parseInt(arg[1]);
                    int r2 = Integer.parseInt(arg[2]);
                    int c2 = Integer.parseInt(arg[3]);
                    for (int i = r1; i <= r2; i++) {
                        for (int j = c1; j <= c2; j++) {
                            roots[i][j][0] = r1;
                            roots[i][j][1] = c1;
                        }
                    }
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(arg[0]);
                    int c = Integer.parseInt(arg[1]);

                    int rr = roots[r][c][0];
                    int rc = roots[r][c][1];

                    for (int i = 1; i <= 50; i++) {
                        for (int j = 1; j <= 50; j++) {
                            if (roots[i][j][0] == rr && roots[i][j][1] == rc) {
                                values[i][j] = "";
                            }
                        }
                    }
                    values[r][c] = values[rr][rc];
                    break;

                case "PRINT":
                    int rrr = Integer.parseInt(arg[0]);
                    int ccc = Integer.parseInt(arg[1]);
                    String value = values[roots[rrr][ccc][0]][roots[rrr][ccc][1]] == "" ?
                            "EMPTY" :
                            values[roots[rrr][ccc][0]][roots[rrr][ccc][1]];
                    result.add(value);
                    break;
                default: break;
            }
        }

        String[] answer = result.toArray(String[]::new);
        return answer;
    }

     public static void main(String[] args) {
         Solution sol = new Solution();
         System.out.println(Arrays.toString(sol.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"})));
         System.out.println(Arrays.toString(sol.solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"})));
     }
}