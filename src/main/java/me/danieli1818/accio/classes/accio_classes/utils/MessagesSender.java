package me.danieli1818.accio.classes.accio_classes.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class MessagesSender {

	private String prefix;
	
	private static MessagesSender instance;
	
	private MessagesSender(String prefix) {
		if (prefix == null) {
			prefix = "";
		}
		this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
	}
	
	public static MessagesSender getInstance(String prefix) {
		if (instance == null) {
			instance = new MessagesSender(prefix);
		}
		return instance;
	}
	
	public static MessagesSender getInstance() {
		return getInstance(null);
	}
	
	public void sendMessage(String message, CommandSender sender) {
		sender.sendMessage(this.prefix + message);
	}
	
	public void sendMessage(String[] messages, CommandSender sender) {
		sender.sendMessage(this.prefix);
		sender.sendMessage(messages);
	}
	
	public void sendMessage(BaseComponent message, CommandSender sender) {
		TextComponent newMessage = new TextComponent(this.prefix);
		newMessage.addExtra(message);
		sender.spigot().sendMessage(newMessage);
	}
	
}
