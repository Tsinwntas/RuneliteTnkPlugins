package net.runelite.client.plugins.botutils;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;
import net.runelite.client.plugins.npchighlight.NpcIndicatorsPlugin;


public class ThievingTask extends BotTask {
    private NPC target;
    private int coinPouch_;
    private int dodge_;

    private static final int pouch = 22531;

    public ThievingTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client,config,plugin, itemsPlugin);
        coinPouch_ = -1;
        dodge_ = -1;
    }

    public NpcIndicatorsPlugin npcPlugin;

    @Override
    protected void performAction() {
        if(Utils.getHealth()<10)
            return;
        if(coinPouch_ != -1) {
            Utils.clickAtInventorySlot(coinPouch_);
            coinPouch_ = -1;
            Utils.sleep();
            repeat=false;
            return;
        }
        if(dodge_ != -1) {
            Utils.clickAtInventorySlot(dodge_);
            dodge_ =  -1;
            Utils.sleep();
            repeat=false;
            return;
        }

        if(target==null)
            target= findClosestTarget();
        if(target.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation())<=1){
            Utils.click(target,0,0,10);
            Utils.sleep(500);
            repeat = true;

        } else if(Utils.isIdle())
            Utils.click(target,0,0,3);

    }

    private boolean hasFullSack() {
        return Utils.getInventoryItem(coinPouch_).getQuantity()<28;
    }

    @Override
    public void onRenderChecks() {
        coinPouch_ = Utils.findInInventory("Coin Pouch");
        if(coinPouch_ != -1 && hasFullSack())
            coinPouch_ = -1;
        if(!isWearingDodgies())
            dodge_ = Utils.findInInventory("Dodgy Necklace");
    }

    private boolean isWearingDodgies() {
        return Utils.findInEquipment("Dodgy Necklace") != -1;
    }

    private NPC findClosestTarget() {
        if (npcPlugin.getHighlightedNpcs().isEmpty())
            return null;
        LocalPoint playerLoc = client.getLocalPlayer().getLocalLocation();
        NPC closest = null;
        for (NPC npc : npcPlugin.getHighlightedNpcs().keySet()) {
            if (npc.isDead())
                continue;
            if (isClosest(playerLoc, closest, npc))
                closest = npc;
        }
        return closest;
    }

    private boolean isClosest(LocalPoint playerLoc, NPC closest, NPC npc) {
        return closest == null || Utils.getDistance(playerLoc, npc.getLocalLocation())
                < Utils.getDistance(playerLoc, closest.getLocalLocation());
    }

}
