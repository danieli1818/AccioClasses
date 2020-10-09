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

public class GiveSubCommand implements SubCommandsExecutor {

	private String prefix;
	
	public GiveSubCommand(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You have to be a player to run this command!", sender);
			return false;
		}
		Player player = (Player)sender;
		String className = ClassesManager.getInstance().getSelectedClass(player.getUniqueId());
		if (args.length >= 1) {
			className = String.join(" ", args);
		}
		if (className == null) {
			MessagesSender.getInstance().sendMessage("Error no class has been selected!", player);
			return false;
		}
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if (itemStack == null) {
			MessagesSender.getInstance().sendMessage("You hold no items!", player);
			return false;
		}
		List<Player> players = ClassesManager.getInstance().getPlayersInClass(className)
				.stream().map((UUID uuid)->Bukkit.getServer().getPlayer(uuid)).collect(Collectors.toList());
		for (Player currentPlayer : players) {
			currentPlayer.getInventory().addItem(itemStack);
		}
		MessagesSender.getInstance().sendMessage("Successfully gave items!", player);
		return true;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Give held item to all the students in the class.";
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
