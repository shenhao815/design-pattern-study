package com.it.decorator;

abstract class Beverage {
    private String description;

    public Beverage(String description) {
        this.description = description;
    }

    public abstract double cost();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


class Decaf extends Beverage {

    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        return 1;
    }
}

class Espresso extends Beverage {

    public Espresso() {
        super("浓缩咖啡");
    }

    @Override
    public double cost() {
        return 2;
    }
}

class DarkRoast extends Beverage {

    public DarkRoast() {
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3;
    }
}

// ==========调料类=============
/*
    判断两个类之间能不能有继承关系，主要看这两个类之间有没有“is a”关系。并且还要符合里氏替换原则！
    以上只是原则，不是语法强制的！也就是说，在特定情况下，可以违返这个规则，比如在装饰器模式中就是这样：
    尽管调料不是饮料，但是为了制作出装饰器模式，我们也只能让调料去继承饮料！
 */
abstract class Condiment extends Beverage {

    // 让调料类，关联饮料类
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

class Milk extends Condiment {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 牛奶";
    }
}

class Soy extends Condiment {
    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.3;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 豆浆";
    }
}

// ============时空线 线以下代表客户端=============================================
// 添加一个饮料
class Tea extends Beverage {

    public Tea() {
        super("茶");
    }

    @Override
    public double cost() {
        return 2;
    }
}

// 添加一个调料
class Gouqi extends Condiment {

    public Gouqi(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 枸杞";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }
}

public class AppTest {
    public static void main(String[] args) {

        Beverage b = new DarkRoast();
        Beverage milk = new Milk(b);
        Beverage soy = new Soy(milk);

        // 客户端自己扩展
        Beverage t = new Tea();
        Beverage g = new Gouqi(t);
        System.out.println(soy.getDescription() + ": " + soy.cost());
    }
}
/*
    优点：
        1. 加一个新的饮料，不会违反开闭原则
           加一个新的调料，也不会违反开闭原则
    缺点：
        1. 类还是有点多，但是已经尽力了！这已经是最优解了！
 */