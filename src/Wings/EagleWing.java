package Wings;

import java.awt.Color;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.inventivetalent.particle.ParticleEffect;

import Solar.Wings.Main;
import Util.Point3D;

public class EagleWing{

	private static Point3D[] outline = { new Point3D(0, 0, -0.5f), new Point3D(0.1f, 0.01f, -0.5f),
			new Point3D(0.3f, 0.03f, -0.5f), new Point3D(0.4f, 0.04f, -0.5f), new Point3D(0.6f, 0.1f, -0.5f),
			new Point3D(0.61f, 0.2f, -0.5f), new Point3D(0.62f, 0.4f, -0.5f), new Point3D(0.63f, 0.6f, -0.5f),
			new Point3D(0.635f, 0.7f, -0.5f), new Point3D(0.7f, 0.7f, -0.5f), new Point3D(0.9f, 0.75f, -0.5f),
			new Point3D(1.2f, 0.8f, -0.5f), new Point3D(1.4f, 0.9f, -0.5f), new Point3D(1.6f, 1f, -0.5f),
			new Point3D(1.8f, 1.1f, -0.5f), new Point3D(1.85f, 0.9f, -0.5f), new Point3D(1.9f, 0.7f, -0.5f),
			new Point3D(1.85f, 0.5f, -0.5f), new Point3D(1.8f, 0.3f, -0.5f), new Point3D(1.75f, 0.1f, -0.5f),
			new Point3D(1.7f, -0.1f, -0.5f), new Point3D(1.65f, -0.3f, -0.5f), new Point3D(1.55f, -0.5f, -0.5f),
			new Point3D(1.45f, -0.7f, -0.5f), new Point3D(1.30f, -0.75f, -0.5f), new Point3D(1.15f, -0.8f, -0.5f),
			new Point3D(1.0f, -0.85f, -0.5f), new Point3D(0.8f, -0.87f, -0.5f), new Point3D(0.6f, -0.7f, -0.5f),
			new Point3D(0.5f, -0.5f, -0.5f), new Point3D(0.4f, -0.3f, -0.5f), new Point3D(0.3f, -0.3f, -0.5f),
			new Point3D(0.15f, -0.3f, -0.5f), new Point3D(0f, -0.3f, -0.5f),

			//
			new Point3D(0.9f, 0.55f, -0.5f), new Point3D(1.2f, 0.6f, -0.5f), new Point3D(1.4f, 0.7f, -0.5f),
			new Point3D(1.6f, 0.9f, -0.5f),
			//
			new Point3D(0.9f, 0.35f, -0.5f), new Point3D(1.2f, 0.4f, -0.5f), new Point3D(1.4f, 0.5f, -0.5f),
			new Point3D(1.6f, 0.7f, -0.5f),
			//
			new Point3D(0.9f, 0.15f, -0.5f), new Point3D(1.2f, 0.2f, -0.5f), new Point3D(1.4f, 0.3f, -0.5f),
			new Point3D(1.6f, 0.5f, -0.5f),
			//
			new Point3D(0.9f, -0.05f, -0.5f), new Point3D(1.2f, 0f, -0.5f), new Point3D(1.4f, 0.1f, -0.5f),
			new Point3D(1.6f, 0.3f, -0.5f),
			//
			new Point3D(0.7f, -0.25f, -0.5f), new Point3D(1.0f, -0.2f, -0.5f), new Point3D(1.2f, -0.1f, -0.5f),
			new Point3D(1.4f, 0.1f, -0.5f),
			//
			new Point3D(0.7f, -0.45f, -0.5f), new Point3D(1.0f, -0.4f, -0.5f), new Point3D(1.2f, -0.3f, -0.5f),
			new Point3D(1.4f, -0.1f, -0.5f),
			//
			new Point3D(1.30f, -0.55f, -0.5f), new Point3D(1.15f, -0.6f, -0.5f), new Point3D(1.0f, -0.65f, -0.5f) };

	public static Point3D[] fill = { new Point3D(1.2f, 0.6f, -0.5f), new Point3D(1.4f, 0.7f, -0.5f),

			new Point3D(1.1f, 0.2f, -0.5f), new Point3D(1.3f, 0.3f, -0.5f),

			new Point3D(1.0f, -0.2f, -0.5f), new Point3D(1.2f, -0.1f, -0.5f), };

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
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), Color.cyan);
			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;

			ParticleEffect.REDSTONE.sendColor(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), Color.cyan);

		}

		for (Point3D point : fill) {
			rotated = point.rotate(rot);

			// ParticleEffect.SWEEP_ATTACK.display(3f, 3f, 3f, 1f, 0,
			// new Location(playerWorld, rotated.x + x, rotated.y + y, rotated.z
			// +z),
			// 50f);
			ParticleEffect.FIREWORKS_SPARK.send(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), 0, 0, 0, 0, 1);

			point.z *= -1;
			rotated = point.rotate(rot + 3.1415f);
			point.z *= -1;
			ParticleEffect.FIREWORKS_SPARK.send(Main.getNearbyPlayer(playerLocation, 30d),
					new Location(player.getWorld(), rotated.x + x, rotated.y + y, rotated.z + z), 0, 0, 0, 0, 1);

			// ParticleEffect.SWEEP_ATTACK.display(3f, 3f, 3f, 1f, 0,
			// new Location(playerWorld, rotated.x + x, rotated.y + y, rotated.z
			// +z),
			// 50f);
		}
	}

	public static void displayTrait(Player player) {
		Location loc = player.getLocation().add(0,0.4,0);
		ParticleEffect.SMOKE_NORMAL.send(Main.getNearbyPlayer(loc, 30d),
				loc, 0,0,0,1,20);

	}

}
