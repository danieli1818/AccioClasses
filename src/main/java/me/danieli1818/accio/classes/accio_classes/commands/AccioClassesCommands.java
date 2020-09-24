package me.danieli1818.accio.classes.accio_classes.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AccioClassesCommands implements CommandExecutor {
	
	private Map<String, SubCommandsExecutor> subCommands;

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			onHelpCommand(1);
			return true;
		}
		SubCommandsExecutor executor = this.subCommands.get(command.getName());
		if (executor == null) {
			sender.sendMessage("Invalid sub command type /help for help!");
			return false;
		}
		String[] arguments = new String[args.length - 1];
		for (int i = 0; i < arguments.length; i++) {
			arguments[i] = args[i + 1];
		}
		return executor.onCommand(sender, args[0], args[0], arguments);
	}
	
	public boolean onHelpCommand(int page) {
		return false;
	}

}
