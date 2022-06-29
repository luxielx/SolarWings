package Wings;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.inventivetalent.particle.ParticleEffect;

import Solar.Wings.Main;
import Util.Point3D;

public class DragonWing {
	private static Point3D[] outline = {
			// 0 line

			new Point3D(0, 0, -0.3f), new Point3D(0.2f, 0f, -0.3f), new Point3D(0.4f, 0f, -0.3f),
			new Point3D(0.6f, 0f, -0.3f), new Point3D(0.8f, 0f, -0.3f), new Point3D(1f, 0f, -0.3f),
			new Point3D(1.2f, 0f, -0.3f), new Point3D(1.4f, 0f, -0.3f), new Point3D(1.6f, 0f, -0.3f),
			new Point3D(1.8f, 0f, -0.3f),

			// -1 line
			new Point3D(0.2f, -0.25f, -0.25f), new Point3D(0.4f, -0.25f, -0.25f), new Point3D(0.6f, -0.25f, -0.25f),
			new Point3D(0.8f, -0.25f, -0.25f), new Point3D(1f, -0.25f, -0.25f), new Point3D(1.2f, -0.25f, -0.25f),
			new Point3D(1.4f, -0.25f, -0.25f), new Point3D(1.6f, -0.25f, -0.25f), new Point3D(1.8f, -0.25f, -0.25f),
			new Point3D(2f, -0.25f, -0.25f),
			// +1 line
			new Point3D(0.25f, 0.25f, -0.25f), new Point3D(0.4f, 0.25f, -0.25f), new Point3D(0.6f, 0.25f, -0.25f),
			new Point3D(1.2f, 0.25f, -0.25f), new Point3D(1.4f, 0.25f, -0.25f), new Point3D(1.6f, 0.25f, -0.25f),
			// +2 line

			new Point3D(1f, 0.5f, -0.3f), new Point3D(1.4f, 0.5f, -0.3f),
			// -2 line

			new Point3D(0.6f, -0.5f, -0.3f), new Point3D(0.8f, -0.5f, -0.3f), new Point3D(1f, -0.5f, -0.3f),
			new Point3D(1.2f, -0.5f, -0.3f), new Point3D(1.6f, -0.5f, -0.3f), new Point3D(1.8f, -0.5f, -0.3f),
			new Point3D(2f, -0.6f, -0.3f),
			// -3 line

			new Point3D(1f, -0.75f, -0.3f)

	};

	public static Point3D[] fill = { new Point3D(2f, 0f, -0.3f), new Point3D(2.2f, -0.25f, -0.3f),
			new Point3D(1.8f, 0.25f, -0.3f), new Point3D(0.8f, 0.25f, -0.3f), new Point3D(1f, 0.25f, -0.3f),
			new Point3D(1.6f, 0.5f, -0.3f), new Point3D(0.4f, 0.5f, -0.3f), new Point3D(1.2f, 0.5f, -0.3f),
			new Point3D(1.4f, 0.75f, -0.3f), new Point3D(2.2f, -0.5f, -0.3f), new Point3D(1.8f, -0.75f, -0.3f) };

	public static void display(Player player) {
		Location playerLocation = player.getEyeLocation();
		float x = (float) playerLocation.getX();
		float y = (float) playerLocation.getY() - 0.2f;
		float z = (float) playerLocation.getZ();
		float rot = -playerLocation.getYaw() * 0.017453292F;

		Point3D rotated = null;
		for (Point3D point : outline) {
			rotated = point.rotate(rot);

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(78, 244, 66));
			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(78, 244, 66));

		}

		for (Point3D point : fill) {
			rotated = point.rotate(rot);

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(1, 152, 117));

			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;
			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(1, 152, 117));

		}
	}

	public static void displayTrait(Player player) {
		Location loc = player.getLocation().add(0, 0.4, 0);
		ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(loc, 30d),
				loc,
				Color.fromRGB(78, 244, 66));

	}
}
