package com.iviystical.collectionlogshowcase.sound;

import com.iviystical.collectionlogshowcase.CollectionLogShowcaseConfig;
import com.iviystical.collectionlogshowcase.rarity.RarityTier;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;
import net.runelite.client.audio.AudioPlayer;

/** A single voice and gain for all unlocks. File access and decoding run off the client thread. */
@Slf4j
@Singleton
public class SoundManager
{
    private static final File SOUNDS_DIR = new File(RuneLite.RUNELITE_DIR, "collection-log-showcase/sounds");
    private final AudioPlayer audioPlayer;
    private final CollectionLogShowcaseConfig config;
    private final ScheduledExecutorService executor;

    @Inject
    public SoundManager(AudioPlayer audioPlayer, CollectionLogShowcaseConfig config, ScheduledExecutorService executor)
    {
        this.audioPlayer = audioPlayer;
        this.config = config;
        this.executor = executor;
    }

    public void play(RarityTier tier)
    {
        int volume = config.soundVolume();
        if (!config.soundEnabled() || volume <= 0) { return; }
        CollectionVoice voice = config.collectionVoice();
        String customFilename = config.customSoundFile();
        executor.execute(() -> {
            try
            {
                float gain = 20f * (float) Math.log10(Math.min(100, volume) / 100f);
                if (voice == CollectionVoice.CUSTOM)
                {
                    if (!SOUNDS_DIR.isDirectory() && !SOUNDS_DIR.mkdirs())
                    {
                        log.warn("Cannot create custom collection log sounds directory");
                        return;
                    }
                    if (customFilename == null || !customFilename.toLowerCase(java.util.Locale.ROOT).endsWith(".wav")
                        || customFilename.contains("/") || customFilename.contains("\\") || customFilename.contains(":"))
                    {
                        log.warn("Custom collection log sound must be a WAV filename inside {}", SOUNDS_DIR);
                        return;
                    }
                    File sound = new File(SOUNDS_DIR, customFilename);
                    if (sound.isFile()) { audioPlayer.play(sound, gain); }
                    else { log.warn("Custom collection log sound is missing: {}", sound); }
                }
                else
                {
                    audioPlayer.play(SoundManager.class, voice.resource(), gain);
                }
            }
            catch (Exception e) { log.warn("Failed to play collection log voice {}", voice, e); }
        });
    }
}
