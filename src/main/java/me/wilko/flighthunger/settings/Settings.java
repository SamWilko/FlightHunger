package me.wilko.flighthunger.settings;

import org.mineacademy.fo.settings.YamlConfig;

public class Settings extends YamlConfig {

	public static int HUNGER_LOSS_INTERVAL = 1;
	public static int HUNGER_LOSS_AMOUNT = 1;

	public Settings() {
		this.loadConfiguration("options.yml");
	}

	public void load() {
		System.out.println("load called");
		HUNGER_LOSS_INTERVAL = this.getInteger("hunger-loss-interval", 1);
		HUNGER_LOSS_AMOUNT = this.getInteger("hunger-loss-amount", 1);
	}

	@Override
	protected void onSave() {
		System.out.println("save called");
		this.set("hunger-loss-interval", HUNGER_LOSS_INTERVAL);
		this.set("hunger-loss-amount", HUNGER_LOSS_AMOUNT);
	}
}
