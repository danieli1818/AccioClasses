package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import me.danieli1818.accio.classes.accio_classes.utils.MessagesSender;

public class SelectSubCommand implements SubCommandsExecutor {

	private String prefix;
	
	public SelectSubCommand(String prefix) {
		this.prefix = prefix;
	}

	public boolean onCommand(CommandSender sender, String subCommand, String label, String[] args) {
		if (!(sender instanceof Player)) {
			MessagesSender.getInstance().sendMessage("You have to be a player to run this command!", sender);
			return false;
		}
		if (args.length < 1) {
			Collection<String> classes = ClassesManager.getInstance().getAllClasses();
			MessagesSender.getInstance().sendMessage(classes.toArray(new String[0]), sender);
			return true;
		}
		Player player = (Player)sender;
		String className = String.join(" ", args);
		if (ClassesManager.getInstance().selectClass(player.getUniqueId(), className)) {
			MessagesSender.getInstance().sendMessage("Successfully selected class " + className, sender);
		} else {
			MessagesSender.getInstance().sendMessage("Class " + className + " doesn't exist!", sender);
		}
		return true;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Selects a class!";
	}

	public String[] getHelp(int page) {
		if (page == 1) {
			String[] helpMessages = new String[1];
			helpMessages[0] = this.prefix + " [class name] - " + getDescription();
			return helpMessages;
		}
		return null;
	}
	
}
