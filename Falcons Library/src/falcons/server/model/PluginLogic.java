apackage falcons.server.model;

import java.util.HashMap;
import java.util.List;

import falcons.plugin.AbstractPlugin;
import falcons.plugin.Pluggable;
import falcons.plugin.Plugin;
import falcons.pluginmanager.PluginManager;

public class PluginLogic {
	
	/**
	 * Tells the pluginmanager to load all plugins.
	 */
	public static void loadPlugins(){
		List<Pluggable> pluginList = PluginManager.loadPlugins();
		HashMap<String, Pluggable> pluginMap = new HashMap<String, Pluggable>();
		for(Pluggable p : pluginList){
			pluginMap.put(p.getClass().getAnnotation(Plugin.class).pluginID(), p);
		}
		PluginModel.setPluginMap(pluginMap);
		
	}

	/**
	 * 
	 * @return Returns a map containing all the currently loaded plugins with
	 *         their pluginID as key.
	 */
	public static HashMap<String, Pluggable> getPluginMap() {
		return PluginModel.getPlugins();
	}
}