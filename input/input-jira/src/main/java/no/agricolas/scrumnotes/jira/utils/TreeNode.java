package no.agricolas.scrumnotes.jira.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Øyvind Strømmen
 */
public class TreeNode<N> {

    private TreeNode<N> parent;

    private List<TreeNode<N>> children;

    private N content;

    public TreeNode(N content) {
        this.content = content;
        this.children = new ArrayList<TreeNode<N>>();
    }

    public TreeNode<N> getRootNode() {
        return recurseToRootNode(this);
    }

    private TreeNode<N> recurseToRootNode(TreeNode<N> node) {
        return node.isRoot() ? node : recurseToRootNode(node.parent);
    }

    public Boolean isRoot() {
        return parent == null;
    }

    public void addChild(TreeNode<N> childNode) {
        childNode.parent = this;
        children.add(childNode);
    }

    public N getContent() {
        return content;
    }

    public List<TreeNode<N>> getChildren() {
        return children;
    }
}
