package programmers.n42893;

import java.util.*;

public class Solution {
    /*
    웹 페이지 구성
        - 기본점수 : 웹페이지 내 검색어 등장 수 (대소 무시)
        - 외부 링크 수
        - 링크점수 : 현 페이지로 링크가 존재하는 다른 페이지의 기본점수 / 다른 페이지 외부 점수
        - 매칭점수 : 기본점수와 링크 점수의 합

    word와 pages(HTML )이 주어질때, 매칭 점수가 가장 높은 index를 구하라.
    결과가 다수라면 그중 가장 번호가 빠른 것을 출력한다.

    pages(node)는 20개이하 자연수

    */
    public int solution(String word, String[] pages) {
        int n = pages.length;

        String[] urls = new String[n];
        int[] defaultScores = new int[n];
        int[] linkingScores = new int[n]; // 외부 링크 수

        // 파싱
        for (int i = 0; i < n; i++) {
            // pages[i];
        }

        // 매칭 점수 산정
        double[] matchingScores = new double[n];
        for (int i = 0; i < n; i++) {
            double linkScore = 0;
            matchingScores[i] = defaultScores[i] + linkScore;
        }


        int idx = 0;

        for (int i = 0; i < n; i++) {
            if (matchingScores[i] < matchingScores[idx]) {
                idx = i;
            }
        }

        return idx; //
    }

    private int findWord(String body) {
        return 0;
    }

    private String parseLink(String meta) {
        return null;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"})); // 0

        System.out.println(sol.solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"})); // 1
    }
}