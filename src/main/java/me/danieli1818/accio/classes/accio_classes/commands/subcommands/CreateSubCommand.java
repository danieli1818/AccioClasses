package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class CreateSubCommand implements SubCommandsExecutor {
	
	private String prefix;
	
	public CreateSubCommand(String prefix) {
		this.prefix = prefix;
	}

	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (args.length < 1) {
			return onHelpCommand(sender, 1);
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage("You have to be a player to run this command!");
			return false;
		}
		Player player = (Player)sender;
		String className = String.join(" ", args);
		ClassesManager.getInstance().createClass(player, className);
		ClassesManager.getInstance().selectClass(player.getUniqueId(), className);
		return true;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Creates a class!";
	}

	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[1];
			helpMessages[0] = this.prefix + " [class name] - " + getDescription();
			return helpMessages;
		}
		return null;
	}

}
