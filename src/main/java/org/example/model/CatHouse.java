package org.example.model;

import java.util.*;

public class CatHouse {
    private static Set<Cat> cats = new HashSet<>();

    static {
        cats.add(new Cat(UUID.randomUUID().toString(),"Василий", "дворовый", 2));
        cats.add(new Cat(UUID.randomUUID().toString(),"Чёрт", "боевой", 6));
    }

    public static Set<Cat> getCatList(){
        return cats;
    }
}
