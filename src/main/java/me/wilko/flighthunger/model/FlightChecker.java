package me.wilko.flighthunger.model;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.PlayerUtil;

import static me.wilko.flighthunger.settings.Settings.HUNGER_LOSS_AMOUNT;
import static me.wilko.flighthunger.settings.Settings.HUNGER_LOSS_INTERVAL;

public class FlightChecker implements Runnable {

	private BukkitTask task;
	private final Player player;

	public FlightChecker(Player player) {
		this.player = player;

		start();
	}

	@Override
	public void run() {

		// Decreases their food level
		player.setFoodLevel(player.getFoodLevel() - HUNGER_LOSS_AMOUNT);
	}

	public void start() {

		stop();

		task = Common.runTimer(HUNGER_LOSS_INTERVAL * 20, HUNGER_LOSS_INTERVAL * 20, this);
	}

	public static boolean canBypass(Player player) {
		return player.getGameMode() != GameMode.SURVIVAL || PlayerUtil.hasPerm(player, "flighthunger.bypass");
	}

	public void stop() {

		if (task != null) {
			task.cancel();
		}
	}
}
