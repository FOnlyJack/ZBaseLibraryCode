package com.lecarlink.zframwork.core.collecion;

/**
 * 拼音排序的javabean必须实现该接口
 */
public interface PinyinKeySortable {
    /**
     * 实现该方法，并返回要拼音排序的字母
     * @return
     */
    public String getPendKey();
}
