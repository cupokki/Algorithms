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
    int[][][] parents;
    String[][] values;
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        parents = new int[51][51][2]; // rootR, rootC
        values = new String[51][51];

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                parents[i][j][0] = i;
                parents[i][j][1] = j;
                values[i][j] = "";
            }
        }
        for (String comm :commands) {
            String[] tokens = comm.split(" ");
            switch (tokens[0]) {
                case "UPDATE" -> update(tokens);
                case "MERGE" -> merge(tokens);
                case "UNMERGE" -> unmerge(tokens);
                case "PRINT" -> print(result, tokens);
                default -> {}
            }
        }

        String[] answer = result.toArray(String[]::new);
        return answer;
    }

    public void merge(String[] tokens) {
        int r1 = Integer.parseInt(tokens[1]);
        int c1 = Integer.parseInt(tokens[2]);
        int r2 = Integer.parseInt(tokens[3]);
        int c2 = Integer.parseInt(tokens[4]);
        String value = values[r1][c1]; // 첫번쨰 값
        values[r1][c1] = values[r2][c2] = "";
        int tr = r1; int tc = c1;
        // 두번쨰 위치에 값이 존재한다면
        if (value.equals("") && values[r2][c2] != "") {
            tr = r2; tc = c2;
            value = values[r2][c2];
        }

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (
                        parents[i][j][0] == tr &&
                        parents[i][j][1] == tc // 같은 부모를 보는 것.
                ) {

                }
            }
        }
        parents[r1][c1][0] = parents[r2][c2][0] = tr;
        parents[r1][c1][1] = parents[r2][c2][1] = tc;
        values[tr][tc] = value;
    }

    public void unmerge(String[] tokens) {
        int r = Integer.parseInt(tokens[1]);
        int c = Integer.parseInt(tokens[2]);

        String value = values[parents[r][c][0]][parents[r][c][1]];


        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (
                        parents[i][j][0] == parents[r][c][0] &&
                        parents[i][j][1] == parents[r][c][1] // 같은 부모를 보는 것.
                ) {
                    values[i][j] = "";
                    parents[i][j][0] = i;
                    parents[i][j][1] = j;
                }
            }
        }

        values[r][c] = value;
    }

    public void update(String[] tokens) {
        if (tokens.length == 4) {
            int r = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);

            if (parents[r][c][0] == r && parents[r][c][1] == c) {
                values[r][c] = tokens[3];
            } else {
                values[parents[r][c][0]][parents[r][c][1]] = tokens[3];
            }
        } else {
            String value1 = tokens[1];
            String value2 = tokens[2];

            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (values[i][j] == value1) {
                        values[i][j] = value2;
                    }
                }
            }
        }
    }

    public void print(List<String> result, String[] tokens) {
        int r = Integer.parseInt(tokens[1]);
        int c = Integer.parseInt(tokens[2]);
        String value = values[parents[r][c][0]][parents[r][c][1]] == "" ?
                "EMPTY" :
                values[parents[r][c][0]][parents[r][c][1]];
        result.add(value);
    }



     public static void main(String[] args) {
         Solution sol = new Solution();
//         System.out.println(Arrays.toString(sol.solution(new String[]{"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"})));
         System.out.println(Arrays.toString(sol.solution(new String[]{"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"})));
     }
}