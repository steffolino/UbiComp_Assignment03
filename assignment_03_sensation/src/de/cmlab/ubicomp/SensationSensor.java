
package de.cmlab.ubicomp;
import com.phidgets.PhidgetException;
import de.cmlab.sensation.*;
import de.cmlab.sensation.xmlrpc.GatewayXMLRPC;
import de.cmlab.sensation.xmlrpc.SensorPort;

public class SensationSensor {

	static PhidgetsTemp phidgets;
	static SensorPort sensPort;
	static GatewayXMLRPC gateway;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Do phidgets stuff
		try {
			phidgets = new PhidgetsTemp();
			int testVal = phidgets.giveTheValue();
			System.out.println("phidgets object created "+testVal);
		} catch (PhidgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Do Sensation stuff
		try {
			String sensorKram = "<sensor id='InterfaceKit' class='Temperature'>";
			gateway = new GatewayXMLRPC();
//			sensPort = new SensorPort();
/*			String id = sensPort.registerSensor("phidgetsSensor");
			//sensPort.ping();
			gateway = new GatewayXMLRPC();
			gateway.register("10.1.8.191", id, "2343");
			sensPort.registerSensor(arg0);
*/		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
