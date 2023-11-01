package com.example.personal_productserviceproxy;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTest {
    @Test
    public void testRandom() {
        Random random = new Random();
        int a = random.nextInt(10);
        assert(a < 5);
    }

    @Test
    public void sayHii(){
        String text= "Hii";
        assertEquals("Hii", text);
    }

    @Test
    public void add(){
        int c = 4+5;
        assert(c==9);
    }
}
