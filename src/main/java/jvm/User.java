package jvm;

import java.util.Date;

/**
 * Created by Admin on 2018/5/12.
 */
public class User {
    private String name;
    private int age;

    private Date birth;

    private boolean flag;

    public User() {
        System.out.println("user create success ...");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean isFlag() {

        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User [name=" + name +  ", age=" + age + ", birth=" + birth + ", flag=" + flag + " ]";
    }
}
