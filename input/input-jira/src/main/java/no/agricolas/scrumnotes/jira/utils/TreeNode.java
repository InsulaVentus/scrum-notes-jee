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

    private boolean target;

    private String fieldName;

    public TreeNode(N content) {
        this(content, false);
    }

    public TreeNode(N content, boolean target) {
        this(content, target, null);
    }

    public TreeNode(N content, boolean target, String fieldName) {
        setContent(content);
        setTarget(target);
        setFieldName(fieldName);
        setChildren(new HashMap<TreeNode<N>, TreeNode<N>>());
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

    public void setChildren(Map<TreeNode<N>, TreeNode<N>> children) {
        this.children = children;
    }

    public Map<TreeNode<N>, TreeNode<N>> getChildren() {
        return children;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public boolean isTarget() {
        return target;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public boolean hasChildren() {
        return !getChildren().isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(content)
                .append(target)
                .append(fieldName)
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
                    .append(target, treeNode.target)
                    .append(fieldName, treeNode.fieldName)
                    .isEquals();
        }
        return true;
    }
}
