package uk.vulcannetworks.phub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.vulcannetworks.phub.VulcanCore;

public class PHubCommandBase implements CommandExecutor {

	VulcanCore vc;
	
	public PHubCommandBase(VulcanCore c){
		this.vc = c;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		// TODO Auto-generated method stub
		
		if(!(sender instanceof Player)){
			
			sender.sendMessage("You may " + ChatColor.RED + "NOT " + ChatColor.RESET + " these commands due to risk of Exceptions!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(args.length == 0){
			p.sendMessage("Help Menu:");
			p.sendMessage(" ");
			p.sendMessage("/suite reload");
			
			return true;
		}else{
			if(args[0].equalsIgnoreCase("reload")){
				if(p.hasPermission("phub.permissions.reload")){
					vc.reloadConfig();
					p.sendMessage("Config reloaded!");
					
					return true;
				}else{
					p.sendMessage("Error running this command, are you authorised?");
					return true;
				}
			}
		}
		
		return false;
	}

}
