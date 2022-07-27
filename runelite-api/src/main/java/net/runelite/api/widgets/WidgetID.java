/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.api.widgets;

/**
 * Utility class mapping widget IDs to global constants.
 * <p>
 * The constants defined directly under the {@link WidgetID} class are
 * Widget group IDs. All child IDs are defined in sub-classes relating
 * to their group.
 * <p>
 * For a more direct group-child widget mapping, use the
 * {@link WidgetInfo} enum class.
 */
public final class WidgetID
{
	public static final int FAIRY_RING_PANEL_GROUP_ID = 381;
	public static final int FAIRY_RING_GROUP_ID = 398;
	public static final int LOGOUT_PANEL_ID = 182;
	public static final int BANK_GROUP_ID = 12;
	public static final int BANK_INVENTORY_GROUP_ID = 15;
	public static final int GRAND_EXCHANGE_INVENTORY_GROUP_ID = 467;
	public static final int GRAND_EXCHANGE_GROUP_ID = 465;
	public static final int DEPOSIT_BOX_GROUP_ID = 192;
	public static final int INVENTORY_GROUP_ID = 149;
	public static final int PLAYER_TRADE_SCREEN_GROUP_ID = 335;
	public static final int PLAYER_TRADE_INVENTORY_GROUP_ID = 336;
	public static final int FRIENDS_LIST_GROUP_ID = 429;
	public static final int IGNORE_LIST_GROUP_ID = 432;
	public static final int RAIDING_PARTY_GROUP_ID = 500;
	public static final int EQUIPMENT_GROUP_ID = 387;
	public static final int EQUIPMENT_INVENTORY_GROUP_ID = 85;
	public static final int EMOTES_GROUP_ID = 216;
	public static final int RUNE_POUCH_GROUP_ID = 190;
	public static final int ACHIEVEMENT_DIARY_GROUP_ID = 259;
	public static final int PEST_CONTROL_BOAT_GROUP_ID = 407;
	public static final int PEST_CONTROL_GROUP_ID = 408;
	public static final int FRIENDS_CHAT_GROUP_ID = 7;
	public static final int MINIMAP_GROUP_ID = 160;
	public static final int LOGIN_CLICK_TO_PLAY_GROUP_ID = 378;
	public static final int CLUE_SCROLL_GROUP_ID = 203;
	public static final int FIXED_VIEWPORT_GROUP_ID = 548;
	public static final int RESIZABLE_VIEWPORT_OLD_SCHOOL_BOX_GROUP_ID = 161;
	public static final int RESIZABLE_VIEWPORT_BOTTOM_LINE_GROUP_ID = 164;
	public static final int PRAYER_GROUP_ID = 541;
	public static final int QUICK_PRAYERS_GROUP_ID = 77;
	public static final int SHOP_GROUP_ID = 300;
	public static final int SHOP_INVENTORY_GROUP_ID = 301;
	public static final int SMITHING_GROUP_ID = 312;
	public static final int GUIDE_PRICES_GROUP_ID = 464;
	public static final int GUIDE_PRICES_INVENTORY_GROUP_ID = 238;
	public static final int COMBAT_GROUP_ID = 593;
	public static final int DIALOG_NPC_GROUP_ID = 231;
	public static final int SLAYER_REWARDS_GROUP_ID = 426;
	public static final int PRIVATE_CHAT = 163;
	public static final int CHATBOX_GROUP_ID = 162;
	public static final int VOLCANIC_MINE_GROUP_ID = 611;
	public static final int BA_ATTACKER_GROUP_ID = 485;
	public static final int BA_COLLECTOR_GROUP_ID = 486;
	public static final int BA_DEFENDER_GROUP_ID = 487;
	public static final int BA_HEALER_GROUP_ID = 488;
	public static final int BA_REWARD_GROUP_ID = 497;
	public static final int BA_TEAM_GROUP_ID = 256;
	public static final int LEVEL_UP_GROUP_ID = 233;
	public static final int DIALOG_SPRITE_GROUP_ID = 193;
	public static final int QUEST_COMPLETED_GROUP_ID = 153;
	public static final int CLUE_SCROLL_REWARD_GROUP_ID = 73;
	public static final int BARROWS_REWARD_GROUP_ID = 155;
	public static final int RAIDS_GROUP_ID = 513;
	public static final int TOB_GROUP_ID = 28;
	public static final int MOTHERLODE_MINE_GROUP_ID = 382;
	public static final int EXPERIENCE_DROP_GROUP_ID = 122;
	public static final int PUZZLE_BOX_GROUP_ID = 306;
	public static final int LIGHT_BOX_GROUP_ID = 322;
	public static final int NIGHTMARE_ZONE_GROUP_ID = 202;
	public static final int NIGHTMARE_PILLAR_HEALTH_GROUP_ID = 413;
	public static final int BLAST_FURNACE_GROUP_ID = 474;
	public static final int WORLD_MAP_GROUP_ID = 595;
	public static final int PYRAMID_PLUNDER_GROUP_ID = 428;
	public static final int CHAMBERS_OF_XERIC_REWARD_GROUP_ID = 539;
	public static final int THEATRE_OF_BLOOD_REWARD_GROUP_ID = 23;
	public static final int EXPERIENCE_TRACKER_GROUP_ID = 122;
	public static final int TITHE_FARM_GROUP_ID = 241;
	public static final int KINGDOM_GROUP_ID = 616;
	public static final int BARROWS_GROUP_ID = 24;
	public static final int BLAST_MINE_GROUP_ID = 598;
	public static final int MTA_ALCHEMY_GROUP_ID = 194;
	public static final int MTA_ENCHANTMENT_GROUP_ID = 195;
	public static final int MTA_GRAVEYARD_GROUP_ID = 196;
	public static final int MTA_TELEKINETIC_GROUP_ID = 198;
	public static final int CORP_DAMAGE = 13;
	public static final int DESTROY_ITEM_GROUP_ID = 584;
	public static final int VARROCK_MUSEUM_QUIZ_GROUP_ID = 533;
	public static final int KILL_LOGS_GROUP_ID = 549;
	public static final int DIARY_QUEST_GROUP_ID = 119;
	public static final int THEATRE_OF_BLOOD_GROUP_ID = 23;
	public static final int WORLD_SWITCHER_GROUP_ID = 69;
	public static final int DIALOG_OPTION_GROUP_ID = 219;
	public static final int DIALOG_PLAYER_GROUP_ID = 217;
	public static final int DRIFT_NET_FISHING_REWARD_GROUP_ID = 607;
	public static final int FOSSIL_ISLAND_OXYGENBAR_ID = 609;
	public static final int MINIGAME_TAB_ID = 76;
	public static final int SPELLBOOK_GROUP_ID = 218;
	public static final int PVP_GROUP_ID = 90;
	public static final int FISHING_TRAWLER_GROUP_ID = 366;
	public static final int FISHING_TRAWLER_REWARD_GROUP_ID = 367;
	public static final int ZEAH_MESS_HALL_GROUP_ID = 235;
	public static final int KOUREND_FAVOUR_GROUP_ID = 246;
	public static final int LOOTING_BAG_GROUP_ID = 81;
	public static final int SKOTIZO_GROUP_ID = 308;
	public static final int ENTERING_HOUSE_GROUP_ID = 71;
	public static final int FULLSCREEN_CONTAINER_TLI = 165;
	public static final int QUESTLIST_GROUP_ID = 399;
	public static final int SKILLS_GROUP_ID = 320;
	public static final int MUSIC_GROUP_ID = 239;
	public static final int BARROWS_PUZZLE_GROUP_ID = 25;
	public static final int KEPT_ON_DEATH_GROUP_ID = 4;
	public static final int GUIDE_PRICE_GROUP_ID = 464;
	public static final int SEED_VAULT_INVENTORY_GROUP_ID = 630;
	public static final int BEGINNER_CLUE_MAP_CHAMPIONS_GUILD = 346;
	public static final int BEGINNER_CLUE_MAP_VARROCK_EAST_MINE = 347;
	public static final int BEGINNER_CLUE_MAP_DRAYNOR = 348;
	public static final int BEGINNER_CLUE_MAP_NORTH_OF_FALADOR = 351;
	public static final int BEGINNER_CLUE_MAP_WIZARDS_TOWER = 356;
	public static final int SEED_BOX_GROUP_ID = 128;
	public static final int SEED_VAULT_GROUP_ID = 631;
	public static final int EXPLORERS_RING_ALCH_GROUP_ID = 483;
	public static final int SETTINGS_SIDE_GROUP_ID = 116;
	public static final int SETTINGS_GROUP_ID = 134;
	public static final int GWD_KC_GROUP_ID = 406;
	public static final int LMS_GROUP_ID = 333;
	public static final int LMS_INGAME_GROUP_ID = 328;
	public static final int ADVENTURE_LOG_ID = 187;
	public static final int COLLECTION_LOG_ID = 621;
	public static final int GENERIC_SCROLL_GROUP_ID = 625;
	public static final int GAUNTLET_TIMER_GROUP_ID = 637;
	public static final int HALLOWED_SEPULCHRE_TIMER_GROUP_ID = 668;
	public static final int BANK_PIN_GROUP_ID = 213;
	public static final int HEALTH_OVERLAY_BAR_GROUP_ID = 303;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_PRIVATE_GROUP_ID = 271;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_SHARED_GROUP_ID = 550;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_INVENTORY_GROUP_ID = 551;
	public static final int DUEL_INVENTORY_GROUP_ID = 421;
	public static final int DUEL_INVENTORY_OTHER_GROUP_ID = 481;
	public static final int TRAILBLAZER_AREAS_GROUP_ID = 512;
	public static final int TEMPOROSS_GROUP_ID = 437;
	public static final int CLAN_GROUP_ID = 701;
	public static final int CLAN_GUEST_GROUP_ID = 702;
	public static final int GRAVESTONE_GROUP_ID = 672;
	public static final int POH_TREASURE_CHEST_INVENTORY_GROUP_ID = 674;
	public static final int GROUP_IRON_GROUP_ID = 726;
	public static final int GROUP_STORAGE_INVENTORY_GROUP_ID = 725;
	public static final int GROUP_STORAGE_GROUP_ID = 724;
	public static final int WILDERNESS_LOOT_CHEST = 742;
	public static final int TRADE_WINDOW_GROUP_ID = 335;

