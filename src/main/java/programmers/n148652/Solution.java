package programmers.n148652;

import java.util.*;

public class Solution {
    /*
    칸토어 집합:
        - 0,1사이 실수인 원소만으로 이루어짐.
        - 각 구간을 3분하여 가운데 구간을 반복적으로 제외

    유사 칸토어
        - 0번째 유사 칸토어 비트열은 "1"
        - n(1 <= n)번째 유사 칸토어 비트열은 n - 1번째 유사 칸토어 비트열에서의 1을 11011을 치환하고 00000로 치환

    n번째 유사 칸토어 비트열에서 특정 구간 내의 1의 개수를 출력한다.
    n: 20이하 자연수

    i == 2)
        "11011/11011/00000/11011/11011"
        "___?_/_____/_____/_____/_____"
        4는 첫번째 블록, i == 1,
    i == 1)
        "?____"
        "11011"

    i == 2)
        "11011" 4 % 5 == 4


    arr[i]는 i번쨰 비트열을 5등분할때, 몇번째에 부분비트열에 속하는지
    [4, 1]
    */
    public static int solution(int n, long l, long r) {
        int answer = 0;
        for (long i = l; i <= r; i++) {
            if (isOne(n, i)) answer++;
        }
        return answer;
    }

    static boolean isOne(int n, long x) {
        // n단계부터 1단계까지 부모를 타고 올라감
        while (n-- > 0) {
            if ((x - 1) % 5 == 2) return false; // 가운데
            x = (x - 1) / 5 + 1; // 부모 노드의 위치로 갱신하고 한 단계 위로 이동
        }
        return true; // 끝까지 0을 만나지 않고 0단계(루트)까지 올라왔다면 1
    }

//     static boolean isOne(int n, long x) {
//         if (n == 0) return true;

//         // 가운데, 즉 0
//         if ((x - 1) % 5 == 2) return false;

//         // 1이면, 부모노드를 찾아 다시 수행
//         return isOne(n - 1, (x - 1) / 5 + 1);
//     }

    public static void main(String[] args) {
        System.out.println(solution(2, 4, 17)); // 8
    }
}
