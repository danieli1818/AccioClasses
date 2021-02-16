package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class ToggleSubCommand implements SubCommandsExecutor {

	private String prefix;
	
	public ToggleSubCommand(String prefix) {
		this.prefix = prefix;
	}

	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (args.length < 1) {
			return onHelpCommand(sender, 1);
		}
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You have to be a player to run this command!", sender);
			return false;
		}
		Player player = (Player)sender;
		String command = args[0].toLowerCase();
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		if (args.length > 1) {
			className = String.join(" ", args).split(" ", 2)[1];
		}
		if (className == null) {
			MessagesSender.getInstance().sendMessage("Error no class has been selected!", player);
			return false;
		}
		if (!ClassesManager.getInstance().doesClassExist(className)) {
			MessagesSender.getInstance().sendMessage("Error no class like this exists!", player);
			return false;
		}
		if (!ClassesManager.getInstance().getProperties(className).hasStarted()) {
			MessagesSender.getInstance().sendMessage("Error class " + className + " hasn't started yet!", player);
			return false;
		}
		if (command.equals("chat")) {
			ClassesManager.getInstance().toggleChat(className);
			String chatOnOff = "on";
			String chatEnabled = "enabled";
			if (!ClassesManager.getInstance().getProperties(className).isChatOn()) {
				chatOnOff = "off";
				chatEnabled = "disabled";
			}
			MessagesSender.getInstance().sendMessage("Toggled chat " + chatOnOff + " successfully!", player);
			ClassesManager.getInstance().sendMessagesToAllPlayersInClass(className, "Chat is now " + chatEnabled + "!");
		} else if (command.equals("magic")) {
			ClassesManager.getInstance().toggleMagic(className);
			String magicOnOff = "on";
			String magicEnabled = "enabled";
			if (!ClassesManager.getInstance().getProperties(className).isMagicOn()) {
				magicOnOff = "off";
				magicEnabled = "disabled";
			}
			MessagesSender.getInstance().sendMessage("Toggled magic " + magicOnOff + " successfully!", player);
			ClassesManager.getInstance().sendMessagesToAllPlayersInClass(className, "Magic is now " + magicEnabled + "!");
		} else {
			MessagesSender.getInstance().sendMessage("Invalid Command! Use help command for help!", player);
			return false;
		}
		return true;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Toggle Class's Chat/Magic On/Off!";
	}

	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[2];
			helpMessages[0] = this.prefix + " chat [class name] - " + getDescription();
			helpMessages[0] = this.prefix + " magic [class name] - " + getDescription();
			return helpMessages;
		}
		return null;
	}
	
}
