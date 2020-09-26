package me.danieli1818.accio.classes.accio_classes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.danieli1818.accio.classes.accio_classes.utils.ClassProperties;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinLeaveListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String className = ClassesManager.getInstance().getClassIn(player.getUniqueId());
		if (className == null) {
			return;
		}
		ClassProperties properties = ClassesManager.getInstance().getProperties(className);
		if (properties.hasStarted() && player.hasPermission("accio.classes.commands.join")) {
			// TODO on/off magic and chat.
			PermissionsEx.getUser(player);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		
	}
	
}
