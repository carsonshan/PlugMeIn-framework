package falcons.plugin.exported.pkj.no.tellstick.device;

import falcons.plugin.Pluggable;
import falcons.plugin.exported.pkj.no.tellstick.JNA;


public class DimmableDevice extends Device implements falcons.plugin.exported.pkj.no.tellstick.device.iface.DimmableDevice, Pluggable{

	public DimmableDevice() throws SupportedMethodsException {
		super(0);
		// TODO Auto-generated constructor stub
	}
	
	public DimmableDevice(int deviceId) throws SupportedMethodsException {
		super(deviceId);
	}

	
	/**
	 * Dims the lights.
	 * @throws TellstickException 
	 * @throws IllegalArguementException
	 */
	@Override
	public void dim(int level) throws TellstickException {
		if (level < 0 || level > 255)throw new IllegalArgumentException("Dim levels must be between 0 and 255.");
		int status = JNA.CLibrary.INSTANCE.tdDim(getId(), level);
		if (status != TELLSTICK_SUCCESS)throw new TellstickException(this, status);
	}
	
	/**
	 * Since Dimmers can be dimmed, we override the Device::isOn.
	 * This checks if the device is turned on.
	 * @return true if device is on. false otherwise.
	 */
	@Override
	public boolean isOn(){
		if (super.isOn() || (JNA.CLibrary.TELLSTICK_DIM & this.getStatus()) > 0)return true;
		else return false;
	}
	
	@Override
	public String getType(){
		return "Dimmer Device";
	}

}
