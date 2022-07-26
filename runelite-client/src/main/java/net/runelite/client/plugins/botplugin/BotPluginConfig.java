/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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

import net.runelite.client.config.*;
import net.runelite.client.plugins.botutils.AgilityTask;
import net.runelite.client.plugins.botutils.TaskType;

import java.awt.*;

@ConfigGroup("npcindicators")
public interface BotPluginConfig extends Config
{
	@ConfigSection(
			name = "Botting",
			description = "The Bot to use",
			position = 0
	)
	String bottingSection = "bottingSection";

	@ConfigItem(
			position = 0,
			keyName = "bottingType",
			name = "Botting Type",
			description = "Botting Type",
			section = bottingSection
	)
	default TaskType bottingType() {
		return TaskType.NONE;
	}

	@ConfigItem(
			position = 1,
			keyName = "dev",
			name = "Developer Mode",
			description = "Perform only some actions.",
			section = bottingSection
	)
	default boolean isDev() {
		return false;
	}

	@ConfigSection(
			name = "Agility",
			description = "",
			position = 1
	)
	String agilitySection = "agilitySection";

	@ConfigItem(
			position = 0,
			keyName = "agilityCourse",
			name = "Course",
			description = "Choose which course.",
			section = agilitySection
	)
	default AgilityTask.AgilityCourse agilityCourse() {
		return AgilityTask.AgilityCourse.CANIFIS;
	}

	@ConfigItem(
			position = 1,
			keyName = "obstacleIndex",
			name = "Starting Obstacle",
			description = "Choose from which obstacle to start(1 isn't always the first)",
			section = agilitySection
	)
	default int obstacleIndex() {
		return 0;
	}

	@ConfigSection(
			name = "Flick Prayer",
			description = "",
			position = 1
	)
	String flickSection = "FlickPrayerSection";

	@ConfigItem(
			position = 0,
			keyName = "doubleClick",
			name = "Double Click Speed",
			description = "Time to sleep between the two clicks in the double click.",
			section = flickSection
	)
	default int doubleClick() {
		return 70;
	}

	@ConfigItem(
			position = 0,
			keyName = "offset",
			name = "Acceptable Offset",
			description = "Maximum acceptable offset for randomizing.",
			section = flickSection
	)
	default int offset() {
		return 10;
	}

	@ConfigItem(
			position = 0,
			keyName = "flickSpeed",
			name = "Flick Speed",
			description = "Time to sleep between flicks.",
			section = flickSection
	)
	default int flickSpeed() {
		return 400;
	}

	@ConfigItem(
			position = 0,
			keyName = "forceClick",
			name = "Force Click",
			description = "Check if prayer status changed or force the click any way.",
			section = flickSection
	)
	default boolean forceClick() {
		return false;
	}

	@ConfigSection(
			name = "NMZ",
			description = "",
			position = 1
	)
	String nmzSection = "NMZSection";

	@ConfigItem(
			position = 0,
			keyName = "useSpecials",
			name = "Use Specials",
			description = "Use Specials if value >= X",
			section = nmzSection
	)
	default int useSpecials() {
		return 100;
	}

	@ConfigItem(
			position = 1,
			keyName = "usePowerUps",
			name = "Use Special Power ups",
			description = "Use Specials Attack Orbs",
			section = nmzSection
	)
	default boolean chaseOrbs() {
		return false;
	}

	@ConfigSection(
			name = "Slayer",
			description = "",
			position = 1
	)
	String slayerSection = "slayerSection";

	@ConfigItem(
			position = 12,
			keyName = "slayerRockslugs",
			name = "Salt Rockslugs",
			description = "Use salt on slayer monster highlighted",
			section = slayerSection
	)
	default boolean slayerRockslugs() {
		return false;
	}
}