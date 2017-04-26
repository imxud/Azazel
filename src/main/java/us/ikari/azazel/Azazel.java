package us.ikari.azazel;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
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
        this.tabs = new ConcurrentHashMap<UUID, Tab>();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Azazel(JavaPlugin plugin, TabAdapter adapter) {
        this(plugin);

        this.adapter = adapter;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        tabs.put(event.getPlayer().getUniqueId(), new Tab());
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        tabs.remove(event.getPlayer().getUniqueId());
    }
}
