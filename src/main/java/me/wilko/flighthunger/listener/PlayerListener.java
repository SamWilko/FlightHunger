package me.wilko.flighthunger.listener;

import me.wilko.flighthunger.model.FlightChecker;
import me.wilko.flighthunger.model.PlayerTimers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.mineacademy.fo.annotation.AutoRegister;

@AutoRegister
public final class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		if (FlightChecker.canBypass(player) || !player.isFlying())
			return;

		PlayerTimers.add(player);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		PlayerTimers.remove(player);
	}

	@EventHandler
	public void onToggleFlight(PlayerToggleFlightEvent event) {

		Player player = event.getPlayer();

		if (FlightChecker.canBypass(player))
			return;

		if (event.isFlying()) {

			PlayerTimers.add(player);
		} else {

			PlayerTimers.remove(player);
		}
	}
}
