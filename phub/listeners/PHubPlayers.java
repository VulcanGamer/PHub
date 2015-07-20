package uk.vulcannetworks.phub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import uk.vulcannetworks.phub.VulcanCore;

public class PHubPlayers implements Listener {

	VulcanCore vc;
	
	public PHubPlayers(VulcanCore c){
		this.vc = c;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if((e.getPlayer().getLocation().getY() < vc.getTeleportBelowId()) && (vc.teleportIfBelow())){
			e.getPlayer().teleport(new Location(Bukkit.getWorld(vc.getSpawnWorld()), vc.getSpawnX(), vc.getSpawnY(), vc.getSpawnZ()));
			e.getPlayer().sendMessage(ChatColor.RED + vc.getPrefix() + ChatColor.GOLD + " You have been magically wisked back to spawn point!");
		}
	}
	
	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e){
		if(vc.allowedToBreak()){
			return;
		}else{
			if(e.getPlayer().isOp()){
				if(vc.opBypassBlock()){
					return;
				}else{
					e.setCancelled(true);
				}
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerBreak(BlockPlaceEvent e){
		if(vc.allowedToPlace()){
			return;
		}else{
			if(e.getPlayer().isOp()){
				if(vc.opBypassBlock()){
					return;
				}else{
					e.setCancelled(true);
				}
			}else{
				e.setCancelled(true);
			}
		}
	}
}
