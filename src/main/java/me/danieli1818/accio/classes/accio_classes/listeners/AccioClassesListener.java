package me.danieli1818.accio.classes.accio_classes.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.elmakers.mine.bukkit.magic.MagicPlugin;

import me.danieli1818.accio.classes.accio_classes.utils.ClassProperties;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class AccioClassesListener implements Listener {
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String className = ClassesManager.getInstance().getClassIn(player.getUniqueId());
		if (className == null) {
			return;
		}
		ClassProperties properties = ClassesManager.getInstance().getProperties(className);
		if (properties.hasStarted() && !properties.isChatOn() && !ClassesManager.getInstance().isClassOwner(player.getUniqueId(), className)) {
			// TODO on/off magic and chat.
			player.sendMessage("Chat is currently disabled!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String className = ClassesManager.getInstance().getClassIn(player.getUniqueId());
		if (className == null) {
			return;
		}
		ClassProperties properties = ClassesManager.getInstance().getProperties(className);
		if (properties.hasStarted() && !properties.isMagicOn() && !ClassesManager.getInstance().isClassOwner(player.getUniqueId(), className)) {
			// TODO on/off magic and chat.
			if (MagicPlugin.getAPI().isWand(event.getItem())) {
				player.sendMessage("Magic is currently disabled!");
				event.setCancelled(true);
			}
		}
		
	}

}
