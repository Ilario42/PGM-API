package it.ilariochiera.www.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import it.ilariochiera.www.main.Main;
import it.ilariochiera.www.main.MySQL;

public class TNTPlaces implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;

	public TNTPlaces(Main instance) {
	  this.plugin = instance;
	}
	
	@EventHandler
	public void OnPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if (block.getType().equals(Material.TNT)) {
			String tntplaced = MySQL.getString(player, "TntPlaced");
			int tntplacedint = Integer.valueOf(tntplaced);	
			MySQL.setStringInt(player, "TntPlaced", tntplacedint + 1);
		}
	}

}
