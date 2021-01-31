package com.it.k_prototype.c;

import java.io.*;
import java.util.Date;

/*
    原型模式：
    针对b包中的问题，修改代码如下，利用序列化实现深克隆！

    推荐使用此原型模式写法！
 */
class WeekReport implements Cloneable , Serializable {
    private int id;
    private String emp;
    private String summary;
    private String plan;
    private String suggestion;
    private Date time;

    @Override
    public Object clone() throws CloneNotSupportedException {

        try {
            /*
                1）有多个流需要关闭时，只需要关闭最上层流即可！
                2）ByteArrayOutputStream/ByteArrayInputStream 是操作内存的输入输出流！
                3）ObjectOutputStream操作对象的流
             */
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(this);
            oos.close();

            byte[] bytes = out.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object clone = ois.readObject();
            ois.close();
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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


        WeekReport wr2 = (WeekReport) wr.clone();
        wr2.setSummary("aaa");
        wr2.setSuggestion("xyz");
        System.out.println(wr2==wr);


        wr2.getTime().setTime(0);
        System.out.println(wr);
        System.out.println(wr2);
    }
}