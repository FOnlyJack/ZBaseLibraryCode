package com.lecarlink.zframwork.objs;

/**
 * 保存类信息的类
 */
public class DelayObj<T> {
    private Class<? extends T> clazz;
    private T obj;

    public Class<? extends T> getClazz() {
        return clazz;
    }

    public DelayObj<T> setClazz(Class<? extends T> clazz) {
        this.clazz = clazz;
        return this;
    }

    public T getObj() {
        return obj;
    }

    public DelayObj<T> setObj(T obj) {
        this.obj = obj;
        return this;
    }

    @Override
    public String toString() {
        return "DelayObj{" +
                "clazz=" + clazz +
                ", obj=" + obj +
                '}';
    }
}
