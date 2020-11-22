package com.it.factoryMethod.order;

import com.it.factoryMethod.pizza.BJCheesePizza;
import com.it.factoryMethod.pizza.BJPepperPizza;
import com.it.factoryMethod.pizza.Pizza;

public class BJOrderPizza extends OrderPizza {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        } else {
            pizza = null;
        }
        return pizza;
    }
}