	public static class WorldMap
	{
		public static final int MAPVIEW = 7;
		public static final int OVERVIEW_MAP = 10;
		public static final int BOTTOM_BAR = 23;
		public static final int SEARCH = 26;
		public static final int SURFACE_SELECTOR = 34;
		public static final int TOOLTIP = 41;
	}

	public static class SlayerRewards
	{
		public static final int TOP_BAR = 12;
	}

	public static class DialogOption
	{
		public static final int OPTIONS = 1;
	}

	public static class DialogNPC
	{
		public static final int HEAD_MODEL = 2;
		public static final int NAME = 4;
		public static final int TEXT = 6;
	}

	public static class DialogPlayer
	{
		public static final int TEXT = 6;
	}

	public static class LogoutPanel
	{
		public static final int WORLD_SWITCHER_BUTTON = 3;
		public static final int LOGOUT_BUTTON = 6;
	}

	public static class PestControl
	{
		public static final int KNIGHT_INFO_CONTAINER = 2;

		public static final int ACTIVITY_SHIELD_CONTAINER = 9;
		public static final int ACTIVITY_BAR = 10;
		public static final int ACTIVITY_PROGRESS = 12;

		public static final int PURPLE_SHIELD = 13;
		public static final int BLUE_SHIELD = 14;
		public static final int YELLOW_SHIELD = 15;
		public static final int RED_SHIELD = 16;

