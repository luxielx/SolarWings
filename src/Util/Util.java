package Util;

import net.md_5.bungee.api.ChatColor;

public class Util {

	public static String hideS(String s) {
		String hidden = "";
		for (char c : s.toCharArray())
			hidden += ChatColor.COLOR_CHAR + "" + c;
		return hidden;
	}
	
	
	public static String unhideS(String s) {
		String r = s.replaceAll("§", "");
		return r;
	}
}
