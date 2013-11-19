package de.cmlab.ubicomp;
import com.phidgets.*;
import com.phidgets.event.*;
import java.io.*;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/**
 * 
 * @author -
 * Class Summary & Package Summary
 * ANT: src, lib, res & build.xml
 * 	--> target prepare: erstellt build, doc, dist
 * 	--> target compile: bindet tasks (zB: javac)
 *  --> target run: java-execute
 *  --> target dist: create jar
 *  --> target doc:
 *  --> target clean:
 */
public class PhidgetsTemp implements SensorChangeListener, 
AttachListener, DetachListener, ErrorListener {
	public static int LED_PORT1=3;
	public static int LED_PORT2=5;
	public static int LED_PORT3=7;
	public int returnValue;

	/**
	 * main method launches the program
	 * user is prompted to input integer
	 * @param args standard main parameter
	 * @throws PhidgetException
	 * @throws IOException
	 */
/*	public static void main(String[] args) throws PhidgetException, IOException {
		String led;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Eingabe (int):");
		led = input.readLine();
		int value = Integer.parseInt(led);
		System.out.println(value+" wird geprueft");
        new PhidgetsTemp(value);
    }
*/		
	/**
	 * Connects to the interface-kit
	 * Checks for sensors
	 * deactivates LEDs
	 * checks if userInput is odd or even
	 * turns on yellow LED (LED_PORT2 = 5) if input is even
	 * checks value of temperature-sensor
	 * if value is >= 285, then red LED(LED_PORT3 = 7) is turned on
	 * if value is < 285, then green LED(LED_PORT1 = 3) is turned on
	 * connection to interface-kit is closed
	 * 
	 * @param number catch userInput
	 * @throws PhidgetException
	 */
	public PhidgetsTemp() throws PhidgetException { 
		InterfaceKitPhidget TempMe = new InterfaceKitPhidget();
		try {
			TempMe.addAttachListener(this);
            TempMe.addDetachListener(this);
			TempMe.addSensorChangeListener(this);
			
			TempMe.openAny();
			TempMe.waitForAttachment();
			
			TempMe.setOutputState(LED_PORT1, false);
			TempMe.setOutputState(LED_PORT2, false);
			TempMe.setOutputState(LED_PORT3, false);
			
/*			if (number%2==0) {
				TempMe.setOutputState(LED_PORT2, true);
				System.out.println(number+" ist gerade, deswegen leuchtet die gelbe LED");
				} else {
					System.out.println(number+" ist ungerade, deswegen leuchtet die gelbe LED nicht");
					}
*/
			returnValue = TempMe.getSensorValue(6);
            if (TempMe.getSensorValue(6) < 285) {
            	TempMe.setOutputState(LED_PORT1, true);
            	System.out.println("Schon frisch (gruene LED): "+TempMe.getSensorValue(6));
            	TempMe.setOutputState(LED_PORT3, false);
            	
	            } else {
	            	TempMe.setOutputState(LED_PORT3, true);
	            	System.out.println("Etwas waermer (rote LED): "+TempMe.getSensorValue(6));
	            	TempMe.setOutputState(LED_PORT1, false);
	            	}
			
		} finally {
			
			TempMe.close();
            System.out.println("Beendet.");			
		}
	}

	public int giveTheValue() {
		return this.returnValue;
	}
	
	/**
	 * catches error
	 * standard method from phidgets library
	 */
	public void error(ErrorEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}

	/**
	 * output if interface-kit is detached or not connected
	 * standard method from phidgets library
	 */
	@Override
	public void detached(DetachEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}

	/**
	 * output if interface-kit is attached or connected
	 * outputs serial number and version number
	 * standard method from phidgets library
	 */
	@Override
	public void attached(AttachEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0);
	}

	/**
	 * outputs the current value of sensor
	 * if value is > 0
	 * standard method from phidgets library
	 */
	@Override
	public void sensorChanged(SensorChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getValue() > 0) {
			System.out.println(arg0);
		}
	}

	@Override
	public void error(TransformerException arg0) throws TransformerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatalError(TransformerException arg0)
			throws TransformerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warning(TransformerException arg0) throws TransformerException {
		// TODO Auto-generated method stub
		
	}
}
