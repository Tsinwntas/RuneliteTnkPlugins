package net.runelite.client.plugins.botutils;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;


public class FlickPrayerTask extends BotTask {
    //MINE WORKS FOR 3 10 350 force

    private boolean prayerSupposedToBeActive;
    private TaskState state_;

    private enum TaskState{
        ACTIVATING,
        FLICK
    }

    public FlickPrayerTask(Client client, BotPluginConfig config, BotPluginPlugin plugin) {
        super(client,config,plugin,null);
        state_ = TaskState.ACTIVATING;
    }

    @Override
    protected void performAction() {
        switch (state_){
            case ACTIVATING:
                if(!isInCombat()){
                    if(!isPrayerInactive())
                        toggleQuickPrayer();
                    break;
                }
                if(isPrayerInactive())
                    toggleQuickPrayer();
                prayerSupposedToBeActive = true;
                state_ = TaskState.FLICK;
                break;
            case FLICK:
                if(!isInCombat()){
                    Utils.sleep(500);
                    state_=TaskState.ACTIVATING;
                    break;
                }
                repeat = true;
                if(prayerSupposedToBeActive && (config.forceClick() || !isPrayerInactive())){
                    flick(false, config.doubleClick());
                } else if (!prayerSupposedToBeActive && (config.forceClick() || isPrayerInactive())) {
                    flick(true, config.flickSpeed());
                }
                Utils.sleep(5);
        }
    }

    private boolean isInCombat() {
        return client.getLocalPlayer().getInteracting() != null;
    }

    private void flick(boolean b, int i) {
        toggleQuickPrayer();
        prayerSupposedToBeActive = b;
        Utils.sleep(i, config.offset());
    }

    private boolean isPrayerInactive() {
        return getQuickPrayerWidget().getActions()[0].contentEquals("Activate");
    }

    private void toggleQuickPrayer() {
        Utils.clickWidget(getQuickPrayerWidget());
    }

    private Widget getQuickPrayerWidget() {
        return client.getWidget(WidgetID.MINIMAP_GROUP_ID, WidgetID.Minimap.QUICK_PRAYER_ORB);
    }

    @Override
    public void onRenderChecks() {
    }

}
