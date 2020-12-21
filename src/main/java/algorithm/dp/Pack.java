package algorithm.dp;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/21
 */
public class Pack {
    public int zeroOnePack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n + 1][capacity + 1];
        // dp[0][0…W] = 0, 前0个物品（即没有物品）装入书包的最大价值为0
        // i 从1开始
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < w[i - 1]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
            }
        }
        return dp[n][capacity];
    }

    public int zeroOnePack2(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j >= w[i - 1]; --j) {
                dp[j] = Math.max(dp[j - w[i - 1]] + v[i - 1], dp[j]);
            }
        }
        return dp[capacity];
    }

    public int unboundedPack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w[i - 1]; j <= capacity; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w[i - 1]] + v[i - 1]);
            }
        }
        return dp[n][capacity];
    }

    public int unboundedPack2(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w[i - 1]; j <= capacity; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + v[i - 1]);
            }
        }
        return dp[capacity];
    }

    public int unboundedPack3(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w[i - 1]; j <= capacity; j++) {
                int cnt = j / w[i - 1];
                for (int k = 0; k <= cnt; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j - k * w[i - 1]] + k * v[i - 1], dp[i][j]);
                }
            }
        }
        return dp[n][capacity];
    }

    public int unboundedPack4(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w[i - 1]; j <= capacity; j++) {
                int cnt = j / w[i - 1];
                for (int k = 0; k <= cnt; k++) {
                    dp[j] = Math.max(dp[j - k * w[i - 1]] + k * v[i - 1], dp[j]);
                }
            }
        }
        return dp[capacity];
    }

    public int boundedPack(int[] w, int[] v, int[] count, int capacity) {
        int n = count.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = w[i - 1]; j <= capacity; j++) {
                int cnt = Math.min(j / w[i - 1], count[i - 1]);
                for (int k = 0; k <= cnt; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j - k * w[i - 1]] + k * v[i - 1], dp[i][j]);
                }
            }
        }
        return dp[n][capacity];
    }

    public int boundedPack2(int[] w, int[] v, int[] count, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j >= w[i - 1]; j--) {
                int cnt = Math.min(j / w[i - 1], count[i - 1]);
                for (int k = 0; k <= cnt; k++) {
                    dp[j] = Math.max(dp[j - k * w[i - 1]] + k * v[i - 1], dp[j]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int[] w = {4, 3, 2};
        int[] v = {2, 5, 1};
        int[] n = {3, 2, 5};
        int capacity = 6;
        Pack pack = new Pack();
        System.out.println(pack.unboundedPack4(w, v, capacity));
        System.out.println(pack.boundedPack2(w, v, n, capacity));
    }
}
