package algorithm.tree;

import java.util.*;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/26
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode root,int level, List<List<Integer>> ans) {
        if (root == null) return;
        if (level == ans.size()) ans.add(new ArrayList<>());
        ans.get(level).add(root.val);
        if (root.left != null) dfs(root.left, level + 1, ans);
        if (root.right != null) dfs(root.right, level + 1, ans);
    }


}
