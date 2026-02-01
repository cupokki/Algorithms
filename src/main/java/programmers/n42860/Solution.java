package programmers.n42860;

public class Solution {
    /*
    예상가능한 커서 이동방향 계속 왼쪽, 계속 오른쪽, 혹은 필요한만큼 한방향 갔다가, 다른방향 쭉
    */
    public static int solution(String name) {
        int answer = 0;
        // 문자자차는 쉽게 최소 변환비용을 계산할 수 있다. 또한 커서이동에 종속적이지 않다.
        // 핵심은 모든 노드를 거치는 최단거리.
        char[] chars = name.toCharArray(); // 각 문자를 노드로 보고, 문자간의 거리는 1, A는 제외하고 바로 거리로?

        int len = chars.length;

        int calculateCost = 0;

        //calculate convert cost
        for (int i = 0; i < len; i++) {
            if (chars[i] != 'A') {
                calculateCost += Math.min(
                        chars[i] - 'A',
                        26 - (chars[i] - 'A') % 26
                );
            }
        }

        int moveCost = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            if (chars[i] == 'A')
                continue;   // 꺾는 후보는 A 아닌 위치만

            // 오른쪽 먼저 i에서 꺾는다.
            int routeA = 2 * i + (len - 1 - i);

            // 왼쪽 먼저 i에서 꺾는다
            int routeB = 2 * (len - 1 - i) + i;

            moveCost = Math.min(moveCost, Math.min(routeA, routeB));
        }

        answer = moveCost + calculateCost;

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("JAZ"));
        System.out.println(solution("JEROEN"));
        System.out.println(solution("BBAAB"));
    }
}