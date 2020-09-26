package me.danieli1818.accio.classes.accio_classes.utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClassesManager {

	private static ClassesManager instance;
	
	private Map<UUID, String> selectedClasses;
	
	private Map<String, List<Map.Entry<UUID, Location>>> playersInClass;
	
	private Map<String, ClassProperties> classesProperties;
	
	private ClassesManager() {
		this.selectedClasses = new HashMap<UUID, String>();
		this.playersInClass = new HashMap<String, List<Map.Entry<UUID, Location>>>();
		this.classesProperties = new HashMap<String, ClassProperties>();
	}
	
	public static ClassesManager getInstance() {
		if (instance == null) {
			instance = new ClassesManager();
		}
		return instance;
	}
	
	public boolean createClass(Player creator, String className) {
		boolean returnValue = true;
		if (this.selectedClasses.containsKey(creator.getUniqueId())) {
			returnValue = false;
		}
		this.selectedClasses.put(creator.getUniqueId(), className);
		if (!returnValue) {
			removeClass(className);
		}
		this.playersInClass.put(className, new ArrayList<Map.Entry<UUID, Location>>());
		this.classesProperties.put(className, new ClassProperties());
		return returnValue;
	}
	
	private long countClassOwners(String className) {
		return this.selectedClasses.values().stream().filter((String currentClassName)->(className == currentClassName)).count();
	}
	
	public boolean removeClass(String className) {
		if (countClassOwners(className) == 0) { // TODO fix thread safety.
			this.playersInClass.remove(className);
			return true;
		}
		return false;
	}
	
	public void forceRemoveClass(String className) {
		List<UUID> players = new ArrayList<UUID>(); // TODO fix thread safety.
		for (Map.Entry<UUID, String> entry : this.selectedClasses.entrySet()) {
			if (entry.getValue().equals(className)) {
				players.add(entry.getKey());
			}
		}
		for (UUID uuid : players) {
			this.selectedClasses.remove(uuid);
		}
		removeAllPlayers(className);
	}
	
	public String getSelectedClass(UUID uuid) {
		return this.selectedClasses.get(uuid);
	}
	
	public List<UUID> getPlayersInClass(String className) {
		return this.playersInClass.get(className).stream().map((Map.Entry<UUID, Location> entry)->entry.getKey()).collect(Collectors.toList());
	}
	
	public void selectClass(UUID uuid, String className) {
		String prevClassName = getSelectedClass(uuid);
		this.selectedClasses.put(uuid, className);
		removeClass(prevClassName);
	}
	
	public boolean addPlayerToClass(Player player, String className) {
		if (this.classesProperties.get(className).hasStarted() || getClassIn(player.getUniqueId()) != null) {
			return false;
		}
		List<UUID> players = getPlayersInClass(className);
		if (players == null) {
			return false;
		}
		if (players.contains(player.getUniqueId())) {
			return false;
		}
		this.playersInClass.get(className).add(new AbstractMap.SimpleEntry<UUID, Location>(player.getUniqueId(), player.getLocation()));
		return true;
	}
	
	public boolean startClass(String className) {
		if (this.classesProperties.get(className).hasStarted()) {
			for (Map.Entry<UUID, Location> entry : this.playersInClass.get(className)) {
				entry.setValue(Bukkit.getServer().getPlayer(entry.getKey()).getLocation());
			}
			this.classesProperties.get(className).setHasStarted(true);
			return true;
		}
		return false;
		
	}
	
	public boolean removePlayer(UUID uuid, String className) {
		Map.Entry<UUID, Location> selectedEntry = null;
		for (Map.Entry<UUID, Location> entry : this.playersInClass.get(className)) {
			if (entry.getKey() == uuid) {
				selectedEntry = entry;
			}
		}
		if (selectedEntry != null) {
			this.playersInClass.get(className).remove(selectedEntry);
			return true;
		}
		return false;
	}
	
	public boolean removeAllPlayers(String className) {
		if (!this.playersInClass.containsKey(className)) {
			return false;
		}
		for (Map.Entry<UUID, Location> entry : this.playersInClass.get(className)) {
			Bukkit.getServer().getPlayer(entry.getKey()).teleport(entry.getValue());
		}
		this.playersInClass.get(className).clear();
		return true;
	}
	
	public String getClassIn(UUID uuid) {
		for (Map.Entry<String, List<Map.Entry<UUID, Location>>> classEntry : this.playersInClass.entrySet()) {
			for (Map.Entry<UUID, Location> entry : this.playersInClass.get(classEntry.getKey())) {
				if (entry.getKey() == uuid) {
					return classEntry.getKey();
				}
			}
		}
		return null;
	}
	
	public boolean toggleChat(String className) {
		ClassProperties properties = this.classesProperties.get(className);
		if (properties == null) {
			return false;
		}
		properties.setIsChatOn(!properties.isChatOn());
		return true;
	}
	
	public boolean toggleMagic(String className) {
		ClassProperties properties = this.classesProperties.get(className);
		if (properties == null) {
			return false;
		}
		properties.setIsMagicOn(!properties.isMagicOn());
		return true;
	}
	
	public ClassProperties getProperties(String className) {
		return this.classesProperties.get(className);
	}
	
}
