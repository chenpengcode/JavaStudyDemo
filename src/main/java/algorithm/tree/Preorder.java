package algorithm.tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/23
 */
public class Preorder {
    List<Integer> preList = new ArrayList<>();

    /**
     * 深度优先遍历，递归方式
     * **/
    public void preorderDFS(TreeNode root) {
        if (root == null) return;
        // 先遍历根节点
        preList.add(root.val);
        // 递归遍历左子节点
        preorderDFS(root.left);
        // 递归遍历右子节点
        preorderDFS(root.right);
    }
    /**
     *  深度优先遍历，迭代方式，需要辅助栈
     * **/
    public void preorderDFS2(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new LinkedList<>();
        // 根节点入栈
        stack.offerLast(root);
        // 循环结束条件：辅助栈为空
        while (!stack.isEmpty()) {
            // 首先出栈
            TreeNode node = stack.pollLast();
            // 加入到遍历结果中
            preList.add(node.val);
            // 因为栈的后进先出特点，首先入栈右子节点，再入栈左子节点
            if (node.right != null) stack.offerLast(node.right);
            if (node.left != null) stack.offerLast(node.left);
        }
    }

    /**
     * Morris遍历在线性时间内，只占用常数空间来实现前序遍历
     * **/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        TreeNode p1 = root, p2;
        while (p1 != null) {
            p2 = p1.left;
            if (p2 == null) {
                ans.add(p1.val);
            } else {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    ans.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            }
            p1 = p1.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        /**
        *          1
        *        /   \
        *       2     3
        *       /\    /\
        *      4  5  6  7
        **/
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Preorder solution = new Preorder();
        ;
        System.out.println(solution.preorderTraversal(node1));
    }
}
