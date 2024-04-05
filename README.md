Assumptions:
- State is shared between multiple potential clients
- Since state is shared, rather than throwing an error on prev() when the list is at the beginning, it returns zero

Design Notes:
- I attempted something somewhat canonical for Spring, but in a practical application, this lacks any persistence
- Base case prepopulates for items 0,1, which makes the tabulation for the rest of the fibonacci items better
- I started out with ints, but was unsatisfied with how few reloads of '/next' it took to reach the upper limit. I then planned out a whole solution which would switch to BigInts in the array, but only for the items that needed it to save on memory, but I decided that would look horrible. I decided using Longs instead gave a sufficiently satisfying upper limit.