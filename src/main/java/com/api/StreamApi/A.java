package com.api.StreamApi;

import java.util.function.Predicate;

public class A {
    public static void main(String[] args) {
//   1.     //predicate functional interface
//        //it takes input and produces boolean value output.
//        Predicate<Integer> val = x->x>3;
//        boolean result = val.test(100);
//        System.out.println(result);
//    }
//}
        //2.
        Predicate<String> val = x -> x.equals("mike");
        boolean result = val.test("mike");
        System.out.println(result);
    }
} //functional interface is used for filtering the record.

