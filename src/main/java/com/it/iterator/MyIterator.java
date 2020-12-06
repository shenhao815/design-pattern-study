package com.it.iterator;

/**
 * 基本概念:
 *  就是提供一种方法顺序访问一个聚合对象中的各个元素，而不是暴露其内部的表示。
 * 使用迭代器模式的优点：
 *  1，遍历集合或者数组
 *  2，忽略集合和数组的结构
 *  3，提供不同的遍历方式
 *  4，符合单一职责原则
 * 迭代器角色：
 *  1，抽象迭代器：该接口必须定义实现迭代功能的最小定义方法集。
 *  2，具体迭代器：迭代器接口Iterator的实现类。可以根据具体情况加以实现。
 *  3，抽象聚合类：定义基本功能以及提供类似Iterator iterator()的方法。
 *  4，具体聚合类：容器接口的实现类。必须实现Iterator iterator()方法。
 */
// 抽象迭代器Iterator.java
public interface MyIterator {
    boolean hasNext();
    Object next();
    void remove();
}
