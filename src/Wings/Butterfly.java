package Wings;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.inventivetalent.particle.ParticleEffect;

import Solar.Wings.Main;
import Util.Point3D;

public class Butterfly {
	private static final float a = 0.2f;
	private static Point3D[] outline = {
			// 0 line

			new Point3D(a * 3 * 1.2f, 0f, -0.3f), new Point3D(a * 4 * 1.2f, 0f, -0.3f),

			// +1 line

			new Point3D(a * 3 * 1.2f, 0.25f, -0.3f), new Point3D(a * 4 * 1.2f, 0.25f, -0.3f),
			// + 2 line

			new Point3D(a * 4 * 1.2f, 0.5f, -0.3f), new Point3D(a * 5 * 1.2f, 0.5f, -0.3f),

			// + 3 line

			new Point3D(a * 4 * 1.2f, 0.75f, -0.3f), new Point3D(a * 5 * 1.2f, 0.75f, -0.3f),
			// + 4 line

			new Point3D(a * 5 * 1.2f, a * 5, -0.3f),
			// +5 line

			// +6 line

			// -1 line

			new Point3D(a * 2 * 1.2f, -0.25f, -0.3f), new Point3D(a * 3 * 1.2f, -0.25f, -0.3f),
			// -2 line
			new Point3D(a * 1.2f, -0.5f, -0.3f), new Point3D(a * 2, -0.5f, -0.3f),
			new Point3D(a * 3 * 1.2f, -0.5f, -0.3f),
			// -3 line
			new Point3D(a * 2 * 1.2f, -0.75f, -0.3f), new Point3D(a * 3 * 1.2f, -0.75f, -0.3f),
			// -4 line

			new Point3D(a * 3 * 1.2f, -a * 5, -0.3f),
			// -5 line

			// -6 line

	};
	public static Point3D[] fill = { new Point3D(a * 2 * 1.2f, 0f, -0.3f), new Point3D(a * 5 * 1.2f, 0f, -0.3f),
			new Point3D(a * 2* 1.2f, 0.25f, -0.3f), new Point3D(a * 5* 1.2f, 0.25f, -0.3f), new Point3D(a * 3* 1.2f, 0.5f, -0.3f),
			new Point3D(a * 6* 1.2f, 0.5f, -0.3f), new Point3D(a * 3* 1.2f, 0.75f, -0.3f), new Point3D(a * 6* 1.2f, 0.75f, -0.3f),
			new Point3D(a * 4* 1.2f, a * 5, -0.3f), new Point3D(a * 6* 1.2f, a * 5, -0.3f), new Point3D(a * 6* 1.2f, 1.25f, -0.3f),
			new Point3D(a* 1.2f * 7, 1.25f, -0.3f), new Point3D(a* 1.2f * 7, 1.5f, -0.3f), new Point3D(a* 1.2f, -0.25f, -0.3f),
			new Point3D(a* 1.2f * 4, -0.25f, -0.3f), new Point3D(0f, -0.5f, -0.3f), new Point3D(a* 1.2f * 4, -0.75f, -0.3f),

			new Point3D(a * 1.2f* 2, -a * 5, -0.3f), new Point3D(a* 1.2f * 4, -a * 5, -0.3f), new Point3D(a* 1.2f * 4, -1.25f, -0.3f),
			new Point3D(a* 1.2f * 3, -1.25f, -0.3f), new Point3D(a* 1.2f, -0.75f, -0.3f),

	};

	public static void display(Player player) {
		Location playerLocation = player.getEyeLocation();
		float x = (float) playerLocation.getX();
		float y = (float) playerLocation.getY() - a;
		float z = (float) playerLocation.getZ();
		float rot = -playerLocation.getYaw() * 0.017453292F;

		Point3D rotated = null;
		for (Point3D point : outline) {
			rotated = point.rotate(rot);

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), Color.RED);
			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), Color.RED);

		}

		for (Point3D point : fill) {
			rotated = point.rotate(rot);

			// ParticleEffect.SWEEP_ATTACK.display(3f, 3f, 3f, a *5, 0,
			// new Location(playerWorld, rotated.x + x, rotated.y + y, rotated.z
			// +z),
			// 50f);
			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(150, 30, 50));

			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;
			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z),
					Color.fromRGB(150, 30, 50));

			// ParticleEffect.SWEEP_ATTACK.display(3f, 3f, 3f, a *5, 0,
			// new Location(playerWorld, rotated.x + x, rotated.y + y, rotated.z
			// +z),
			// 50f);
		}
	}

	public static void displayTrait(Player player) {
		Location loc = player.getLocation().add(0, 0.5, 0);
		ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(loc, 30d),
				loc,
				Color.fromRGB(150, 30, 50));
		ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(loc, 30d),
				loc, Color.RED);

	}

}
