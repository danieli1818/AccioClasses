package me.danieli1818.accio.classes.accio_classes.commands;

import org.bukkit.command.CommandSender;

import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public interface SubCommandsExecutor {

	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args);
	
	public String getDescription();
	
	public String[] getHelp(int page);
	
	default boolean onHelpCommand(CommandSender sender, int page) {
		if (getHelp(page) == null) {
			MessagesSender.getInstance().sendMessage("Invalid page number!", sender);
			return false;
		}
		MessagesSender.getInstance().sendMessage(getHelp(page), sender);
		return true;
	}
	
}
