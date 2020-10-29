package it.ilariochiera.www.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import it.ilariochiera.www.main.Main;
import it.ilariochiera.www.main.MySQL;

public class KillClass implements Listener {
private Main plugin;
	
	public KillClass(Main instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player killed = e.getEntity();
		Player killer = e.getEntity().getKiller();
		String killer1 = MySQL.getString(killer, "Kills");
		int killer1int = Integer.valueOf(killer1);	
		String killed1 = MySQL.getString(killed, "Deaths");
		int killed1int = Integer.valueOf(killed1);	
		String killsconfig = this.plugin.getConfig().getString("Stats.Kills");
		String killscconfig = this.plugin.getConfig().getString("Coins.Kills");
		int killsconfigint = Integer.valueOf(killsconfig);
		int killscconfigint = Integer.valueOf(killscconfig);
		String deathsconfig = this.plugin.getConfig().getString("Stats.Deaths");
		String deathscconfig = this.plugin.getConfig().getString("Coins.Deaths");
		int deathsconfigint = Integer.valueOf(deathsconfig);
		int deathscconfigint = Integer.valueOf(deathscconfig);
		MySQL.setStringInt(killer, "Kills", killer1int + killsconfigint);
		MySQL.setStringInt(killed, "Deaths", killed1int + deathsconfigint);
		MySQL.setStringInt(killer, "Coins", killer1int + killscconfigint);
		MySQL.setStringInt(killed, "Coins", killed1int - deathscconfigint);
	}

}
