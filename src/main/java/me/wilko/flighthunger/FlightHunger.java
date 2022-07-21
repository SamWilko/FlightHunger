package me.wilko.flighthunger;

import me.wilko.flighthunger.settings.Settings;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class FlightHunger extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		new Settings().load();
	}

	@Override
	protected void onReloadablesStart() {

	}

	public static FlightHunger getInstance() {
		return (FlightHunger) SimplePlugin.getInstance();
	}
}
