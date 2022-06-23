package net.runelite.client.plugins.botutils;

import com.google.common.collect.Table;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.TileObject;
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

    //EXTRAS
    private final int TOKEN_ID = 4161;

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
    private boolean locatingTarget;

    public AgilityTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client, config, plugin, itemsPlugin);
        currentState = TaskState.COURSING;
        index = config.obstacleIndex();
        course = config.agilityCourse();
    }

    @Override
    protected void performAction() {
        switch (currentState) {
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
                System.out.println(index + "*****************************************");
                if (client.getLocalPlayer().getWorldLocation().getPlane() == 0 && index != 0) {
                    reset();
                    break;
                }

                moveToTarget(targetTile[0]);
                Utils.sleep();
                Utils.click();
                currentState = TaskState.MOVING;
                break;
            case MOVING:
                if(!locatingTarget) {
                    if (lastPlayerLocation != null && lastPlayerLocation.distanceTo(client.getLocalPlayer().getWorldLocation()) == 0) {
                        System.out.println("STOPPED MOVING");
                        Utils.sleep();
                        lastPlayerLocation = null;
                        currentState = TaskState.TIMED_OUT;
                    }else{
                        lastPlayerLocation = client.getLocalPlayer().getWorldLocation();
                        Utils.sleep(300);
                    }
                    break;
                }
                System.out.println("Locating Target");
            case PICKING_UP_TOKEN:
                if ((timeout -= 500) <= 0) {
                    System.out.println("TIMED OUT!");
                    currentState = TaskState.TIMED_OUT;
                    timeout = 2000;
                }
                break;
            default:
                break;
        }

    }

    private void moveToTarget(TileObject tileObject) {
        if (tileObject.getClickbox() == null || !Utils.isCloseToPlayer(tileObject.getWorldLocation(),15)) {
            locatingTarget = true;
            WorldPoint target = Utils.findClosesTileInPath(tileObject.getWorldLocation(),15);
            Utils.pointAt(Utils.getActualPoint(LocalPoint.fromWorld(client,target)),0,0,5);
        } else {
            locatingTarget=false;
            Utils.pointAt(Utils.getRandomPointInShape(tileObject.getClickbox()));
        }
    }

    @Override
    public void onChatChecks(String msg) {

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
                    pickUpItem(item);
                }
                return;
            }
        }
        if (currentState == TaskState.TIMED_OUT || currentState == TaskState.PICKING_UP_TOKEN) {
            currentState = TaskState.COURSING;
        }
    }

    private void pickUpItem(GroundItem item) {
        Utils.pointAt(Utils.getActualPoint(LocalPoint.fromWorld(client, item.getLocation())));
        Utils.sleep();
        Utils.click();
        Utils.sleep();
    }

    public void increment() {
        index = (index + 1) % course.obstacles.length;
        Utils.sleep();
//        currentState = TaskState.COURSING;
    }

    public void reset() {
        index = 0;
//        currentState = TaskState.COURSING;
    }

}
