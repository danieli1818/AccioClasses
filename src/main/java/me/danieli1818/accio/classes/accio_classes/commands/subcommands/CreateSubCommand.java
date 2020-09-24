package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class CreateSubCommand implements SubCommandsExecutor {
	
	public CreateSubCommand() {
		// TODO Auto-generated constructor stub
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
		return true;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Creates a class!";
	}

	public String getHelp(int page) {
		return "create [class name] - " + getDescription();
	}
	
	private boolean onHelpCommand(CommandSender sender, int page) {
		sender.sendMessage(getHelp(page));
		return true;
	}

}
