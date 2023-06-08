package com.yihantaiduo.myanotation.intdef;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * idea 进行语法检查
 */
public class Test {
    private static WeekDay mCurrentDay;

    enum WeekDay {
        SUNDAY, MONDAY
    }
    @WekDay
    private static final int SUNDAY = 0;
    @WekDay
    private static final int MONDAY = 1;
    @IntDef({SUNDAY,MONDAY})
    @Target({ElementType.FIELD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @interface WekDay{

    }

    public static WeekDay getmCurrentDay() {
        return mCurrentDay;
    }

    public static void setmCurrentDay(@WekDay int mCurrentDay) {
       mCurrentDay = mCurrentDay;
    }

    public static void main(String[] args) {
        setmCurrentDay(SUNDAY);
    }
}
