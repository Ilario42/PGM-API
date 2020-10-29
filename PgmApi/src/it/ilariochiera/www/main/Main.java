package it.ilariochiera.www.main;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import it.ilariochiera.www.commands.General;
import it.ilariochiera.www.commands.Ranks;
import it.ilariochiera.www.commands.StatsAdmin;
import it.ilariochiera.www.events.JoinClass;
import it.ilariochiera.www.events.KillClass;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	JavaPlugin plugin;
	
	public void onEnable() {
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Enabling PGM-API...");
		if (!Bukkit.getPluginManager().isPluginEnabled("PGM")) {
			console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.RED + "The plugin PGM is required!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		saveDefaultConfig();
		reloadConfig();
		getServer().getPluginManager().registerEvents(this, this);
	    getServer().getPluginManager().registerEvents(new JoinClass(this), this);
	    getServer().getPluginManager().registerEvents(new KillClass(this), this);
	    getCommand("pgmstats").setExecutor(new StatsAdmin());
	    getCommand("pgmranks").setExecutor(new Ranks());
	    getCommand("pgm").setExecutor(new General());
	    getCommand("pgmapi").setExecutor(new General());
		MySQL.host = getConfig().getString("MySQL.Host");
		MySQL.port = getConfig().getString("MySQL.Port");
	    MySQL.db = getConfig().getString("MySQL.DbName");
	    MySQL.user = getConfig().getString("MySQL.Username");
	    MySQL.pass = getConfig().getString("MySQL.Password");
	    MySQL.openConnection();
	    try {
	        PreparedStatement np = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS `Users` (ID INTEGER PRIMARY KEY AUTO_INCREMENT, `Player` VARCHAR(16), `Rank` VARCHAR(20), `Online` VARCHAR(20), `LastLogin` VARCHAR(20),  `Coins` LONGTEXT, `Kills` LONGTEXT, `Deaths` LONGTEXT, `Wins` LONGTEXT, `Losts` LONGTEXT)");
	        np.execute();
	        np.close();
	      } catch (Exception e1) {
	        e1.printStackTrace();
	        console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.RED + "Database Error. Please check your Config.yml");
	        Bukkit.getPluginManager().disablePlugin(this);
	        return;
	    } 
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Plugin Enabled!");
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Developed By Ilario42");
	}
	
	public void onDisable() {
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Disabling PGM-API...");
		if (MySQL.connection != null) MySQL.closeConnection();
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Plugin Disabled!");
		console.sendMessage(ChatColor.GOLD + "[PGM-API] " + ChatColor.AQUA + "Developed By Ilario42");
	}
	

}