		public static final int PURPLE_ICON = 17;
		public static final int BLUE_ICON = 18;
		public static final int YELLOW_ICON = 19;
		public static final int RED_ICON = 20;

		public static final int PURPLE_HEALTH = 21;
		public static final int BLUE_HEALTH = 22;
		public static final int YELLOW_HEALTH = 23;
		public static final int RED_HEALTH = 24;
	}

	public static class FriendList
	{
		public static final int TITLE = 3;
		public static final int FULL_CONTAINER = 5;
		public static final int SORT_BY_NAME_BUTTON = 7;
		public static final int SORT_BY_LAST_WORLD_CHANGE_BUTTON = 8;
		public static final int SORT_BY_WORLD_BUTTON = 9;
		public static final int LEGACY_SORT_BUTTON = 10;
		public static final int NAMES_CONTAINER = 11;
		public static final int SCROLL_BAR = 12;
		public static final int LOADING_TEXT = 13;
		public static final int PREVIOUS_NAME_HOLDER = 18;
	}

	public static class IgnoreList
	{
		public static final int TITLE = 3;
		public static final int FULL_CONTAINER = 5;
		public static final int SORT_BY_NAME_BUTTON = 7;
		public static final int LEGACY_SORT_BUTTON = 8;
		public static final int NAMES_CONTAINER = 9;
		public static final int SCROLL_BAR = 10;
		public static final int LOADING_TEXT = 11;
		public static final int PREVIOUS_NAME_HOLDER = 16;
	}

	public static class FriendsChat
	{
		public static final int ROOT = 0;
		public static final int TITLE = 1;
		public static final int OWNER = 2;
		public static final int LIST = 12;
	}

