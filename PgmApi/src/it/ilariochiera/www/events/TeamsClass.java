package it.ilariochiera.www.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import it.ilariochiera.www.main.Main;
import it.ilariochiera.www.main.MySQL;

public class TeamsClass implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;

	public TeamsClass(Main instance) {
	  this.plugin = instance;
	}
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    Action action = event.getAction();
	    ItemStack item = event.getItem();
	     if ( action.equals( Action.RIGHT_CLICK_AIR ) || action.equals( Action.RIGHT_CLICK_BLOCK ) ) {
	         if ( item != null && item.getType() == Material.LEATHER_HELMET ) {
	        	 String joinedt = MySQL.getString(player, "TeamsJoins");
	        	 int joinedtint = Integer.valueOf(joinedt);	
	        	 MySQL.setStringInt(player, "TeamsJoins", joinedtint + 1);
	         } 
	     }

	}

}
