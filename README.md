# Pair generator
## What does it use for
  This small project is aim at generating pair schedule for the team.
  It will generate a perfect schedule for you, a perfect schedule require:
  - Everyone in the team should pair with each other just for one time.
  - If the team size is even, then in every round, every one should have someone to pair with. 
  - If the team size is odd, every one should have one time to solo(the program will tell you someone will pair with nobody).
  
For example, Suppose we have 4 guys(A,B,C,D) in the team, the perfect schedule is :
   - Round1:A pair B, C pair with D.
   - Round2:A pair C, B pair with D. 
   - Round3:A pair D, B pair with C.

It's easy to figure out manually when the team size is small, but when the team getting bigger, it's hard to make a perfect schedule.
So this project is to help you make a pair plan automatically even with a big team size.

## How to use
In the `PairGenerator.class`, replace the team member's name. If you want to add more member, just add them into the set `teamMembers`
then run the application, you can see the result printed in the console.

Note: If the team size is and odd, the application will add one member call `Nobody`(see PairUtils) to the team, which means that when we generate the pair schedule, 
**we always use even team size**.

## version list
### version V2
This version change the algorithm,and solves the bug in V1 that the program will break down when team size get bigger.
It's quickly when the team size is small, but the team size getting bigger, it will be slowly, I will try to optimize performance in latter version

### version V1
Shortage(found at 2022.1.12)
I'm sorry to inform you that, this program is not perfect.
**It doesn't work when the team number is over than 8 member.**
After using a random team size to test, I found that, when the team size go bigger, this program will fail get the answer,
due to the rollback process doesn't work, I am trying to use a new algorithm to fix this issue.
