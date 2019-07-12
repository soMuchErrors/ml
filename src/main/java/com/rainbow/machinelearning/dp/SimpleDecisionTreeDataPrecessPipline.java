package com.rainbow.machinelearning.dp;

import com.alibaba.fastjson.JSONObject;
import com.rainbow.machinelearning.data.DataSet;
import com.rainbow.machinelearning.dto.DataTreeNode;
import com.rainbow.machinelearning.dto.ResultNode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单决策树数据处理类
 *
 * @author ${xiami}
 * @version $Id: SimpleDesitionTreeDataPrecessPipline.java, v 0.1 2019年01月07日 17:51 Exp $
 */
public class SimpleDecisionTreeDataPrecessPipline implements DataProcessPipline{

    private final static double ONE = 1.0;

    private final static String MELON_RESULT_KEY = "isGood";

    /**
     *
     * @param dataSets
     * @return
     */
    @Override
    public Object dataProcess(String[] dataSets) {

        List<Map<String,String>> dataList = new ArrayList<>();

        for (String json : dataSets){
            dataList.add(JSONObject.parseObject(json, Map.class));
        }

        // 属性集
        Map<String,List<String>> attrs = new HashMap<>();

        // 数据集
        Map<String, DataTreeNode> treeNodes = new HashMap<>();

        for (Map<String,String> map : dataList){
            for (String key : map.keySet()){
                // 组装属性集
                if (!StringUtils.isEmpty(map.get(key)) && !MELON_RESULT_KEY.equals(key)) {
                    if (attrs.get(key) == null) {
                        attrs.put(key, new ArrayList<String>() {{
                            add(map.get(key));
                        }});
                    } else if (!attrs.get(key).contains(map.get(key))) {
                        attrs.get(key).add(map.get(key));
                    }
                }

                // 组装数据集
                if(MELON_RESULT_KEY.equals(key)){
                    continue;
                }

                // 如果没有这个根节点
                if (treeNodes.get(key) == null){
                    DataTreeNode treeNode = new DataTreeNode();
                    treeNode.setAttrName(key);
                    treeNode.setResultNodes(new HashMap<>());
                    treeNode.setTreeNodeMap(new HashMap<>());
                    treeNode.setTotal(ONE);
                    treeNodes.put(key, treeNode);

                    // 新增结果信息
                    ResultNode resultNode = new ResultNode();
                    resultNode.setResultName(map.get(MELON_RESULT_KEY));
                    resultNode.setNumber(ONE);
                    treeNode.getResultNodes().put(map.get(MELON_RESULT_KEY), resultNode);

                    // 如果该属性为空，则不追加子属性
                    if (StringUtils.isEmpty(map.get(key))){
                        continue;
                    }

                    // 增加子属性信息
                    DataTreeNode childTreeNode = createChildTreeNode(map, key);
                    treeNode.getTreeNodeMap().put(map.get(key), childTreeNode);
                }else {
                    DataTreeNode treeNode = treeNodes.get(key);
                    treeNode.setTotal(treeNode.getTotal() + ONE);

                    // 新增结果信息
                    Map<String, ResultNode> resultNodeMap = treeNode.getResultNodes();
                    if (!resultNodeMap.containsKey(map.get(MELON_RESULT_KEY))){
                        ResultNode resultNode = new ResultNode();
                        resultNode.setResultName(map.get(MELON_RESULT_KEY));
                        resultNode.setNumber(ONE);
                        treeNode.getResultNodes().put(map.get(MELON_RESULT_KEY), resultNode);
                    } else {
                        ResultNode resultNode = treeNode.getResultNodes().get(map.get(MELON_RESULT_KEY));
                        resultNode.setNumber(resultNode.getNumber() + ONE);
                    }

                    // 如果该属性为空，则不追加子属性
                    if (StringUtils.isEmpty(map.get(key))){
                        continue;
                    }

                    if (treeNode.getTreeNodeMap().get(map.get(key)) == null){
                        // 增加子属性信息
                        DataTreeNode childTreeNode = createChildTreeNode(map, key);
                        treeNode.getTreeNodeMap().put(map.get(key), childTreeNode);
                    }else {
                        DataTreeNode childTreeNode = treeNode.getTreeNodeMap().get(map.get(key));
                        childTreeNode.setTotal(childTreeNode.getTotal() + ONE);

                        if (!childTreeNode.getResultNodes().containsKey(map.get(MELON_RESULT_KEY))){
                            ResultNode resultNode = new ResultNode();
                            resultNode.setResultName(map.get(MELON_RESULT_KEY));
                            resultNode.setNumber(ONE);
                            childTreeNode.getResultNodes().put(map.get(MELON_RESULT_KEY), resultNode);
                        }else {
                            ResultNode resultNode = childTreeNode.getResultNodes().get(map.get(MELON_RESULT_KEY));
                            resultNode.setNumber(resultNode.getNumber() + ONE);
                        }
                    }
                }
            }
        }

        System.out.println("treeNodes : " +JSONObject.toJSONString(treeNodes));
        System.out.println("attrs : " + JSONObject.toJSONString(attrs));

        return null;
    }

    private DataTreeNode createChildTreeNode(Map<String,String> map, String key){
        DataTreeNode childTreeNode = new DataTreeNode();
        childTreeNode.setAttrName(map.get(key));
        childTreeNode.setResultNodes(new HashMap<>());

        ResultNode childResultNode = new ResultNode();
        childResultNode.setResultName(map.get(MELON_RESULT_KEY));
        childResultNode.setNumber(ONE);
        childTreeNode.getResultNodes().put(map.get(MELON_RESULT_KEY), childResultNode);
        childTreeNode.setTotal(ONE);

        return childTreeNode;
    }

    /**
     * 查询需要的结果集
     *
     * @param matchString
     * @return
     */
    public List<String> searchDataByAttr(String matchString){
        List<String> rstList = new ArrayList<>();
        for (String s : DataSet.WATER_MELON_TRAIN_SET){
            if (s.matches(matchString)){
                rstList.add(s);
            }
        }
        return rstList;
    }

    public static void main(String... args){
//        long t = System.currentTimeMillis();
//        SimpleDecisionTreeDataPrecessPipline simpleDecisionTreeDataPrecessPipline = new SimpleDecisionTreeDataPrecessPipline();
//        simpleDecisionTreeDataPrecessPipline.dataProcess(DataSet.WATER_MELON);
//        System.out.println(System.currentTimeMillis() - t);

    }
}
