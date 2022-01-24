# 8puzzle
MODIFIED 8 PUZZLE

This is my implementation of the 8 puzzle in which I had to keep track of each tile value which would be used to calculate the total cost of moving tiles. 

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

Some of the algorithms don't return anything which is due to constant increase in queue size. This is probably due to my implementation to check for which state has been visited already and if there are any repeated ones in the current queue. I will try to work out my logic and commit changes as I find my solution. 

Update: A lot of them do work but it takes a long time. Astar will return an answer after running for four mins
