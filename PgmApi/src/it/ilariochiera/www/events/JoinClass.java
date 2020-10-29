package it.ilariochiera.www.events;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import it.ilariochiera.www.main.Main;
import it.ilariochiera.www.main.MySQL;

public class JoinClass implements Listener {
	private Main plugin;
	
	public JoinClass(Main instance) {
		this.plugin = instance;
	}
	DateFormat df = new SimpleDateFormat();
	String date = df.format(new Date());
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent e) {
		if (MySQL.connection != null &&  !MySQL.containsPlayer(e.getPlayer()))
	      try {
	        PreparedStatement np = MySQL.connection.prepareStatement("INSERT INTO `Users` (`Player`, `Rank`, `Online`, `LastLogin`, `Coins`, `Kills`, `Deaths`, `Wins`, `Losts`) value (?,?,?,?,?,?,?,?,?);");
	        np.setString(1, e.getPlayer().getName());
	        np.setString(2, "Null");
	        np.setString(3, "Offline");
	        np.setString(4, "0");
	        np.setString(5, "0");
	        np.setString(6, "0");
	        np.setString(7, "0");
	        np.setString(8, "0");
	        np.setString(9, "0");
	        np.execute();
	        np.close();
	      } catch (Exception e1) {
	        e1.printStackTrace();
	      } 
		Player player = (Player) e.getPlayer();
		MySQL.setString(player, "Online", this.plugin.getConfig().getString("Server"));
		MySQL.setString(player, "LastLogin", "Online");
	}
	
	@EventHandler
	  public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = (Player) e.getPlayer();
		MySQL.setString(player, "Online", this.plugin.getConfig().getString("Server"));
		MySQL.setString(player, "LastLogin", date);
	}

}
