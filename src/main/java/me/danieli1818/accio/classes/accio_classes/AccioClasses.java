package me.danieli1818.accio.classes.accio_classes;

import org.bukkit.plugin.java.JavaPlugin;

import me.danieli1818.floo_network.commands.FlooNetworkCommands;
import me.danieli1818.floo_network.listeners.FlooNetworkListener;
import me.danieli1818.floo_network.stations.FlooNetworkStationsManager;
import me.danieli1818.floo_network.utils.scheduler.Scheduler;
import me.danieli1818.floo_network.utils.serialization.SavingAndLoadingUtils;

public class AccioClasses extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		
//		Scheduler.getInstance(this);
		
//		SavingAndLoadingUtils.registerConfigurationSerializables();
		
//		createStationsConfig();
		
//		FlooNetworkStationsManager.getInstance().reloadStations();
		
//		getCommand("floonetwork").setExecutor(FlooNetworkCommands.getInstance());
		
//		getServer().getPluginManager().registerEvents(new FlooNetworkListener(), this);
		
		System.out.println("Plugin has been successfully loaded!!!!");
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
//		FlooNetworkStationsManager.getInstance().saveStations();
		
		System.out.println("Plugin has been disabled.");
	}
	
}
