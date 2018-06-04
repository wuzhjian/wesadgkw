package jvm;

/**
 * Created by Admin on 2018/5/13.
 */
public class Test11 {

    public Test11 obj;

    /**
     * 方法返回Test11对象，发送逃逸
     * @return
     */
    public Test11 getInstance() {
        return obj == null? new Test11() : obj;
    }

    /**
     * 为成员属性赋值，发送逃逸
     */
    public void setObj() {
        this.obj = new Test11();
    }

    /**
     * 对象的作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useTest11() {
        Test11 s = new Test11();
    }

    /**
     * 引用成员变量的值，发送逃逸
     */
    public void useTest112() {
        Test11 s = getInstance();
    }
}
