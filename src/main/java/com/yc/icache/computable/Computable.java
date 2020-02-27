package com.yc.icache.computable;

/**
 * 有一个计算函数computer，用来代表耗时计算，每个计算器
 * 都要实现这个接口，这样就可以无侵入实现缓存功能
 *
 * @version 1.0 create at 2020/2/27
 * @auther yangchuan
 */
public interface Computable<A, V> {

    V compute(A arg) throws Exception;

}
