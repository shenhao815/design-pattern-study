package com.it.l_builder.b;

/*
    针对a包中的问题，现下修改代码如下：
    这就是建造者模式

 */

class Computer{
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", memory='" + memory + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }
}

// 电脑建造者类，建造者类必须关联电脑产品
interface ComputerBuilder{
    Computer build();
    void setCpu();
    void setGpu();
    void setMemory();
    void setHd();

}
class AdvanceComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();
    public Computer build(){
        return computer;
    }

    @Override
    public void setCpu() {
        computer.setCpu("i7 8750hk");
    }

    @Override
    public void setGpu() {
        computer.setGpu("rtx2080ti");

    }

    @Override
    public void setMemory() {
        computer.setMemory("32g");
    }

    @Override
    public void setHd() {
        computer.setHd("500g ssd");
    }
}
class MiddleComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();
    public Computer build(){
        return computer;
    }

    @Override
    public void setCpu() {
        computer.setCpu("i7 7700hq");
    }

    @Override
    public void setGpu() {
        computer.setGpu("rtx1080");

    }

    @Override
    public void setMemory() {
        computer.setMemory("16g");
    }

    @Override
    public void setHd() {
        computer.setHd("500g ssd");
    }
}
class LowComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();
    public Computer build(){
        return computer;
    }

    @Override
    public void setCpu() {
        computer.setCpu("i5 5300u");
    }

    @Override
    public void setGpu() {
        computer.setGpu("rx940mx");

    }

    @Override
    public void setMemory() {
        computer.setMemory("8g");
    }

    @Override
    public void setHd() {
        computer.setHd("500g 机械");
    }
}

// 建造模式的指挥者
class Director{
    public Computer build(ComputerBuilder cb) {
        cb.setCpu();
        cb.setGpu();
        cb.setMemory();
        cb.setHd();
        return cb.build();
    }
}

// =========================================================
// 客户端自己扩展一个中高配的电脑
class MiddleHighComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public Computer build() {
        return computer;
    }

    @Override
    public void setCpu() {
        computer.setCpu("i3 5300u");
    }

    @Override
    public void setGpu() {
        computer.setGpu("tgx950mx");
    }

    @Override
    public void setMemory() {
        computer.setMemory("16g");
    }

    @Override
    public void setHd() {
        computer.setHd("500g 机械");
    }
}

public class AppTest {
    public static void main(String[] args) {

        // 创建一个建造者
        AdvanceComputerBuilder acb = new AdvanceComputerBuilder();
        MiddleComputerBuilder mcb = new MiddleComputerBuilder();
        LowComputerBuilder lcb = new LowComputerBuilder();

        Director director = new Director();

        // 玩游戏
        Computer c1 = director.build(acb);
        System.out.println(c1);

        // 开发
        Computer c2 = director.build(mcb);
        System.out.println(c2);

        // 娱乐
        Computer c3 = director.build(lcb);
        System.out.println(c3);

        // 中高配
        MiddleHighComputerBuilder mhcb = new MiddleHighComputerBuilder();
        Computer c4 = director.build(mhcb);
        System.out.println(c4);

    }
}
/*
这就是建造者模式：
    优点：
    1。创建对象的过程是稳定不变的（因为有ComputerBuilder接口来稳定过程）
    2。创建对象的过程只写了一次，没有重复代码（因为是由指挥者完成）
    3。当需要扩展指挥者的时候，不用修改之前的代码，这符合了开闭原则

建造者与工厂模式的区别：
工厂模式只需要一个简单的new，new出产品即可
建造者理加注重，在new出产品之后的，为产品属性赋值的过程！！

 */
