package com.iviystical.collectionlogshowcase.sound;

import com.iviystical.collectionlogshowcase.CollectionLogShowcaseConfig;
import com.iviystical.collectionlogshowcase.rarity.RarityTier;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.concurrent.ScheduledExecutorService;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import net.runelite.client.audio.AudioPlayer;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

public class VoiceAudioTest
{
    @Test
    public void everyBundledVoiceIsDecodable() throws Exception
    {
        for (CollectionVoice voice : CollectionVoice.values())
        {
            if (voice == CollectionVoice.CUSTOM) { continue; }
            try (InputStream input = getClass().getResourceAsStream(voice.resource()))
            {
                assertNotNull(voice.toString(), input);
                try (AudioInputStream audio = AudioSystem.getAudioInputStream(new BufferedInputStream(input)))
                {
                    assertTrue(audio.getFrameLength() > 0);
                    assertTrue(audio.getFormat().getSampleRate() > 0);
                }
            }
        }
    }

    @Test
    public void allTiersUseOneVoiceAndMutePreventsScheduling() throws Exception
    {
        CollectionLogShowcaseConfig config = mock(CollectionLogShowcaseConfig.class);
        AudioPlayer player = mock(AudioPlayer.class);
        ScheduledExecutorService executor = mock(ScheduledExecutorService.class);
        when(config.soundEnabled()).thenReturn(true);
        when(config.soundVolume()).thenReturn(65);
        when(config.collectionVoice()).thenReturn(CollectionVoice.C_ENGINEER);
        doAnswer(invocation -> { ((Runnable) invocation.getArgument(0)).run(); return null; }).when(executor).execute(any(Runnable.class));
        SoundManager manager = new SoundManager(player, config, executor);
        for (RarityTier tier : RarityTier.values()) { manager.play(tier); }
        verify(player, times(RarityTier.values().length)).play(eq(SoundManager.class), eq(CollectionVoice.C_ENGINEER.resource()), anyFloat());
        clearInvocations(player, executor);
        when(config.soundEnabled()).thenReturn(false);
        manager.play(RarityTier.PET);
        verifyNoInteractions(player, executor);
        when(config.soundEnabled()).thenReturn(true);
        when(config.soundVolume()).thenReturn(0);
        manager.play(RarityTier.COMMON);
        verifyNoInteractions(player, executor);
    }
}
