package boj.dp.n12852;

import java.util.*;

/**
 * https://www.acmicpc.net/problem/12852
 * 1로 만들기 2
 *
 * 정수 X(<=10^6)에 사용할 수 있는 연산 3가지
 *  1. x가 3으로 나누어 떨어지면 3으로 나눈다.
 *  2. x가 2로 나누어 떨어지면, 2로 나눈다.
 *  3. 1을 뺀다.
 *
 * 최소 연산으로 1을 만들라
 * 출력의 첫째줄에 최솟값, 둘쨰 줄에 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다.
 * 3시 5분
 *
 *
 */
public class Main {
    static int min = Integer.MAX_VALUE;
    static List<Integer> result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        dfs(N, list);

        System.out.println(min);
        for(int i = 0; i < result.size(); i++ ) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }

    static void dfs(int depth, List<Integer> list) {
        list.add(depth);

        if (list.size() > min) {
            return;
        }
        if (depth < 0) {
            return;
        }
        if (depth == 1) {
            if (min > list.size() - 1) {
                min = list.size() - 1;
                result = new ArrayList<>();
                for(int i = 0; i < list.size(); i++) {
                    result.add(list.get(i));
                }
            }
            return;
        }

        if(depth % 3 == 0) {
            List<Integer> newList = copyList(list);
            dfs(depth / 3, newList);
        }
        if(depth % 2 == 0) {
            List<Integer> newList = copyList(list);
            dfs(depth / 2, newList);
        }
        List<Integer> newList = copyList(list);
        dfs(depth - 1, newList);
    }

    private static List<Integer> copyList(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
}
