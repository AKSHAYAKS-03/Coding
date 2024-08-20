import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Random random = new Random();


        // Initial values of ans, a, b, and c
        int ans = 2066;
        int a = 0, b = 42, c = 2024;
        
        int ran = random.nextInt(5);
        //System.out.println(ran);


        
        for(int i=0;i<5;i++){
        
        System.out.println("Enter no A, B, and C: ");
        
        // Reading the values from the user
        int a1 = s.nextInt();
        int b1 = s.nextInt();
        int c1 = s.nextInt();
         
         
        if(i == ran){
            int tempans = random.nextInt(1000);
            System.out.println("Total legs:  "+tempans);

        }
        else{
        
        int tempans = a1*a + b1*b + c1*c;

        System.out.println("Total legs:  "+tempans);

        // Outputting the values to verify
        //System.out.println("A has " + a + " legs.");
        //System.out.println("B has " + b + " legs.");
        ///System.out.println("C has " + c + " legs.");
        //System.out.println("Total " + tempans + " legs.");
        }
        }
       System.out.println("\nGuess the no of legs\nA  B  C: "); 
        int a1 = s.nextInt();
        int b1 = s.nextInt();
        int c1 = s.nextInt();
        
        if(a1 == a && b1 == b && c1==c){
            System.out.println("\n Correct Guess "); 

        }
        else{
            System.out.println("\n Wrong Guess ");

        }
    }
}
