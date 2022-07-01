package net.runelite.client.plugins.botutils;

import net.runelite.api.*;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.plugins.agility.AgilityPlugin;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItem;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;
import net.runelite.client.plugins.motherlode.MotherlodePlugin;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class Utils {

    private static Client client;
    private static GroundItemsPlugin itemsPlugin;
    private static AgilityPlugin agilityPlugin;
    private static MotherlodePlugin motherlodePlugin;
    private static Canvas canvas;
    public static BotTask bot;
    private static Point location;
    private static BotPluginConfig config;

    private static WorldPoint lastPlayerLocation;

//    private Utils(LootManager lootManager) {
////        this.client = client;
////        this.config = config;
////        this.plugin = plugin;
////        this.itemsPlugin = itemsPlugin;
//        this.lootManager = lootManager;
//    }

    public static Point getActualPoint(LocalPoint point) {
        return Perspective.localToCanvas(client, point, client.getPlane());
    }

    public static void pointAt(Point p) {
        pointAt(p, 0, 0, 0);
    }

    public static void pointAt(Point p, int randomOffset) {
        pointAt(p, 0, 0, randomOffset);
    }

    public static void pointAt(Point p, int offsetX, int offsetY, int randomOffset) {
        int randX = (int) (Math.random() * randomOffset) * (Math.random() > 0.5 ? -1 : 1);
        int randY = (int) (Math.random() * randomOffset) * (Math.random() > 0.5 ? -1 : 1);
        location = new Point(p.getX() + offsetX + randX,
                p.getY() + offsetY + randY);
        canvas.dispatchEvent(new MouseEvent(canvas, MouseEvent.MOUSE_MOVED, Instant.now().toEpochMilli(), MouseEvent.NOBUTTON, location.getX(), location.getY(), 0, true));
    }

    public static void click(NPC npc) {
        click(npc, 0, 0, 0);
    }

    public static void click(NPC npc, int randomOffset) {
        click(npc, 0, 0, randomOffset);
    }

    public static void click(NPC npc, int offsetX, int offsetY, int randomOffset) {
        if (npc != null) {
            pointAt(getActualPoint(npc.getLocalLocation()), offsetX, offsetY, randomOffset);
            sleep();
            click();
        }

    }

    public static void click() {
        canvas.dispatchEvent(new MouseEvent(canvas, MouseEvent.MOUSE_PRESSED, Instant.now().toEpochMilli(), MouseEvent.BUTTON1, location.getX(), location.getY(), 1, true));
        sleep(10);
        canvas.dispatchEvent(new MouseEvent(canvas, MouseEvent.MOUSE_RELEASED, Instant.now().toEpochMilli(), MouseEvent.BUTTON1, location.getX(), location.getY(), 1, true));
    }

    public static void sleep() {
        sleep(100);
    }

    public static void sleep(int millis) {
        try {
            int rand = (int) (Math.random() * (millis / 15.0) * (Math.random() > 0.5 ? 1 : -1));
            Thread.sleep(millis + rand);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double getDistance(LocalPoint p1, LocalPoint p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public static void pointAtInventorySlot(int index, int offset) {
        pointAtWidgetSlot(client.getWidget(WidgetInfo.INVENTORY), index, offset);
    }

    public static void pointAtSpellbookSlot(int index, int offset) {
        pointAtWidget(client.getWidget(WidgetID.SPELLBOOK_GROUP_ID, 5 + index), offset);
    }

    public static void pointAtWidgetSlot(Widget w, int index, int offset) {
        WidgetItem slot = w.getWidgetItem(index);
        pointAt(new Point(slot.getCanvasLocation().getX() + slot.getCanvasBounds().width / 2 - 3,
                slot.getCanvasLocation().getY() + slot.getCanvasBounds().height / 2 - 3), offset);
    }

    public static void pointAtWidget(Widget w, int offset) {
        pointAt(new Point(w.getCanvasLocation().getX() + w.getWidth() / 2 + w.getWidth() / 4 - 3,
                w.getCanvasLocation().getY() + w.getHeight() / 4 + 3), offset);
    }

    public static void clickWidget(Widget w){
        clickWidget(w,3);
    }

    public static void clickWidget(Widget w, int offset){
        pointAtWidget(w,offset);
        sleep();
        click();
    }

    public static boolean isCloseToPlayer(WorldPoint currentTarget, int maxDistance) {
        return currentTarget.distanceTo(client.getLocalPlayer().getWorldLocation())<=maxDistance;
    }

    public static WorldPoint findClosesTileInPath(WorldPoint worldLocation, int maxDistance) {
        WorldPoint currentTarget = worldLocation;
        WorldPoint player = client.getLocalPlayer().getWorldLocation();
        while(!isCloseToPlayer(currentTarget,maxDistance)){
            currentTarget = new WorldPoint((int)((Math.random()<0.5 ? -1 : 1) * (Math.random()*3)+(currentTarget.getX()+ player.getX())/2),
                    (int)((Math.random()<0.5 ? -1 : 1) * (Math.random()*3)+((currentTarget.getY()+player.getY())/2)),
                    player.getPlane());
        }
        return currentTarget;
    }

    public static void start(Client client, BotPluginPlugin plugin, BotPluginConfig config) {
        Utils.client = client;
        Utils.config = config;
        canvas = client.getCanvas();
        if (config.bottingType() != TaskType.NONE) {
            bot = Utils.config.bottingType().getTask(client, config, plugin, itemsPlugin);
            extraPlugins(config);
            bot.start();
        }
    }

    public static void extraPlugins(BotPluginConfig config) {
        if (Utils.config.bottingType() == TaskType.AGILITY)
            ((AgilityTask) bot).agilityPlugin = agilityPlugin;
        if (Utils.config.bottingType() == TaskType.MOTHERLOAD)
            ((MotherLodeTask) bot).motherlodePlugin = motherlodePlugin;
    }

    public static void stop() {
        if (bot != null)
            bot.finish();
    }

    public static void passMessage(String msg) {
        if (bot != null)
            bot.onChatChecks(msg);
    }

    public static void setNpcDialog(GameTick event) {
        Widget npcDialog = client.getWidget(WidgetInfo.DIALOG_NPC_TEXT);
        if (bot != null && npcDialog != null) {
//            bot.onChatChecks();
        }
    }

    public static void onRenderChecks() {
        if (bot != null) {
            bot.onRenderChecks();
        }
    }

    public static boolean isIdle() {
        return client.getLocalPlayer().getAnimation() == AnimationID.IDLE && isNotMoving();
//        if(idle_){
//            idle_ = false;
//            return true;
//        }
//        return false;
    }

    public static boolean isNotMoving() {
        if (lastPlayerLocation != null && lastPlayerLocation.distanceTo(client.getLocalPlayer().getWorldLocation()) == 0) {
            sleep();
            lastPlayerLocation = null;
            return true;
        }else{
            lastPlayerLocation = client.getLocalPlayer().getWorldLocation();
            sleep(300);
            return false;
        }
    }

    public static boolean isInventoryFull() {
        return client.getItemContainer(InventoryID.INVENTORY) != null && Arrays.stream(client.getItemContainer(InventoryID.INVENTORY).getItems()).filter(i->i.getId()!=-1).count()==28;
    }

    public static boolean isInventoryEmpty() {
        return isInventoryEmpty(0);
    }

    public static boolean isInventoryEmpty(int leeway) {
        return client.getItemContainer(InventoryID.INVENTORY) == null || Arrays.stream(client.getItemContainer(InventoryID.INVENTORY).getItems()).filter(i->i.getId()==-1).count()>=(client.getItemContainer(InventoryID.INVENTORY).getItems().length-leeway);
    }

    public static void setItemsPlugin(GroundItemsPlugin plugin) {
        Utils.itemsPlugin = plugin;
    }

    public static void setAgilityPlugin(AgilityPlugin plugin) {
        Utils.agilityPlugin = plugin;
    }

    public static void setMotherlodePlugin(MotherlodePlugin plugin) {
        Utils.motherlodePlugin = plugin;
    }

    public static void incrementAgility() {
        if(config.bottingType()==TaskType.AGILITY && bot!=null)
            ((AgilityTask)bot).increment();
    }

    public static void resetAgility() {
        if(config.bottingType()==TaskType.AGILITY && bot!=null)
            ((AgilityTask)bot).reset();

    }

    public static void moveToTarget(TileObject tileObject) {
        if (tileObject.getClickbox() == null || !Utils.isCloseToPlayer(tileObject.getWorldLocation(),15)) {
            WorldPoint target = findClosesTileInPath(tileObject.getWorldLocation(),15);
            pointAt(Utils.getActualPoint(LocalPoint.fromWorld(client,target)),0,0,5);
        } else {
            pointAt(Utils.getRandomPointInShape(tileObject.getClickbox()));
        }
        sleep();
        click();
    }

    public static Point getRandomPointInShape(Shape s) {
        Random random = new Random();
        double randX = 0;
        double randY = 0;
        do {
            randX = random.ints(s.getBounds().x+s.getBounds().width/4, s.getBounds().x + s.getBounds().width-s.getBounds().width/4)
                    .findFirst()
                    .getAsInt();

            randY = random.ints(s.getBounds().y+s.getBounds().height/4, s.getBounds().y + s.getBounds().height-s.getBounds().height/4)
                    .findFirst()
                    .getAsInt();
        } while (!s.contains(randX, randY));
        return new Point((int) randX, (int) randY);
    }

    public static Point getLocation(){
        if(location == null)
            location = new Point(0,0);
        return location;
    }

    public static void pickUpItem(GroundItem item) {
        pointAt(Utils.getActualPoint(LocalPoint.fromWorld(client, item.getLocation())));
        sleep();
        click();
        sleep();
    }
}
