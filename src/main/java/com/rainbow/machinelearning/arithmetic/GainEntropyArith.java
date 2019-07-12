package com.rainbow.machinelearning.arithmetic;

/**
 * 信息增益算法
 * @author ${xiami}
 * @version $Id: GainEntropyArith.java, v 0.1 2019年01月05日 11:41 Exp $
 */
public class GainEntropyArith {

    public static double gainEnt(double[][][] px){
        double temp = 0.0;

        double y = 0;
        double x = 0;

        for (double[][] pxx : px){
            y += pxx[0][1];
            x += pxx[0][0];
        }

        double[][] dx = new double[][]{{x,y},{y-x , y}};

        double entDx = EntropyArith.entropy(dx);

        for (double[][] pxx : px){
            temp += pxx[0][1] / y * EntropyArith.entropy(pxx);
        }

        return entDx - temp;
    }

}
