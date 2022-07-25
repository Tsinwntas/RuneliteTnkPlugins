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

    FLICKER("Flick Prayer"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin){
            System.out.println("STARTING FLICK PRAYER");
            return new FlickPrayerTask(client, config, plugin);
        }
    },

    LOOT("Loot"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING LOOT");
            return new LootTask(client, config, plugin, itemsPlugin);
        }
    },

    MOTHERLOAD("Motherload Mine"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin){
            System.out.println("STARTING MOTHERLOAD");
            return new MotherLodeTask(client, config, plugin, itemsPlugin);
        }
    },

    NMZ("NMZ"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin){
            System.out.println("STARTING NMZ");
            return new NMZTask(client, config, plugin);
        }
    },

    SLAYER("Slayer"){
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING SLAYER");
            return new SlayerTask(client, config, plugin, itemsPlugin);
        }
    },

    THIEVING("Thieving") {
        @Override
        public BotTask getTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
            System.out.println("STARTING THIEVING");
            return new ThievingTask(client, config, plugin, itemsPlugin);
        }
    },

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
