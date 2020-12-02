package com.it.composite.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 组合模式 + 迭代器模式
 * <p>
 * 组合模式只适用于树型结构的场景
 * <p>
 * 此例中 菜单等于树型结构中的非子节点  菜单项等于树型结构中的子节点
 */

// 为了保证菜单和菜单项具有相同的接口，所以在它们的父类这里，把菜单需要的方法和菜单项需要的方法统统定义出来
// 菜单
abstract class MenuComponent {
    private String name;
    private String description;

    public MenuComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // 属于菜单的方法：add remove getChild
    // 这些方法对于菜单而言，是有意义的，但是对于菜单项而言是没有意义的，那为什么还非要定义到这个父类中呢？
    // 为的就是：组合模式使得用户对单个对象和组合对象的使用具有一致性
    public void add(MenuComponent mc) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent mc) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // 属于菜单项的方法：getPrice() isVegetarian()
    // 这些方法对于菜单项而言，是有意义的，但是对于菜单而言没有意义，那为什么还非要定义到这个父类中呢？
    // 为的就是：组合模式使得用户对单个对象和组合对象的使用具有一致性
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }


    public List getList() {
        throw new UnsupportedOperationException();
    }

    public CompositeIterator iterator() {
        throw new UnsupportedOperationException();
    }


    public abstract void print(String prefix);

}

class Menu extends MenuComponent {

    private List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        super(name, description);
    }

    @Override
    public void add(MenuComponent mc) {
        list.add(mc);
    }

    @Override
    public void remove(MenuComponent mc) {
        list.remove(mc);
    }

    @Override
    public MenuComponent getChild(int i) {
        return list.get(i);
    }

    @Override
    public void print(String prefix) {
        System.out.println(prefix + "<<" + getName() + ">>   " + getDescription());
        Iterator<MenuComponent> it = list.iterator();
        while (it.hasNext()) {
            MenuComponent mc = it.next();
            mc.print("\t" + prefix);
        }
    }

    public List<MenuComponent> getList() {
        return list;
    }

    public CompositeIterator iterator() {
        return new CompositeIterator(list.iterator());
    }
}

class MenuItem extends MenuComponent {
    private boolean vegetarian;
    private double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        super(name, description);
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void print(String prefix) {
        String str = vegetarian ? "(素食)" : "";
        System.out.println(prefix + getName() + str + ": " + getDescription());
    }
}

/**
 * 迭代器模式  非常重要
 */
class CompositeIterator implements Iterator<MenuComponent> {
    private Stack<Iterator<MenuComponent>> s = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> it) {
        s.push(it);
    }

    @Override
    public boolean hasNext() {
        if (s.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComponent> it = s.peek();
            if (!it.hasNext()) {
                s.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public MenuComponent next() {
        Iterator<MenuComponent> it = s.peek();
        MenuComponent mc = it.next();
        if (mc instanceof Menu) {
            s.push(((Menu) mc).getList().iterator());

        }
        return mc;
    }
}


public class AppTest {
    public static void main(String[] args) {
        MenuComponent menu = setDatas();

        CompositeIterator iterator = menu.iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            try {
                if (next.isVegetarian()) {
                    next.print("");
                }
            } catch (Exception ex) {
                // 吞异常
            }
        }

    }

    private static MenuComponent setDatas() {
        MenuComponent menu = new Menu("蜗牛餐厅菜单", "没有蜗牛，不要点蜗牛");
        MenuComponent menu1 = new Menu("陕菜", "xxxxxxxxxx");
        MenuComponent menu2 = new Menu("川菜", "yyyyyyyyyy");
        MenuComponent menu3 = new Menu("鲁菜", "zzzzzzzzzz");

        MenuComponent mi1 = new MenuItem("胡辣汤1", "aaaaaaa", false, 6);
        MenuComponent mi2 = new MenuItem("凉皮", "aaaaaaa", true, 6);
        MenuComponent mi3 = new MenuItem("胡辣汤3", "aaaaaaa", false, 6);

        MenuComponent mi4 = new MenuItem("剁椒鱼头1", "aaaaaaa", false, 16);
        MenuComponent mi5 = new MenuItem("干煸豆角", "aaaaaaa", true, 16);
        MenuComponent mi6 = new MenuItem("剁椒鱼头3", "aaaaaaa", false, 16);

        MenuComponent mi7 = new MenuItem("牛肉拉面", "aaaaaaa", false, 12);
        MenuComponent mi8 = new MenuItem("老干妈土豆丝", "aaaaaaa", true, 7);
        MenuComponent mi9 = new MenuItem("热干面", "aaaaaaa", true, 7);

        menu1.add(mi1);
        menu1.add(mi2);
        menu1.add(mi3);

        menu2.add(mi4);
        menu2.add(mi5);
        menu2.add(mi6);

        menu3.add(mi7);
        menu3.add(mi8);
        menu3.add(mi9);

        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        return menu;
    }
}
