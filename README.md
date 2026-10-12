# Collection Log Showcase

**A standalone fork of [Collection Log Popup Enhanced](https://github.com/TimHeessels/collection-log-popup-enhanced) by SnakeSteak, developed by IVIystical.**

This project keeps the original plugin's collection-log detection, rarity calculation, local datasets, kill-count tracking, unlock queue, bulk sound handling, native-popup screenshot integration and Chambers of Xeric censor/delay features. It replaces the original visual skins and tier-specific audio system with an animated compact banner and a selectable collection-log voice.

## Our popup

- A compact item compartment that opens into a reward banner, with a moving edge highlight and optional glass shimmer.
- Bold Pixel text, rendered at its final size. No text-rendering selector.
- Item name, rarity, drop rate or rate range, percentage of players who own the item, and positive GP values. Zero GP / high-alch entries are hidden. Positive GP values use compact k/m/b units (one decimal for k/m, up to two for b). Ownership is off by default.
- Content fits the height; longer stat lines wrap. Additional statistic chooses kill count or completion; Primary statistic chooses value or rate ordering.
- Progress bar, scale, background and foreground opacity, text colours, ownership display and shimmer remain configurable.
- Alt-drag positioning and Preview popup remain available.

Only **Showcase** and **Audio only (native popup)** modes are offered. Audio only leaves the normal game popup visible and plays the selected voice. CoX censoring/delay still takes precedence while loot is hidden.

## Audio and source credits

**We have also pulled the bundled audio files from various other RuneLite plugins. These recordings are not original recordings made for this project.**

| Voice | Source plugin and maintainer | Clip |
| --- | --- | --- |
| C Engineer (default) | [C Engineer: Completed — m0bilebtw](https://github.com/m0bilebtw/c-engineer-completed) | `ColLogSlotCompleted_r1.wav` |
| B0aty — variant 1 | [Boaty Hype Man — Devdeve](https://github.com/Devdeve/boatyHypeMan-plugin) | `collog1.wav` |
| B0aty — variant 2 | [Boaty Hype Man — Devdeve](https://github.com/Devdeve/boatyHypeMan-plugin) | `collog2.wav` |
| Tedious | [Tedious Collection Log — evaan](https://github.com/evaan/tedious-collection-log) | `the_collection_log.wav` |
| Odablock | [Odablock — DapperMickie](https://github.com/DapperMickie/odablock-sounds) | `collectionlog/ColLogSlotCompleted_r1.wav` |

The exact source revisions are recorded in [audio-sources.txt](src/main/resources/licenses/audio-sources.txt). The source repositories' BSD-2-Clause license notices are included in [licenses](src/main/resources/licenses) and bundled in the plugin JAR. Voice names identify the recordings and do not imply endorsement of this fork.

Audio has one **Play collection log audio** toggle and one **Volume** for every tier and pets. **Bulk unlock SFX** preserves the original behaviour: one sound for an unlock batch when enabled. Select a voice in the separate **Audio files** section. All bundled clips are local WAV resources; the plugin does not download them at runtime. None of Collection Log Popup Enhanced's original five sound effects remain bundled.

For your own recording, select **Custom WAV** and place a valid WAV inside `.runelite/collection-log-showcase/sounds/`. Set **Custom WAV filename** to its filename, for example `custom.wav`. Selecting Custom and triggering a preview creates the folder if necessary. An absent or invalid custom file does not silently substitute another voice.

## Setup and previews

Enable the game's **Collection log — New addition notification** and **New addition popup** settings. The native popup must remain enabled for RuneLite's screenshot timing; Showcase hides only its paint while replacing it.

Select a rarity in **Preview popup** to inspect the banner and hear the chosen voice. Preview now runs the full reveal, hold and smooth exit fade, then repeats while selected. Fade out duration (ms) controls the exit for previews and real unlocks alike. Disable preview afterwards.

Showcase conflicts with Collection Log Popup Enhanced so RuneLite does not run both popup replacements together. It has its own Java identity and `collection-log-showcase` settings group. Shared preferences are copied once from the earlier group when no Showcase value exists. Old Colourful/Neutral modes become Showcase; Audio only remains Audio only. The old common sound toggle and volume initialise the unified audio controls; old per-tier differences are intentionally retired. The original settings are not modified.

## Chambers of Xeric

The upstream CoX code and defaults are retained: **Delay CoX popups until chest** defaults off, and **Chatbox censor** defaults Off. Enable them if you want loot hidden until the reward chest, storage or bank is opened. Delayed collection-log screenshots remain tied to release of the popup. Avoid combining the censor with CoX Special Loot Hider.

## Development and validation

Java 11-compatible source. Author metadata: **IVIystical**. Follow current [RuneLite Plugin Hub guidelines](https://github.com/runelite/plugin-hub). Run `gradlew test` for automated checks and `gradlew run` for the development client. For Jagex accounts, use RuneLite's [development-client login guide](https://github.com/runelite/runelite/wiki/Using-Jagex-Accounts).

Manual verification is still needed for real unlocks, audio output, screenshots, bulk unlocks and CoX chest release. Development previews and automated tests do not establish those behaviours in-game. This fork has not been submitted to the Plugin Hub.

## Upstream attribution

The original BSD-2-Clause license and SnakeSteak copyright remain in [LICENSE](LICENSE). The original project credits C Engineer for collection-log popup tie-ins, Trailblazer audio for playback references, and CoX Special Loot Hider (Karambtwo, Rikten X, Fraac) for chat censoring and delayed screenshots. Our fork retains that attribution. The original Colourful/Neutral art and five sound files have been removed from the distributed resources.
