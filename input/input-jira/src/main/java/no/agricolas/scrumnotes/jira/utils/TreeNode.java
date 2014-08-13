package no.agricolas.scrumnotes.jira.utils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Øyvind Strømmen
 */
public class TreeNode<N> {

    private TreeNode<N> parent;

    private Map<TreeNode<N>, TreeNode<N>> children;

    private N content;

    public TreeNode() {
        this(null);
    }

    public TreeNode(N content) {
        this.content = content;
        this.children = new HashMap<TreeNode<N>, TreeNode<N>>();
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

    public Boolean addChild(TreeNode<N> childNode) {
        if (!children.containsKey(childNode)) {
            children.put(childNode, childNode);
            childNode.parent = this;
            return true;
        }
        return false;
    }

    public TreeNode<N> getChild(TreeNode<N> childNode) {
        return children.get(childNode);
    }

    public N getContent() {
        return content;
    }

    public void setContent(N content) {
        this.content = content;
    }

    public Map<TreeNode<N>, TreeNode<N>> getChildren() {
        return children;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(content)
                .toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object instanceof TreeNode) {
            final TreeNode treeNode = (TreeNode) object;
            return new EqualsBuilder()
                    .append(content, treeNode.content)
                    .isEquals();
        }
        return true;
    }
}
