package com.snakesteak.collectionlogpopupenhanced.overlay;

import org.junit.Test;
import static org.junit.Assert.*;

public class CompactDetailTest
{
	@Test
	public void zeroValuePetsKeepRateAndOwnershipWithoutAlchNoise()
	{
		assertEquals("1/5000   2.1% players", CollectionLogOverlay.compactDetail("1/5000", 0, true, 2.1, false));
	}

	@Test
	public void positiveValuesAndValueOrderingAreRetained()
	{
		assertEquals("480 gp (HA)   1/64   23.8% players", CollectionLogOverlay.compactDetail("1/64", 480, true, 23.8, true));
		assertEquals("1/64   480 gp", CollectionLogOverlay.compactDetail("1/64", 480, false, null, false));
	}
}