	public static class Bank
	{
		public static final int BANK_CONTAINER = 1;
		public static final int INVENTORY_ITEM_CONTAINER = 3;
		public static final int BANK_TITLE_BAR = 3;
		public static final int TUTORIAL_BUTTON = 4;
		public static final int ITEM_COUNT_TOP = 5;
		public static final int ITEM_COUNT_BAR = 6;
		public static final int ITEM_COUNT_BOTTOM = 7;
		public static final int GROUP_STORAGE_BUTTON = 8;
		public static final int CONTENT_CONTAINER = 10;
		public static final int TAB_CONTAINER = 11;
		public static final int ITEM_CONTAINER = 13;
		public static final int SCROLLBAR = 14;
		public static final int SEARCH_BUTTON_BACKGROUND = 40;
		public static final int DEPOSIT_INVENTORY = 42;
		public static final int DEPOSIT_EQUIPMENT = 44;
		public static final int INCINERATOR = 46;
		public static final int INCINERATOR_CONFIRM = 47;
		public static final int EQUIPMENT_CONTENT_CONTAINER = 69;
		public static final int SETTINGS_BUTTON = 112;
		public static final int EQUIPMENT_BUTTON = 113;
	}

	public static class GroupStorage
	{
		public static final int UI = 2;
		public static final int ITEM_CONTAINER = 10;
	}

	public static class GrandExchange
	{
		public static final int WINDOW_CONTAINER = 0;
		public static final int OFFER_CONTAINER = 25;
		public static final int OFFER_DESCRIPTION = 26;
	}

	public static class GrandExchangeInventory
	{
		public static final int INVENTORY_ITEM_CONTAINER = 0;
	}

	public static class DepositBox
	{
		public static final int INVENTORY_ITEM_CONTAINER = 2;
	}

	public static class Shop
	{
		public static final int INVENTORY_ITEM_CONTAINER = 0;
	}

	public static class Smithing
	{
		public static final int INVENTORY_ITEM_CONTAINER = 0;
	}

	public static class GuidePrices
	{
		public static final int ITEM_CONTAINER = 2;
		public static final int INVENTORY_ITEM_CONTAINER = 0;
	}

	public static class Equipment
	{
		public static final int INVENTORY_ITEM_CONTAINER = 0;
	}

	public static class Emotes
	{
		public static final int EMOTE_WINDOW = 0;
		public static final int EMOTE_SCROLL_CONTAINER = 1;
		public static final int EMOTE_CONTAINER = 2;
		public static final int EMOTE_SCROLLBAR = 4;
	}

	public static class Cluescroll
	{
		public static final int CLUE_TEXT = 2;
		public static final int CLUE_SCROLL_ITEM_CONTAINER = 3;
	}

	public static class Minimap
	{
		public static final int XP_ORB = 5;
		public static final int HEALTH_ORB = 6;
		public static final int PRAYER_ORB = 17;
		public static final int QUICK_PRAYER_ORB = 19; // Has the "Quick-prayers" name
		public static final int PRAYER_ORB_TEXT = 20;
		public static final int RUN_ORB = 25;
		public static final int TOGGLE_RUN_ORB = 27; // Has the "Toggle run" name
		public static final int RUN_ORB_TEXT = 28;
		public static final int SPEC_ORB = 33;
		public static final int WORLDMAP_ORB = 48;
		public static final int WIKI_BANNER_PARENT = 49;
		public static final int WIKI_BANNER = 50;
		public static final int WORLDMAP_OPTIONS = 53;
	}

	public static class LoginClickToPlayScreen
	{
		public static final int MESSAGE_OF_THE_DAY = 7;
	}

	public static class FixedViewport
	{
		public static final int MINIMAP = 8;
		public static final int MINIMAP_DRAW_AREA = 21;
		public static final int FIXED_VIEWPORT = 32;
		public static final int MULTICOMBAT_INDICATOR = 36;
		public static final int FRIENDS_CHAT_TAB = 46;
		public static final int FRIENDS_TAB = 48;
		public static final int IGNORES_TAB = 47;
		public static final int LOGOUT_TAB = 49;
		public static final int OPTIONS_TAB = 50;
		public static final int EMOTES_TAB = 51;
		public static final int MUSIC_TAB = 52;
		public static final int FRIENDS_CHAT_ICON = 53;
		public static final int FRIENDS_ICON = 55;
		public static final int IGNORES_ICON = 54;
		public static final int LOGOUT_ICON = 56;
		public static final int OPTIONS_ICON = 57;
		public static final int EMOTES_ICON = 58;
		public static final int MUSIC_ICON = 59;
		public static final int COMBAT_TAB = 62;
		public static final int STATS_TAB = 63;
		public static final int QUESTS_TAB = 64;
		public static final int INVENTORY_TAB = 65;
		public static final int EQUIPMENT_TAB = 66;
		public static final int PRAYER_TAB = 67;
		public static final int MAGIC_TAB = 68;
		public static final int COMBAT_ICON = 69;
		public static final int STATS_ICON = 70;
		public static final int QUESTS_ICON = 71;
		public static final int INVENTORY_ICON = 72;
		public static final int EQUIPMENT_ICON = 73;
		public static final int PRAYER_ICON = 74;
		public static final int MAGIC_ICON = 75;
		public static final int ROOT_INTERFACE_CONTAINER = 16;
		public static final int BANK_CONTAINER = 77;
		public static final int INTERFACE_CONTAINER = 78;
		public static final int INVENTORY_CONTAINER = 82;
	}

