package me.wilko.flighthunger.command;

import me.wilko.flighthunger.gui.SettingsMenu;
import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.SimpleCommand;

@AutoRegister
public final class FlightHungerCommand extends SimpleCommand {

	public FlightHungerCommand() {
		super("flighthunger|fh");

		setDescription("Opens the settings menu");
		setPermission("flighthunger.admin");
	}

	@Override
	protected void onCommand() {
		new SettingsMenu().displayTo(getPlayer());
	}
}
