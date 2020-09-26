package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class KickSubCommand implements SubCommandsExecutor {

	private String prefix;
	
	public KickSubCommand(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You have to be a player to run this command!");
			return false;
		}
		if (args.length < 1) {
			sender.sendMessage("Missing Arguments! Use the help command for help!");
			return false;
		}
		Player player = (Player)sender;
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		String playerName = args[0];
		if (args.length >= 2) {
			className = String.join(" ", args).split(" ", 1)[1];
		}
		if (className == null) {
			player.sendMessage("Error no class has been selected!");
			return false;
		}
		if (!ClassesManager.getInstance().removePlayer(Bukkit.getServer().getPlayer(playerName).getUniqueId(), className)) {
			player.sendMessage("Player " + playerName + " isn't in " + className + " class!");
			return false;
		}
		player.sendMessage("Successfully kicked player " + playerName + "!");
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
