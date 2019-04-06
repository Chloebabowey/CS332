import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Chloe Brown
 * CS 332 - P1
 * November 21, 2017
 * 
 * This program tests whether strings entered are in the particular language that is being read by 
 * the FileReader. This code has been written to work for any language, composed of 'a' and 'b', read by 
 * the FileReader. This code includes two language examples and allows users to input as many strings as 
 * they'd like to test.
 * 
 */

public class FSM {
	public static void main(String[] args) throws IOException {
		int currentState=0, j=0;
		int[][] states = new int [30][2];
//		FileReader fr = new FileReader("FiniteStateMachine.txt");   //one finite state machine example: lang = a^+(a+b)^*b			
		FileReader fr = new FileReader("FSMexample2.txt");   //another example: lang = baab^+
		BufferedReader br = new BufferedReader(fr);
		int acceptingState = Integer.parseInt(br.readLine());

		// Reading the values from file
		String line = br.readLine();
		while(line!= null) {
			String st[] = line.split(" ");    
			states[j][0] = Integer.parseInt(st[0]);	//assigns first column to a
			states[j][1] = Integer.parseInt(st[1]); //assigns second column to b
			j++;
			line = br.readLine();
		}

		while(true) {	//this loop continually allows strings to be entered by user
			System.out.println("Please enter a string: ");
			Scanner in = new Scanner(System.in);
			String str = in.nextLine();	

			//starts off with an initial state depending on what the first character in input string is
			char c = str.charAt(0);
			if(c=='a') {
				currentState = states[0][0]; 
			}
			if(c=='b') {
				currentState = states[0][1]; 
			}
			if (c!='a' && c!='b') {
				currentState = states[j-1][0];
			}

			//goes through loop starting with second character
			for(int i=1; i<str.length(); i++) {
				c = str.charAt(i);	
				if (c!='a' && c!='b') {	//invalid if current character not a or b
					currentState = 0;
					break;
				}
				if(c=='a') {
					currentState = states[currentState][0];
				}
				if(c=='b') {
					currentState = states[currentState][1];
				}
			}

			if(currentState==acceptingState) {
				System.out.println("Valid String. CONGRATS!\n");
			}	
			else {
				System.out.println("Invalid String - not in language\n");
			}
		}
	}
}

