package com.yc.threadlocal;

/**
 * 演示ThreadLocal的用法2，避免传递参数的麻烦
 *
 * @version 1.0 create at 2020/2/3
 * @auther yangchuan
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1{
    public void process(){
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}
class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);
        //演示remove
        UserContextHolder.holder.remove();
        UserContextHolder.holder.set(user);
        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);

        UserContextHolder.holder.remove();
    }
}
class UserContextHolder{
    public static ThreadLocal<User> holder =
            new ThreadLocal<>();

}

class User{
    String name;

    public User(String name) {
        this.name = name;
    }
}
