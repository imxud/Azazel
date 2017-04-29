package us.ikari.azazel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;
import us.ikari.azazel.tab.Tab;
import us.ikari.azazel.tab.TabAdapter;
import us.ikari.azazel.tab.TabTemplate;

import java.util.*;

public class AzazelTask extends BukkitRunnable {

    private final Azazel azazel;

    public AzazelTask(Azazel azazel, JavaPlugin plugin) {
        this.azazel = azazel;

        runTaskTimerAsynchronously(plugin, 2L, 2L);
    }

    @Override
    public void run() {
        TabAdapter adapter = azazel.getAdapter();
        if (adapter != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Tab tab = azazel.getTabByPlayer(player);
                if (tab != null) {
                    TabTemplate template = adapter.getTemplate(player);

                    if (template == null) {
                        for (Tab.TabEntryPosition position : tab.getPositions()) {
                            Team team = player.getScoreboard().getTeam(position.getKey());
                            if (team != null) {
                                if (team.getPrefix() != null && !team.getPrefix().isEmpty()) {
                                    team.setPrefix("");
                                }
                                if (team.getSuffix() != null && !team.getSuffix().isEmpty()) {
                                    team.setSuffix("");
                                }
                            }
                        }
                    }

                    List<List<String>> rows = Arrays.asList(template.getLeft(), template.getMiddle(), template.getRight());
                    for (int l = 0; l < rows.size(); l++) {
                        for (int i = 0; i < rows.get(l).size(); i++) {
                            Team team = tab.getByLocation(l, i);
                            if (team != null) {
                                Map.Entry<String, String> prefixAndSuffix = getPrefixAndSuffix(rows.get(l).get(i));
                                String prefix = prefixAndSuffix.getKey();
                                String suffix = prefixAndSuffix.getValue();

                                if (team.getPrefix().equals(prefix) && team.getSuffix().equals(suffix)) {
                                    continue;
                                }

                                team.setPrefix(prefix);
                                team.setSuffix(suffix);
                            }
                        }
                    }
                }
            }
        }
    }

    private Map.Entry<String, String> getPrefixAndSuffix(String text) {
        String prefix, suffix;

        text = ChatColor.translateAlternateColorCodes('&', text);

        if (text.length() > 16) {
            prefix = text.substring(0, 16);
            suffix = ChatColor.getLastColors(prefix) + text.substring(16, text.length());
            if (suffix.length() > 16) {
                if (suffix.length() <= 16) {
                    suffix = text.substring(16, text.length());
                } else {
                    suffix = suffix.substring(0, 16);
                }
            }
        } else {
            prefix = text;
            suffix = "";
        }

        return new AbstractMap.SimpleEntry<>(prefix, suffix);
    }
}
