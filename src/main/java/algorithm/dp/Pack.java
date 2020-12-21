package algorithm.dp;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/21
 */
public class Pack {
    public int zeroOnePack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n][capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            if (j < w[0]) dp[0][j] = 0;
            else dp[0][j] = v[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
            }
        }
        return dp[n - 1][capacity];
    }

    public int zeroOnePack2(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            if (j >= w[0]) dp[j] = v[0];
            else dp[j] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= w[i]; --j) {
                dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
            }
        }
        return dp[capacity];
    }

    public int unboundedPack(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] dp = new int[n][capacity + 1];
        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[0][j] = (j / w[0]) * v[0];
            else dp[0][j] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w[i]] + v[i]);
            }
        }
        return dp[n - 1][capacity];
    }

    public int unboundedPack2(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[j] = (j / w[0]) * v[0];
            else dp[j] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        return dp[capacity];
    }

    public int unboundedPack3(int[] w, int[] v, int capacity) {
        int n = v.length;
        int[][] dp = new int[n][capacity + 1];

        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[0][j] = (j / w[0]) * v[0];
            else dp[0][j] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                int cnt = j / w[i];
                for (int k = 0; k <= cnt; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j - k * w[i]] + k * v[i], dp[i][j]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public int unboundedPack4(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[j] = (j / w[0]) * v[0];
            else dp[j] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= w[i]; j--) {
                int cnt = j / w[i];
                for (int k = 1; k <= cnt; k++) {
                    dp[j] = Math.max(dp[j - k * w[i]] + k * v[i], dp[j]);
                }
            }
        }
        return dp[capacity];
    }

    public int boundedPack(int[] w, int[] v, int[] count, int capacity) {
        int n = count.length;
        int[][] dp = new int[n][capacity + 1];
        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[0][j] = (Math.min(j / w[0], count[0])) * v[0];
            else dp[0][j] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = w[i]; j <= capacity; j++) {
                int cnt = Math.min(j / w[i], count[i]);
                for (int k = 1; k <= cnt; k++) {
                    dp[i][j] = Math.max(dp[i - 1][j - k * w[i]] + k * v[i], dp[i][j]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public int boundedPack2(int[] w, int[] v, int[] count, int capacity) {
        int n = w.length;
        int[] dp = new int[capacity + 1];
        for (int j = 0; j <= capacity; ++j) {
            if (j >= w[0]) dp[j] = (Math.min(j / w[0], count[0])) * v[0];
            else dp[j] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= w[i]; j--) {
                int cnt = Math.min(j / w[i - 1], count[i - 1]);
                for (int k = 0; k <= cnt; k++) {
                    dp[j] = Math.max(dp[j - k * w[i]] + k * v[i], dp[j]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int[] w = {4, 3, 2, 2};
        int[] v = {2, 4, 1, 3};
        int[] n = {3, 2, 5, 4};
        int capacity = 6;
        Pack pack = new Pack();
//        System.out.println(pack.unboundedPack4(w, v, capacity));
        System.out.println(pack.boundedPack2(w, v, n, capacity));
    }
}
