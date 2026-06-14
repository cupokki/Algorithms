package programmers.n150366;

import java.util.*;

public class Solution {
    /*
    표편집 프로그램
    Update: r,c 위치를 value로
    Uddate: 모든 value1를 value2로
    Merge r1, c1, r2, c2의 모든 셀을 r1,c1의 값으로 만든다.
    Unmerge  r, c에 해당하는 영역의 값을 최초 상태로 돌린다.
    Print r, c 위치의 값을 출력 비어있다면 "EMPTY"


    명령은 1000회 이하.
    */
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        String[][] table = new String[51][51];

        for (String comm :commands) {
            String[] arg = comm.split(" ");
            switch (arg[0]) {
                case "UPDATE":
                    if (arg.length == 3) {
                        int r = Integer.parseInt(arg[0]);
                        int c = Integer.parseInt(arg[1]);
                        int value = Integer.parseInt(arg[2]);
                    } else {
                        int value1 = Integer.parseInt(arg[0]);
                        int value2 = Integer.parseInt(arg[1]);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(arg[0]);
                    int c1 = Integer.parseInt(arg[1]);
                    int r2 = Integer.parseInt(arg[2]);
                    int c2 = Integer.parseInt(arg[3]);

                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(arg[0]);
                    int c = Integer.parseInt(arg[1]);

                    break;
                case "PRINT":
                    result.add(table[Integer.parseInt(arg[0])][Integer.parseInt(arg[1])]);
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