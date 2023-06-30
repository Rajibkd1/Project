import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Search {
    public static boolean searchWord(String text, String word) {
        int n = text.length();
        int m = word.length();
        int[] lps = computeLPSArray(word);

        int i = 0; // for the text
        int j = 0; // for the word

        while (i < n) {
            if (text.charAt(i) == word.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return true; 
                }
            } else {
                if (j!=0) {
                    j=lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false; 
    }

    public static int[] computeLPSArray(String word) {
        int m =word.length();
        int[] lps =new int[m];

        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;

        while (i < m) {
            if (word.charAt(i) == word.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len-1];
                } else {
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Asus\\Downloads\\PIC\\Output.txt";
        String wordToSearch = "নোয়াখালী";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isMatchFound = false;

            while ((line = reader.readLine()) != null) {
                if (searchWord(line, wordToSearch)) {
                    isMatchFound = true;
                    break;
                }
            }

            if (isMatchFound) {
                System.out.println("Match found!");
            } else {
                System.out.println("Match not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
