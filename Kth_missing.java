/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;
public class Main
{
	public static void main(String[] args) {
	
	
	int n =4;
	int arr[] = {1,2,6,8};        // 3 4 5 7 9 10
	int k =6;
	int i=0;
	Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
    }
    int m =0;
    
	for( i=0;i<set.size()+k;i++){
	    
	    if(!set.contains(i)){
	        m++;
	    }
	    if(m == k){
	        System.out.println(i+1);
	        break;

	    }
	 }
	}
}
