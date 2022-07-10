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

    public ThievingTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client,config,plugin, itemsPlugin);
    }

    public NpcIndicatorsPlugin npcPlugin;

    @Override
    protected void performAction() {
        if(target==null)
            target= findClosestTarget();
        if(target.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation())<=1){
            Utils.click(target,0,0,10);
            Utils.sleep(500);
            repeat = true;

        } else if(Utils.isIdle())
            Utils.click(target,0,0,3);

    }

    @Override
    public void onRenderChecks() {

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
