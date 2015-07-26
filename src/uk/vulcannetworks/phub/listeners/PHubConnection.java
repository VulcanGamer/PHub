package uk.vulcannetworks.phub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import uk.vulcannetworks.phub.VulcanCore;

public class PHubConnection implements Listener {

	VulcanCore vc;
	
	public PHubConnection(VulcanCore c){
		this.vc = c;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
	
		
		e.getPlayer().sendMessage(ChatColor.RED + "" + vc.getPrefix() + " " + ChatColor.GOLD + vc.getJoinMessage());
		
		if(vc.teleportOnJoin()){
			e.getPlayer().teleport(new Location(Bukkit.getWorld(vc.getSpawnWorld()), vc.getSpawnX(), vc.getSpawnY(), vc.getSpawnZ()));
		}
		
	}
	
}
