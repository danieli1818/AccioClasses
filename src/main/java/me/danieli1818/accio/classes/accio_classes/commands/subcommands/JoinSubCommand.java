package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class JoinSubCommand implements SubCommandsExecutor {
	
	private String prefix;
	
	public JoinSubCommand(String prefix) {
		this.prefix = prefix;
	}

	@Override
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
		if (className == null) {
			player.sendMessage("Error no class has been selected!");
			return false;
		}
		if (!ClassesManager.getInstance().addPlayerToClass(player, className)) {
			player.sendMessage("You are already in class or class doesn't exist!");
			return false;
		}
		player.sendMessage("Successfully joined class!");
		return true;
	}

	@Override
	public String getDescription() {
		return "Join class.";
	}

	@Override
	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[1];
			helpMessages[0] = this.prefix + " {class name} - " + getDescription();
			return helpMessages;
		}
		return null;
	}

	
	
}
