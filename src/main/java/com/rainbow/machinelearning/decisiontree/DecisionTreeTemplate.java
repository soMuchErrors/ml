package com.rainbow.machinelearning.decisiontree;

import com.rainbow.machinelearning.dto.DecisionTreeNode;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 决策树模板类
 * 输入 数据集，输出 决策树模型
 * @author ${xiami}
 * @version $Id: DecisionTreeTemplate.java, v 0.1 2019年01月12日 16:06 Exp $
 */
public abstract class DecisionTreeTemplate {


    /**
     * 输入训练集生成决策树
     * @param trainSets
     * @return
     */
    protected DecisionTreeNode createDecisionTree(String[] trainSets, String[] testSets) {

        // 获取训练集的属性集
        Map<String, List<String>> attrs = getAttrs(trainSets);


        for (String attr : attrs.keySet()){

        }

        return null;
    }

    /**
     * 获取属性集合
     * @param trainSets
     * @return
     */
    protected abstract Map<String, List<String>> getAttrs(String[] trainSets);

    public static void main(String... args){
        System.out.println(new Date(1519629576232L));
    }

}

