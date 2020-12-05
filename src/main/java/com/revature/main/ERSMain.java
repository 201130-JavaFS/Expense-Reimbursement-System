package com.revature.main;

import org.apache.log4j.Logger;

public class ERSMain {
	
	private static Logger log = Logger.getLogger(ERSMain.class);

	public static void main(String[] args) {

		log.info("this should NOT show up in logs");
//		log.warn("warning");
//		log.error("error");
	}

}
