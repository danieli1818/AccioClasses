package me.danieli1818.accio.classes.accio_classes.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.danieli1818.accio.classes.accio_classes.commands.SubCommandsExecutor;
import me.danieli1818.accio.classes.accio_classes.utils.ClassesManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class CreateSubCommand implements SubCommandsExecutor {
	
	private String prefix;
	
	public CreateSubCommand(String prefix) {
		this.prefix = prefix;
	}

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
		ClassesManager.getInstance().createClass(player, className);
		ClassesManager.getInstance().selectClass(player.getUniqueId(), className);
		sender.sendMessage("Successfully created class " + className);
		TextComponent message = new TextComponent( "Class " + className + " is about to start. Click Me to join!" );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/accioclasses join " + className ) );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new Text( "Click Me to join class " + className + "!" ) ) );
		for (Player currentPlayer : Bukkit.getServer().getOnlinePlayers()) {
			currentPlayer.spigot().sendMessage(message);
		}
		return true;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return "Creates a class!";
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
