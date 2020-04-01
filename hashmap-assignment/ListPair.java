package day14.inClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListPair {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int length = Integer.parseInt(reader.readLine());
        Map<Pair, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < length; i++){
            int number = Integer.parseInt(reader.readLine());

            for (Integer integer : set){
                int multiply = number*integer;

                Pair pair = new Pair(number,integer);
                Integer value = map.get(pair);

                if(value != null){
                    System.out.printf("(%d, %d) and (%d, %d)\n", number,integer,value, multiply/value);
                    return;
                }
                map.put(pair, number);
            }
            set.add(number);
        }
        System.out.println("No pairs found");
    }

}

class Pair {
    int firstElem, secondElem;

    public Pair(int firstElem, int secondElem) {
        this.firstElem = firstElem;
        this.secondElem = secondElem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair p = (Pair) o;
        return p.firstElem != this.secondElem && p.secondElem != this.firstElem;
    }

    @Override
    public int hashCode() {
        return firstElem*secondElem;
    }
}
