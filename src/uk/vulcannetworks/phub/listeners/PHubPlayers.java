package uk.vulcannetworks.phub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import uk.vulcannetworks.phub.VulcanCore;

public class PHubPlayers implements Listener {

	VulcanCore vc;
	public PHubPlayers(VulcanCore c){
		this.vc = c;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		
		if(vc.customChat()){
			e.setFormat(vc.getBeforeName() + e.getPlayer().getDisplayName() + vc.getAfterName() + " " +  e.getMessage());
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if((e.getPlayer().getLocation().getY() < vc.getTeleportBelowId()) && (vc.teleportIfBelow())){
			e.getPlayer().teleport(new Location(Bukkit.getWorld(vc.getSpawnWorld()), vc.getSpawnX(), vc.getSpawnY(), vc.getSpawnZ()));
			e.getPlayer().sendMessage(ChatColor.RED + vc.getPrefix() + ChatColor.GOLD + " You have been magically wisked back to spawn point!");
		}
		
		if(vc.particleEnabled()){
			Player p = (Player) Bukkit.getPlayer(vc.getPlayer());
			if(p == null){
				return;
			}
			
			if(vc.getParticle() == 1){
				p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
				return;
			}else if(vc.getParticle() == 2){
				p.getWorld().playEffect(p.getLocation(), Effect.COLOURED_DUST, 1);
				return;
			}else if(vc.getParticle() == 3){
				p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
				return;
			}else{
				return;
			}
		}
			
	}
	
	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e){
		if(vc.allowedToBreak()){
			return;
		}else{
			if(e.getPlayer().isOp()){
				if(vc.opBypassBlock()){
					e.setCancelled(false);
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
