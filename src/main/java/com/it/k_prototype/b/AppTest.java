package com.it.k_prototype.b;

import java.util.Date;

/*
    原型模式：
    1。必须让目标类型实现Cloneable接口，该接口中没有任何抽象方法。这样的接口仅仅是一个“标记接口”，作用是：告诉JVM，
    任何实现了该Cloneable接口的类的对象，可以被克隆！！
    2。必须重写java.lang.Object的clone方法，一定要把该方法的访问修饰符，重写为public！！不然无法调用clone方法！
 */
class WeekReport implements Cloneable {
    private int id;
    private String emp;
    private String summary;
    private String plan;
    private String suggestion;
    private Date time;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "WeekReport{" +
                "id=" + id +
                ", emp='" + emp + '\'' +
                ", summary='" + summary + '\'' +
                ", plan='" + plan + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}


public class AppTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        //第一周周报
        WeekReport wr = new WeekReport();
        wr.setEmp("张珊珊");
        wr.setSummary("讲解完了7大设计原则");
        wr.setPlan("讲解完设计模式");
        wr.setSuggestion("无");
        wr.setTime(new Date());
        System.out.println(wr);

        // 克隆wr对象，得到一个新对象
        // 思考1：clone方法，会不会引起构造器的调用？不会！那么这个clone方法是如何实现克隆对象的效果呢？
        // clone方法是直接赋值内存中的2进制。效率更高！
        // 思考2：既然clone方法没有引起构造器的调用，那么克隆出的对象，和原先的对象，地址是否一致？不一致！
        // 最终是两个不同空间中的对象。
        WeekReport wr2 = (WeekReport) wr.clone();
        wr2.setSummary("aaa");
        wr2.setSuggestion("xyz");
        System.out.println(wr2==wr);

        // 问题是，修改了其中一个对象的属性，另一个对象的属性是否会变化？
        // 下面修改了wr2的time属性，而wr的time字段也一起被修改了！为什么呢？因为，目前这种克隆方式是“浅拷贝”
        // 所谓的浅拷贝，就是把原来的对象的2进制，原封不动的复制！
        wr2.getTime().setTime(0);
        System.out.println(wr);
        System.out.println(wr2);
    }
}