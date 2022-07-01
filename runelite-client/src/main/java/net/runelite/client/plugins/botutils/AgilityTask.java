package net.runelite.client.plugins.botutils;

import com.google.common.collect.Table;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.agility.AgilityPlugin;
import net.runelite.client.plugins.agility.Obstacle;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItem;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;

import static net.runelite.api.ObjectID.*;


public class AgilityTask extends BotTask {

    public static enum AgilityCourse {
        CANIFIS("Canifis", new int[]{TALL_TREE_14843, GAP_14844, GAP_14845, GAP_14848, GAP_14846, POLEVAULT, GAP_14847, GAP_14897}),

        SEERS("Seers", new int[]{WALL_14927, GAP_14928, TIGHTROPE_14932, GAP_14929, GAP_14930, EDGE_14931}),

        POLLIVNEACH("Polli", new int[]{BASKET_14935, MARKET_STALL_14936, BANNER_14937, GAP_14938, TREE_14939, ROUGH_WALL_14940,
                MONKEYBARS, TREE_14944, DRYING_LINE}),

        RELLEKA("Relleka", new int[]{ROUGH_WALL_14946, GAP_14947, TIGHTROPE_14987, GAP_14990, GAP_14991, TIGHTROPE_14992, PILE_OF_FISH}),

        ARDOUGNE("Ardougne", new int[]{WOODEN_BEAMS, GAP_15609, PLANK_26635, GAP_15610, GAP_15611, STEEP_ROOF, GAP_15612})
        ;


        private String label;
        private int[] obstacles;

        AgilityCourse(String label, int[] obstacles) {
            this.label = label;
            this.obstacles = obstacles;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public AgilityPlugin agilityPlugin;

    private enum TaskState {
        COURSING,
        PICKING_UP_TOKEN,
        MOVING,
        TIMED_OUT
    }

    private TaskState currentState;
    private int index;
    private AgilityCourse course;
    private WorldPoint lastPlayerLocation;

    public AgilityTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client, config, plugin, itemsPlugin);
        currentState = TaskState.COURSING;
        index = config.obstacleIndex();
        course = config.agilityCourse();
    }

    @Override
    protected void performAction() {
        Tile mark = null;
        if(currentState != TaskState.MOVING && agilityPlugin.getMarksOfGrace().size()>0) {
            mark = agilityPlugin.getMarksOfGrace().get(0);
            if(Utils.isCloseToPlayer(mark.getWorldLocation(),10) && mark.getWorldLocation().getPlane()==client.getLocalPlayer().getWorldLocation().getPlane())
                currentState = TaskState.PICKING_UP_TOKEN;
        }
        switch (currentState) {
            case PICKING_UP_TOKEN:
                Utils.moveToTarget(mark.getItemLayer());
                currentState = TaskState.MOVING;
                break;
            case COURSING:
                TileObject[] targetTile = new TileObject[1];
                Obstacle[] targetObstacle = new Obstacle[1];
                agilityPlugin.getObstacles().forEach((object, obstacle) ->
                {
                    if (object.getId() == course.obstacles[index]) {
                        targetTile[0] = object;
                        targetObstacle[0] = obstacle;
                    }

                });
                if (currentState == TaskState.PICKING_UP_TOKEN)
                    break;
                if (client.getLocalPlayer().getWorldLocation().getPlane() == 0 && index != 0) {
                    reset();
                    break;
                }

                Utils.moveToTarget(targetTile[0]);
                currentState = TaskState.MOVING;
                break;
            case MOVING:
                if(Utils.isIdle())
                    currentState = TaskState.COURSING;
//                if (lastPlayerLocation != null && lastPlayerLocation.distanceTo(client.getLocalPlayer().getWorldLocation()) == 0) {
//                    Utils.sleep();
//                    lastPlayerLocation = null;
//                    currentState = TaskState.COURSING;
//                }else{
//                    lastPlayerLocation = client.getLocalPlayer().getWorldLocation();
//                    Utils.sleep(300);
//                }
                break;
            case TIMED_OUT:
                if ((timeout -= 500) <= 0) {
                    currentState = TaskState.COURSING;
                    timeout = 2000;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onRenderChecks() {
        //redo
        Table<WorldPoint, Integer, GroundItem> itemSpawns = itemsPlugin.getCollectedGroundItems();
//        }
        for (GroundItem item : itemSpawns.values()) {
            if (LocalPoint.fromWorld(client, item.getLocation()) == null)
                continue;
            if (Utils.getDistance(client.getLocalPlayer().getLocalLocation(), LocalPoint.fromWorld(client, item.getLocation())) > 1000)
                continue;
            ItemComposition itemDefinition = client.getItemDefinition(item.getId());
            if (itemDefinition.getId() == ItemID.MARK_OF_GRACE) {
                if (currentState != TaskState.PICKING_UP_TOKEN) {
                    currentState = TaskState.PICKING_UP_TOKEN;
                    Utils.pickUpItem(item);
                }
                return;
            }
        }
        if (currentState == TaskState.TIMED_OUT || currentState == TaskState.PICKING_UP_TOKEN) {
            currentState = TaskState.COURSING;
        }
    }

    public void increment() {
        index = (index + 1) % course.obstacles.length;
        Utils.sleep();
    }

    public void reset() {
        index = 0;
    }

}
