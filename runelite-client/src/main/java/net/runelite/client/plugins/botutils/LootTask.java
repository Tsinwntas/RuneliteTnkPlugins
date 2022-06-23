package net.runelite.client.plugins.botutils;

import com.google.common.collect.Table;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.MenuEntry;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItem;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;
import net.runelite.client.util.Text;

public class LootTask extends BotTask {

    private GroundItem target;
    private boolean swapped;

    private enum TaskState {
        CHOOSING_ITEM,
        SWAP_MENUS,
        PICKING_ITEM
    }

    private TaskState currentState;

    LootTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client, config, plugin, itemsPlugin);
        currentState = TaskState.CHOOSING_ITEM;
    }


    @Override
    protected void performAction() {
        System.out.println(currentState.toString() + " " + (target != null ? target.getName() : ""));
        switch (currentState) {
            case CHOOSING_ITEM:
                if (target != null) {
                    currentState = TaskState.SWAP_MENUS;
                }
                break;
            case SWAP_MENUS:
                if (swapped) {
                    Utils.click();
                    currentState = TaskState.PICKING_ITEM;
                }
                break;

            case PICKING_ITEM:
                break;
        }
    }

    private boolean setItemFirstInMenu() {
        Utils.pointAt(Utils.getActualPoint(LocalPoint.fromWorld(client, target.getLocation())));
        Utils.sleep(10);
        MenuEntry[] menuEntries = client.getMenuEntries();
        for (int e = 0; e < menuEntries.length; e++) {
            MenuEntry entry = menuEntries[e];
            String entryOption = Text.removeTags(entry.getOption()).toLowerCase();
            String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase().replaceAll("[ ][(][0-9]+[)]","");
            if (client.getItemDefinition(target.getId()).getName().equalsIgnoreCase(entryTarget) && entryOption.equalsIgnoreCase("take")) {
                if (e == 0)
                    return true;
                else {
                    menuEntries[e] = menuEntries[0];
                    menuEntries[0] = entry;
                    client.setMenuEntries(menuEntries);
                    return true;
                }
            }
        }
        return false;
    }

    private GroundItem findNextInPriority() {
        Table<WorldPoint, Integer, GroundItem> itemSpawns = itemsPlugin.getCollectedGroundItems();
        ItemComposition suspect = null;
        GroundItem gSuspect = null;
        for (GroundItem item : itemSpawns.values()) {
            if (LocalPoint.fromWorld(client, item.getLocation()) == null)
                continue;
            if (Utils.getDistance(client.getLocalPlayer().getLocalLocation(), LocalPoint.fromWorld(client, item.getLocation())) > 100)
                continue;
            ItemComposition itemDefinition = client.getItemDefinition(item.getId());
            if (!itemDefinition.isTradeable()) {
                return item;
            }
            if (itemDefinition.isStackable())
                return item;
            if (suspect == null || itemDefinition.getPrice() > suspect.getPrice()) {
                suspect = itemDefinition;
                gSuspect = item;
            }
        }
        return gSuspect;
    }

    @Override
    public void onChatChecks(String msg) {

    }

    @Override
    public void onRenderChecks() {
        if (currentState == TaskState.CHOOSING_ITEM)
            target = findNextInPriority();
        else if (currentState == TaskState.SWAP_MENUS)
            swapped = setItemFirstInMenu();

    }
}
