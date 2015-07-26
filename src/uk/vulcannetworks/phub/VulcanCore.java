package uk.vulcannetworks.phub;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.vulcannetworks.phub.commands.PHubCommandBase;
import uk.vulcannetworks.phub.listeners.PHubConnection;
import uk.vulcannetworks.phub.listeners.PHubPlayers;

public class VulcanCore extends JavaPlugin {

	@Override
	public void onEnable() {

		/** startup **/

		File file = new File(getDataFolder() + "/config.yml");

		if (!file.exists()) {

			getLogger().info("Generating hub config file.");
			this.getConfig().options().copyDefaults(true);
			this.saveDefaultConfig();

		} else {
			getLogger().info("Found the 'config.yml' file.");
		}

		pm().registerEvents(new PHubConnection(this), this);
		pm().registerEvents(new PHubPlayers(this), this);
		getCommand("suite").setExecutor(new PHubCommandBase(this));
		
		if(broadcastMessage()){
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
				
				public void run(){
					
					Bukkit.broadcastMessage(ChatColor.RED + getPrefix() + " " + ChatColor.GOLD + getBroadcasterMessage());
					
				}
				
			}, this.broadcasterDelay() * 20, this.broadcasterRepeatEvery() * 20);
		}
	}

	public PluginManager pm(){
		return Bukkit.getPluginManager();
	}
	
	public String getPrefix(){
		return getConfig().getString("chat.prefix");
	}
	
	public int getSpawnX(){
		return getConfig().getInt("join.teleport.x");
	}
	
	public int getSpawnY(){
		return getConfig().getInt("join.teleport.y");
	}
	
	public int getSpawnZ(){
		return getConfig().getInt("join.teleport.z");
	}
	
	public int broadcasterDelay(){
		return getConfig().getInt("broadcaster.delay");
	}
	
	public int broadcasterRepeatEvery(){
		return getConfig().getInt("broadcaster.every");
	}
	
	public boolean broadcastMessage(){
		return getConfig().getBoolean("broadcaster.enable");
	}
	
	public String getBroadcasterMessage(){
		return getConfig().getString("broadcaster.message");
	}
	
	public String getSpawnWorld(){
		return getConfig().getString("join.teleport.world");
	}
	
	public String getJoinMessage(){
		return getConfig().getString("join.joinmessage");
	}
	
	public boolean teleportOnJoin(){
		return getConfig().getBoolean("join.teleport.enabled");
	}
	
	public boolean teleportIfBelow(){
		return getConfig().getBoolean("listeners.teleport.ifbelow.enabled");
	}
	
	public int getTeleportBelowId(){
		return getConfig().getInt("listeners.teleport.ifbelow.y");
	}
	
	public boolean allowedToBreak(){
		return getConfig().getBoolean("listeners.block.breaking");
	}
	
	public boolean allowedToPlace(){
		return getConfig().getBoolean("listeners.block.placing");
	}
	
	public boolean opBypassBlock(){
		return getConfig().getBoolean("listeners.block.op-bypass");
	}
	
	public void setVariable(String path, Object variable){
		getConfig().set(path, variable);
	}
	
	public String getPlayer(){
		return getConfig().getString("admin.particles.user");
	}
	
	public int getParticle(){
		return getConfig().getInt("admin.particles.particle");
	}
	
	public boolean particleEnabled(){
		return getConfig().getBoolean("admin.particles.enable");
	}
	
	public boolean customChat(){
		return getConfig().getBoolean("chat.custom-chat.enable");
	}
	
	public String getBeforeName(){
		return getConfig().getString("chat.custom-chat.beforename");
	}
	
	public String getAfterName(){
		return getConfig().getString("chat.custom-chat.aftername");
	}
	
}
