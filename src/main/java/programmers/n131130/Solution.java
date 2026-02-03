package programmers.n131130;

import java.util.Arrays;

public class Solution {
    /*
    숫자 카드더미에 100장. 100이하의 자연수가 적혀이싿.
    2이상 100이하의 자연수 a이하의 카드들을 준비한다.
    준비한 카드 수 만큼 작은 상자를 준비
    - 상자에 카드 한장씩 넣고, 상자를 무작위로 섞어 일렬로 나열.
    - 상자에는 오름차순 인덱스를 붙인다.
    - 임의의 상자를 선택해 상자안의 숫자 확인. 안의 카드에 적힌 번호의 상자를 연달아 확인.
    - 위를 카드번호가 이미 열려있는 상자일때까지 반복
    - 이렇게 뽑힌 상자는 1번그룹. 타 상자들과 분리.
    - 1번그룹에 모든 상자가 포함되면 0점
    - 아니라면 임의의 상자 하나를 골라 같은 프로세스로 다음 그룹만든다.
    - 1그룹과 2그룹에 속한 카드 수를 곱한 값이 점수가 된다.

    얻을 수 있는 최고 점수를 구하라.
    cards 배열은 상자안에 들어있는 카드 번호가 순서대로 담긴 배열
    처음 임의의 카드를 잘 골라야 최고 점수를 구할 수 있다.
    케이스는 cards.length개 중 하나.
     */
    public static int solution(int[] cards) {
        int answer = 0;
        int len = cards.length;
        int[] group = new int[len];
        Arrays.fill(group, -1);
        int[] size = new int[len];
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (group[i] != -1) {
                continue;
            }
            int idx = cards[i] - 1;
            while(group[idx] == -1 ) {
                group[idx] = num;
                idx = cards[idx] - 1;
            }
            num++;
        }

        for (int i = 0; i < len; i++) {
            size[group[i]]++;
        }

        Arrays.sort(size);

        answer = size[len - 1] * size[len - 2];
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{8,6,3,7,2,5,1,4}));
    }
}
