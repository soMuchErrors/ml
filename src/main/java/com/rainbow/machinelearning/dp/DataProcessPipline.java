package com.rainbow.machinelearning.dp;


/**
 * @author ${xiami}
 * @version $Id: DataProcessPipline.java, v 0.1 2019年01月07日 17:48 Exp $
 */
public interface DataProcessPipline {

    /**
     * 定义数据处理方法
     * @param dataSets
     * @return
     */
    Object dataProcess(String[] dataSets);
}
