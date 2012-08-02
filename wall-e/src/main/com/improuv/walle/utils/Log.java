package com.improuv.walle.utils;

import lejos.nxt.comm.RConsole;

/**
 * Easy switch - print on Wall-E's display (remoteConsole == false -> System.out.println) or stream it back
 * from the NXT to your computer. For that to work, go to the commandline and type "nxjconsole" after you
 * started Wall-E's program.
 */
public class Log {

	private final static boolean USE_REMOTE_CONSOLE = false; //if true, go to commandline and open nxjconsole
	private static boolean remoteConsoleInitialized = false;
	
	public static void print(Object s) {
		
		if(USE_REMOTE_CONSOLE) {
			if(!remoteConsoleInitialized) {
				RConsole.open();
				remoteConsoleInitialized = true;
			}
			RConsole.print(s+"\n");
		} else {
			System.out.println(s); //Prints to the LCD of the NXT
		}
	}
	
	/** Closes the console. Does not necessarily have to be called. */
	public static void closeConsole() {
		if(USE_REMOTE_CONSOLE)
			RConsole.close();
	}
}
