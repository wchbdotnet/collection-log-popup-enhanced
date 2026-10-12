package com.iviystical.collectionlogshowcase.overlay;

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
	@Test
	public void moneyUsesCompactUnitsWithoutTrailingZeroes()
	{
		long[] values = {8511, 100000, 1000000, 1500000, 101500000, 1100000000, 1150000000, 999, 999950, 999950000};
		String[] expected = {"8.5k", "100k", "1m", "1.5m", "101.5m", "1.1b", "1.15b", "999", "1m", "1b"};
		for (int i = 0; i < values.length; i++)
		{
			assertEquals(expected[i], CollectionLogOverlay.formatValue(values[i]));
		}
		assertFalse(new com.iviystical.collectionlogshowcase.CollectionLogShowcaseConfig() {}.compactShowOwnership());
	}
}
