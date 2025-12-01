/*constructor overloading
Create one default constructor to initialize the fields as default values to calculate volume 
of the cube and one parameterized constructor to pass parameters and initialize them accordingly. 
You can pass 3 integer parameters at runtime 
(While using parameterized constructor, the program, should also display the output with the default constructor also) */

import java.util.Scanner;

public class Constructor {
    private int length;
    private int width;
    private int height;
    public Constructor(){
        this.length = 10;
        this.width = 10;
        this.height = 10;
        System.out.println("Constructor without parameter");
        displayVolume();
    }
    public Constructor(int length, int width, int height){
        this.length = length;
        this.width = width;
        this.height = height;
        System.out.println("Constructor with parameter");
        displayVolume();
    }
    public void displayVolume(){
        double volume = (double) this.length * this.width * this.height;
        System.out.println("Volume is " + volume);
    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        Constructor cuboid1 = new Constructor();
        int l = s.nextInt();
        int w = s.nextInt();
        int h = s.nextInt();
        Constructor cuboid2 = new Constructor(l, w, h);
        s.close();
    }
}





	
    
