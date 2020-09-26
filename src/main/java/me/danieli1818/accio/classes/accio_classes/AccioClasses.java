package me.danieli1818.accio.classes.accio_classes;

import org.bukkit.plugin.java.JavaPlugin;

import me.danieli1818.accio.classes.accio_classes.commands.AccioClassesCommands;
import me.danieli1818.accio.classes.accio_classes.commands.LeaveCommand;

public class AccioClasses extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		
//		Scheduler.getInstance(this);
		
//		SavingAndLoadingUtils.registerConfigurationSerializables();
		
//		createStationsConfig();
		
//		FlooNetworkStationsManager.getInstance().reloadStations();
		
		getCommand("accioclasses").setExecutor(new AccioClassesCommands());
		
		getCommand("leave").setExecutor(new LeaveCommand());
		
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
