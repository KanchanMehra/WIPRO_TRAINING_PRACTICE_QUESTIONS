/*Hashmap
Write a java program to remove an element from the Map, you can use the remove() method. This method takes the key value and
removes the mapping for a key from this map if it is present in the map. */

import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Hashmaps {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String s1 = s.next();
        String s2 = s.next();
        String s3 = s.next();
        String s4 = s.next();
        int remo = s.nextInt();
   
    HashMap<Integer,String> map = new HashMap<>();
    map.put(1,s1);
    map.put(2,s2);
    map.put(3,s3);
    map.put(4,s4);
    System.out.println("Mappings of HashMap are : " + map);
    map.remove(remo);
    System.out.println("Mappings after removal are : " + map);
    }
}
