# Programming-Assignment-3


Problem 1)

First let's address Minotaur's initial plan and what went wrong:
"The servants realized at the end of the day that they had more presents than “Thank you” notes" this problem came from the the unorganized manner the servants were adding gifts to the chain and writing thank you cards and removing/setting aside presents. Multiple thank you cards were written for some presents.

For my implementation, I chose to implemment my concurrent linked list with a ConcurrentLinkedQueue in order to ensure a lock free and wait-free implementation. My threads alternate task and whenever a task is taken the altTask flag is changed so the next servant takes on the other task. 
With task 1, servants search for present in the unordered "bag" (Hashset) in ID order and adds the present to the ordered chain 
Task 2 is where servants take from the ordered chain and write thank you notes, also removing the present from the ordered chain
I track the num of thank you cards written and print this at the end of the run
When running, this program will ask how many presents (enter num of presents here) and this will be where you input the amount of presents! 
Our threads finish their tasks before another thread can finish its own task, this is implemented using join() and also with the ConcurrentLinkedQueue.

500,000 presents will compile and run , its just a tad over 60 seconds ): It can do it, just be patient!

to compile: run command javac Problem01.java java Problem01



Problem 2)

When running, this program will ask how many hourly reports you want to run, enter this number!
My implementation is using an ArrayList that all threads have access to as storage
I have 60 runs (60 minutes or one hours) of each of my 8 sensors reading a temperature (-100 to 70 degrees) and reporting that to the ArrayList, thats 480 readings in total
Joining my threads after I start them ensures that my sensors do not overstep each other, the eight readings happen in one run of the loop and are ordered by reads. One minute's reads won't happen before another minute's; The first 8 temp reads in my array list are from the first minute and so on. 
After this the report will be compiled from whats stored in the arraylist


This will return the top 5 (unique )hourly temps, top 5 lowest, and the interval of the highest ten min interval temp difference (1-6)
this will loop through however many reports you inputted

to compile: run command javac Problem2.java java Problem2
