package falcons.plugin.exported.tellstickPlugin;

import java.util.ArrayList;

import javax.swing.*;

import falcons.plugin.AbstractPlugin;
import falcons.plugin.Plugin;
import falcons.plugin.PluginCall;
import falcons.plugin.exported.pkj.no.tellstick.*;
import falcons.plugin.exported.pkj.no.tellstick.device.*;
import falcons.plugin.exported.tellstickPlugin.view.TellstickMainPanel;


@Plugin(pluginID = "Tellstick", versionID = "0.1")
public class TellstickPlugin extends AbstractPlugin {

	TellstickMainPanel mainPanel;

	public static void main(String[] args) {
		TellstickPlugin tellstick = new TellstickPlugin();
		
	}
	
	public TellstickPlugin() {
		
		System.setProperty("jna.library.path", "/Applications/TellDus/");

		// Set supported methods for this app.
		TellstickDevice.setSupportedMethods(JNA.CLibrary.TELLSTICK_TURNOFF | JNA.CLibrary.TELLSTICK_TURNON);

		try{
			ArrayList<TellstickDevice> devices = TellstickDevice.getDevices();

			// Loop devices.
			for(TellstickDevice device : devices){
				// Now a device can be many types, you can check it with instance of.
				if (device instanceof Device){
					// Cast to device so we can get the method.
					try{
						((Device) device).on();
					}catch(TellstickException e){
						System.out.println(e.getMessage()); // Prints error right from the tellstick if we get error. Forexample if tellstick is not found this will print " Tellstick not found ". 
					}
				}
				
				// Check if its a bell device.
				if (device instanceof BellDevice){
					try{
						((BellDevice) device).bell();
					}catch(TellstickException e){
						System.out.println(e.getMessage()); // Prints error right from the tellstick if we get error. Forexample if tellstick is not found this will print " Tellstick not found ". 
					}
				}
			}
		} catch(SupportedMethodsException e){
			e.printStackTrace();
		}


	}

	@Override
	public void receiveCall(PluginCall call) {

	}

	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}

}
	