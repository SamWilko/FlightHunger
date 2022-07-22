package me.wilko.flighthunger.settings;

import me.wilko.flighthunger.FlightHunger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class Settings {

	private static File settingsFile;
	private static YamlConfiguration config;

	public static int HUNGER_LOSS_INTERVAL = 1;
	public static int HUNGER_LOSS_AMOUNT = 1;

	public static void load() {

		FlightHunger instance = FlightHunger.getInstance();
		String path = "settings.yml";

		settingsFile = new File(instance.getDataFolder(), path);

		if (!settingsFile.exists())
			instance.saveResource(path, false);

		config = YamlConfiguration.loadConfiguration(settingsFile);

		HUNGER_LOSS_INTERVAL = config.getInt("hunger-loss-interval", 1);
		HUNGER_LOSS_AMOUNT = config.getInt("hunger-loss-amount", 1);
	}

	public static void save() {
		config.set("hunger-loss-interval", HUNGER_LOSS_INTERVAL);
		config.set("hunger-loss-amount", HUNGER_LOSS_AMOUNT);

		try {

			config.save(settingsFile);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
