# CST370-HW5_2_Java

(hw5_2) Write a Java program for hw5_2 to simulate the operations of linear probing covered in the lecture

Input format: This is a sample input from a user:

5
10
insert 17
insert 12
displayStatus 2
tableSize 
insert 20
tableSize
search 20
search 15
displayStatus 1
displayStatus 2

The first line (= 5 in the example) is the initial size of the hash table. The size will be always a prime number. The second line (= 10 in the example) indicates the number of commands you have to conduct to the hash table. The commands include “insert” (= insert a key to the table), “displayStatus” (= display the status of an entry in the table), “tableSize” (= display the size of the table), and “search” (= search a key in the table). 
For the homework, you can assume that the “insert” numbers are non-negative. They will be zero or greater than zero. 

Note that if the load factor becomes greater than 0.5 after a new insert, you have to conduct the rehashing. In other words, you have to find the first prime number after doubling the current table size and move the keys in the current table to the new table. After that, you have to insert the new key value. For this homework, you can assume that the table size is always less than 200. In other words, we will not test the case which requires a table size with more than or equal to 200. 

This is the correct output. For the “displayStatus” command, your program should display the status of an entry of the table. For example, your program should display “17” for the first “displayStatus 2” command. For the second “displayStatus 2” command, it should display “Empty”.

17
5
11
20 Found
15 Not found
12
Empty

Sample Run 1: Assume that the user typed the following lines

7
7
insert 100
insert 16
insert 37
displayStatus 3
displayStatus 2
search 37
tableSize

This is the correct output:

16
100
37 Found
7

