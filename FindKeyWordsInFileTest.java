import java.util.Objects;

public class FindKeyWordsInFileTest {
    public static void main(String[] args){
        FindKeyWordsInFile test = new FindKeyWordsInFile();
        test.computeWordFrequencies(5, "file2.txt", "MostFrequentEnglishWords.txt");
        //test1
        if(test.wordFrequencies.get("the").compareTo(11)==0 &&
           test.wordFrequencies.get("and").compareTo(3)==0 &&
           test.wordFrequencies.get("italian").compareTo(2) == 0 &&
           test.wordFrequencies.get("considered").compareTo(1) == 0){
            System.out.println("word counts are fine, test 1 passed");
        }
        else{
            System.out.println("counting word frq wrong, test 1 failed");
        }
//        System.out.println(test.keywordFrequencies.get("the") + " " + test.keywordFrequencies.get("and") +  " " + test.keywordFrequencies.get("italian") + " " + test.keywordFrequencies.get("considered"));
        // test 2
        if(Objects.equals(test.kwords.strip(), "italian:2 lisa:2 painting's:1 paris:1 parodied:1")){
            System.out.println("the k most freq words are correct, test 2 passed");
        }
        else{
            System.out.println("the k most freq words are wrong, test 2 failed");
        }
        // test 3
        if(test.keywordFrequencies.get("the") == null && test.keywordFrequencies.get("is") == null &&test.keywordFrequencies.get("a") == null ){
            System.out.println("test 3 passed, filters common english words correctly");
        }
        else{
            System.out.println("test 3 failed, does not filter common english words correctly");
        }
        // test 4
        test.computeWordFrequencies(10,"file3.txt", "MostFrequentEnglishWords.txt");
        if(test.kwords.equals("elephant:8 kangaroo:4 lily:8 umbrella:8 violet:8 waffle:4 watermelon:4 willow:4 x-ray:4 xylophone:4 ")){
            System.out.println("test case 4 passed, correct output for test file3");
        }
        else{
            System.out.println("test case 4 failed, wrong output for test file3");
        }
        // test 5
        try{
            test.computeWordFrequencies(0, "empty", "MostFrequentEnglishWords.txt");
            System.out.println("Handles empty files, test 5 passed");
        }catch(Exception e){
            System.out.println("Does not handle empty files, test 5 failed");
        }
        // test 6
        try{
            test.computeWordFrequencies(10,"blah","blah");
            System.out.println("handles exception for non-existing filename, passes test number 6");
        }
        catch(Exception e){
            System.out.println("test 6 failed, does not handle wrong filenames");
        }
    }
}
