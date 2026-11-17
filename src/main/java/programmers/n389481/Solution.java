package programmers.n389481;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /*
    goal
        - 주문서에서 특정 주문을 지웠을때, n번째 주문을 찾아낸다.

    given information
        - 주문은 알파벳 소문자 11자
        - 글자수가 적은 순
        - 글자수가 같다면 사전순
        - n은 10^15 이하의 자연수
        - bans의 길이는 300000 이하.
        - bans에 중복된 주문은 없음

    n번째 문자열는 바로 알 수 있다.
    bans[i]의 문자열도 몇번째인지 유추가능하다.

    이분탐색 수행

    일종의 26진수
     */
    public String solution(long n, String[] bans) {
        String answer = "";

    //        Arrays.sort(bans, Comparator.comparing(String::length)
    //                .thenComparing(String::compareTo));
    //
    //        long[] nums = new long[bans.length];
    //
    //        long targetIdx = n;
    //
    //        for (int i = 0; i < bans.length; i++) {
    //            int digit = bans[i].length() - 1;
    //            for (char c : bans[i].toCharArray()) {
    //                nums[i] += (c - 'a' + 1) * (long) Math.pow(26, digit);
    //                digit--;
    //            }
    //
    //            if (nums[i] > targetIdx) {
    //                break;
    //            } else {
    //                targetIdx++; // 인덱스 오류
    //            }
    //        }
    //
    //        StringBuilder sb = new StringBuilder();
    //        while (targetIdx > 0) {
    //            targetIdx--;
    //            long remainder = targetIdx % 26;
    //            sb.append((char) ('a' + remainder));
    //            targetIdx /= 26;
    //        }
    //
    //        sb.reverse();
    //        answer = sb.toString();

        Arrays.sort(bans, Comparator.comparingLong(String::length)
                .thenComparing(String::compareTo));

        long low = 0, high = Long.MAX_VALUE;
        long idx = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (check(mid, bans, n)) {
                idx = mid;
                high =  mid - 1;
            } else {
                low = mid + 1;
            }
        }
        answer= numberToString(idx);
        return answer;
    }

    //mid 번쨰 주문이 bans를 고려했을 때, 실제 주문서에서 몇번째이며 n 이상인가
    boolean check(long num, String[] bans, long n) {
        String target = numberToString(num);

        int cnt = 0;
        int low = 0, high = bans.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // bans[mid]가 target보다 작거나 같으면, mid까지의 개수를 카운트하고 더 오른쪽을 탐색
            if (bans[mid].length() < target.length() ||
                    (bans[mid].length() == target.length() && bans[mid].compareTo(target) <= 0)) {
                cnt = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (num - cnt) >= n;
    }

    String numberToString(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--;
            long remainder = num % 26;
            sb.append((char) ('a' + remainder));
            num /= 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        // ah
        System.out.println(sol.solution(30, new String[]{"d", "e", "bb", "aa", "ae"}));

        // jxk
        System.out.println(sol.solution(7388, new String[]{"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"}));
    }
}
