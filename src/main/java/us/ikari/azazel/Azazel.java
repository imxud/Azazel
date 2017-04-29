package us.ikari.azazel;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import us.ikari.azazel.tab.Tab;
import us.ikari.azazel.tab.TabAdapter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Azazel implements Listener {

    private final JavaPlugin plugin;
    private final Map<UUID, Tab> tabs;
    @Getter @Setter private TabAdapter adapter;

    public Azazel(JavaPlugin plugin) {
        this.plugin = plugin;
        this.tabs = new ConcurrentHashMap<>();

        if (Bukkit.getMaxPlayers() < 60) {
            Bukkit.getLogger().severe("There aren't 60 player slots, this will fuck up the tab list."); //TODO: Possibly set max players to 60?
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 47) {
                if (!(tabs.containsKey(player.getUniqueId()))) {
                    tabs.put(player.getUniqueId(), new Tab(player, true));
                }
            }
        }

        new AzazelTask(this, plugin);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Azazel(JavaPlugin plugin, TabAdapter adapter) {
        this(plugin);

        this.adapter = adapter;
    }

    public Tab getTabByPlayer(Player player) {
        return tabs.get(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (((CraftPlayer)event.getPlayer()).getHandle().playerConnection.networkManager.getVersion() < 47) {
            tabs.put(event.getPlayer().getUniqueId(), new Tab(event.getPlayer(), true));
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        tabs.remove(event.getPlayer().getUniqueId());
    }
}
