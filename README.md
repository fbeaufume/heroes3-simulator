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
| Skeleton Warriors | 6        |           |            |
| Halberdier        | 9        |           |            |
| Battle Dwarf      | 13       |           |            |
| Silver Pegasus    | 29       |           |            |
| Crusader          | 43       | 354       | 3427       |
| Minotaur King     | 66       |           |            |
| Naga Queen        | 184      | 1443      | 13899      |
| Arch Devil        | 441      |           |            |
| Archangel         | 453      | 3506      | 34334      |
| Azure Dragon      | 1331     |           |            |

## Limitations

This simulator has multiple limitations:
- Only one stack of creatures versus one stack of creatures
- No battlefield and no movement, creatures are assumed adjacent and using melee attacks
- No heroes and no magic
- Only some creature abilities are supported: no enemy retaliation, double attack
