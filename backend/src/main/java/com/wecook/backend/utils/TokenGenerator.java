package com.wecook.backend.utils;

public class TokenGenerator {

   static  RandomString session = new RandomString();
    public static String generateToken(){


        return session.nextString();

    }
}
