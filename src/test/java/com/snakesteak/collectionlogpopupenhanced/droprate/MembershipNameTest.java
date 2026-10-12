package com.snakesteak.collectionlogpopupenhanced.droprate;

import com.snakesteak.collectionlogpopupenhanced.CollectionLogSlotNames;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class MembershipNameTest
{
	@Test
	public void previewMembershipMarkerDoesNotHideDropRate()
	{
		DropRateResolver resolver = new DropRateResolver();
		resolver.reload(Map.of("Corporeal Beast", Map.of("Spectral sigil", 1.0 / 1365)));
		assertEquals(1.0 / 1365, resolver.dropProbabilityByItemName("Spectral sigil (Members)"), 0.00000001);
		assertEquals("Spectral sigil", CollectionLogSlotNames.withoutMembershipSuffix("Spectral sigil (Members)"));
		assertEquals("Spectral sigil", CollectionLogSlotNames.withoutMembershipSuffix("Spectral sigil"));
		assertNull(resolver.dropProbabilityByItemName("Unlisted item (Members)"));
	}
}
