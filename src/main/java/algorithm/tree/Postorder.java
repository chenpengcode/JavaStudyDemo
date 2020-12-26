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
class Pair {
    TreeNode node;
    String flag;

    public Pair(TreeNode node, String flag) {
        this.node = node;
        this.flag = flag;
    }
}

public class Postorder {
    List<Integer> postList = new ArrayList<>();

    // 递归方式
    public void postorderDFS(TreeNode root) {
        if (root == null) return;
        postorderDFS(root.left);
        postorderDFS(root.right);
        postList.add(root.val);
    }

    // 迭代方式
    public void postorderDFS2(TreeNode root) {
        Deque<Pair> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(new Pair(root, "left"));
                root = root.left;
            }

            while (!stack.isEmpty() && stack.peekLast().flag.equals("right")) {
                postList.add(stack.pollLast().node.val);
            }

            if (!stack.isEmpty() && stack.peekLast().flag.equals("left")) {
                root = stack.peekLast().node;
                stack.peekLast().flag = "right";
                root = root.right;
            }
        }
    }

    // 迭代方式2
    public void postorderDFS3(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        TreeNode pre = null, cur;
        while (!stack.isEmpty()) {
            cur = stack.peekLast();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                stack.pollLast();
                postList.add(cur.val);
                pre = cur;
            } else {
                if (cur.right != null) stack.offerLast(cur.right);
                if (cur.left != null) stack.offerLast(cur.left);
            }
        }
    }

    // 迭代方式3
    public void postorderDFS4(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            if (root.right == null || root.right == pre) {
                postList.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.offerLast(root);
                root = root.right;
            }
        }
    }

    // 迭代方式4
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> stack = new LinkedList<>();
        // pre 为当前后序遍历的最后一个节点
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.offerLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            if (root.right == null || root.right == pre) {
                ans.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.offerLast(root);
                root = root.right;
            }
        }
        return ans;
    }

    // Morris遍历
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        TreeNode p1 = root, p2 = null;
        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(ans, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(ans, root);
        return ans;
    }

    private void addPath(List<Integer> ans, TreeNode root) {
        int count = 0;
        while (root != null) {
            ++count;
            ans.add(root.val);
            root = root.right;
        }
        int left = ans.size() - count, right = ans.size() - 1;
        while (left < right) {
            int tmp = ans.get(left);
            ans.set(left, ans.get(right));
            ans.set(right, tmp);
            ++left;
            --right;
        }
    }
}