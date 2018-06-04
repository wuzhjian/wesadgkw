package jvm;

/**
 * Created by Admin on 2018/5/5.
 * 运行时, jvm 把appmain的信息都放入方法区
 */
public class Sample {

    // new Sample实例后， name 引用放入栈区里，  name 对象放入堆里
    private String name;

    public Sample(String name) {
        this.name = name;
    }

    //print方法本身放入 方法区里。
    public void printName() {
        System.out.println(name);
    }


}
