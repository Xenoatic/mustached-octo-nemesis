package com.gmail.xenoatic;

import java.awt.EventQueue;

/** Insert program description here
 */

/** 
 * @author Josh
 *
 */
public class Main {
	
	/** this calls WeightlossManager to start the program
	 * @param args are unused
	 */
	public static void main(String[] args){
		
	//we are creating a new weightloss manager in it's own thread
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WeightLossManager frame = new WeightLossManager();
				frame.setVisible(true);
				frame.setTitle("Weight Loss Manager 1.0");
				frame.setResizable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}

}