	public static class ResizableViewport
	{
		public static final int RESIZABLE_VIEWPORT_OLD_SCHOOL_BOX = 15;
		public static final int MULTICOMBAT_INDICATOR = 19;
		public static final int MINIMAP = 93;
		public static final int MINIMAP_DRAW_AREA = 29;
		public static final int MINIMAP_ORB_HOLDER = 32;
		public static final int FRIENDS_CHAT_TAB = 42;
		public static final int IGNORES_TAB = 43;
		public static final int FRIENDS_TAB = 44;
		public static final int LOGOUT_TAB = 45;
		public static final int OPTIONS_TAB = 46;
		public static final int EMOTES_TAB = 47;
		public static final int MUSIC_TAB = 48;
		public static final int FRIENDS_CHAT_ICON = 49;
		public static final int FRIENDS_ICON = 51;
		public static final int IGNORES_ICON = 50;
		public static final int LOGOUT_ICON = 52;
		public static final int OPTIONS_ICON = 53;
		public static final int EMOTES_ICON = 54;
		public static final int MUSIC_ICON = 55;
		public static final int COMBAT_TAB = 58;
		public static final int STATS_TAB = 59;
		public static final int QUESTS_TAB = 60;
		public static final int INVENTORY_TAB = 61;
		public static final int EQUIPMENT_TAB = 62;
		public static final int PRAYER_TAB = 63;
		public static final int MAGIC_TAB = 64;
		public static final int COMBAT_ICON = 65;
		public static final int STATS_ICON = 66;
		public static final int QUESTS_ICON = 67;
		public static final int INVENTORY_ICON = 68;
		public static final int EQUIPMENT_ICON = 69;
		public static final int PRAYER_ICON = 70;
		public static final int MAGIC_ICON = 71;
		public static final int INTERFACE_CONTAINER = 72;
		public static final int INVENTORY_CONTAINER = 78;
		public static final int CHATBOX_PARENT = 94;
		public static final int INVENTORY_PARENT = 95;
	}

	public static class ResizableViewportBottomLine
	{
		public static final int RESIZABLE_VIEWPORT_BOTTOM_LINE = 15;
		public static final int MINIMAP = 90;
		public static final int MINIMAP_DRAW_AREA = 29;
		public static final int MINIMAP_ORB_HOLDER = 32;
		public static final int LOGOUT_BUTTON_OVERLAY = 33;
		public static final int MINIMAP_LOGOUT_BUTTON = 34;
		public static final int FC_ICON = 43;
		public static final int FRIEND_ICON = 45;
		public static final int SETTINGS_ICON = 46;
		public static final int EMOTE_ICON = 47;
		public static final int MUSIC_ICON = 48;
		public static final int INVENTORY_TAB = 54;
		public static final int PRAYER_TAB = 56;
		public static final int CMB_ICON = 58;
		public static final int SKILLS_ICON = 59;
		public static final int QUESTS_ICON = 60;
		public static final int INVENTORY_ICON = 61;
		public static final int EQUIP_ICON = 62;
		public static final int PRAYER_ICON = 63;
		public static final int MAGIC_ICON = 64;
		public static final int INTERFACE_CONTAINER = 69;
		public static final int INVENTORY_CONTAINER = 75;
		public static final int CHATBOX_PARENT = 91;
		public static final int TABS1 = 92;
		public static final int TABS2 = 93;
		public static final int INVENTORY_PARENT = 94;
	}

	public static class Chatbox
	{
		public static final int PARENT = 0;
		public static final int BUTTONS = 1;
		public static final int TAB_ALL = 4;
		public static final int TAB_GAME = 7;
		public static final int TAB_PUBLIC = 11;
		public static final int TAB_PRIVATE = 15;
		public static final int TAB_CHANNEL = 19;
		public static final int TAB_CLAN = 23;
		public static final int TAB_TRADE = 27;
		public static final int REPORT_TEXT = 33;
		public static final int FRAME = 34;
		public static final int TRANSPARENT_BACKGROUND = 35;
		public static final int CONTAINER = 37;
		public static final int TITLE = 41;
		public static final int FULL_INPUT = 42;
		public static final int GE_SEARCH_RESULTS = 50;
		public static final int MESSAGES = 53;
		public static final int TRANSPARENT_BACKGROUND_LINES = 54;
		public static final int INPUT = 55;
		public static final int MESSAGE_LINES = 56;
		public static final int FIRST_MESSAGE = 57;
	}

