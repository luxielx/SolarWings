package Solar.Wings;

import java.util.ArrayList;
import java.util.HashMap;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import Menu.GUI;
import Menu.Lis;
import Wings.WingType;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {
	FileConfiguration config;
	public static HashMap<Player, WingType> hasWings = new HashMap<>();
	private static HashMap<Player, Location> lastLoc = new HashMap<>();
	private static PlayerPoints playerPoints;
	public static Main main;
	private static Permission perms = null;
	public static HashMap<Player, WingType> getHasWings() {
		return hasWings;

	}

	public static PlayerPoints getPP() {
		return playerPoints;
	}

	public static boolean hasWing(Player player) {
		if (hasWings.containsKey(player)) {
			return true;
		} else {
			return false;
		}
	}
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	public static void removeWing(Player player) {
		if (hasWing(player)) {
			hasWings.remove(player);
		}
	}

	public static void addWing(Player player, WingType wing) {
		hasWings.put(player, wing);
	}

	public static Main getMain() {
		return main;
	}
	public static Permission getPermissions() {
        return perms;
    }
	@Override
	public void onEnable() {
		main = this;
		config = getConfig();
		config.options().copyDefaults(true);
		Bukkit.getServer().getPluginManager().registerEvents(new Lis(this), this);
		hookPlayerPoints();
		createRunnable();
		setupPermissions();
	}

	private void createRunnable() {
		new BukkitRunnable() {

			@Override
			public void run() {

				for (Player player : hasWings.keySet()) {
					if (!player.isValid()) {
						Main.removeWing(player);

					}

					if (hasWings.containsKey(player)) {

						if (lastLoc.containsKey(player)) {

							if (lastLoc.get(player).getWorld() != player.getWorld()
									|| lastLoc.get(player).distance(player.getLocation()) >= 0.01) {

								hasWings.get(player).displayTrait(player);
								lastLoc.put(player, player.getLocation());

							} else {

								hasWings.get(player).display(player);

								lastLoc.put(player, player.getLocation());

							}
						} else {

							hasWings.get(player).display(player);

							lastLoc.put(player, player.getLocation());
						}
					} else {
						if (lastLoc.containsKey(player)) {
							lastLoc.remove(player);
						}

					}

				}
			}
		}.runTaskTimerAsynchronously(main, 0, 3);
	}

	private boolean hookPlayerPoints() {
		final Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
		playerPoints = PlayerPoints.class.cast(plugin);
		return playerPoints != null;
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("hieuung") || cmd.getName().equalsIgnoreCase("hu")) {
				// hu give SolarFavor ButterFly
				if(args.length > 1){
				if(args[0].equalsIgnoreCase("give") && player.hasPermission("sscouhivudhs")){
					Player z = Bukkit.getPlayer(args[1]);
					Main.getPermissions().playerAdd(z, "solar.wing." + ChatColor.stripColor(args[2].toUpperCase()));}
				}
				else{
					GUI.main.open(player);
				}
				

				return true;

			} 
		}

		return false;
	}

	public static ArrayList<Player> getNearbyPlayer(Location player, Double radius) {
		World world = player.getWorld();
		ArrayList<Player> list = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
		for (Player online : Bukkit.getServer().getOnlinePlayers()) {
			if (online.getWorld() == world) {
				if (online.getLocation().distance(player) > radius) {
					list.remove(online);
				}
			}
		}
		return list;
	}

}
