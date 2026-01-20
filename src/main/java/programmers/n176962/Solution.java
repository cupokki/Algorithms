package programmers.n176962;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    /*
    과제를 다음 순서로 진행한다.
    1. 시간이 되면 시작
    2. 새 과제 시작 시간 도달시, 기존 과제가 있다면 멈추고 새 과제
    3. 진행중이던 과제를 끝냈을 때, 멈춘 과제가 있다면 멈춘 과제 진행
        - 만약, 동시에 끝낸 시각에 새로 할게 있다면 새 것 부터
    4. 멈춰둔 과제가 여러개라면 최근에 멈춘 과제부터

    Plan의 원소는 name, start, playtime 순이다.
    plans는 시작순으로 정렬되있지 않을 수 있다.
    plans의 길이는 3이상, 1000이하의 자연수


     */
    static final class Plan {
        String name;
        int s;
        int l;
        Plan(String name, int startAt, int left) {
            this.name = name;
            this.s = startAt;
            this.l = left;
        }
    }
    public static String[] solution(String[][] plans) {
        int n = plans.length;
        String[] answer = new String[n];

        PriorityQueue<Plan> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.s));
        for (int i = 0; i < n; i++) {
            String[] cur = plans[i];
            String name = cur[0];
            String[] temp = cur[1].split(":");
            int s = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            int e = Integer.parseInt(cur[2]);

            pq.offer(new Plan(name, s, e));
        }
        Stack<Plan> onHold = new Stack<>();
        int idx = 0;
        Plan cur = pq.poll();
        int now = cur.s;
        while(!pq.isEmpty() && idx < n) {
            Plan next = pq.peek();

            // 현재 작업 처리(완료, 보류)
            int currentEnd = now + cur.l;

            if (currentEnd <= next.s) {
                answer[idx++] = cur.name; // 작업 완료.
                now = currentEnd;
                if (!onHold.isEmpty() && now < next.s) {
                    cur = onHold.pop();
                    continue;
                }
            } else {
                // 현재 종료시간보다 다음 시작시간이 빠르다.
                int left = currentEnd - next.s;
                cur.l = left;
                onHold.push(cur); // 현 작업 보류
            }

            cur = pq.poll();
            now = cur.s;
        }

        answer[idx++] = cur.name;
        while(!onHold.isEmpty()) {
            answer[idx++] = onHold.pop().name;
        }

        return answer;
    }


    public static void main(String[] args) {
//        var i1 = new String[][] {
//                {"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}
//        };
//        Arrays.stream(solution(i1)).forEach(s->System.out.println(s));
//
//        var i2 = new String[][] {
//                {"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}
//        };
//        Arrays.stream(solution(i2)).forEach(s->System.out.println(s));

//        var i2 = new String[][] {
//                {"a", "00:01", "2"}, {"b", "00:02", "1"}, {"c", "0:03", "1"}, {"d", "00:05", "1"}
//        };
//        Arrays.stream(solution(i2)).forEach(s->System.out.println(s));
//
        var i3 = new String[][] {
                {"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}
        };
        Arrays.stream(solution(i3)).forEach(s->System.out.println(s));

//        var i3 = new String[][] {
//                {"aaa", "12:00", "10"}, {"bbb", "12:10", "10"}, {"ccc", "12:20", "10"}
//        };
//        Arrays.stream(solution(i3)).forEach(s->System.out.println(s));
    }
}

