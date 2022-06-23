package net.runelite.client.plugins.botutils;

import net.runelite.api.Client;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;

public enum TaskType {
    NONE("None") {
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            return null;
        }
    },

    SLAYER("Slayer"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING SLAYER");
            return new SlayerTask(client, config, plugin, itemsPlugin);
        }
    },

    AGILITY("Agility"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING AGILITY");
            return new AgilityTask(client, config, plugin, itemsPlugin);
        }
    },

    ALCHEMY("Alchemy") {
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING ALCHEMY");
            return new AlchemyTask(client, config, plugin, itemsPlugin);
        }
    },

    LOOT("Loot"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING LOOT");
            return new LootTask(client, config, plugin, itemsPlugin);
        }
    }

    ;

    private String label;

    TaskType(String label){
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public abstract BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin);
}
