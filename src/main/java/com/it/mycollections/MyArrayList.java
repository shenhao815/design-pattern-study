package com.it.mycollections;

import com.it.iterator.MyIterator;

// 具体迭代器Itr 具体聚合类MyArrayList
public class MyArrayList {

    private static int capacity = 10;
    private Object[] list;
    private int size;

    public MyArrayList() {
        this(capacity);
    }

    public MyArrayList(int length) {
        this.list = new Object[length];
    }

    public void add(Object object) {
        ensureCapacityInternal(size+1);
        list[size++] = object;
    }

    public void remove(int index) {
        if (index == size-1) {
            list[index] = null;
        }
        for (int i = index; i < size-1; i++) {
            list[i] = list[i + 1];
        }
        list[size-1] = null;
        size--;

    }

    public Object get(int index) {
        return list[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacityInternal(int newSize) {
        if (capacity == newSize) {
            System.out.println("===========");
            capacity = size + size / 2;
            Object[] newList = new Object[capacity];
            for (int i = 0; i < size; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }

    public MyIterator iterator() {
        return new Itr();
    }

    private class Itr implements MyIterator {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Object next() {
            return list[cursor++];
        }

        @Override
        public void remove() {

        }
    }

}

class AppTest{
    public static void main(String[] args) {

        MyArrayList mal = new MyArrayList();
        mal.iterator();

        mal.add("a");
        mal.add("b");
        mal.add("c");
        mal.add("d");
        mal.add("e");
        mal.add("f");
        mal.add("f");
        mal.add("f");
        mal.add("f");

        /*for (int i = 0; i < mal.size(); i++) {
            System.out.print(mal.get(i));
        }
        mal.remove(mal.size());
        mal.remove(mal.size());
        mal.remove(mal.size());
        for (int i = 0; i < mal.size(); i++) {
            System.out.print(mal.get(i));
        }
        System.out.println(mal.size());*/

        MyIterator myIterator = mal.iterator();
        while (myIterator.hasNext()) {
            Object it = myIterator.next();
            System.out.println(it);
        }
    }
}