	public static class Prayer
	{
		public static final int THICK_SKIN = 5;
		public static final int BURST_OF_STRENGTH = 6;
		public static final int CLARITY_OF_THOUGHT = 7;
		public static final int SHARP_EYE = 23;
		public static final int MYSTIC_WILL = 24;
		public static final int ROCK_SKIN = 8;
		public static final int SUPERHUMAN_STRENGTH = 9;
		public static final int IMPROVED_REFLEXES = 10;
		public static final int RAPID_RESTORE = 11;
		public static final int RAPID_HEAL = 12;
		public static final int PROTECT_ITEM = 13;
		public static final int HAWK_EYE = 25;
		public static final int MYSTIC_LORE = 26;
		public static final int STEEL_SKIN = 14;
		public static final int ULTIMATE_STRENGTH = 15;
		public static final int INCREDIBLE_REFLEXES = 16;
		public static final int PROTECT_FROM_MAGIC = 17;
		public static final int PROTECT_FROM_MISSILES = 18;
		public static final int PROTECT_FROM_MELEE = 19;
		public static final int EAGLE_EYE = 27;
		public static final int MYSTIC_MIGHT = 28;
		public static final int RETRIBUTION = 20;
		public static final int REDEMPTION = 21;
		public static final int SMITE = 22;
		public static final int PRESERVE = 33;
		public static final int CHIVALRY = 29;
		public static final int PIETY = 30;
		public static final int RIGOUR = 31;
		public static final int AUGURY = 32;
	}

	public static class QuickPrayer
	{
		public static final int PRAYERS = 4;

		public static final int THICK_SKIN_CHILD_ID = 0;
		public static final int BURST_OF_STRENGTH_CHILD_ID = 1;
		public static final int CLARITY_OF_THOUGHT_CHILD_ID = 2;
		public static final int SHARP_EYE_CHILD_ID = 18;
		public static final int MYSTIC_WILL_CHILD_ID = 19;
		public static final int ROCK_SKIN_CHILD_ID = 3;
		public static final int SUPERHUMAN_STRENGTH_CHILD_ID = 4;
		public static final int IMPROVED_REFLEXES_CHILD_ID = 5;
		public static final int RAPID_RESTORE_CHILD_ID = 6;
		public static final int RAPID_HEAL_CHILD_ID = 7;
		public static final int PROTECT_ITEM_CHILD_ID = 8;
		public static final int HAWK_EYE_CHILD_ID = 20;
		public static final int MYSTIC_LORE_CHILD_ID = 21;
		public static final int STEEL_SKIN_CHILD_ID = 9;
		public static final int ULTIMATE_STRENGTH_CHILD_ID = 10;
		public static final int INCREDIBLE_REFLEXES_CHILD_ID = 11;
		public static final int PROTECT_FROM_MAGIC_CHILD_ID = 12;
		public static final int PROTECT_FROM_MISSILES_CHILD_ID = 13;
		public static final int PROTECT_FROM_MELEE_CHILD_ID = 14;
		public static final int EAGLE_EYE_CHILD_ID = 22;
		public static final int MYSTIC_MIGHT_CHILD_ID = 23;
		public static final int RETRIBUTION_CHILD_ID = 15;
		public static final int REDEMPTION_CHILD_ID = 16;
		public static final int SMITE_CHILD_ID = 17;
		public static final int PRESERVE_CHILD_ID = 28;
		public static final int CHIVALRY_CHILD_ID = 25;
		public static final int PIETY_CHILD_ID = 26;
		public static final int RIGOUR_CHILD_ID = 24;
		public static final int AUGURY_CHILD_ID = 27;
	}

	public static class Combat
	{
		public static final int WEAPON_NAME = 1;
		public static final int LEVEL = 3;
		public static final int STYLE_ONE = 4;
		public static final int STYLE_TWO = 8;
		public static final int STYLE_THREE = 12;
		public static final int STYLE_FOUR = 16;
		public static final int SPELLS = 20;
		public static final int DEFENSIVE_SPELL_BOX = 21;
		public static final int DEFENSIVE_SPELL_ICON = 23;
		public static final int DEFENSIVE_SPELL_SHIELD = 24;
		public static final int DEFENSIVE_SPELL_TEXT = 25;
		public static final int SPELL_BOX = 26;
		public static final int SPELL_ICON = 28;
		public static final int SPELL_TEXT = 29;
		public static final int AUTO_RETALIATE = 30;
	}

