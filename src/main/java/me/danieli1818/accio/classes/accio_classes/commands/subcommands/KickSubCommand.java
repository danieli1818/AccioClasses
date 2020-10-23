package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class KickSubCommand implements SubCommandsExecutor {

	private String prefix;
	
	public KickSubCommand(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You have to be a player to run this command!", sender);
			return false;
		}
		if (args.length < 1) {
			MessagesSender.getInstance().sendMessage("Missing Arguments! Use the help command for help!", sender);
			return false;
		}
		Player player = (Player)sender;
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		String playerName = args[0];
		if (args.length >= 2) {
			String[] argsSpacesArray = String.join(" ", args).split(" ", 1);
			if (argsSpacesArray.length > 1) {
				className = String.join(" ", args).split(" ", 1)[1];
			}
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
		Player playerBeingKicked = Bukkit.getServer().getPlayer(playerName);
		if (playerBeingKicked == null || !ClassesManager.getInstance().removePlayer(playerBeingKicked.getUniqueId(), className)) {
			MessagesSender.getInstance().sendMessage("Player " + playerName + " isn't in " + className + " class!", player);
			return false;
		}
		MessagesSender.getInstance().sendMessage("Successfully kicked player " + playerName + "!", player);
		return true;
	}

	@Override
	public String getDescription() {
		return "Kick player from class.";
	}

	@Override
	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[1];
			helpMessages[0] = this.prefix + " [player name] {class name} - " + getDescription();
			return helpMessages;
		}
		return null;
	}
	
}
