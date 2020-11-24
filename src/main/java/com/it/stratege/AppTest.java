package com.it.stratege;

/**
 * 策略模式
 * 核心：
 * 1、将多变的行为抽象为接口（FlyBehavior,QuackBehavior）
 * 分别实现这些接口，来定义具体的行为
 * 2、在核心的类（Duck）中关联这个两个接口，并定义performFly,performQuack
 * 3、在Duck的子类中的构造器中分别给出FlyBehavior、QuackBehavior的具体实现
 *  这样的话，performFly和performQuack就会有不同的实现了
 * 4、在Duck类中还可以给FlyBehavior,QuackBehavior这两个接口set/get方法，
 *  这样的话，Duck的子类就可以在运行中修改类的行为！
 *
 * 这就是策略模式！
 *
 */


interface FlyBehavior {
    void fly();
}
class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("用翅膀飞。。");
    }
}
class FlyWithRocket implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("背上绑上火箭飞！");
    }
}

class FlyWithKick implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("用脚踢飞！");
    }
}

class FlyNoway implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}

interface QuackBehavior{
    void quack();
}

class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}

class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("吱吱吱的叫");
    }
}

class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}

abstract class Duck{

    protected FlyBehavior fb;
    protected QuackBehavior qb;

    public FlyBehavior getFb() {
        return fb;
    }

    public void setFb(FlyBehavior fb) {
        this.fb = fb;
    }

    public QuackBehavior getQb() {
        return qb;
    }

    public void setQb(QuackBehavior qb) {
        this.qb = qb;
    }

    public void performFly() {
        fb.fly();
    }
    public void performQuack(){
        qb.quack();
    }

    public void swim(){
        System.out.println("游泳。。。");
    }


    public abstract void display();
}

class MallardDuck extends Duck {

    public MallardDuck() {
        this.fb = new FlyWithWings();
        this.qb = new Quack();
    }

    @Override
    public void display() {
        System.out.println("外观是野鸭");
    }
}

class RubberDuck extends Duck {

    public RubberDuck() {
        this.fb = new FlyWithKick();
        this.qb = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("外观是红头野鸭");
    }
}

class MuteDuck extends Duck {
    public MuteDuck(){
        this.fb = new FlyNoway();
    }


    @Override
    public void display() {
        System.out.println("外观是诱饵鸭");
    }
}


public class AppTest {
    public static void main(String[] args) {
        MallardDuck d = new MallardDuck();
        d.display();
        d.swim();
        d.performFly();
        d.performQuack();

        /**
         * 策略模式的强大之处之一：可在运行时修改类的行为
         * 此例中，d2本来不会飞，加入d2.setFb(new FlyWithRocket());后，d2就可以飞了
         */
        MuteDuck d2 = new MuteDuck();
        d2.performFly();
        d2.setFb(new FlyWithRocket());
        d2.performFly();
    }
}
