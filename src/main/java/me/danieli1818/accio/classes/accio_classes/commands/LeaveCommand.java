package me.danieli1818.accio.classes.accio_classes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class LeaveCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to run this command!");
			return false;
		}
		Player player = (Player)sender;
		String className = ClassesManager.getInstance().getClassIn(player.getUniqueId());
		if (className != null) {
			if (!sender.hasPermission("accio.classes.leave")) {
				sender.sendMessage("You don't have permissions to run this command!");
			}
			if (ClassesManager.getInstance().removePlayer(player.getUniqueId(), className)) {
				player.sendMessage("Left class successfully!");
				return true;
			} else {
				player.sendMessage("There has been an error contact staff for help!");
				return false;
			}
		}
		return true;
	}

}
