package programmers.n12946;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static List<int[]> list;
    public static int[][] solution(int n) {
        list = new ArrayList<>();
        hanoi(n, 1, 2, 3);
        return list.toArray(int[][]::new);
    }
    static void hanoi(int n, int from, int via, int to) {
        if (n == 0) {
           return;
        }

        hanoi(n - 1, from, to, via); // 위 원판 임시로 옮기기
        list.add(new int[]{from, to}); // n원판 목표로 이동
        hanoi(n - 1, via, from, to); // 임시로 옮긴 위 원판 옮기기

    }

    public static void main(String[] args) {
        solution(1);
        solution(2);
    }
}
