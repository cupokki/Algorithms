package programmers.n72411;

import java.util.*;

public class Solution {
    /*
    orders의 각 문자열에서 course[i]개의 문자를 선택하여 같은 문자열을 2개 이상으로 구성한다.
    이 규칙의 모든 조합을 출력한다.(오름차순)

    가중 그래프로 나타낸다?, 가중치가 1이하인 관계는 고려하지안흔다.
    모든 노드에서 bfs 순회하며 조건을 만족하는 "경로"를 집합을 채운다.

    이렇게하면 각 주문들이 섞여버리네.. 각 주문마다 케이스를 얻어야한다.\
    근데 주문의 최대길이 20, course 10개, Map쓰면 되려나?

    n(n - 1)/2 -> O(n^2)

    길이가 짧은것부터하면 성능 최적화 가능할듯.
     */
//    static int[][] adjMatrix;
//    static Set<String> set;
//    static Set<Integer> courseSet;
//    static Set<Integer> menuSet;
//    public static String[] solution(String[] orders, int[] course) {
//        adjMatrix = new int[26][26]; // 모든 알파벳, 값 0은 관계 없다는 뜻
//        set = new HashSet<>();
//        menuSet = new HashSet<>();
//        courseSet = new HashSet<>();
//        for (int n : course) {
//            courseSet.add(n);
//        }
//        for (String s : orders) {
//            int len = s.length();
//            for(int i = 0 ; i < len; i++) {
//                int u = s.charAt(i) - 'A';
//                for (int j = i + 1; j < len; j++) {
//                    int v = s.charAt(j) - 'A';
//                    adjMatrix[u][v]++;
//                    adjMatrix[v][u]++;
//                    menuSet.add(v);
//                }
//
//            }
//        }
//        Set<String> set = new HashSet<>();
//        for (int i : menuSet) {
//            bfs(i);
//        }
//
//        String[] answer = set.stream().toArray(String[]::new);
//        for (String s: answer) {
//            System.out.print(s + " ");
//        }
//        System.out.println();
//        return answer;
//    }
//    static boolean bfs(int s) {
//        boolean[] visited = new boolean[26];
//        Queue<int[]> q = new LinkedList<>();
//        char[] result = new char[4];
//        q.offer(new int[]{s, 1, 2});
//        result[0] = ((char) (s + 'A'));
//        visited[s] = true;
//        while(!q.isEmpty()) {
//            int[] state = q.poll();
//            int u  = state[0];
//            int depth  = state[1];
//            int minE  = state[2];
//            if(minE < 2) {
//                continue;
//            }
//            if (courseSet.contains(depth)) {
//                char[] temp = new char[depth];
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < depth; i++) {
//                    temp[i] = result[i];
//                }
//                Arrays.sort(temp);
//                sb.append(temp);
//                set.add(sb.toString());
//            }
//            for (int v = 0; v < 26; v++) {
//                if (visited[v]) continue;
//                if (adjMatrix[u][v] < 2) continue;
//                result[depth] = (char) (v + 'A');
//
//                q.offer(new int[]{v, depth + 1, Math.min(minE, adjMatrix[u][v])});
//                visited[v] = true;
//            }
//        }
//        return true;
//    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        int[] cnt = new int[26];
        for (String s: orders) {
            for (char c: s.toCharArray()) {
                cnt[c - 'A']++;
            }
        }

        for (int n : course) {
            for (int i = 0; i < 26; i++) {
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2, 3, 4};
        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2, 3, 5};
        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2, 3, 4};

//        System.out.println(solution(orders1, course1));
//        System.out.println(solution(orders2, course2));
        System.out.println(solution(orders3, course3));
    }
}
