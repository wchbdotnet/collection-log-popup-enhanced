package com.iviystical.collectionlogshowcase.overlay;

import com.iviystical.collectionlogshowcase.CollectionLogShowcaseConfig;
import com.iviystical.collectionlogshowcase.rarity.PreviewTier;
import com.iviystical.collectionlogshowcase.rarity.RarityTier;
import com.iviystical.collectionlogshowcase.sound.SoundManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import net.runelite.api.Client;
import net.runelite.client.game.ItemManager;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PreviewFadeTest
{
    @Test
    public void previewFadesAndExpiresUsingConfiguredDuration()
    {
        for (PreviewTier preview : new PreviewTier[] {PreviewTier.NONE, PreviewTier.COMMON})
        {
            Client client = mock(Client.class);
            when(client.getViewportWidth()).thenReturn(800);
            CollectionLogShowcaseConfig config = new CollectionLogShowcaseConfig()
            {
                @Override public PreviewTier previewTier() { return preview; }
                @Override public int overlayDisplaySeconds() { return 0; }
                @Override public int fadeOutMillis() { return 600; }
                @Override public boolean compactShimmer() { return false; }
            };
            AtomicLong clock = new AtomicLong();
            CollectionLogOverlay overlay = new CollectionLogOverlay(client, mock(ItemManager.class), config, mock(SoundManager.class), clock::get);
            overlay.enqueue("Mask of Ranul", -1, RarityTier.UNCOMMON, 8511, false, null, null, null, null, .001, List.of());
            render(overlay);
            clock.set(950);
            assertEquals(255, render(overlay).getRGB(10, 10) >>> 24);
            clock.set(1250);
            int alpha = render(overlay).getRGB(10, 10) >>> 24;
            assertTrue("mid-fade opacity", alpha > 100 && alpha < 155);
            assertFalse(overlay.isIdle());
            clock.set(1550);
            assertEquals(0, render(overlay).getRGB(10, 10) >>> 24);
            assertTrue(overlay.isIdle());
        }
    }

    private BufferedImage render(CollectionLogOverlay overlay)
    {
        BufferedImage image = new BufferedImage(400, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        try { overlay.render(graphics); }
        finally { graphics.dispose(); }
        return image;
    }
}
