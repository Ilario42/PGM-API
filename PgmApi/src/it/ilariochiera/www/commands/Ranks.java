package it.ilariochiera.www.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import it.ilariochiera.www.main.MySQL;

public class Ranks implements CommandExecutor, Listener {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.getPlayer().hasPermission("pgmapi.admin")) {
			if (args.length == 0) {
				sender.sendMessage("§9PGM-API §8» §6Ranks Commands Admin:");
				sender.sendMessage("§8» §e/pgmrank setrank <user> <rank>");
				sender.sendMessage(" ");
				sender.sendMessage("§8» §e§lGeneral Ranks:");
				sender.sendMessage("§8» §6Administrator§e, §4Moderator§e, §3Referee§e, §bCordinator§e, §5Donator§e, §7User");
				sender.sendMessage(" ");
				sender.sendMessage("§7https://www.ilariochiera.it/documentations/pgmapi");
			} else {
				if (args[0].equalsIgnoreCase("setrank")) {
					if ((args[1]) != null && (args[2]) != null) {
						MySQL.setStringS(args[1], "Rank", args[2]);
						sender.sendMessage("§9PGM-API §8» §b" + args[2] + " §aRank §2set §ato §b" + args[1]);
					} else sender.sendMessage("§9PGM-API §8» §cError: §aUse §b/pgmapi");
				} else {
					sender.sendMessage("§9PGM-API §8» §4Error. §aUse §b/pgmapi");
				}
			}
				
		}
		return false;
	}

}
