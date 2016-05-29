package pebblesolitaire2;

import java.io.*;
import java.math.*;
import java.util.*;


public class pebblesolitaire2 {
	static int[] c = new int[1 << 25];
	static int RemainingPebbles;
	static String Text;
	static int n;
	
	public static void main(String args[]){
		new pebblesolitaire2().View();	
	}
	
	private void View() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for(int i = 0 ; i < n ;i++){
			Text = in.next();
			RemainingPebbles = 0;
	
			System.out.println(ModellController());
		}		
	}
	
	private static int ModellController(){
		for ( int i = 0; i < Text.length(); i++ ){
			if ( Text.charAt(i) == 'o' )
			{	
				RemainingPebbles |= (1 << i);	
			}
		}	
		for ( int i = 0; i < 1 << 25; i++ ){
			c[i] = -1;
		}
		return Recursive(RemainingPebbles);
	}

	private static int Recursive(int BitMask){
		String f = Integer.toBinaryString(BitMask);
		StringBuilder fs = new StringBuilder(f);
		System.out.println(fs.reverse().toString());
		if ( c[BitMask] != -1 ){
			return c[BitMask];
		}
		else{			
		int counter = 0;			
		for(int i = 0; i < Text.length(); i++){
			if((BitMask & (1 << i)) != 0){	
				counter++;
			}
		}
		for( int i = 0; i < Text.length(); i++ ){
			if(i >= 2 && ((BitMask ^ (3 << (i - 2))) & (7 << (i - 2))) == 0 ){
				counter = Math.min(counter, Recursive(BitMask ^ (7 << (i - 2))));
			}
			if(i <= Text.length()-2 && ((BitMask ^ (6 << (i))) & (7 << (i))) == 0 ){
				counter = Math.min(counter,Recursive(BitMask ^ (7 << i)) );
			}
		}
		return c[BitMask] = counter;
		}
	}
}
