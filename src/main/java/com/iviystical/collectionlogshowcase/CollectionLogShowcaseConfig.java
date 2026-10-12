package com.iviystical.collectionlogshowcase;

import com.iviystical.collectionlogshowcase.cox.CoxChatCensorMode;
import com.iviystical.collectionlogshowcase.overlay.LeftPanelStat;
import com.iviystical.collectionlogshowcase.overlay.PanelStyle;
import com.iviystical.collectionlogshowcase.overlay.RightPanelStat;
import com.iviystical.collectionlogshowcase.sound.CollectionVoice;
import com.iviystical.collectionlogshowcase.rarity.PreviewTier;
import com.iviystical.collectionlogshowcase.rarity.RarityBasis;
import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("collection-log-showcase")
public interface CollectionLogShowcaseConfig extends Config
{
	@ConfigSection(
		name = "Preview",
		description = "Preview the popup without waiting for an unlock",
		position = 0
	)
	String previewSection = "previewSection";

	@ConfigSection(
		name = "Style",
		description = "Our animated popup or native popup with audio",
		position = 1
	)
	String styleSection = "styleSection";

	@ConfigSection(
		name = "Rarity",
		description = "How an item's rarity tier is determined",
		position = 6
	)
	String raritySection = "raritySection";

	@ConfigSection(
		name = "Statistics",
		description = "Choose the additional statistic and primary statistic ordering",
		position = 7
	)
	String overlaySection = "overlaySection";

	@ConfigSection(
		name = "Audio",
		description = "Sound effects played on new collection log unlocks",
		position = 2
	)
	String audioSection = "audioSection";

	@ConfigSection(
		name = "Appearance",
		description = "Tune to your liking",
		position = 4
	)
	String appearanceSection = "appearanceSection";

	@ConfigSection(
		name = "Text colours",
		description = "Colour of the caption and statistic text",
		position = 5
	)
	String textColoursSection = "textColoursSection";

	@ConfigSection(
		name = "Chambers of Xeric censor",
		description = "Hiding CoX purples until chest opened.",
		position = 8
	)
	String tweaksSection = "tweaksSection";

	@ConfigItem(
		keyName = "previewTier",
		name = "Preview popup",
		description = "Shows a random item popup of the selected tier",
		position = 0,
		section = previewSection
	)
	default PreviewTier previewTier()
	{
		return PreviewTier.NONE;
	}

	@ConfigItem(
		keyName = "rarityBasis",
		name = "Rarity based on",
		description = "What the rarity tiers are based on",
		position = 0,
		section = raritySection
	)
	default RarityBasis rarityBasis()
	{
		return RarityBasis.COMBINATION;
	}

	@ConfigItem(
		keyName = "valueUncommonThreshold",
		name = "Uncommon at (gp)",
		description = "Value threshold for uncommon tier",
		position = 1,
		section = raritySection
	)
	default int valueUncommonThreshold()
	{
		return 100_000;
	}

	@ConfigItem(
		keyName = "valueRareThreshold",
		name = "Rare at (gp)",
		description = "Value threshold for rare tier",
		position = 2,
		section = raritySection
	)
	default int valueRareThreshold()
	{
		return 1_000_000;
	}

	@ConfigItem(
		keyName = "valueVeryRareThreshold",
		name = "Very rare at (gp)",
		description = "Value threshold for very rare tier",
		position = 3,
		section = raritySection
	)
	default int valueVeryRareThreshold()
	{
		return 10_000_000;
	}

	@ConfigItem(
		keyName = "leftPanelStat",
		name = "Additional statistic",
		description = "Kill count adds a row when available; Completion uses the ownership display toggle",
		position = 0,
		section = overlaySection
	)
	default LeftPanelStat leftPanelStat()
	{
		return LeftPanelStat.KILL_COUNT;
	}

	@ConfigItem(
		keyName = "rightPanelStat",
		name = "Primary statistic",
		description = "Choose whether value or drop rate is listed first",
		position = 1,
		section = overlaySection
	)
	default RightPanelStat rightPanelStat()
	{
		return RightPanelStat.DROP_RATE;
	}

	@ConfigItem(
		keyName = "bulkUnlockSfx",
		name = "Bulk unlock SFX",
		description = "When multiple items unlock at once, play a single sound instead",
		position = 0,
		section = audioSection
	)
	default boolean bulkUnlockSfx()
	{
		return false;
	}

	@ConfigItem(keyName = "soundEnabled", name = "Play collection log audio", description = "Play the selected voice for every collection log slot, including pets", position = 1, section = audioSection)
	default boolean soundEnabled() { return true; }

