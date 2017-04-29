package us.ikari.azazel.tab.example;

import org.bukkit.entity.Player;
import us.ikari.azazel.tab.TabAdapter;
import us.ikari.azazel.tab.TabTemplate;

public class ExampleTabAdapter implements TabAdapter {
    public TabTemplate getTemplate(Player player) {
        return new TabTemplate().left("test").middle("hi").right("anothertest");
    }
}
