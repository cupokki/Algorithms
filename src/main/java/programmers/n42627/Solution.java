package programmers.n42627;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    /*
    우선순위 디스크 컨트롤러
    1. 작업 요청시 번호와 요청시각, 소요시간을 저장하는 대기 큐
    2. 작업 소요시간 짧은 순, 요청 빠른 순, 번호 작은 순으로 수행
    3. 한번 작업 맡으면 끝날때까지 수행
    4. 작업이 끝난 순간에 대기작업과 대기큐를 확인하여 우선순위가 높은 것을 수행

    요청시간의 평균을 정수부를 출력하라.

    jobs 길이 500이하 자연수

    */
    static class Job implements Comparable<Job> {
        int id;
        int s;
        int l;

        Job(int id, int s, int l) {
            this.id = id;
            this.s = s;
            this.l = l;
        }

        @Override
        public int compareTo(Job o) {
            if (this.l == o.l) {
                if (this.s == o.s) {
                    return this.id - o.id;
                }
                return this.s - o.s;
            }
            return this.l - o.l;
        }

        public static int solution(int[][] jobs) {
            int answer = 0;
            int n = jobs.length;
            List<Job> jobList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                jobList.add(new Job(i, jobs[i][0], jobs[i][1]));
            }
            PriorityQueue<Job> pq = new PriorityQueue<>();
            int sum = jobList.get(0).l;
            int now = jobList.get(0).l;
            int idx = 1;
            Job cur;
            while (idx < n) {
                if (pq.isEmpty() || jobList.get(idx).compareTo(pq.peek()) < 0) {
                    cur = jobList.get(idx);
                } else { // 큐작업 수행
                    pq.offer(jobList.get(idx));
                    cur = pq.poll();
                }
                now += cur.l;
                sum += now - cur.s;
                idx++;

            }

            while (!pq.isEmpty()) {
                cur = pq.poll();
                now += cur.l;
                sum += now - cur.s;
            }

            answer = (int)Math.floor(sum / n);
            return answer;
        }

        public static void main(String[] args) {
            System.out.println(solution(new int[][]{
                    {0, 3}, {1, 9}, {3, 5}
            }));
        }
    }
}