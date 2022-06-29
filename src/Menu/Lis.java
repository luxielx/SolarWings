package Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import Solar.Wings.Main;
import Util.Util;
import Wings.WingType;
import net.md_5.bungee.api.ChatColor;

public class Lis implements Listener {
	Main plugin;

	public Lis(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void leftEvt(PlayerQuitEvent e) {

		if (Main.hasWing(e.getPlayer())) {
			Main.getHasWings().remove(e.getPlayer());
		}
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) {

			return;
		}
		if (e.getCurrentItem().getType() == Material.AIR) {
			return;

		}
		Player player = (Player) e.getWhoClicked();
		if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Hiệu Ứng")) {
			e.setCancelled(true);
			if (e.getCurrentItem().equals(GUI.WingButton())) {
				GUI.wing.open(player);
			} else if (e.getCurrentItem().equals(GUI.removeParticle(player))) {
				Main.removeWing(player);
				GUI.main.open(player);

			}

		}
		if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "Cánh")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getLore().contains(Util.hideS("solarwing"))) {
				
				if (WingType.valueOf(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase()) == null)

					return;
				Main.addWing(player,
						WingType.valueOf(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase()));
				player.closeInventory();
			} else if (e.getCurrentItem().getItemMeta().getLore().contains(Util.hideS("solarnowing"))) {
				int price = Integer.valueOf(Util.unhideS(e.getCurrentItem().getItemMeta().getLore()
						.get(e.getCurrentItem().getItemMeta().getLore().size() -1 )));
				
				if (Main.getPP().getAPI().look(player.getUniqueId()) >= price) {
					Main.getPermissions().playerAdd(player, "solar.wing." + ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase());
					
					Main.getPP().getAPI().take(player.getUniqueId(), price);
					GUI.wing.open(player);
				}else{
					int slot = e.getSlot();
					ItemStack itemstack = e.getCurrentItem();
					e.getClickedInventory().setItem(slot, potato(Main.getPP().getAPI().look(player.getUniqueId()), price));
					new BukkitRunnable() {
						
						@Override
						public void run() {
							if(e.getClickedInventory().getTitle() != null)
							e.getClickedInventory().setItem(slot, itemstack);
							
						}
					}.runTaskLaterAsynchronously(Main.getMain(), 45);
				}

			}
		}
	}

	private ItemStack potato(int look , int price) {
		ItemStack is = new ItemStack(Material.POTATO_ITEM);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Bạn Còn Thiếu " +ChatColor.GREEN+ ( price - look)  +ChatColor.BLUE + " Point(s)" + ChatColor.RED +" Để mua vật phẩm này");
		
		is.setItemMeta(im);
		return is;
	}

}
