Assumptions:
- State is shared between multiple potential clients
- Since state is shared, rather than throwing an error on prev() when the list is at the beginning, it returns zero
- The equivalent happens at the upperbound for longs.

Design Notes:
- I attempted something somewhat canonical for Spring, so most of the logic is in the service layer.
- Base case prepopulates for items 0,1, which makes the tabulation for the rest of the fibonacci items better
- It occurred to me to use `BigInt`, since the upper bound for fibonacci numbers in `long` form is not _that_ large, however I thought `long` had a high enough ceiling to be satisfying, faster, easier to test, while also emulating the fact that a production server should have some reasonable limit rather than extending to an unreasonable weight on server capacity.
- I also considered using a 302 redirect on an error for '/previous' and 'next' however since this uses a REST Controller, it seemed better not to.