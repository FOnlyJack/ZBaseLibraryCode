package com.lecarlink.zframwork.utils;

import java.util.Random;

public class ZAnimUtil {
    private static Random random;

    /**
     * 生成一个振动频率
     *
     * @param count
     * @param scope
     * @return
     */
    public static float[] generateShakeRate(int count, int scope) {
        if (scope < 0) {
            scope = -scope;
        }
        if (scope == 0) {
            scope = 10;
        }
        if (null == random) {
            random = new Random();
        }

        float[] rate = new float[count];
        for (int i = 0; i < count; i++) {
            if (i == 0 || i == count - 1) { // 头尾都为0
                rate[i] = 0;
                continue;
            }
            if (i % 2 == 0) {
                rate[i] = random.nextInt(scope);
            } else {
                rate[i] = random.nextInt(scope) - scope;
            }
        }
        return rate;
    }


}