	public static class VolcanicMine
	{
		public static final int STABILITY_INFOBOX_CONTAINER = 2;
		public static final int TIME_LEFT = 6;
		public static final int POINTS = 8;
		public static final int STABILITY = 10;
		public static final int PLAYER_COUNT = 12;
		public static final int VENT_A_PERCENTAGE = 16;
		public static final int VENT_B_PERCENTAGE = 17;
		public static final int VENT_C_PERCENTAGE = 18;
		public static final int VENT_A_STATUS = 20;
		public static final int VENT_B_STATUS = 21;
		public static final int VENT_C_STATUS = 22;
		public static final int VENTS_INFOBOX_CONTAINER = 26;
	}

	public static class BarbarianAssault
	{
		public static class ATK
		{
			public static final int ROLE_SPRITE = 11;
			public static final int ROLE = 12;
		}

		public static class HLR
		{
			public static final int TEAMMATES = 13;
			public static final int TEAMMATE1 = 18;
			public static final int TEAMMATE2 = 22;
			public static final int TEAMMATE3 = 26;
			public static final int TEAMMATE4 = 30;
		}

		public static final int TEAM = 2;

		public static final int ROLE_SPRITE = 10;
		public static final int ROLE = 11;

		public static final int REWARD_TEXT = 57;
	}

	public static class LevelUp
	{
		public static final int SKILL = 1;
		public static final int LEVEL = 2;
	}

	public static class QuestCompleted
	{
		public static final int NAME_TEXT = 4;
	}

	public static class Raids
	{
		public static final int POINTS_INFOBOX = 3;
	}

	public static class ChambersOfXericStorageUnitPrivate
	{
		public static final int PRIVATE_CHEST_ITEM_CONTAINER = 6;
	}

	public static class Tob
	{
		public static final int PARTY_INTERFACE = 5;
		public static final int PARTY_STATS = 7;
		public static final int HEALTHBAR_CONTAINER = 9;
	}

	public static class PuzzleBox
	{
		public static final int VISIBLE_BOX = 4;
	}

	public static class LightBox
	{
		public static final int LIGHT_BOX = 1;
		public static final int LIGHT_BOX_WINDOW = 2;
		public static final int LIGHT_BULB_CONTAINER = 3;
		public static final int BUTTON_A = 8;
		public static final int BUTTON_B = 9;
		public static final int BUTTON_C = 10;
		public static final int BUTTON_D = 11;
		public static final int BUTTON_E = 12;
		public static final int BUTTON_F = 13;
		public static final int BUTTON_G = 14;
		public static final int BUTTON_H = 15;
	}

	public static class DialogSprite
	{
		public static final int SPRITE = 1;
		public static final int TEXT = 2;
	}

	public static class ExperienceTracker
	{
		public static final int WIDGET = 4;
		public static final int BOTTOM_BAR = 16;
	}

	public static class FairyRingPanel
	{
		public static final int HEADER = 2;
		public static final int LIST = 7;
		public static final int FAVORITES = 8;
		public static final int SEPARATOR = 9;
		public static final int SCROLLBAR = 152;
	}

	public static class FairyRing
	{
		public static final int LEFT_ORB_CLOCKWISE = 19;
		public static final int LEFT_ORB_COUNTER_CLOCKWISE = 20;
		public static final int MIDDLE_ORB_CLOCKWISE = 21;
		public static final int MIDDLE_ORB_COUNTER_CLOCKWISE = 22;
		public static final int RIGHT_ORB_CLOCKWISE = 23;
		public static final int RIGHT_ORB_COUNTER_CLOCKWISE = 24;
		public static final int TELEPORT_BUTTON = 26;
	}

	public static class FairyRingCode
	{
		public static final int FAIRY_QUEEN_HIDEOUT = 139;
	}

	public static class Barrows
	{
		public static final int BARROWS_BROTHERS = 4;
		public static final int BARROWS_POTENTIAL = 5;
	}

	public static class Diary
	{
		public static final int DIARY_TITLE = 2;
		public static final int DIARY_TEXT = 3;
	}

	public static class DestroyItem
	{
		public static final int DESTROY_ITEM_NAME = 6;
		public static final int DESTROY_ITEM_YES = 1;
		public static final int DESTROY_ITEM_NO = 3;
	}

	public static class VarrockMuseum
	{
		public static final int VARROCK_MUSEUM_QUESTION = 28;
		public static final int VARROCK_MUSEUM_FIRST_ANSWER = 29;
		public static final int VARROCK_MUSEUM_SECOND_ANSWER = 30;
		public static final int VARROCK_MUSEUM_THIRD_ANSWER = 31;
	}

	public static class KillLog
	{
		public static final int TITLE = 3;
		public static final int MONSTER = 13;
		public static final int KILLS = 14;
		public static final int STREAK = 15;
	}

