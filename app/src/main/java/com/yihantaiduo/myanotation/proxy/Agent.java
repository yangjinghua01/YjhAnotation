package com.yihantaiduo.myanotation.proxy;

public class Agent implements Massage {
    private final Massage massage;

    public Agent(Massage massage) {
        this.massage = massage;
    }
//    前置处理
    public void before(){
        System.out.println("执行前置处理");
    }
//    后置处理
    public void after(){
        System.out.println("执行后置处理");
    }

    @Override
    public void massage() {
        before();
        massage.massage();
        after();
    }
}