	@Range(min = 0, max = 100)
	@ConfigItem(keyName = "soundVolume", name = "Volume", description = "Volume for all collection log sounds", position = 2, section = audioSection)
	default int soundVolume() { return 65; }

	@ConfigSection(name = "Audio files", description = "Choose a collection log voice or your own WAV", position = 3)
	String audioFilesSection = "audioFilesSection";

	@ConfigItem(keyName = "collectionVoice", name = "Collection log voice", description = "Bundled collection log clips credited to their source plugins", position = 0, section = audioFilesSection)
	default CollectionVoice collectionVoice() { return CollectionVoice.C_ENGINEER; }

	@ConfigItem(keyName = "customSoundFile", name = "Custom WAV filename", description = "For Custom voice: filename inside .runelite/collection-log-showcase/sounds/", position = 1, section = audioFilesSection)
	default String customSoundFile() { return "custom.wav"; }

	@ConfigItem(
		keyName = "showProgressBar",
		name = "Show progress bar",
		description = "Show your collection log progress in the center bar",
		position = 0,
		section = appearanceSection
	)
	default boolean showProgressBar()
	{
		return true;
	}

	@ConfigItem(
		keyName = "compactShowOwnership",
		name = "Show ownership",
		description = "Include the percentage of players who own the item in the stat line",
		position = 10,
		section = appearanceSection
	)
	default boolean compactShowOwnership()
	{
		return true;
	}

	@ConfigItem(
		keyName = "compactShimmer",
		name = "Glass shimmer",
		description = "Sweep a soft glass highlight across the banner once during its reveal",
		position = 12,
		section = appearanceSection
	)
	default boolean compactShimmer()
	{
		return true;
	}

	@ConfigItem(
		keyName = "compactTextColour",
		name = "Item and values",
		description = "High contrast text colour for the compact reward banner",
		position = 11,
		section = textColoursSection
	)
	default Color compactTextColour()
	{
		return Color.WHITE;
	}

	@ConfigItem(
		keyName = "overlayDisplaySeconds",
		name = "Overlay display duration (seconds)",
		description = "How long each new collection log item stays on screen",
		position = 1,
		section = appearanceSection
	)
	default int overlayDisplaySeconds()
	{
		return 6;
	}

	@Range(min = 50, max = 200)
	@ConfigItem(
		keyName = "overlayScalePercent",
		name = "Popup scale (%)",
		description = "Size of the popup",
		position = 2,
		section = appearanceSection
	)
	default int overlayScalePercent()
	{
		return 100;
	}

	@Range(min = 0, max = 100)
	@ConfigItem(
		keyName = "backgroundOpacity",
		name = "Background opacity (%)",
		description = "Background opacity",
		position = 4,
		section = appearanceSection
	)
	default int backgroundOpacity()
	{
		return 100;
	}

	// Floored above 0 so the popup can never be made entirely invisible.
	@Range(min = 10, max = 100)
	@ConfigItem(
		keyName = "foregroundOpacity",
		name = "Border & text opacity (%)",
		description = "Foreground opacity",
		position = 5,
		section = appearanceSection
	)
	default int foregroundOpacity()
	{
		return 100;
	}

	@ConfigItem(
		keyName = "panelStyle",
		name = "Panel style",
		description = "Showcase replaces the popup; Audio only leaves the native game popup visible",
		position = 0,
		section = styleSection
	)
	default PanelStyle panelStyle()
	{
		return PanelStyle.COMPACT;
	}

	@ConfigItem(
		keyName = "colourCaption",
		name = "Caption text",
		description = "Colour of the Collection Log caption at the top",
		position = 0,
		section = textColoursSection
	)
	default Color colourCaption()
	{
		return new Color(0xFF981F);
	}

	@ConfigItem(
		keyName = "colourStatLabel",
		name = "Statistic labels",
		description = "Colour of the additional statistic text",
		position = 1,
		section = textColoursSection
	)
	default Color colourStatLabel()
	{
		return new Color(0xDCDCD6);
	}

	@ConfigItem(
		keyName = "delayCoxPopupUntilChest",
		name = "Delay CoX popups until chest",
		description = "Hide the collection log popup for CoX purples until the reward chest, storage or bank is opened. The collection log screenshot is taken then too.",
		position = 0,
		section = tweaksSection
	)
	default boolean delayCoxPopupUntilChest()
	{
		return false;
	}

	@ConfigItem(
		keyName = "coxChatCensor",
		name = "Chatbox censor",
		description = "Replace CoX purples in chat with ??? until the reward chest, storage or bank is opened",
		position = 1,
		section = tweaksSection
	)
	default CoxChatCensorMode coxChatCensor()
	{
		return CoxChatCensorMode.OFF;
	}

}
