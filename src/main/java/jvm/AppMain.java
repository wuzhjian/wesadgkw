package jvm;

/**
 * Created by Admin on 2018/5/5.
 * 运行时, jvm 把appmain的信息都放入方法区
 */
public class AppMain {

    public static void main(String[] args) {

        Sample test1 = new Sample("测试1"); //test1是引用，所以放到栈区里， Sample是自定义对象应该放到堆里面

        Sample test2 = new Sample("测试2");

        test1.printName();
        test2.printName();




    }

}
