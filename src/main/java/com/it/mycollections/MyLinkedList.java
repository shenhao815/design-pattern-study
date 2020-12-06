package com.it.mycollections;

import com.it.iterator.MyIterator;

import java.util.Iterator;
import java.util.LinkedList;

public class MyLinkedList {
    private class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void add(Object obj) {
        Node node = new Node(obj);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size ++;
    }

    public void remove(Object object) {
        Node first = head;
        Node prev = head;
        if (first == null) {
            return;
        }
        if (first.value == object) {
            head = first.next;
            size--;
            return;
        }
        while (first.next != null) {
            prev = first;
            if (first.value == object) {
                prev.next = first.next;
                size--;
                return;
            } else {
                first = first.next;
            }
        }
        if (tail.value == object) {
            prev.next = null;
            size--;
            return;
        }
    }

    public Object get(int index) {
        Node first = head;
        int i = 0;
        while (first.next != null) {
            if (i == index) {
                return first.value;
            } else {
                first = first.next;
            }
            i++;
        }
        return null;
    }

    public int size() {
        return size;
    }




    /*private class Itr implements MyIterator {
        private Node cursor = head;
        @Override
        public boolean hasNext() {
           //return cursor != tail;
        }

        @Override
        public Object next() {

            cursor = cursor.next;
            *//*Object val = cursor.value;
            cursor = cursor.next;
            return val;*//*
        }

        @Override
        public void remove() {

        }
    }*/


}

// =========================
class AppTest2 {
    public static void main(String[] args) {
        MyLinkedList mll = new MyLinkedList();
        mll.add("1a");
        mll.add("2b");
        mll.add("3c");
        mll.add("4e");
        mll.add("5f");

        mll.remove("5");
        mll.add("6");

        //System.out.println(mll.size());

    }
}
