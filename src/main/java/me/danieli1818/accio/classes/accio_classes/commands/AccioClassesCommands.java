package me.danieli1818.accio.classes.accio_classes.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.danieli1818.accio.classes.accio_classes.commands.subcommands.CreateSubCommand;
import me.danieli1818.accio.classes.accio_classes.commands.subcommands.GiveSubCommand;
import me.danieli1818.accio.classes.accio_classes.commands.subcommands.JoinSubCommand;
import me.danieli1818.accio.classes.accio_classes.commands.subcommands.TeleportSubCommand;
import me.danieli1818.accio.classes.accio_classes.commands.subcommands.RunCommandSubCommand;

public class AccioClassesCommands implements CommandExecutor {
	
	private Map<String, SubCommandsExecutor> subCommands;
	
	public AccioClassesCommands() {
		this.subCommands = new HashMap<String, SubCommandsExecutor>();
		this.subCommands.put("create", new CreateSubCommand("/accioclasses create"));
		this.subCommands.put("teleport", new TeleportSubCommand("/accioclasses teleport"));
		this.subCommands.put("runcommand", new RunCommandSubCommand("/accioclasses runCommand"));
		this.subCommands.put("join", new JoinSubCommand("/accioclasses join"));
		this.subCommands.put("give", new GiveSubCommand("/accioclasses give"));
		this.subCommands.put("start", new GiveSubCommand("/accioclasses start"));
		this.subCommands.put("kick", new GiveSubCommand("/accioclasses kick"));
		this.subCommands.put("dismiss", new GiveSubCommand("/accioclasses dismiss"));
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("accio.classes.commands")) {
			sender.sendMessage("You don't have permissions to run this command!");
		}
		if (args.length < 1) {
			onHelpCommand(1);
			return true;
		}
		if (!sender.hasPermission("accio.classes.commands." + args[0].toLowerCase())) {
			sender.sendMessage("You don't have permissions to run this command!");
		}
		SubCommandsExecutor executor = this.subCommands.get(args[0].toLowerCase());
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
