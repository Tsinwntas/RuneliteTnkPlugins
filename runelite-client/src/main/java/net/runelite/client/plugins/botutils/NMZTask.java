package net.runelite.client.plugins.botutils;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.NPC;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.nightmarezone.NightmareZonePlugin;


public class NMZTask extends FlickPrayerTask {

    public NightmareZonePlugin nmzPlugin_;

    private long lastInteraction;


    public NMZTask(Client client, BotPluginConfig config, BotPluginPlugin plugin) {
        super(client,config,plugin);
        lastInteraction = System.currentTimeMillis();
    }

    @Override
    protected void performAction() {
        if(!nmzPlugin_.isInNightmareZone())
            return;
        if(getInactivity())
            interact();
        if(isPhaseOne())
            super.performAction();
        else if (isPhaseTwo()) {
            performPhaseTwo();
        }
        else if (isPhaseThree()){
            if(Utils.getPrayer()>0)
                super.performAction();
            performPhaseThree();
        }
    }

    private boolean getInactivity() {
        if(isInCombat()){
            lastInteraction = System.currentTimeMillis();
            return false;
        }
        return System.currentTimeMillis() - lastInteraction > 30000;
    }

    private void interact() {
        NPC target = Utils.findClosestTarget();
        if(target != null)
            Utils.click(target,0,0,10);
    }

    private void performPhaseTwo() {
//        while(!Utils.isInventoryShown()){
//            Utils.clickWidget(Utils.getInventoryBottomBarWidget());
//            Utils.sleep(500);
//        }
        int absorptionIndex = -1;
        while((absorptionIndex = Utils.findInInventory(ItemID.ABSORPTION_4))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(200);
        }
        while((absorptionIndex = Utils.findInInventory(ItemID.ABSORPTION_3))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(200);
        }
        while((absorptionIndex = Utils.findInInventory(ItemID.ABSORPTION_2))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(200);
        }
        while((absorptionIndex = Utils.findInInventory(ItemID.ABSORPTION_1))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(200);
        }
        while(Utils.getHealth()>1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(0);
            Utils.sleep(200);
        }
    }

    private void checkPrayer() {
        while(isPrayerInactive()){
            toggleQuickPrayer();
            Utils.sleep(500);
        }
    }

    private void performPhaseThree() {
        while(Utils.getHealth()>1) {
            if(!nmzPlugin_.isInNightmareZone())
                return;
            Utils.clickAtInventorySlot(0);
            Utils.sleep(300);
        }
    }

    private boolean isPhaseOne() {
        return Utils.getHealth()>25 && Utils.getPrayer()>20;
    }

    private boolean isPhaseTwo() {
        return Utils.getHealth()>1;
    }

    private boolean isPhaseThree() {
        return Utils.getHealth()==1;
    }

    @Override
    public void onRenderChecks() {
    }

}
