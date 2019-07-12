package com.rainbow.machinelearning.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ${xiami}
 * @version $Id: DesitionTree.java, v 0.1 2019年01月12日 16:53 Exp $
 */
@Data
public class DecisionTreeNode {

    private String treeAttrName;

    private Boolean decisionResult;

    private Boolean hasChildren;

    private List<DecisionTreeNode> childTreeNodes;
}
