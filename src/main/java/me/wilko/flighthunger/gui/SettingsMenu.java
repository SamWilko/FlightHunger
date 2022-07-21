package me.wilko.flighthunger.gui;

import me.wilko.flighthunger.model.FlightChecker;
import me.wilko.flighthunger.model.PlayerTimers;
import me.wilko.flighthunger.settings.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.TimeUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import static me.wilko.flighthunger.settings.Settings.HUNGER_LOSS_AMOUNT;
import static me.wilko.flighthunger.settings.Settings.HUNGER_LOSS_INTERVAL;

public class SettingsMenu extends Menu {
	private final int size = 9 * 3;
	private final Button intervalButton;
	private final Button amountButton;

	private final Button closeButton;

	public SettingsMenu() {

		setSize(size);
		setTitle("&8Flight Hunger Settings");

		intervalButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

				if (click.isLeftClick()) {
					HUNGER_LOSS_INTERVAL++;

					// Go through every players' timer and reset them
					for (FlightChecker checker : PlayerTimers.getAllCheckers())
						checker.start();

					restartMenu();

				} else if (click.isRightClick() && HUNGER_LOSS_INTERVAL > 1) {
					HUNGER_LOSS_INTERVAL--;

					// Go through every players' timer and reset them
					for (FlightChecker checker : PlayerTimers.getAllCheckers())
						checker.start();

					restartMenu();
				}

				new Settings().save();
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.CLOCK,
								"&6&lInterval",
								"",
								"&7Change how quickly",
								"&7hunger goes down",
								"",
								"&6Current&7: &e" + TimeUtil.formatTimeShort(HUNGER_LOSS_INTERVAL),
								"",
								"&aLeft click to increase",
								"&cRight click to decrease")
						.makeMenuTool();
			}
		};

		amountButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {

				if (click.isLeftClick() && HUNGER_LOSS_AMOUNT < 20) {
					HUNGER_LOSS_AMOUNT++;

					restartMenu();
				} else if (click.isRightClick() && HUNGER_LOSS_AMOUNT > 1) {
					HUNGER_LOSS_AMOUNT--;

					restartMenu();
				}

				new Settings().save();
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.COOKED_BEEF,
								"&6&lLoss Amount",
								"",
								"&7Change how much hunger",
								"&7is lost every interval",
								"",
								"&6Current&7: &e" + (HUNGER_LOSS_AMOUNT / 2.0) + (HUNGER_LOSS_AMOUNT > 2 ? " bars" : " bar"),
								"",
								"&aLeft click to increase",
								"&cRight click to decrease")
						.makeMenuTool();
			}
		};

		closeButton = new Button() {
			@Override
			public void onClickedInMenu(Player player, Menu menu, ClickType click) {
				player.closeInventory();
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator.of(CompMaterial.SPRUCE_DOOR, "&cClose Menu").makeMenuTool();
			}
		};
	}

	@Override
	public ItemStack getItemAt(int slot) {

		switch (slot) {

			case 12: {
				return intervalButton.getItem();
			}
			case 14: {
				return amountButton.getItem();
			}
			case size - 1: {
				return closeButton.getItem();
			}

			default:
				return ItemCreator.of(CompMaterial.GRAY_STAINED_GLASS_PANE, " ").makeMenuTool();
		}
	}
}
