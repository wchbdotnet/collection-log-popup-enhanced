package com.iviystical.collectionlogshowcase;

import net.runelite.client.config.ConfigManager;

/** Copy shared preferences once, without changing the upstream plugin's configuration. */
final class SettingsMigration
{
    static final String GROUP = "collection-log-showcase";
    static final String LEGACY = "collection-log-popup-enhanced";
    private static final String[] KEYS = {
        "previewTier", "rarityBasis", "valueUncommonThreshold", "valueRareThreshold", "valueVeryRareThreshold",
        "leftPanelStat", "rightPanelStat", "bulkUnlockSfx", "showProgressBar", "compactShowOwnership",
        "compactShimmer", "compactTextColour", "overlayDisplaySeconds", "overlayScalePercent",
        "backgroundOpacity", "foregroundOpacity", "colourCaption", "colourStatLabel",
        "delayCoxPopupUntilChest", "coxChatCensor"
    };

    static void migrate(ConfigManager manager)
    {
        if (manager.getConfiguration(GROUP, "settingsMigrated") != null) { return; }
        for (String key : KEYS) { copy(manager, key, key); }
        copy(manager, "soundEnabledCommon", "soundEnabled");
        copy(manager, "soundVolumeCommon", "soundVolume");
        if (manager.getConfiguration(GROUP, "panelStyle") == null)
        {
            String previous = manager.getConfiguration(LEGACY, "panelStyle");
            manager.setConfiguration(GROUP, "panelStyle", "AUDIO_ONLY".equals(previous) ? "AUDIO_ONLY" : "COMPACT");
        }
        manager.setConfiguration(GROUP, "settingsMigrated", true);
    }

    private static void copy(ConfigManager manager, String oldKey, String newKey)
    {
        if (manager.getConfiguration(GROUP, newKey) != null) { return; }
        String value = manager.getConfiguration(LEGACY, oldKey);
        if (value != null) { manager.setConfiguration(GROUP, newKey, value); }
    }
}
