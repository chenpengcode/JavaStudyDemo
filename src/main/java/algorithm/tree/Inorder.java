package algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/23
 */

public class Inorder {
    List<Integer> inList = new ArrayList<>();

    /**
     * 递归方式
     **/
    public void inorderDFS(TreeNode root) {
        if (root == null) return;
        inorderDFS(root.left);
        inList.add(root.val);
        inorderDFS(root.right);
    }

    // 迭代方式
    public void inorderDFS2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            inList.add(root.val);
            root = root.right;
        }
    }

    // Morris遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode p1 = null;
        while (root != null) {
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                p1 = root.left;
                while (p1.right != null && p1.right != root) {
                    p1 = p1.right;
                }
                if (p1.right == null) {
                    p1.right = root;
                    root = root.left;
                } else {
                    ans.add(root.val);
                    p1.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}

