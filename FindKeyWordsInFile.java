import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindKeyWordsInFile {
    AVLTree<String, Integer> wordFrequencies;
    AVLTree<String, Integer> englishWords;
    AVLTree<String, Integer> keywordFrequencies;
    String kwords;
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String englishWordsFileName = args[2];
        FindKeyWordsInFile check = new FindKeyWordsInFile();
        check.computeWordFrequencies(k,inputFileName, englishWordsFileName);
    }

    public void computeWordFrequencies(int k, String inputFileName, String englishWordsFileName){
        this.wordFrequencies = new AVLTree<String, Integer>();
        this.englishWords = new AVLTree<>();
        this.keywordFrequencies = new AVLTree<>();

        try {
            //Part 1
            // function name => computeWordFrequencies
            //reads input from a specified file
            Scanner sc=new Scanner(new File(inputFileName));

            //reads the input file line by line until there are no more lines to read(end of file).
            while(sc.hasNext()){
                String str=sc.nextLine();
                //stores each word separated by space into the array "ln"
                String[] ln=str.split(" ");
                //converts each word stored in the array "ln" to lowercase
                //checks if the last character of each word stored in the array "ln" is a non-alphabetic character
                //If true(last character of word is a non-alphabetic character) then stores the word without the last character
                for(int i=0;i< ln.length;i++){
                    str=ln[i].toLowerCase();
                    while(str.length()>0 && str.charAt(str.length()-1) < 65){
                        str=str.substring(0,str.length()-1);
                    }
                    while(str.length()>0 && str.charAt(0) < 65){
                        str=str.substring(1);
                    }
                    //checks if the first character of each word stored in array "ln" is alphabetic
                    //If true(first character is alphebetic) then checks if the word already exists in the AVL tree "wordFrequencies"
                    //If the word doesn't exist in the AVL tree "wordFrequencies" then the word is added into the AVL tree with a value of 1
                    //If the word exists in the AVL tree "wordFrequencies" then it retrieves the value and increments it by 1
                    if(str.length()>0 && Character.isAlphabetic(str.charAt(0))){
                        if (wordFrequencies.get(str) == null) {
                            wordFrequencies.put(str, 1);
                        } else {
                            Integer n = wordFrequencies.get(str);
                            wordFrequencies.get(str, n).value++;
                        }
                    }
                }
            }


            //Part 2
            // function name => findKMostFrequentWords
            //creates a priority queue called "MaxHeap" which stores all the nodes of AVL tree "wordFrequencies" in descending order of their values
            PriorityQueue<AVLTree.node> MaxHeap=new PriorityQueue<AVLTree.node>();
            wordFrequencies.fillheap(MaxHeap);

            //Part 3
            // function name => filterCommonEnglishWords
            //that reads data from the file specified by the "englishWordsFileName" variable
            sc=new Scanner(new File(englishWordsFileName));
            //reads the input file line by line until there are no more lines to read(end of file)
            //seprates each word in the line and adds it to the AVL tree "englishWords" with the value of 1
            while(sc.hasNext()){
                String word=sc.nextLine().strip();
                englishWords.put(word, 1);
            }
            //iterates through the MaxHeap
            for(int i =0;i<k;i++){
                //checks if the priority queue is empty by retrieving the element with the highest priority in the queue
                AVLTree.node temp=MaxHeap.poll();
                if(temp==null){
                    System.out.println("value of k too large");
                }
                //checks each word in MaxHeap against AVL tree "englishWords" if the word exists in AVL tree "englishWords" then it is skipped
                //If the word doesnt exist in AVL tree "englishWords" then its key-value pair is added to the AVL tree "keywordFrequencies"
                if(englishWords.contains( (String) temp.key)){
                    i--;
                }else{
                    keywordFrequencies.put((String) temp.key, (Integer) temp.value);
                }
            }
            //orders the AVL tree "keywordFrequencies" in alphabetical order
            kwords = keywordFrequencies.inOrderTraversal();
            System.out.println(kwords);
        }
        catch (Exception e) {
            //prints "null value exception" if the value of k entered is too large
            if(e instanceof NullPointerException) System.out.println("null value exception");
            //prints "filename not valid" if the filename entered is incorrect
            if(e instanceof FileNotFoundException) System.out.println("filename not valid");
//            e.printStackTrace();
        }
    }

}

