package it.ilariochiera.www.other;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import it.ilariochiera.www.main.Main;

//WARN: This piece of code informs me if you are using my plugin. Nothing more'. Rest assured friend!

public class Notify implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	//WARN: This piece of code informs me if you are using my plugin. Nothing more'. Rest assured friend!
	public Notify(Main instance) {
	  this.plugin = instance;
	}
	//WARN: This piece of code informs me if you are using my plugin. Nothing more'. Rest assured friend!
	public void onPlayerJoin(PlayerJoinEvent e2) {  
	    Player p = e2.getPlayer();
		if (e2.getPlayer().getName().contains("Ilario42")) {
		  p.sendMessage(ChatColor.GOLD + "§9PGM-API §8»" + ChatColor.DARK_RED + "§lThis server uses your plugin");
		}
	}
	//WARN: This piece of code informs me if you are using my plugin. Nothing more'. Rest assured friend!
}
//WARN: This piece of code informs me if you are using my plugin. Nothing more'. Rest assured friend!