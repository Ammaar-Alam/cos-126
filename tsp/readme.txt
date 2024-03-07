Programming Assignment 8:  Traveling Salesperson Problem


/******************************************************************************
 *  Approximate number of hours to complete this assignment
 ******************************************************************************/

Number of hours:
19-ish idk man i hated this assignment idk why i wanted the leaderboard so badly 😭

/******************************************************************************
 *   Explain how you implemented the nearest insertion heuristic.
 ******************************************************************************/
This heuristic was implemented by starting with a one-point tour and iteratively
adding each new point after the point to which it is closest. For each new point,
the algorithm calculates the distance to each point in the current tour, finding
the point with the minimum distance, and then inserts the new point after this closest point.

/******************************************************************************
 *  Explain how you implemented the smallest insertion heuristic. Describe
 *  only the differences between this heuristic and the nearest insertion one.
 ******************************************************************************/
This heuristic is similar to the nearest insertion but differs in the insertion criterion.
Instead of inserting the new point after the nearest point, it is inserted at the position
where it causes the least increase in the tour's total length. This is achieved by calculating
the increase in length for inserting the new point after each point in the current tour and
selecting the position that results in the smallest increase.

/******************************************************************************
 *  Explain the advantage of using a linked list instead of an array.
 ******************************************************************************/
A linked list is advantageous because it allows for efficient insertions and deletions.
Unlike arrays, there is no need to shift elements or allocate new arrays for size changes.
This flexibility is particularly useful in the TSP problem, where we frequently insert new points into the tour.



/******************************************************************************
 *  Explain what is the advantage of using a *circular* linked list instead
 *  of a null-terminated linked list.
 ******************************************************************************/
A circular linked list simplifies the traversal and insertion process by eliminating
the need to check for null pointers. In the TSP problem, where we return to the starting
point, a circular linked list naturally represents the tour's cyclic nature, making operations
more straightforward and less error-prone.

/******************************************************************************
 *  In the table below, fill in the lengths that are computed by the two
 *  heuristics (nearest insertion and smallest insertion).
 ******************************************************************************/


data file      nearest insertion     smallest insertion
-----------------------------------------------------
tsp10.txt         1566.1363             1655.7462
tsp100.txt        7389.9297             4887.2190
tsp1000.txt       27868.7106            17265.6282
usa13509.txt      77449.9794            45074.7769

/******************************************************************************
 *  Do two timing analyses. Estimate the running time (in seconds)
 *  of each heuristic as a function of n, using expressions of the
 *  form: a * n^b, where b is an integer.
 *
 *  Explain how you determined each of your answers. You must include
 *  your experimental data.
 *
 *  To get your data points, run the two heuristics for n = 1,000,
 *  and repeatedly double n until the execution time exceeds 60
 *  seconds.
 *
 *  You may use TSPTimer to help do this, as per the checklist.
 *  If you do so, execute it with the -Xint option. This turns off
 *  various compiler optimizations, which helps normalize and
 *  stabilize the timing data that you collect.
 *
 *  If n = 1,000 takes over 60 seconds, your code is too slow.
 *
 *  Use the table below to provide your data for n = 1000, 2000
 *  etc.
 *
 ******************************************************************************/

n               nearest time           smallest time
------------------------------------------------------------
1000            0.025                  0.06
2000            0.089                  0.235
4000            0.341                  0.931
8000            1.345                  3.689
16000           5.367                  14.705
32000           21.394                 58.417
64000           85.772                 237.618

Nearest Heuristic:
T(n) = 7.99 * 10^-8 x n^1.83
Using the doubling method, the ratio of running times for n and 2
2n was calculated. For instance, when n doubled from 1,000 to 2,000, the time
increased from 0.025s to 0.089s. This ratio was used to estimate b, which was
found to be approximately 1.83.


Smallest Heuristic:
T(n) = 7.34 * 10^-8 x n^1.97
Similarly, for the smallest heuristic, the doubling method was used.
When n doubled from 1,000 to 2,000, the time increased from 0.06s to 0.235s.
This ratio was used to estimate b, which was found to be approximately 1.97.

/******************************************************************************
 *  If you implemented your own heuristic, please describe here how it differs
 *  from the assignments' heuristics, and why you think it will perform better.
 ******************************************************************************/

[ if you made a Leaderboard submission, you may copy your answer here ]
my heuristic combines the greedy approach with an enhanced 2-opt optimization.
initially, a tour is generated using a greedy algorithm that always extends the
tour by choosing the nearest unvisited point. this method ensures a quick construction
of a complete tour but doesn't guarantee the shortest possible path (aint no way i can
find that, already killed my sanity on this alone. i went insane trying to make it
shorter while still running usa13509 within 60 seconds). the enhanced 2-opt
optimization is then applied iteratively to further improve the tour length.
(i think? i wrote this damn thing and idek how it works fully lmfao)


/******************************************************************************
 *  If you implemented your own heuristic, please provide the lengths of
 *  these heuristics for the following two input files.
 ******************************************************************************/

[ if you made a Leaderboard submission, you may copy your answer here ]

- distance on tsp1000.txt: 16408.49448851058
- distance on usa13509.txt: 42611.66645063329



/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?
just my sanity dying hehe



/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/
n/a
