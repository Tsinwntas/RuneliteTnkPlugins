package net.runelite.client.plugins.botutils;

import net.runelite.api.Client;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;

public abstract class BotTask extends Thread {
    protected final Client client;
    protected final BotPluginConfig config;
    protected final BotPluginPlugin plugin;
    protected final GroundItemsPlugin itemsPlugin;

    protected boolean running;
    protected boolean repeat;
    protected int timeout = 2000;

    public BotTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.itemsPlugin = itemsPlugin;
        running = true;
    }

    public void finish() {
        running = false;
    }

    @Override
    public void run(){
        while(running) {
            performAction();
            sleepOrRepeat();
        }
    }

    protected void sleepOrRepeat(){
        if(!repeat)
            Utils.sleep(500);
        else
            repeat = false;
    }

    protected abstract void performAction();

    public void onChatChecks(String msg){};

    public abstract void onRenderChecks();

    public void notifyTask(){};

    public void onGameObjectSpawned(GameObjectSpawned event) {
    }

    public void onGameObjectDespawned(GameObjectDespawned event){}


    public void onGroundObjectSpawned(GroundObjectSpawned event) {
    }

    public void onItemSpawned(ItemSpawned event) {
    }
}
