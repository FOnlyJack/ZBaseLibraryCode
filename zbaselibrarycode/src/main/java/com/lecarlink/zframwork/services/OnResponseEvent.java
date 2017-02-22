package com.lecarlink.zframwork.services;


public interface OnResponseEvent<T extends BaseAccessResponse> {
    /**
     * 线程返回回调
     */
    T onResponse(Object object);

}
