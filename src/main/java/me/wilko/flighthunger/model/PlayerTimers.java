package me.wilko.flighthunger.model;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlayerTimers {

	private static final Map<Player, FlightChecker> PLAYER_TIMERS = new HashMap<>();

	public static void add(Player player) {

		remove(player);

		PLAYER_TIMERS.put(player, new FlightChecker(player));
	}

	public static void remove(Player player) {

		FlightChecker checker = getFlightCheckerFor(player);
		if (checker != null)
			checker.stop();

		PLAYER_TIMERS.remove(player);
	}

	public static FlightChecker getFlightCheckerFor(Player player) {
		return PLAYER_TIMERS.get(player);
	}

	public static List<FlightChecker> getAllCheckers() {
		return new ArrayList<>(PLAYER_TIMERS.values());
	}
}
