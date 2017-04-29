package us.ikari.azazel.tab;

import lombok.Getter;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Tab {

    private Scoreboard scoreboard;
    private Map<TabEntryPosition, String> entries;

    public Tab(Player player, boolean hook) {
        this.entries = new ConcurrentHashMap<>();

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        if (hook && !player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            scoreboard = player.getScoreboard();
        } else {
            player.setScoreboard(scoreboard);
        }

        initialize(player);
    }

    public Set<TabEntryPosition> getPositions() {
        return entries.keySet();
    }

    public Team getByLocation(int x, int y) {
        for (TabEntryPosition position : entries.keySet()) {
            if (position.getX() == x && position.getY() == y) {
                return scoreboard.getTeam(position.getKey());
            }
        }
        return null;
    }

    private void initialize(Player player) {
        for (int i = 0; i < 60; i++) {
            int x = i % 3;
            int y = i / 3;

            TabEntryPosition position = new TabEntryPosition(x, y, scoreboard);
            entries.put(position, getNextBlank());

            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(getPlayerPacket(entries.get(position)));

            Team team = scoreboard.registerNewTeam(position.getKey());
            team.addEntry(entries.get(position));
        }
    }

    private String getNextBlank() {
        outer: for (String blank : getBlanks()) {
            for (String identifier : entries.values()) {
                if (identifier.equals(blank)) {
                    continue outer;
                }
            }
            return blank;
        }
        return null;
    }

    private List<String> getBlanks() {
        List<String> toReturn = new ArrayList<>();
        for (ChatColor chatColor : ChatColor.values()) {

            String identifier = chatColor + "" + ChatColor.RESET;
            if (scoreboard.getTeam(identifier) != null) {
                continue;
            }

            toReturn.add(identifier);
            for (ChatColor chatColor1 : ChatColor.values()) {

                if (toReturn.size() >= 60) {
                    return toReturn;
                }

                identifier = chatColor + "" + chatColor1 + ChatColor.RESET;
                if (scoreboard.getTeam(identifier) != null) {
                    continue;
                }

                toReturn.add(identifier);
            }
        }

        return toReturn;
    }

    private static PacketPlayOutPlayerInfo getPlayerPacket(String name) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();

        Field action;
        Field username;
        try {
            action = PacketPlayOutPlayerInfo.class.getDeclaredField("action");
            username = PacketPlayOutPlayerInfo.class.getDeclaredField("username");

            action.setAccessible(true);
            username.setAccessible(true);
            action.set(packet, 3);
            username.set(packet, name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return packet;
    }

    public static class TabEntryPosition {
        @Getter private final int x, y;
        @Getter private final String key;

        public TabEntryPosition(int x, int y, Scoreboard scoreboard) {
            this.x = x;
            this.y = y;
            this.key = UUID.randomUUID().toString().substring(0, 16);
        }
    }

}
