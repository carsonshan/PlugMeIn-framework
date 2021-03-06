package falcons.plugin.exported.pkj.no.tellstick.device;

import falcons.plugin.Pluggable;
import falcons.plugin.exported.pkj.no.tellstick.JNA;

public class TellstickException extends Exception implements Pluggable{

	public TellstickException() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TellstickDevice dev;

	protected int errorcode;
	
	public TellstickException(TellstickDevice dev, int errorcode){
		super();
		this.dev = dev;
		this.errorcode = errorcode;
	}
	
	
	@Override
	public String getMessage(){
		String error = JNA.CLibrary.INSTANCE.tdGetErrorString(errorcode);
		JNA.CLibrary.INSTANCE.tdReleaseString(error);
		return dev.getName() + ": " + error;
	}
	
}
