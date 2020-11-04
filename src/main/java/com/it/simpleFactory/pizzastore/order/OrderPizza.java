package com.it.simpleFactory.pizzastore.order;

import com.it.simpleFactory.pizzastore.pizza.CheesePizza;
import com.it.simpleFactory.pizzastore.pizza.GreekPizza;
import com.it.simpleFactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 传统实现方式
 * 优缺点：
 * 1） 优点是比较好理解，简单易操作
 * 2） 缺点是违反了设计模式的ocp原则，即对扩展开放，对修改关闭。即当我们给类增加
 *      新功能的时候，尽量不修改代码，或者尽可能少修改代码。
 * 3） 比如我们这时要新增加一个Pizza的种类（Pepper披萨），我们需要修改OrderPizza类，
 *      但是如果需求中有多个OrderPizza情景时，那么所有OrderPizza都会有代码改动。
 *
 * 改进思路：
 * 分析：修改代码可以接受，但是如果我们在其它的地方也有创建Pizza的代码，就意味着也需要修改，
 *      而创建Pizza的代码，往往有多处。
 * 思路：把创建Pizza对象封装到一个类中，这样我们有新的Pizza种类时，只需要改该类就可以了，
 *      其它有创建到Pizza对象的代码此方法不需要修改了。这就是：简单工厂模式。“
 *      
 */
public class OrderPizza {

    public OrderPizza() {
        Pizza pizza = null;
        String orderType; // 订购披萨的类型
        do {
            orderType = getType();
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();
                pizza.setName(" 希腊披萨 ");
            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
                pizza.setName(" 奶酷披萨 ");
            } else {
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 类型： ");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
