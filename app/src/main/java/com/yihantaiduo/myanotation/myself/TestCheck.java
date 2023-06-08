package com.yihantaiduo.myanotation.myself;

import com.yihantaiduo.myanotation.intdef.Test;

public class TestCheck {
    private String a;
    private String b;
    public TestCheck() {
    }

    public TestCheck(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @checkInput()
    public void add(String s, String s1){
        try {
            CheckImpl.checkInputUti(TestCheck.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
