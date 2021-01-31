package com.it.j_abstractfactory;

/*
    抽象工厂方法
        优点：
        1。仍然有简单工厂和工厂方法的优点
        2. 更重要的是，抽象工厂把工厂类的数量减少了！无论有多少个产品等级，工厂就一套。

        杠点：
        1。为什么三秦工厂中，就必须是米线搭配冰峰呢？为什么就不能是米线搭配可乐？
         解释：抽象工厂中，可以生产多个产品，并且多个产品之间，必须有一定的内存联系。
         同一个工厂中的产品都属于同一个产品簇！不能把不同产品簇中的产品混合到一个抽象工厂的实现类中。

        缺点：
        1。当产品等级发现变化时（增加产品等级、删除产品等级），都要引起所有以前工厂代码的修改，这就违反了“开闭原则”！

        结论：当产品等级比较固定时，可以考虑使用抽象工厂
             如果产品等级经常变化，则不建议使用抽象工厂。

 */
interface Food{
    void eat();
}

// 具体产品1
class Hamburger implements Food {

    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

class RiceNoodle implements Food {
    @Override
    public void eat() {
        System.out.println("吃过桥米线");
    }
}


// 具体产品2
interface Drink {
    void drink();
}

class Cola implements Drink {

    @Override
    public void drink() {
        System.out.println("喝可口可乐");
    }
}

class IcePeak implements Drink {
    @Override
    public void drink() {
        System.out.println("喝冰峰");
    }
}

interface Factory {
    Food getFood();
    Drink getDrink();
}

class KFCFactory implements Factory {
    @Override
    public Food getFood() {
        return new Hamburger();
    }

    @Override
    public Drink getDrink() {
        return new Cola();
    }
}

class SanqinFactory implements Factory {

    @Override
    public Food getFood() {
        return new RiceNoodle();
    }

    @Override
    public Drink getDrink() {
        return new IcePeak();
    }
}

// 服务端定义的业务类
class Bussiness {
    public static void taste(Factory ff) {
        Food food = ff.getFood();
        System.out.println("评委1，品尝：");
        food.eat();

        Drink drink = ff.getDrink();
        System.out.println("评委1，喝饮料");
        drink.drink();
    }
}


// =====================================

// 客户端现在自己拓展
class Lp implements Food {
    @Override
    public void eat() {
        System.out.println("吃面皮");
    }
}

class Fenda implements Drink {
    @Override
    public void drink() {
        System.out.println("喝芬达");
    }
}

class BaoJiFactory implements Factory {
    @Override
    public Food getFood() {
        return new Lp();
    }

    @Override
    public Drink getDrink() {
        return new Fenda();
    }
}


public class AppTest {
    public static void main(String[] args) {
        // Bussiness.taste(new SanqinFactory());
        Bussiness.taste(new BaoJiFactory());
    }
}
