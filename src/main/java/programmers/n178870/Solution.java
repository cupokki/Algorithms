package programmers.n178870;

public class Solution {
    /*
    비내림차순 수열에서 아래 조건을 만족하는부분 수열의 시작, 끝 인덱스를 구하라.
    - 임의의 두 인덱스와 그 사이 원소를 모두 포함하는 부분 수열이다.
    - 부분 수열의 합은 k이다.
    - k인 부분 수열중 길이가 가장 짧아야한다.
    - 가장 짧은 수열이 다수라면 시작 인덱스가 최소인 부분 수열을 선택한다.

    길이가 100만인 수열
    k는 10억 이하의 자연수
    주어지는 케이스에 예외는 없다.

    1. 단순 완전탐색으로 가능한가?
        -> O(n^2)

    2. 부분합으로 계산하면 좋을듯하다.

    3. 어떤 순서대로 탐색을하면 탐색하지않을 가지를 처낼 수 있을거같은데
        - 짧으면서, 첫째자리 부터 계산하면 이후는 실행할 필요가 없음.

    4. 근데 항상 최악을 생각하면 그건 아니잖아
        - 촤악의 경우는 다 더해서 k일때.



     */
    public static int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        int[] prefixSum = new int[len + 1];
        int[] answer = new int[]{0, len - 1};
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + sequence[i - 1];
        }

        int temp = 0;
        int s = 0;
        int e = 1;
        while(e <= len && s <= len) {
            if (temp < k) { // 예외 케이스는 주어지지않으므로 경계조건 x
                e++;
            }
            else if (temp > k){
                s++;
            }
            temp = prefixSum[e] - prefixSum[s];

            if (temp == k && e - 1 - s < answer[1] - answer[0]) {
                answer[0] = s;
                answer[1] = e - 1;
            }
        }


        System.out.println(answer[0] + ", " + answer[1]);
        return answer;

    }

    public static void main(String[] args) {
        solution(new int[]{1, 2, 3, 4, 5}, 7);
        solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5);
        solution(new int[]{2, 2, 2, 2, 2}, 6);
    }
}
