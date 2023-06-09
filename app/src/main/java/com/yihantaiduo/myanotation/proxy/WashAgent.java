package com.yihantaiduo.myanotation.proxy;

public class WashAgent implements Wash {
    private final Wash wash;

    public WashAgent(Wash wash) {
        this.wash = wash;
    }

    @Override
    public void Wash() {
        wash.Wash();
    }

    @Override
    public void massage() {
        wash.massage();
    }
}
