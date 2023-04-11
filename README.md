# Heroes 3 Simulator

Heroes 3 Simulator is a very simple combat simulator for the Heroes of Might and Magic 3 game.

It started with a simple question: how many peasants does it take to defeat an archangel?

The response is 453, see the results section for extra data.

This project is also a sandbox to keep working on Kotlin related technologies.

## Results

The PV ("peasant value") is the number of peasants needed to defeat a given creature stack.

The PV for several stack sizes of a selection of creatures is:

| Creature          | PV for 1 | PV for 10 | PV for 100 |
|-------------------|----------|-----------|------------|
| Skeleton Warriors | 6        | 49        | 485        |
| Halberdier        | 9        | 69        | 668        |
| Marksman          | 28       | 289       | 2912       |
| Battle Dwarf      | 13       | 106       | 1038       |
| Silver Pegasus    | 29       | 225       | 2184       |
| Crusader          | 44       | 354       | 3427       |
| Sharpshooter      | 80       | 800       | 7979       |
| Minotaur King     | 66       | 542       | 5336       |
| Naga Queen        | 184      | 1443      | 13899      |
| Behemot           | 206      | 1619      | 15773      |
| Ancient Behemot   | 291      | 2256      | 22044      |
| Arch Devil        | 441      | 3275      | 32237      |
| Archangel         | 453      | 3506      | 34334      |
| Azure Dragon      | 1331     | 9974      | 96716      |

This means that it takes 453 peasants to defeat one archangel, and 3506 peasants to defeat 10 archangels.

We can generalize by computing the combat value in any creature besides peasants:

| Creature        | Quantity | Value in         | Is  |
|-----------------|----------|------------------|-----|
| Minotaur King   | 10       | Battle Dwarf     | 52  |
| Naga Queen      | 10       | Crusader         | 44  |
| Behemot         | 10       | Dendroid Soldier | 43  |
| Ancient Behemot | 10       | Dendroid Soldier | 65  |
| Behemot         | 10       | Archangel        | 6   |
| Ancient Behemot | 10       | Archangel        | 11  |
| Archangel       | 10       | Manticore        | 56  |
| Archangel       | 10       | Nix Warrior      | 34  |
| Archangel       | 10       | Naga Queen       | 29  |
| Azure Dragon    | 10       | Archangel        | 51  |
| Azure Dragon    | 20       | Archangel        | 102 |

This means that it takes 44 crusaders to defeat 10 naga queens.

## Features and limitations

- This simulator only supports one stack of creatures versus one stack of creatures
- Creature can be melee or ranged (and ammunition are supported)
- No heroes and no magic
- Only some creature abilities are supported:
  - Double attack (e.g. crusaders)
  - No enemy retaliation (e.g. naga queens)
  - No melee penalty (e.g. zealots)
  - No distance penalty (e.g. sharpshooters)
  - Defense reduction (e.g. behemots)
  - Attack reduction (e.g. nix warriors)
