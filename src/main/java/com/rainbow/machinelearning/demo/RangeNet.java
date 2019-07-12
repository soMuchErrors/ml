package com.rainbow.machinelearning.demo;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ${xiami}
 * @version $Id: HadoopDemo.java, v 0.1 2019年03月18日 18:15 Exp $
 */
public class RangeNet {

    public static void main(String... args){
        // 训练数据集 X
        double[][] X = {{5,4.5}, {4,3.7}, {2, 1.8}};

        // 初始化 形状参数 学习率
        double gm = 0.1, std = 0.1;

        // 输出化权值
        double[] w = {0, -1 , 1};

        double[] scores = new double[X.length];

        double[] tempW = new double[w.length];

        int index = 0;

        //
        int size = 0;
        for (int i = 0;i < scores.length; i++) {
            for (int j = i + 1; j < scores.length; j++) {
                size ++;
            }
        }

        // 交叉熵
        double[] pairLoss = new double[size];
        double[] tempPairLoss = new double[size];

        do {
            index ++;
            System.out.println("第" + index + "次 ----------------- 开始");

            System.out.println("权值：" + JSONObject.toJSONString(w));

            // 计算得分
            for (int i = 0; i < X.length; i ++){
                scores[i] = score(X[i], w);
            }

            System.out.println("得分：" + JSONObject.toJSONString(scores));


            double[] list = new double[size];

            for (int i = 0;i < scores.length; i++){
                for (int j = i + 1; j< scores.length; j++){
                    int s = 0;
                    if (scores[i] > scores[j]){
                        s = 1;
                    }else if (scores[i] < scores[j]){
                        s = -1;
                    }

                    double gd = gradientDescent(s, gm, scores[i], scores[j]);
                    pairLoss[i+j-1] = rangeNetLoss(s, gm, scores[i], scores[j]);
//                    System.out.println("lambda " +i+j +": " + gd);

                    list[i] = new BigDecimal(list[i] + gd).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
                    list[j] = new BigDecimal(list[j] - gd).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }

            System.out.println("lambda list :" + JSONObject.toJSONString(list));
            System.out.println("交叉熵 :" + JSONObject.toJSONString(pairLoss));

            for (int i=0; i< tempW.length; i++){
                double sumLambda = 0;
                for (int j= 0; j < X.length; j++){
                    if (w[i] == 0){
                        sumLambda += 0;
                    }else {
                        sumLambda += list[j] * X[j][i-1];
                    }
                }


                    tempW[i] = w[i] + std * sumLambda;

            }

            System.out.println("迭代的权值 :" + JSONObject.toJSONString(tempW));

            // 重置权值
            if (tempW.length > 0){
                for (int i=0; i<tempW.length; i++){
                    w[i] = tempW[i];
                }
            }

            System.out.println("第" + index + "次 ----------------- 结束");
        }while (stopOpinion(scores));

        System.out.println("最终得分 :" + JSONObject.toJSONString(scores));
//        System.out.println(rangeNetLoss(-1, 0.1, -0.5, -0.3));
    }

    /**
     * 损失函数
     * @param s
     * @param gm
     * @param s1
     * @param s2
     * @return
     */
    public static double rangeNetLoss(Integer s,double gm,double s1, double s2){
        return new BigDecimal(- 0.5 * (1 - s) * gm * (s1 - s2) + Math.log(1 + Math.pow(Math.E, - gm * (s1 - s2)))).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 得分函数
     * @param x
     * @param w
     * @return
     */
    public static double score(double[] x, double[] w){
        double score = 0.0;

        for (int i = 0; i < w.length; i++){
            if (i == 0){
                score += w[i];
            }else {
                score += w[i] * x[i-1];
            }
        }

        return new BigDecimal(score).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 梯度下降算法
     * @return
     */
    public static double gradientDescent(Integer s, double gm, double s1, double s2){
        return new BigDecimal(gm * (0.5 * (1 - s) - Math.pow(1 + (Math.pow(Math.E, gm * (s1 - s2))), -1))).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static boolean stopOpinion(double[] scores){
        for (int i = 1; i < scores.length; i++){
            if (scores[i - 1] < scores[i]){
                return true;
            }
        }
        return false;
    }


}
