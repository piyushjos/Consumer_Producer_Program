# Consumer_Producer_Program
Write a Producer-Consumer multi-threaded Java program.

Introduction
One thread, the producer, will generate a sequence of integers and write them in order to successive buffer cells. Several consumer threads will then read these integers from the buffer and print them out in the order read. The is a circular buffer shared between the producer and consumers.

Tasks
Create a single producer thread that produces a stream of integers and writes them into a circular buffer, and several consumer threads that read the integers from the buffer and print them to the console.

The producer thread should produce integers until the buffer is full; then, it will wait until the consumers remove some entries from the buffer. The producer should continue to create integers until it reaches a maximum specified on the command line, then it should exit.

The consumer threads should consume integers from the buffer until it is empty, then wait for the producer to put more integers into the buffer. The consumers should continue to remove integers from the buffer, and print them until they have removed and printed all of them; then, they should exit.

Note that this requires several shared variables and data structures: the buffer, input and output locations, and counts of integers produced and consumed.

2.1 Thread Entry Points

Each thread must have a function that represents the entry point into the thread's flow of control.

You must therefore implement several functions to serve as thread entry points:

int producer() Generates a sequence of integers starting at 0, and writes them to successive buffer locations.
int consumer() Reads the next entry from the buffer and writes the integer contained therein to the console.
2.2 Circular Buffer Abstraction

Use Blocking Operations: You use condition variables to make a function blocking. The thread library provides a function that blocks until the specified condition variable is true. Thus any function that includes a call to that function becomes a blocking operation.

Note that you need to protect the critical section of each of the operations. So use a mutex or binary semaphore with lock() implementing the semaphore wait operation, and unlock() implementing the signal operation.

2.3 Creating threads

Once you have implemented your producer and consumer functions, you have to create producer and consumer threads in your program. Do this in main(). Note: your program should take one argument: the number of consumer threads to create.

Deliverables
Submit a zip file containing the following items:

  1. All of your source files, tested.
  2. An executable that runs the program so your TA doesn't have to build your code to run and grade it
  3. Screenshots of the Console showing it working so we can give you the benefit of the doubt in some cases if it doesn't run properly for the TA
  4. Demo video
  5. A README file, this format:

                             COEN 283 Assignment 1

      Your info

      -------

      # first-name last-name id email

 

      Results

      -------

      # In what order were the integers printed?

 

      Discussion

      ----------

      # Answer the following:

How many of each integer should you see printed?
In what order should you expect to see them printed? Why?
Did your results differ from your answers in (1) and (2)? Why or why not?
Evaluation
Your implementation will be graded in terms of correctness (50%), clarity of implementation (15%), and commenting and style (15%), and demo/presentation (20%).  Report the execution times of the test programs on each of the sample inputs and outputs.

Failure to provide complete documentation (either within the source file, as a detailed header comment, or as an accompanying README file), will result in a significant penalty (up to sixty percent), as would providing code that does not solve the problem, or is not readily testable (the remaining forty percent of the grade). It is your responsibility to make sure that your solution can be evaluated, and that you provide adequate instructions on how to do so. 

 5. Hints

Build your program in a sequence of stages, testing and saving each stage before going on to the next stage.
Write a skeleton with one producer thread that just writes integers to the console.
Enhance the producer to write integers to the buffer. When it fills the buffer, have it exit, then print the entire buffer out.
Implement and test the circular buffer.
Put the printing part inside the producer: when the buffer is full, print and remove the integers from within the producer.
Finally, implement the consumer threads.
Debugging concurrent programs is difficult, so you must have confidence in your basic components before you put them into threads.
