package com.iviystical.collectionlogshowcase.overlay;

public enum PanelStyle
{
	COMPACT("Showcase"), AUDIO_ONLY("Audio only (native popup)");
	private final String label;
	PanelStyle(String label) { this.label = label; }
	@Override public String toString() { return label; }
}
