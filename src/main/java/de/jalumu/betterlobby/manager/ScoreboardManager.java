package de.jalumu.betterlobby.manager;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager implements Configurable {
	
	private static Map<UUID,Objective> objectives = new HashMap<>();

	@Override
	public void defaultConfiguration() {
		BetterLobby.getConfiguration().addDefault("scoreboard.enabled",true);
		BetterLobby.getConfiguration().addDefault("scoreboard.head","&2example.de");
		for (int i = 1; i <= 15;i++){
			BetterLobby.getConfiguration().addDefault("scoreboard.row." + i + ".enabled",true);
			BetterLobby.getConfiguration().addDefault("scoreboard.row." + i + ".text","Line-" + i);
		}
	}

	public ScoreboardManager(BetterLobby betterLobby){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(betterLobby, new Runnable() {
			@Override
			public void run() {
				for (Player players : Bukkit.getOnlinePlayers()){
					updateScoreboard(players);
				}
			}
		},0,60);
	}


	public static void updateScoreboard(Player player) {

		if (!objectives.containsKey(player.getUniqueId())){
			registerScoreboard(player);
		}

		Objective objective = objectives.get(player.getUniqueId());
		Scoreboard scoreboard = objective.getScoreboard();
		objectives.remove(player.getUniqueId(),objective);
		objective.unregister();

		objective = scoreboard.registerNewObjective("lobobj","dummy");

		objective.setDisplayName(TextUtil.parse(BetterLobby.getConfiguration().getString("scoreboard.head")));
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		for (int i = 1; i <= 15;i++){
			if (BetterLobby.getConfiguration().getBoolean("scoreboard.row." + i + ".enabled")) {
				String text = TextUtil.parse(BetterLobby.getConfiguration().getString("scoreboard.row." + i + ".text"), player);
				objective.getScore(text).setScore(16 - i);
			}
		}
		objectives.put(player.getUniqueId(),objective);
		player.setScoreboard(scoreboard);


	}

	private static void registerScoreboard(Player player){
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("lobobj","dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objectives.put(player.getUniqueId(),objective);
		player.setScoreboard(scoreboard);
	}
	




}
