package me.danieli1818.accio.classes.accio_classes;

import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.danieli1818.accio.classes.accio_classes.commands.AccioClassesCommands;
import me.danieli1818.accio.classes.accio_classes.commands.LeaveCommand;
import me.danieli1818.accio.classes.accio_classes.listeners.AccioClassesListener;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class AccioClasses extends JavaPlugin {
	
	private File configFile;
	private FileConfiguration config;

	@Override
	public void onEnable() {
		super.onEnable();
		
		createConfig();
		
		MessagesSender.getInstance(config.getString("prefix"));
		
		getCommand("accioclasses").setExecutor(new AccioClassesCommands());
		
		getCommand("leave").setExecutor(new LeaveCommand());
		
		getServer().getPluginManager().registerEvents(new AccioClassesListener(), this);
		
		System.out.println("Plugin has been successfully loaded!!!!");
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
//		FlooNetworkStationsManager.getInstance().saveStations();
		
		System.out.println("Plugin has been disabled.");
	}
	
	private void createConfig() {
		
		AbstractMap.Entry<File, FileConfiguration> arenasConfigs = createConfigurationFile("config.yml");
		this.configFile = arenasConfigs.getKey();
		this.config = arenasConfigs.getValue();
		
	}
	
	private AbstractMap.Entry<File, FileConfiguration> createConfigurationFile(String name) {
		File configFile = new File(getDataFolder(), name);
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			saveResource(name, false);
		}
		
		FileConfiguration config = new YamlConfiguration();
		try {
			config.load(configFile);
			return new AbstractMap.SimpleEntry<File, FileConfiguration>(configFile, config);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public File getConfigFile() {
		this.configFile = new File(getDataFolder(), "config.yml");
		return this.configFile;
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
}
