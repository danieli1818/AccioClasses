package me.danieli1818.accio.classes.accio_classes.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ClassesManager {

	private static ClassesManager instance;
	
	private Map<UUID, String> selectedClasses;
	
	private Map<String, List<UUID>> playersInClass;
	
	private ClassesManager() {
		this.selectedClasses = new HashMap<UUID, String>();
		this.playersInClass = new HashMap<String, List<UUID>>();
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
		this.playersInClass.remove(className);
	}
	
	public String getSelectedClass(UUID uuid) {
		return this.selectedClasses.get(uuid);
	}
	
	public List<UUID> getPlayersInClass(String className) {
		return this.playersInClass.get(className);
	}
	
}
