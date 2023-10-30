# Most-Frequent-Word-Finder
This program is a simple java keyword extraction program from a text file. The name of the program is FindKeyWordsInFile. A keyword is a word that frequently occurs in a file and yet is not a very common English word. The first step will be to read a text file, say "file.txt," and count the frequency of all the words it contains. For example, if the word "apple" occurs 3 times in "file.txt", its frequency should be set to 3. The next step is to extract k most frequent words from "file.txt", where k will be provided as a command line argument. Most of these k words will be simply the most common English words like "the", "to", "from", etc. They do not tell us much about the contents of the file. Therefore, the last step is to find, among the k extracted frequent words from" file.txt", all the words that are not common English words. For this purpose, I am giving you the file "MostFrequentEnglishWords.txt", which contains 1000 most common English words. Your program should output only those words among the k most frequent in" file.txt" that are not in the file "MostFrequentEnglishWords.txt".

There are three command-line arguments to your program. It should be evoked as:
                                               
                                  Java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt

Where,

• The first input argument is an integer k which specifies how many most frequent words to consider. For example, if k = 100, then only the 100 most frequent words in "file.txt" are eligible to become the keywords.

• The second command line argument is the name of the file, "file.txt," to extract keywords from.

• The last command line argument, "MostFrequentEnglishWords.txt" is a text file that contains the most frequent English words. 

The program should output a list of keywords and their frequencies in alphabetical order. Each keyword and its frequency should be displayed on a separate line. For instance, if the words "apple" (frequency 5), "ball" (frequency 6), and "rocket" (frequency 4) were extracted by the program, the output should appear as follows:

apple 5

ball 6

rocket 4
