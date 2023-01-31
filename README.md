# Heroes 3 Simulator

Heroes 3 Simulator is a very simple combat simulator for the Heroes of Might and Magic 3 game.

It started with a simple question: how many peasants does it take to defeat an archangel?
See the results section for the answer.

This project is also a sandbox to keep working on Kotlin related technologies.

## Results

The "peasant value" (i.e. the number of peasants needed to defeat a given creature) for some creatures is:

| Creature          | Peasant value |
|-------------------|---------------|
| Skeleton Warriors | 6             |
| Halberdier        | 9             |
| Battle Dwarf      | 13            |
| Silver Pegasus    | 29            |
| Crusader          | 43            |
| Minotaur King     | 66            |
| Naga Queen        | 184           |
| Arch Devil        | 441           |
| Archangel         | 453           |
| Azure Dragon      | 1331          |

## Limitations

This simulator has multiple limitations:
- Only one stack of creatures versus one stack of creatures
- No battlefield and no movement, creatures are assumed adjacent and using melee attacks
- No heroes and no magic
- Only some creature abilities are supported: no enemy retaliation, double attack
