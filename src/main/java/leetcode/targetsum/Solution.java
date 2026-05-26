package leetcode.targetsum;

import java.util.*;

public class Solution {
    /*
        nums앞에 +/- 기호를 붙여 수식을 완성하고, 결과가 target이 되는 모든 경우의 수를 출력한다.
        nums의 길이는 20, dfs 가능?
    */
    // public int findTargetSumWays(int[] nums, int target) {
    //     cnt = 0;
    //     dfs(nums, target, 0, 0);
    //     return cnt;
    // }

    // int cnt;

    // void dfs (int[] nums, int target, int depth, int sum) {
    //     if (depth == nums.length) {
    //         if (sum == target) cnt++;
    //         return;
    //     }

    //     dfs(nums, target, depth + 1, sum - nums[depth]);
    //     dfs(nums, target, depth + 1, sum + nums[depth]);
    // }

    static int[][] memo;
    public static int findTargetSumWays(int[] nums, int target) {
        memo = new int[nums.length][2001]; // 2001 is shifted to positive result as given sum range.
        for (int i = 0; i < nums.length; i++) Arrays.fill(memo[i], -1);
        return dfs(nums, target, 0, 0);
    }

    static int dfs (int[] nums, int target, int depth, int sum) {
        if (depth == nums.length)  {
            if (sum == target) return 1;
            else return 0;
        }

        if (memo[depth][sum + 1000] != -1) {
            return memo[depth][sum + 1000];
        }

        // plus way and minus way
        memo[depth][sum + 1000] =
                dfs(nums, target, depth + 1, sum + nums[depth]) +
                        dfs(nums, target, depth + 1, sum - nums[depth]);
        return memo[depth][sum + 1000];
    }

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1}, 1));
    }
}