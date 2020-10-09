package me.danieli1818.accio.classes.accio_classes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class LeaveCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You must be a player to run this command!", sender);
			return false;
		}
		Player player = (Player)sender;
		String className = ClassesManager.getInstance().getClassIn(player.getUniqueId());
		if (className != null) {
			if (!sender.hasPermission("accio.classes.leave")) {
				MessagesSender.getInstance().sendMessage("You don't have permissions to run this command!", sender);
			}
			if (ClassesManager.getInstance().removePlayer(player.getUniqueId(), className)) {
				MessagesSender.getInstance().sendMessage("Left class successfully!", player);
				return true;
			} else {
				MessagesSender.getInstance().sendMessage("There has been an error contact staff for help!", player);
				return false;
			}
		}
		return true;
	}

}
