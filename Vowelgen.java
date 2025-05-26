import java.io.*;
import java.util.*;

public class Vowelgen {
    public static void main(String[] args) {
        
        String inputFile = "para.txt";
        String outputFile = "vowel_words_output.txt";

        
        Map<String, Integer> vowelWordCounts = new LinkedHashMap<>();
        int totalVowelWords = 0;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken().replaceAll("[^a-zA-Z]", ""); 
                    if (!word.isEmpty() && startsWithVowel(word)) {
                        word = word.toLowerCase(); 
                        vowelWordCounts.put(word, vowelWordCounts.getOrDefault(word, 0) + 1);
                        totalVowelWords++;
                    }
                }
            }

            
            writer.write("Words starting with vowels and their counts:\n");
            for (Map.Entry<String, Integer> entry : vowelWordCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            writer.write("\nTotal number of vowel-starting words: " + totalVowelWords + "\n");

            System.out.println("Vowel word counts written to: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static boolean startsWithVowel(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        return "aeiou".indexOf(firstChar) != -1;
    }
}