	public static class WorldSwitcher
	{
		public static final int WORLD_LIST = 16;
	}

	public static class Minigames
	{
		public static final int TELEPORT_BUTTON = 30;
	}

	public static class StandardSpellBook
	{
		public static final int LUMBRIDGE_HOME_TELEPORT = 6;
		public static final int KOUREND_HOME_TELEPORT = 4;
		public static final int CATHERBY_HOME_TELEPORT = 5;
	}

	public static class AncientSpellBook
	{
		public static final int EDGEVILLE_HOME_TELEPORT = 100;
	}

	public static class LunarSpellBook
	{
		public static final int LUNAR_HOME_TELEPORT = 101;
		public static final int FERTILE_SOIL = 126;
	}

	public static class ArceuusSpellBook
	{
		public static final int ARCEUUS_HOME_TELEPORT = 145;
	}

	public static class Pvp
	{
		public static final int KILLDEATH_RATIO = 26;
		public static final int WILDERNESS_SKULL_CONTAINER = 44;
		public static final int SKULL_CONTAINER = 45;
		public static final int SAFE_ZONE = 47;
		public static final int WILDERNESS_LEVEL = 50; // this can also be the Deadman Mode "Protection" text
	}

	public static class KourendFavour
	{
		public static final int KOUREND_FAVOUR_OVERLAY = 1;
	}

	public static class Zeah
	{
		public static final int MESS_HALL_COOKING_DISPLAY = 2;
	}

	public static class LootingBag
	{
		public static final int LOOTING_BAG_INVENTORY = 5;
	}

	public static class Skotizo
	{
		public static final int CONTAINER = 2;
	}

	public static class QuestList
	{
		public static final int BOX = 0;
		public static final int CONTAINER = 2;
	}

	public static class Music
	{
		public static final int CONTAINER = 0;
		public static final int SCROLL_CONTAINER = 3;
		public static final int LIST = 5;
		public static final int SCROLLBAR = 6;
	}

	public static class Barrows_Puzzle
	{
		public static final int PARENT = 0;
		public static final int SEQUENCE_1 = 3;
		public static final int ANSWER1_CONTAINER = 12;
		public static final int ANSWER1 = 13;
		public static final int ANSWER2_CONTAINER = 14;
		public static final int ANSWER2 = 15;
		public static final int ANSWER3_CONTAINER = 16;
		public static final int ANSWER3 = 17;
	}

	public static class SeedVault
	{
		public static final int INVENTORY_ITEM_CONTAINER = 1;
		public static final int TITLE_CONTAINER = 2;
		public static final int ITEM_CONTAINER = 15;
		public static final int ITEM_TEXT = 16;
	}

	public static class ExplorersRing
	{
		public static final int INVENTORY = 7;
	}

	public static class SettingsSide
	{
		public static final int CAMERA_ZOOM_SLIDER_TRACK = 100;
		public static final int MUSIC_SLIDER = 29;
		public static final int SOUND_EFFECT_SLIDER = 43;
		public static final int AREA_SOUND_SLIDER = 57;
	}

	public static class Settings
	{
		public static final int INIT = 1;
	}

	public static class AchievementDiary
	{
		public static final int CONTAINER = 2;
	}

	public static class Skills
	{
		public static final int CONTAINER = 0;
	}

	public static class Lms
	{
		public static final int INFO = 2;
	}

	public static class LmsKDA
	{
		public static final int INFO = 4;
	}

	public static class AdventureLog
	{
		public static final int CONTAINER = 0;
	}

	public static class CollectionLog
	{
		public static final int CONTAINER = 0;
		public static final int TABS = 3;
		public static final int ENTRY = 17;
		public static final int ENTRY_HEADER = 19;
		public static final int ENTRY_ITEMS = 36;
	}

	public static class GenericScroll
	{
		public static final int TEXT = 7;
	}

	public static class GauntletTimer
	{
		public static final int CONTAINER = 2;
	}

	public static class HallowedSepulchreTimer
	{
		public static final int CONTAINER = 2;
	}

	public static class BankPin
	{
		public static final int CONTAINER = 0;
	}

	public static class TrailblazerAreas
	{
		public static final int TELEPORT = 59;
	}

	public static class TemporossStatus
	{
		public static final int STATUS_INDICATOR = 2;
	}

	public static class Clan
	{
		public static final int LAYER = 0;
		public static final int HEADER = 1;
		public static final int MEMBERS = 6;
	}

	public static class ClanGuest
	{
		public static final int LAYER = 0;
		public static final int HEADER = 1;
		public static final int MEMBERS = 6;
	}

	public static class Trade
	{
		public static final int HEADER = 31;
	}
}
