package com.rainbow.machinelearning.arithmetic;

/**
 * 信息熵算法:
 *
 * @author ${xiami}
 * @version $Id: EntropyArith.java, v 0.1 2019年01月05日 11:22 Exp $
 */
public class EntropyArith {

    /**
     * 入参说明：
     * 二位数组，没一个数组包含x,y  px= x/y 结果保留四位小数
     * @param px
     * @return
     */
    public  static double entropy(double[][] px){
        double rst = 0.0;
        for (double[] pxx : px){
            rst += - (pxx[0]/pxx[1] * (Math.log(pxx[0]/pxx[1]) / Math.log(2)));
        }

        return rst;
    }

    public static void main(String... args){
        System.out.println(entropy(new double[][]{{8.0,17},{9.0,17}}));
    }
}
