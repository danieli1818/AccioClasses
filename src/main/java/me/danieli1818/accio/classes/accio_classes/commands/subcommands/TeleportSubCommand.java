package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;

public class TeleportSubCommand implements SubCommandsExecutor {
	
	private String prefix;
	private static String[] coordinatesAllies = {"coordinates", "coords"};

	public TeleportSubCommand(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You have to be a player to run this command!");
			return false;
		}
		Player player = (Player)sender;
		Location location = player.getLocation();
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		if (args.length >= 1 && Arrays.asList(coordinatesAllies).contains(args[0])) {
			if (args.length < 5) {
				sender.sendMessage("Missing Arguments!");
				return false;
			}
			try {
				location.setX(Double.parseDouble(args[1]));
				location.setY(Double.parseDouble(args[2]));
				location.setZ(Double.parseDouble(args[3]));
			} catch (NumberFormatException e) {
				sender.sendMessage("Invalid Arguments! X, Y, Z has to be doubles!");
				return false;
			}
			if (args.length >= 5) {
				String[] arguments = new String[args.length - 4];
				for (int i = 0; i < arguments.length; i++) {
					arguments[i] = args[i + 4];
				}
				className = String.join(" ", arguments);
			}
		} else {
			if (args.length >= 1) {
				className = String.join(" ", args);
			}
		}
		if (className == null) {
			player.sendMessage("Error no class has been selected!");
			return false;
		}
		if (ClassesManager.getInstance().getPlayersInClass(className) == null) {
			player.sendMessage("Error class " + className + " doesn't exist!");
			return false;
		}
		List<Player> players = ClassesManager.getInstance().getPlayersInClass(className)
				.stream().map((UUID uuid)->Bukkit.getServer().getPlayer(uuid)).collect(Collectors.toList());
		for (Player currentPlayer : players) {
			currentPlayer.teleport(location);
		}
		return true;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Teleports all the students in class to the player or coordinates";
	}

	@Override
	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[2];
			helpMessages[0] = this.prefix + " {class name} - " + getDescription();
			helpMessages[1] = this.prefix + " coordinates {X} {Y} {Z} [class name] - " + getDescription();
			return helpMessages;
		}
		return null;
	}
	
}
