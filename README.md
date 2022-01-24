# 8puzzle

This is a modified 8 puzzle which need me to keep track of total cost as cost of per move was based off the swapping tile. 

The algorithms implemented are: 

BFS,
DFS,
UFS,
GBF,
A*

For the A* three different Heuristics were implemented 

1. Number of incorrect position
2. Manhatten distance of each misplaced tile
3. Sum for each tile ( Cost of tile * tile's manhattan distance)

Additional Information: 

Three boards were provided to be the test cases and were based off difficulty to solve for the program. 
Some metrics that were mesure were Depth, Time, Cost, and Max stack size

ToDo: 

Some of the algorithms don't return anything which is due to constant increast in queue size. This is probably due to my implementation to check for which state has been visited already and if there are any repeated ones in the current queue. I will try to work out my logic and commit changes as I find my solution. 
