package com.it.u_observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2021-2-3
 */
// 主体类,即被观察者
interface Subject {
    void registObserver(Observer observer);

    void removeObserver(Observer observer);
}

// 观察者
interface Observer {
    void update();
}

class WeatherStation {
    private Integer tempterature;
    private Integer humidity;
    private Integer pressure;

    private List<Observer> obs = new ArrayList<>();

    public void registObserver(Observer observer) {
        this.obs.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.obs.remove(observer);
    }

    public void setData(Integer tempterature, Integer humidity, Integer pressure) {
        this.tempterature = tempterature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyAllObserver();
    }

    private void notifyAllObserver() {
        for (Observer o : obs) {
            o.update();
        }
    }

    public Integer getTempterature() {
        return tempterature;
    }

    public void setTempterature(Integer tempterature) {
        this.tempterature = tempterature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }
}

class Phone implements Observer {
    WeatherStation ws;

    public Phone(WeatherStation ws) {
        this.ws = ws;
        ws.registObserver(this);
    }

    @Override
    public void update() {
        System.out.println("当前数据:手机端");
        System.out.println("温度：" + ws.getTempterature());
        System.out.println("湿度：" + ws.getHumidity());
        System.out.println("气压：" + ws.getPressure());
    }
}

// 具体观察者1
class Computer implements Observer {
    WeatherStation ws;

    public Computer(WeatherStation weatherStation) {
        this.ws = weatherStation;
        ws.registObserver(this);
    }

    @Override
    public void update() {
        System.out.println("当前数据:电脑端");
        System.out.println("温度：" + ws.getTempterature());
        System.out.println("湿度：" + ws.getHumidity());
        System.out.println("气压：" + ws.getPressure());
    }
}

// ====================================================
// 客户端自己扩展一个在电视上显示
class TV implements Observer {
    WeatherStation ws;

    public TV(WeatherStation weatherStation) {
        this.ws = weatherStation;
        ws.registObserver(this);
    }

    @Override
    public void update() {
        System.out.println("当前数据:电视端");
        System.out.println("温度：" + ws.getTempterature());
        System.out.println("湿度：" + ws.getHumidity());
        System.out.println("气压：" + ws.getPressure());
    }
}

public class AppTest {

    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();
        Observer phone = new Phone(ws);
        Observer computer = new Computer(ws);
        Observer tv = new TV(ws);
        ws.setData(35, 60, 101);

    }

}
