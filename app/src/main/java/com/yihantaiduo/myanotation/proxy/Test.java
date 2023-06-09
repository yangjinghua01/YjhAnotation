package com.yihantaiduo.myanotation.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        静态代理
//        Massage massage = new SanGe();
//        Agent agent = new Agent(massage);
//        agent.massage();
//        动态代理
        SanGe sanGe = new SanGe();



       Object obj =  Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Massage.class, Wash.class}, new InvocationHandler() {
            @Override
            public Object invoke (  Object proxy, Method method, Object[] args ) throws Throwable {
                System.out.println("------->");
                return method.invoke(sanGe,args);
            }
        });
       Massage massage1 = (Massage) obj;
       massage1.massage();




       Wash wash = (Wash) obj;
       wash.Wash();
    }
}
