package programmers.n42890;

import java.util.*;

public class Solution {
    /*
    인적사항 릴레이션.
    정리를 위한 후보키?
    R에서 튜플은 유일하게 식별할 수 있는 속성 또는 속성의 집합 중 다음 성질을 만족하는 것을 후보키
        - 유일성: 유일한 튜플
        - 최소성: 유일성을 가진 키를 구성하는 속성 중 하나라도 제외하면 유일성이 깨진다.

     릴레이션이 주어질때 최대 후보키의 개수를 구하라
     학번 o
     이름, 전공 o
     => 2개

     칼럼은 8이하 자연수
     로우는 20이하
     */
    static List<Integer> candidateKeys;
    static int R, C;
    public static int solution(String[][] relation) {
        candidateKeys = new ArrayList<>();
        R = relation.length;
        C = relation[0].length;
        dfs(relation, 0, 0);
        return candidateKeys.size();
    }
    static void dfs(String[][] relation, int bitmask, int depth) {
        if (depth == C) {
            if (bitmask == 0) {
                return;
            }
            Set<String> set = new HashSet<>();
            String[] composites = new String[R];
            Arrays.fill(composites, "");
            for (int c = 0; c < C; c++) {
                if ((bitmask & (1 << c)) != 0) {
                    for (int r = 0; r < R; r++) {
                        composites[r] += relation[r][c];
                    }
                }
            }
            for (int i = 0; i < R; i++) {
                set.add(composites[i]);
            }

            if (set.size() == R) {
                for (int k : candidateKeys) { // 최소성 검사
                    if ((k & bitmask) == k) {
                        return;
                    }
                }
                candidateKeys.add(bitmask);
            }
            return;
        }

        //뽑거나 안뽑거나
        dfs(relation, bitmask, depth + 1);
        dfs(relation, bitmask | (1 << depth), depth + 1);
    }

    public static void main(String[] args) {
        var r1 = new String[][] {
                {"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}
        };

        System.out.println(solution(r1));

    }
}
