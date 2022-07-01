package net.runelite.client.plugins.botutils;

import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.AgilityShortcut;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;
import net.runelite.client.plugins.motherlode.MotherlodePlugin;

import java.util.Arrays;

public class MotherLodeTask extends BotTask {

    private static final int HOPPER_ID = 26674;
    private static final int SACK_ID = 26688;
    private static final int DEPOSIT_BOX = 25937;
    private WorldPoint agilityIn = new WorldPoint(3760, 5670, 0), agilityOut = new WorldPoint(3764, 5671, 0), trapSpot = new WorldPoint(3769, 5668, 0);
    private boolean depositBoxPanelShown_;

    private enum TaskState {
        MINING,
        AGILITY_OUT,
        CLEARING_PATH,
        FILLING,
        UNLOADING,
        BANKING,
        DEPOSIT_ALL,
        CLOSE_BANK,
        AGILITY_IN
    }

    private TaskState currentState;
    private TaskState actualState;

    private TileObject hopper, depositBox, sack, agilityInWall, agilityOutWall;

    public MotherlodePlugin motherlodePlugin;

    public MotherLodeTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client, config, plugin, itemsPlugin);
        changeBothStates(TaskState.AGILITY_IN);
        initialiseValues();
    }

    private void initialiseValues() {
        for (Tile[][] t2d : client.getScene().getTiles()) {
            if (t2d == null)
                continue;
            for (Tile[] t1d : t2d) {
                if (t1d == null)
                    continue;
                for (Tile t : t1d) {
                    if (t == null)
                        continue;
                    for (GameObject g : t.getGameObjects()) {
                        if (g == null)
                            continue;
                        if (agilityIn.distanceTo(g.getWorldLocation()) == 0)
                            agilityInWall = g;
                        else if (agilityOut.distanceTo(g.getWorldLocation()) == 0)
                            agilityOutWall = g;
                        else if (HOPPER_ID == g.getId())
                            hopper = g;
                        else if (SACK_ID == g.getId())
                            sack = g;
                        else if (DEPOSIT_BOX == g.getId())
                            depositBox = g;
                    }
                    if (t.getGroundObject()!=null && t.getGroundObject().getId() == SACK_ID)
                        sack = t.getGroundObject();
                }
            }
        }
    }

    @Override
    protected void performAction() {
        switch (currentState) {
            case MINING:
                if (Utils.isInventoryFull())
                    changeBothStates(TaskState.AGILITY_OUT);
                else if (Utils.isIdle()) {
                    TileObject rock = findClosestRockInPath();
                    if(client.getLocalPlayer().getWorldLocation().distanceTo(trapSpot)==0){
                        Utils.moveToTarget(rock);
                        currentState = TaskState.CLEARING_PATH;
                        return;
                    }
                    TileObject vein = findClosestActiveVain();
                    if (vein.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) > 1 &&
                            rock.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) == 1)
                        Utils.moveToTarget(rock);
                    else
                        Utils.moveToTarget(vein);
                }
                break;
            case AGILITY_OUT:
                if (passedThrough())
                    changeBothStates(TaskState.FILLING);
                else if (Utils.isIdle()) {
                    TileObject rock = findClosestRockInPath();
                    if (agilityOutWall.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) > 1 &&
                            rock.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) == 1)
                        Utils.moveToTarget(rock);
                    else
                        Utils.moveToTarget(agilityOutWall);
                }
                break;
            case CLEARING_PATH:
                if (Utils.isIdle())
                    retrieveState();
                break;
            case FILLING:
                if (Utils.isInventoryFull()){
                    if(Utils.isIdle())
                        Utils.moveToTarget(hopper);
                }
                else
                    changeBothStates(TaskState.UNLOADING);
                break;
            case UNLOADING:
                if (Utils.isInventoryEmpty(5)) {
                    if(Utils.isIdle())
                        Utils.moveToTarget(sack);
                }
                else
                    changeBothStates(TaskState.BANKING);
                break;
            case BANKING:
                if (Utils.isIdle() && !depositBoxPanelShown_)
                    Utils.moveToTarget(depositBox);
                else if (depositBoxPanelShown_)
                    changeBothStates(TaskState.DEPOSIT_ALL);
                break;
            case DEPOSIT_ALL:
                if (Utils.isInventoryEmpty()) {
                    changeBothStates(TaskState.AGILITY_IN);
                } else {
                    // 12582915,12582916
                    Utils.clickWidget(client.getWidget(WidgetID.DEPOSIT_BOX_GROUP_ID, 4));
                    Utils.sleep();
                }
                break;
            case CLOSE_BANK:
                if (depositBoxPanelShown_) {
                    Utils.clickWidget(client.getWidget(WidgetID.DEPOSIT_BOX_GROUP_ID, 1));
                    Utils.sleep();
                } else
                    changeBothStates(TaskState.AGILITY_IN);
                break;
            case AGILITY_IN:
                if (passedThrough())
                    changeBothStates(TaskState.MINING);
                else if (Utils.isIdle())
                    Utils.moveToTarget(agilityInWall);
                break;
        }
    }

    private TileObject findClosestActiveVain() {
        int minDistance = 1000;
        WallObject closestVain = null;
        for (WallObject vein : motherlodePlugin.getVeins()) {
            if (vein.getWorldLocation().getX() < 3765)
                continue;
            if (vein.getWorldLocation().getY() > 5674 && vein.getWorldLocation().getX() < 3767)
                continue;
            if (vein.getWorldLocation().getY() < 5657)
                continue;
            int distance = vein.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) + vein.getWorldLocation().distanceTo(agilityOut);
            if (distance < minDistance) {
                minDistance = distance;
                closestVain = vein;
            }
        }
        return closestVain;
    }

    private TileObject findClosestRockInPath() {
        int minDistance = 1000;
        GameObject closestRock = null;
        for (GameObject rock : motherlodePlugin.getRocks()) {
            int distance = rock.getWorldLocation().distanceTo(client.getLocalPlayer().getWorldLocation());
            if (distance < minDistance) {
                minDistance = distance;
                closestRock = rock;
            }
        }
        return closestRock;
    }

    private boolean passedThrough() {
        return client.getLocalPlayer().getWorldLocation().distanceTo(currentState == TaskState.AGILITY_IN ? agilityOut : agilityIn) == 1;
    }

    private void retrieveState() {
        currentState = actualState;
    }

    private void changeBothStates(TaskState state) {
        actualState = state;
        currentState = state;
    }

    @Override
    public void onRenderChecks() {
        depositBoxPanelShown_ = client.getWidget(WidgetID.DEPOSIT_BOX_GROUP_ID, 0) != null && !client.getWidget(WidgetID.DEPOSIT_BOX_GROUP_ID, 0).isHidden();
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        event.getMessage().contentEquals("Can't reach that!");
        {
            Utils.moveToTarget(findClosestRockInPath());
            currentState = TaskState.CLEARING_PATH;
            Utils.sleep();
        }
    }

}
