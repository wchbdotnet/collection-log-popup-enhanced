package com.iviystical.collectionlogshowcase;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CollectionLogShowcasePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CollectionLogShowcasePlugin.class);
		RuneLite.main(args);
	}
}