/*
 * Copyright (c) 2018, James Swindle <wilingua@gmail.com>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.botplugin;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.botutils.Utils;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.Text;
import net.runelite.client.util.WildcardMatcher;

import javax.inject.Inject;
import java.awt.*;
import java.time.Instant;
import java.util.List;
import java.util.*;
import java.util.function.Function;

@PluginDescriptor(
		name = "00_Botting",
		description = "Highlight NPCs on-screen and/or on the minimap",
		tags = {"highlight", "minimap", "npcs", "overlay", "respawn", "tags"}
)
@Slf4j
public class BotPluginPlugin extends Plugin
{

	@Inject
	private Client client;

	@Inject
	private BotPluginConfig config;

	@Inject
	private BotPluginSceneOverlay overlay;

	@Inject
	private OverlayManager overlayManager;


	@Provides
	BotPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BotPluginConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		if (client.getGameState() == GameState.LOGGED_IN)
			Utils.start(client,this,config);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		Utils.stop();
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		Utils.setNpcDialog(event);
	}

//    @Subscribe
//    public void onItemSpawned(ItemSpawned itemSpawned) {
//        TileItem item = itemSpawned.getItem();
//        Tile tile = itemSpawned.getTile();
//
//        GroundItem groundItem = buildGroundItem(tile, item);
//
//        GroundItem.GroundItemKey groundItemKey = new GroundItem.GroundItemKey(item.getId(), tile.getWorldLocation());
//        GroundItem existing = collectedGroundItems.putIfAbsent(groundItemKey, groundItem);
//        if (existing != null) {
//            existing.setQuantity(existing.getQuantity() + groundItem.getQuantity());
//            // The spawn time remains set at the oldest spawn
//        }
//
//        if (!config.onlyShowLoot()) {
//            notifyHighlightedItem(groundItem);
//        }
}
