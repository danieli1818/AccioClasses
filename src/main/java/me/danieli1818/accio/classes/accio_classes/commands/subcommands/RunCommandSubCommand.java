package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class RunCommandSubCommand implements SubCommandsExecutor {
	
	private String prefix;
	
	public RunCommandSubCommand(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (args.length < 1) {
			return onHelpCommand(sender, 1);
		}
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You have to be a player to run this command!", sender);
			return false;
		}
		Player player = (Player)sender;
		String command = String.join(" ", args);
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		if (className == null) {
			MessagesSender.getInstance().sendMessage("Error no class has been selected!", player);
			return false;
		}
		List<Player> players = ClassesManager.getInstance().getPlayersInClass(className)
				.stream().map((UUID uuid)->Bukkit.getServer().getPlayer(uuid)).collect(Collectors.toList());
		for (Player currentPlayer : players) {
			player.performCommand(command.replace("<player>", currentPlayer.getName()));
		}
		return true;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Run command for each player. Type <player> where you want the player name to be.";
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
