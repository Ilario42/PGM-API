package it.ilariochiera.www.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import it.ilariochiera.www.main.MySQL;

public class StatsAdmin implements CommandExecutor, Listener {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.getPlayer().hasPermission("pgmapi.admin")) {
			if (args.length == 0) {
				sender.sendMessage("§9PGM-API §8» §6Stats Commands Admin:");
				sender.sendMessage("§8» §e/pgmapi setkill <user> <kill>");
				sender.sendMessage("§8» §e/pgmapi setdeath <user> <death>");
				sender.sendMessage("§8» §e/pgmapi stats <user>");
				sender.sendMessage(" ");
				sender.sendMessage("§9PGM-API §8» §6Powered By §bIlario42");
				sender.sendMessage("§7https://www.ilariochiera.it/documentations/pgmapi");
			} else  {
				if (args[0].equalsIgnoreCase("setkill")) {
					if ((args[1]) != null && (args[2]) != null) {
						try{
						    @SuppressWarnings("unused")
							int num = Integer.parseInt(args[2]);
						} catch (NumberFormatException e) {
							p.sendMessage("§9PGM-API §8» §cError. §b" + args[2] + "§a is not a number!");
							return true;
						}
						MySQL.setStringStringInt(args[1], "Kills", Integer.valueOf(args[2]));
						sender.sendMessage("§9PGM-API §8» §b" + args[2] + " §aKills §2set §ato §b" + args[1]);
					} else sender.sendMessage("§9PGM-API §8» §cError: §aUse §b/pgmapi");
				} else if (args[0].equalsIgnoreCase("setdeath")) {
					if ((args[1]) != null && (args[2]) != null) {
						try{
						    @SuppressWarnings("unused")
							int num = Integer.parseInt(args[2]);
						} catch (NumberFormatException e) {
							p.sendMessage("§9PGM-API §8» §cError. §b" + args[2] + "§a is not a number!");
							return true;
						}
						MySQL.setStringStringInt(args[1], "Deaths", Integer.valueOf(args[2]));
						sender.sendMessage("§9PGM-API §8» §b" + args[2] + " §aDeaths §2set to §b" + args[1]);
					} else sender.sendMessage("§9PGM-API §8» §cError: §aUse §b/pgmapi");		
				} else if (args[0].equalsIgnoreCase("stats")) {
					String lastlogin = MySQL.getStringString(args[1], "LastLogin");
					if (lastlogin != null) {
					sender.sendMessage("§9PGM-API §8» §bStats user §e" + args[1] + "§b:");
					sender.sendMessage("§9PGM-API §8» §eOnline: §d" + MySQL.getStringString(args[1], "Online"));
					sender.sendMessage("§9PGM-API §8» §eLast: §d" + lastlogin);
					sender.sendMessage("§9PGM-API §8» §eKills: §a" + MySQL.getStringString(args[1], "Kills"));
					sender.sendMessage("§9PGM-API §8» §eDeaths: §4" + MySQL.getStringString(args[1], "Deaths"));
					} else {
						sender.sendMessage("§9PGM-API §8» §7User not found!");
					}
					
				} else {
					sender.sendMessage("§9PGM-API §8» §4Error. §aUse §b/pgmapi");
				}
			}
		}
		
		return false;
	}

}

