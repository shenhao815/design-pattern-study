package com.it.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * 实现的关键之处：
 * 1、报社有一个记录了所有订阅者的列表
 * 2、每当报社有新报纸，就会遍历整个列表，然后把报纸“推”给列表中的每一个用户
 * 3、用户也知道报社的电话，可以随时订阅和取消订阅（即对列表进行增删）
 */

// 发布者接口（主题接口）
interface Subject {
    // 注册观察者
    void registerObserver(Observer observer);
    // 删除观察者
    void removeObserver(Observer observer);
    // 给所有观察者发送消息
    void update();
}
// 观察者接口
interface Observer {
    // 接收发布者发来的消息
    void updateNewsPaper(String newsPaper);

    // 订阅某个报纸
    void add(Subject subject);
}


// 报社 （代码是固定的，不可动）
class Office{
    protected String newsPaper;

    public void setNewsPaper(String newsPaper) {
        // 新报纸出炉
        this.newsPaper = newsPaper;
        // 发送报纸
        dataChange();
    }

    public void dataChange() {
    }
}

// 用户
class User implements Observer{
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void updateNewsPaper(String newsPaper) {
        System.out.println(name + "收到报纸，头条：" + newsPaper);
    }

    @Override
    public void add(Subject subject) {
        subject.registerObserver(this);
    }
}


class Sender extends Office implements Subject {

    private List<Observer> list = new ArrayList<>();

    @Override
    public void dataChange() {
        this.update();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void update() {
        for (Observer observer: list){
            observer.updateNewsPaper(newsPaper);
        }
    }
}



// =======================================时空线
public class AppTest {

    public static void main(String[] args) {
        Sender sender = new Sender();
        User user = new User("张珊珊");
        User user1 = new User("李思思");
        User user2 = new User("王微微");

        user.add(sender);
        user1.add(sender);
        user2.add(sender);

        sender.setNewsPaper("杨幂离婚了！");
    }
}
