package me.danieli1818.accio.classes.accio_classes.commands;

import org.bukkit.command.CommandSender;

public interface SubCommandsExecutor {

	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args);
	
	public String getDescription();
	
	public String getHelp(int page);
	
}
