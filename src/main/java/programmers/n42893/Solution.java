package programmers.n42893;

import java.util.*;

public class Solution {
    /*
    웹 페이지 구성
        - 기본점수 : 웹페이지 내 검색어 등장 수 (대소 무시)
        - 외부 링크 수
        - 링크점수 : 현 페이지로 링크가 존재하는 다른 페이지의 기본점수 / 다른 페이지 외부 링크 수
        - 매칭점수 : 기본점수와 링크 점수의 합

    word와 pages(HTML )이 주어질때, 매칭 점수가 가장 높은 index를 구하라.
    결과가 다수라면 그중 가장 번호가 빠른 것을 출력한다.

    pages(node)는 20개이하 자연수

    */
    class Page {
        int defaultScore;
        List<String> links = new ArrayList();
    }
    public int solution(String word, String[] pages) {
        int n = pages.length;

        word = word.toLowerCase();

        Map<String, Page> pageMap = new HashMap<>();
        String[] urls = new String[n];

        // 파싱
        for (int i = 0; i < n; i++) {
            Page page = new Page();

            // meta url 파싱

            int metaStart = pages[i].indexOf("<meta");

            while (metaStart != -1) {
                int metaEnd = pages[i].indexOf(">", metaStart);

                String metaTag = pages[i].substring(metaStart, metaEnd + 1);
                metaTag = metaTag.toLowerCase();
                if (metaTag.contains("og:url")) {
                    int start = metaTag.indexOf("content=\"") + 9;
                    int end = metaTag.indexOf("\"", start);
                    urls[i] = metaTag.substring(start, end);
                    break;
                }
                metaStart = pages[i].indexOf("<meta", metaEnd + 1);
            }

            pageMap.put(urls[i], page);

            int start = 0;
            int end = 0;
            while ((start = pages[i].indexOf("<a href=\"", end)) != -1) {
                start += 9;
                end = pages[i].indexOf("\"", start);
                page.links.add(pages[i].substring(start, end));
            }

            start = pages[i].indexOf("<body>") + 7;
            end = pages[i].indexOf("</body>", start);
            String body = pages[i].substring(start, end);
            body = body.replaceAll("(<a href=).*?(>)", " ");
            body = body.replaceAll("</a>", " ");
            body = body.replaceAll("[^a-zA-Z]", " ");
            body = body.toLowerCase();

            String[] tokens = body.split(" ");
            for (String token : tokens) {
                if (word.equals(token)){
                    page.defaultScore++;
                }
            }
        }

        int answer = 0;
        double maxScore = 0;

        for (int i = 0; i < n; i++) {
            Page page = pageMap.get(urls[i]);

            double linkScore = 0;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                Page linkedPage = pageMap.get(urls[j]);

                for (String link : linkedPage.links) {
                    if (link.equals(urls[i])) {
                        linkScore += (double) linkedPage.defaultScore / linkedPage.links.size();
                    }
                }
            }

            double matchingScore = page.defaultScore + linkScore;

            if (matchingScore > maxScore) {
                answer = i;
                maxScore = matchingScore;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"})); // 0

        System.out.println(sol.solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"})); // 1
    }
}