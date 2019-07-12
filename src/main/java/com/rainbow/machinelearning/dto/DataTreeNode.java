package com.rainbow.machinelearning.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author ${xiami}
 * @version $Id: DesitionTreeNode.java, v 0.1 2019年01月08日 14:33 Exp $
 */
@Data
public class DataTreeNode {

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 数据集总数
     */
    private Double total;

    /**
     * 结果数组
     */
    private Map<String, ResultNode> resultNodes;

    private Map<String, DataTreeNode> treeNodeMap;

    @Override
    public boolean equals(Object o){
        if (o instanceof DataTreeNode){
            if (this.getAttrName().equals (((DataTreeNode) o).getAttrName())){
                return true;
            }
        }
        return false;
    }
}
