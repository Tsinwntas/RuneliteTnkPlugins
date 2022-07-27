package net.runelite.client.plugins.botutils;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.ItemSpawned;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.nightmarezone.NightmareZonePlugin;


public class NMZTask extends FlickPrayerTask {

    public NightmareZonePlugin nmzPlugin_;
    private TileObject target_;
    private long lastInteraction;
    private long lastOverload;


    public NMZTask(Client client, BotPluginConfig config, BotPluginPlugin plugin) {
        super(client,config,plugin);
        lastInteraction = System.currentTimeMillis();
        lastOverload = 0;
    }

    @Override
    protected void performAction() {
        if(config.isDev()) {
            checkForOverload();
            return;
        }
        if(!nmzPlugin_.isInNightmareZone())
            return;
        if(getInactivity())
            interact();
        checkForOverload();
        if(target_ != null && Utils.getDistance(client.getLocalPlayer().getLocalLocation(), target_.getLocalLocation())>3){
            try {
                Utils.moveToTarget(target_);
                Utils.sleep(1000);
            } catch (Exception e){
                target_ = null;
            }
        }
        if(Utils.getSpec()>=config.useSpecials() && isSpecInactive())
            Utils.clickWidget(client.getWidget(WidgetID.MINIMAP_GROUP_ID, WidgetID.Minimap.SPEC_ORB));

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

    private void checkForOverload() {
        if (isOverloadAcceptable() && Utils.getHealth() > 50) {
            drinkOverload();
        }
    }

    private boolean isOverloadAcceptable() {
        return System.currentTimeMillis() - lastOverload > (4*60*1000);
    }

    private boolean isSpecInactive() {
        return client.getWidget(WidgetID.MINIMAP_GROUP_ID, 37).getSpriteId() == SpriteID.MINIMAP_ORB_SPECIAL;
    }

    private boolean getInactivity() {
        if(isInCombat()){
            lastInteraction = System.currentTimeMillis();
            return false;
        }
        return System.currentTimeMillis() - lastInteraction > 5000;
    }

    private void interact() {
        NPC target = Utils.findClosestTarget(true);
        if(target != null)
            Utils.click(target,0,0,10);
    }

    private void performPhaseTwo() {
//        while(!Utils.isInventoryShown()){
//            Utils.clickWidget(Utils.getInventoryBottomBarWidget());
//            Utils.sleep(500);
//        }

        drinkAbsorbtion();

        while(Utils.getHealth()>1){
            if(!nmzPlugin_.isInNightmareZone())
                return;

            checkPrayer();
            checkForOverload();

            Utils.clickAtInventorySlot(0);
            Utils.sleep(200);
        }
    }

    private void drinkAbsorbtion() {
        int absorptionIndex = -1;
        while((absorptionIndex = Utils.findAnyItem(ItemID.ABSORPTION_1, ItemID.ABSORPTION_2, ItemID.ABSORPTION_3, ItemID.ABSORPTION_4))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(200);
        }
    }

    private void drinkOverload() {
        int absorptionIndex = -1;
        if((absorptionIndex = Utils.findAnyItem(ItemID.OVERLOAD_1, ItemID.OVERLOAD_2, ItemID.OVERLOAD_3, ItemID.OVERLOAD_4))>-1){
            if(!nmzPlugin_.isInNightmareZone())
                return;
            lastOverload = System.currentTimeMillis();
            checkPrayer();
            Utils.clickAtInventorySlot(absorptionIndex);
            Utils.sleep(10000);
        }
    }

    private void checkPrayer() {
        while(isPrayerInactive() && Utils.getPrayer()>0){
            toggleQuickPrayer();
            Utils.sleep(500);
        }
    }

    private void performPhaseThree() {
        while(Utils.getHealth()>1) {
            if(!nmzPlugin_.isInNightmareZone())
                return;
            checkForOverload();
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

    @Override
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        if(event.getGameObject().getId() == ObjectID.POWER_SURGE && config.chaseOrbs())
            target_ = event.getGameObject();
    }

    @Override
    public void onGameObjectDespawned(GameObjectDespawned event)
    {
        if(event.getGameObject().getId() == ObjectID.POWER_SURGE && config.chaseOrbs())
            target_ = null;
    }

    @Override
    public void onGroundObjectSpawned(GroundObjectSpawned event)
    {
        if(event.getGroundObject().getId() == ObjectID.POWER_SURGE && config.chaseOrbs())
            target_ = event.getGroundObject();
    }

}
