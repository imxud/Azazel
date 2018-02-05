package com.bizarrealex.azazel.tab.example;

import com.bizarrealex.azazel.tab.TabAdapter;
import com.bizarrealex.azazel.tab.TabTemplate;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ExampleTabAdapter implements TabAdapter {
    public TabTemplate getTemplate(Player player) {
        TabTemplate template = new TabTemplate();

        template.middle("&4&lAzazel Demo");
        template.middle("");
        template.middle(player.getName());

        Location location = player.getLocation();

        template.middle(location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());

        template.left(5, "If you're seeing");
        template.middle(5, "this you probs");
        template.right(5, "didn't set up");
        template.left(6, "Azazel properly");
        template.middle(6, "or maybe you're");
        template.right(6, "just cool. :)");

        template.left(8, "01234567890123456");
        template.middle(8, "01234567890123456789012345678901");
        template.middle(9, "^^ wow 32 chars");

        template.left(18, "DOCUMENTATION");
        template.middle(18, "AVAILABLE AT");
        template.right(18, "LINK BELOW");
        template.left(19, "https://github.com");
        template.middle(19, "/ bizarre / ");
        template.right(19, "azazel");

        return template;
    }
}
