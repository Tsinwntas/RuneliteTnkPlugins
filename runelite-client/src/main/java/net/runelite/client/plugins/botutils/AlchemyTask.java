package net.runelite.client.plugins.botutils;

import net.runelite.api.*;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.botplugin.BotPluginConfig;
import net.runelite.client.plugins.botplugin.BotPluginPlugin;
import net.runelite.client.plugins.grounditems.GroundItemsPlugin;

import java.util.regex.Pattern;


public class AlchemyTask extends BotTask {

    private static final Pattern CHAT_GEM_PROGRESS_MESSAGE = Pattern.compile("^(?:You're assigned to kill|You have received a new Slayer assignment from .*:) (?:[Tt]he )?(?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>[^;]+))?(?:; only | \\()(?<amount>\\d+)(?: more to go\\.|\\))$");
    private static final Pattern NPC_CURRENT_MESSAGE = Pattern.compile("^You're (?:still(?: meant to be)?|currently assigned to) (?:hunting|bringing balance to|kill|bring balance to|slaying) (?<name>.+?)(?: (?:in|on|south of) (?:the )?(?<location>.+))?(?:, with|; (?:you have|only)) (?<amount>\\d+)(?: more)? to go\\..*");

    //EXTRAS
    private final int COINS_ID = 995;
    private final int NAT_ID = 561;

    private boolean inventoryShown;
    private boolean spellbookShown;

    private enum TaskState{
        WAITING_FOR_SPELL_BOOK,
        WAITING_FOR_INVENTORY,
    }

    private TaskState currentState;

    public AlchemyTask(Client client, BotPluginConfig config, BotPluginPlugin plugin, GroundItemsPlugin itemsPlugin) {
        super(client,config,plugin,itemsPlugin);
        currentState = TaskState.WAITING_FOR_SPELL_BOOK;
    }

    @Override
    protected void performAction() {
        if(!hasRunes()){
            finish();
            return;
        }
        switch (currentState) {
            case WAITING_FOR_INVENTORY:
                if(inventoryShown){
                    int index = findNextTarget();
                    if(index == -1)
                        break;
                    Utils.pointAtInventorySlot(index,8);
                    Utils.sleep();
                    Utils.click();
                    currentState = TaskState.WAITING_FOR_SPELL_BOOK;
                }
                break;
            case WAITING_FOR_SPELL_BOOK:
                if(spellbookShown){
                    int index = 34;
                    Utils.pointAtSpellbookSlot(index,3);
                    Utils.sleep();
                    Utils.click();
                    currentState = TaskState.WAITING_FOR_INVENTORY;
                }
                break;
        }

    }

    @Override
    public void onChatChecks(String msg) {

    }

    @Override
    public void onRenderChecks() {
        inventoryShown = !client.getWidget(WidgetInfo.INVENTORY).isHidden();
        spellbookShown = !client.getWidget(WidgetID.SPELLBOOK_GROUP_ID, 0).isHidden();
    }

    private boolean hasRunes() {
        Item[] items = client.getItemContainer(InventoryID.INVENTORY).getItems();
        for(int i =0; i< items.length; i++ ){
            if(items[i].getId() == NAT_ID) {
                return items[i].getQuantity()>0;
            }
        }
        finish();
        return false;
    }

    private int findNextTarget() {
        Item[] items = client.getItemContainer(InventoryID.INVENTORY).getItems();
        for(int i =0; i< items.length; i++ ){
            if(items[i].getId() != -1 && items[i].getId() != COINS_ID && items[i].getId() != NAT_ID) {
                return i;
            }
        }
        finish();
        return -1;
    }

}
