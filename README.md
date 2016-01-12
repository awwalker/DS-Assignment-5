# DS-Assignment-5

Programming Project 5

Find Words Revisited

In this project you will revisit your project 2 implementation of a program that produces all possible words given a set of letters and a dictionary. Your program should produce all anagrams of all different lengths of a given set of letters that are valid words in the provided language dictionary. For example, if the letters are
recounts
your program should print, in alphabetical order and one per line, all different words that can be made out of those letters (assuming that they are present in the dictionary that the program uses):
construe
counters
recounts

The program that you write has to be command line based (no graphical interface). It should use the dictionary from a file provided as a command line argument and it should prompt the user for a set of letters. The output should be printed to the terminal. Once the anagrams are displayed, the program should terminate.
Your program should use the implementation provided with this assignment. You should make changes only to the Dictionary class and provide classes that are required by the Dictionary class.

## Objectives

The goal of this programming project is for you to master (or at least get practice on) the following tasks:

* using/modifying existing
* implementation of a generic binary search tree (or AVL tree)
* extending existing classes
* writing Java programs

## The Program Input and output

You should not change this part of the implementation.

## Computational Task

Once the user has entered the letters, the program displays all the words in the dictionary that can be formed as combinations of all the letters entered by the user.

### Creating all Possible Words

The task of creating possible words should be achieved recursively using the backtracking technique.
The LetterBag class that performs this computation is provided for you and you should not be making any changes to it. Your Dictionary class need to provide all the tools required by this class to work properly.

### Searching in the Dictionary

In order to determine if a given sequence is a valid word in the dictionary, your program needs to perform searches. As before, you need to provide a Dictionary class that allows to search for particular words and for the prefixes to words.

## Program Design

Your program must contain at least four classes (some of them are provided):
* BST <E> class (or AVL <E> class) that implements a binary search tree. All of its methods should be implemented recursively (adding, removing and searching). You will need to define your own generic node class for this BST <E> class.
* Dictionary class that represents the collection of words read in from the input file (i.e, the dictionary used by the program). This class is responsible for performing queries in the dictionary. The Dictionary class should extend the BST <String> class (or AVL <String>). This means that words should be stored in a binary search tree, not an ArrayList.
* LetterBag class to represent the letters entered by the user/player. This class is given and you should not make any changes to it.
* FindWords class that is the runnable program containing the main() method. This class is given and you should not make any changes to it.

 
