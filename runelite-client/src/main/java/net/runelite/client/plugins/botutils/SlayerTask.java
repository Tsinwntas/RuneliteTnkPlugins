package net.runelite.client.plugins.botutils;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;
import net.runelite.client.plugins.npchighlight.NpcIndicatorsPlugin;

import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SlayerTask extends BotTask {

    private static final Pattern CHAT_GEM_PROGRESS_MESSAGE = Pattern.compile("^(?:You're assigned to kill|You have received a new Slayer assignment from .*:) (?:[Tt]he )?(?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>[^;]+))?(?:; only | \\()(?<amount>\\d+)(?: more to go\\.|\\))$");
    private static final Pattern NPC_CURRENT_MESSAGE = Pattern.compile("^You're (?:still(?: meant to be)?|currently assigned to) (?:hunting|bringing balance to|kill|bring balance to|slaying) (?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>.+))?(?:, with|; (?:you have|only)) (?<amount>\\d+)(?: more)? to go\\..*");

    //EXTRAS
    private final int SALT_ID = 4161;

    private NPC lastTarget;
    private NPC target;

    private enum TaskState {
        IDLE,
        IN_COMBAT,

        //EXTRAS
        ADDING_SALT
    }

    private TaskState currentState;

    public SlayerTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client,config,plugin, itemsPlugin);
        currentState = TaskState.IDLE;
    }

    public NpcIndicatorsPlugin npcPlugin;

    @Override
    protected void performAction() {
        switch (currentState) {
            case IDLE:
                target = findClosestTarget();
                if (target == null)
                    break;
                lastTarget = target;
                Utils.click(target, 0, -5, 0);
                currentState = TaskState.IN_COMBAT;
                Utils.sleep(1000);
                break;
            case IN_COMBAT:
                if (!isInteractionCorrect() || target.isDead()) {
                    if (target.isDead()) {
                        checkExtra();
                    }
                    if (currentState == TaskState.IN_COMBAT) {
                        target = null;
                        currentState = TaskState.IDLE;
                    }
                }
                break;

            //EXTRAS
            case ADDING_SALT:
                if ((timeout -= 500) <= 0) {
                    currentState = TaskState.IN_COMBAT;
                    timeout = 2000;
                }
                break;
        }

    }

    @Override
    public void onChatChecks(String msg) {
        System.out.println(msg);
        if (config.slayerRockslugs())
            setSaltAdded(msg);
        if (msg.toLowerCase().contains("return to a slayer master"))
            finish();

    }

    @Override
    public void onRenderChecks() {

    }

    private boolean isInteractionCorrect() {
        if (client.getLocalPlayer().getInteracting() == null)
            return false;
        if (!client.getLocalPlayer().getInteracting().getName().equalsIgnoreCase(target.getName())) {
            repeat = true;
            return false;
        }
        return true;
    }

    private void checkExtra() {
        if (config.slayerRockslugs()) {
            useSaltOnTarget();
        }
    }

    private void setSaltAdded(String msg) {
        if (msg.contains("The rockslug shrivels up and dies.") || msg.contains("Nothing happens") || msg.contains("The rockslug isn't weak enough to be affected by the salt")) {
            Utils.sleep(300);
            target = null;
            currentState = TaskState.IDLE;
        }
    }

    private void useSaltOnTarget() {
        currentState = TaskState.ADDING_SALT;
        Item[] items = client.getItemContainer(InventoryID.INVENTORY).getItems();
        findSalt(items);
        Utils.click();
        Utils.sleep(700);
        Utils.click(target, 0, 20, 0);
    }

    private void findSalt(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId() == SALT_ID) {
                Utils.pointAtInventorySlot(i, 3);
                break;
            }
        }
    }

    private NPC findClosestTarget() {
        if (npcPlugin.getHighlightedNpcs().isEmpty())
            return null;
        LocalPoint playerLoc = client.getLocalPlayer().getLocalLocation();
        NPC closest = null;
        boolean ignoreLast = false;
        if (npcsShareId())
            ignoreLast = true;
        for (NPC npc : npcPlugin.getHighlightedNpcs().keySet()) {
            if(isNpcInteractingWithLocalPlayer(npc)){
                return npc;
            }
            if (npc.getInteracting() != null || npc.isDead())
                continue;
            if (isClosest(playerLoc, closest, npc) && (ignoreLast || isNotSameAsLastTarget(npc)))
                closest = npc;
        }
        return closest;
    }

    private boolean isNpcInteractingWithLocalPlayer(NPC npc) {
        if(npc.getInteracting() == null || npc.isDead())
            return false;
        return npc.getInteracting().getName().contentEquals(client.getLocalPlayer().getName());
    }

    private boolean npcsShareId() {
        return npcPlugin.getHighlightedNpcs().keySet().stream().filter(npc -> npc.getId() == npcPlugin.getHighlightedNpcs().keySet().stream().findFirst().get().getId()).collect(Collectors.toList()).size() > 1;
    }

    private boolean isNotSameAsLastTarget(NPC npc) {
        return lastTarget == null || lastTarget.getId() != npc.getId();
    }

    private boolean isClosest(LocalPoint playerLoc, NPC closest, NPC npc) {
        return closest == null || Utils.getDistance(playerLoc, npc.getLocalLocation())
                < Utils.getDistance(playerLoc, closest.getLocalLocation());
    }

}
