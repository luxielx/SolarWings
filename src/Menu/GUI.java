package Menu;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Solar.Wings.Main;
import Util.Util;
import Wings.WingType;
import io.netty.util.internal.ThreadLocalRandom;
import net.md_5.bungee.api.ChatColor;

public enum GUI {
	main(1), wing(2);
	int id;

	private GUI(int id) {
		this.id = id;

	}

	public void open(Player player) {
		switch (id) {
		case (1):
			openMain(player);
			break;
		case (2):
			openWing(player);
			break;
		}
	}

	private void openWing(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "Cánh");
		int i = 0;
		for (WingType a : WingType.values()) {
			inv.setItem(i, WingTypes(a, player, a.getPrice()));
			i++;
		}
		player.openInventory(inv);

	}

	public static ItemStack WingTypes(WingType a, Player player, int price) {
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta im = i.getItemMeta();

		List<String> r = Arrays
				.asList(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" });

		im.setDisplayName("§" + (String) r.get(ThreadLocalRandom.current().nextInt(0, r.size())) + a.getName() );

		i.setItemMeta(im);

		if (player.hasPermission("solar.wing." + a.toString().toLowerCase())) {
			im.setLore(Arrays.asList(
					new String[] { ChatColor.BLUE + "- " + ChatColor.GREEN + "Đã mở khóa", Util.hideS("solarwing") }));
		} else {
			im.setLore(Arrays.asList(new String[] {
					ChatColor.BLUE + "- " + ChatColor.RED + "Mua ngay với giá " + ChatColor.YELLOW + price
							+ ChatColor.BLUE + " Point(s)",
					Util.hideS("solarnowing"), Util.hideS(String.valueOf(price)) }));
		}
		i.setItemMeta(im);
		return i;
	}

	private void openMain(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "Hiệu Ứng");
		inv.setItem(4, WingButton());
		inv.setItem(49, removeParticle(player));
		player.openInventory(inv);
	}

	public static ItemStack WingButton() {
		ItemStack is = new ItemStack(Material.FEATHER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Cánh");
		im.setLore(Arrays.asList(
				new String[] { ChatColor.BLUE + "- " + ChatColor.YELLOW + " Các loại cánh thời trang số một" }));
		is.setItemMeta(im);
		return is;
	}

	public static ItemStack removeParticle(Player player) {
		ItemStack is;
		if (Main.hasWing(player)) {
			is = new ItemStack(Material.WOOL, 1, (short) 14);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.RED + "Tắt Hiệu Ứng");
			im.setLore(
					Arrays.asList(new String[] { ChatColor.BLUE + "- " + ChatColor.RED + " Bấm vào để tắt hiệu ứng" }));
			is.setItemMeta(im);
		} else {
			is = new ItemStack(Material.WOOL, 1, (short) 5);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "Tắt Hiệu Ứng");
			im.setLore(Arrays
					.asList(new String[] { ChatColor.BLUE + "- " + ChatColor.BLUE + " Bạn Không Có Hiệu Ứng để tắt" }));
			is.setItemMeta(im);
		}

		return is;
	}
}
