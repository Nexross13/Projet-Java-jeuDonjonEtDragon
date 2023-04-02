package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class testShuffle {
    public static void main(String[] args) {
        String[] labyrintheModele ={"E","S","M","M","M","M","M","F","HB","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","HD","HD","HD","HD","HD","HD","HD","HD","HD","HD","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF"};
        List<String> list =Arrays.asList(labyrintheModele);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
