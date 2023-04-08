import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WordPuzzleGame {
    private static final int MAX_ATTEMPTS = 4;
    private static final int NUM_WORDS_PER_LEVEL = 10;
    private static final int HINT_PENALTY = 2;
    private static int MAX_HINTS = 3;

    private int level;
    private int score;
    private int wordsGuessed;

    private String[] words;
    private int currentWordIndex;

    public WordPuzzleGame() {
        level = 1;
        score = 0;
        wordsGuessed = 0;
        words = new String[] { "apple", "banana", "cherry", "durian", "french", "cat", "dog", "fish", "bird", "bear",
                "german", "italian", "japanese", "korean", "spanish", "turkish", "lion", "tiger", "zebra", "giraffe",
                "elephant", "monkey", "gorilla", "hippopotamus", "rhinoceros", "elderberry", "lion", "tiger", "zebra",
                "fig", "guava", "tomato", "potato", "onion", "lychee", };

        // Remove duplicates
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        words = uniqueWords.toArray(new String[0]);

        // Shuffle array
        Collections.shuffle(Arrays.asList(words), new Random());
        currentWordIndex = 0;
    }

    public void play() {
        
        Map<Integer, Double> numBlanksMap = new HashMap<>();
        numBlanksMap.put(1, 0.3);
        numBlanksMap.put(2, 0.4);
        numBlanksMap.put(3, 0.5);
        numBlanksMap.put(4, 0.6);
        numBlanksMap.put(5, 0.7);
    
        try (Scanner scanner = new Scanner(System.in)) {
            while (level < 2 || (level >= 2 && level <= 5 && wordsGuessed < NUM_WORDS_PER_LEVEL)) {
                String word = words[currentWordIndex];
                int numChars = word.length();
                int numBlanks = (int) Math.round(numChars * numBlanksMap.getOrDefault(level, 0.1));
                
                StringBuilder display = new StringBuilder();
                for (int i = 0; i < numChars; i++) {
                    if (i < numBlanks) {
                        display.append("_");

                    } else {
                        display.append(word.charAt(i));
                    }
                }

                int attempts = 0;
                boolean guessed = false;

                while (attempts < MAX_ATTEMPTS && !guessed) {
                    System.out.println("Level " + level + " - Word " + (wordsGuessed + 1) + ": " + display);
                    System.out.print("Guess a letter or the whole word to continue playing or exit to quit game: ");
                    String guess = scanner.nextLine();

                    if (guess.equalsIgnoreCase("exit")) {
                        System.out.println("Thanks for playing!" + " Final score: " + score);
                        return;
                    } else if (guess.equalsIgnoreCase("hint")) {
                        MAX_HINTS--;
                        if (attempts + HINT_PENALTY >= MAX_ATTEMPTS) {
                            System.out.println("Sorry, you can't use a hint on this word.");
                        } else {
                            System.out.println("Hint: The word starts with " + word.charAt(0));
                            attempts += HINT_PENALTY;
                        }

                        if (MAX_HINTS == 0) {
                            System.out.println(
                                    "Sorry, you have used all your hints. You are knocked off and brought back to the start. Your score is now 0.");
                            score = 0;
                            wordsGuessed = 0;
                            level = 1;
                            currentWordIndex = 0;
                        }
                        continue;
                    }

                    if (guess.equalsIgnoreCase(word)) {
                        System.out.println("Congratulations, you guessed the word!");
                        int points = numChars - attempts - 1;
                        if (points < 1) {
                            points = 1;
                        }
                        score += points;
                        System.out.println("You earned " + points + " points. Total score: " + score);
                        guessed = true;
                        wordsGuessed++;
                        currentWordIndex++;
                    } else if (guess.length() == 1 && word.indexOf(guess) != -1) {
                        System.out.println("Good guess!");
                        for (int i = 0; i < numChars; i++) {
                            if (word.charAt(i) == guess.charAt(0)) {
                                display.setCharAt(i, guess.charAt(0));
                            }
                        }
                    } else {
                        System.out.println("Sorry, that's not correct.");
                        attempts++;
                    }
                }

                if (!guessed) {
                    System.out.println("You have exceeded the maximum number of attempts. The word was: " + word);
                    score = 0;
                    wordsGuessed = 0;
                    level = 1;
                    System.out.println("You are knocked off and brought back to the start. Your score is now 0.");
                    currentWordIndex = 0;
                }

                else if (wordsGuessed == NUM_WORDS_PER_LEVEL) {
                    System.out.println("Congratulations, you have completed level " + level + "!");
                    level++;
                    wordsGuessed = 0;
                    currentWordIndex = 0;
                    if (level >= 2 && level <= 5) {
                        words = new String[] { "lemon", "lime", "peach", "pear", "plum", "strawberry", "watermelon",
                                "blueberry", "raspberry", "blackberry", "kiwi", "mango", "coconut", "pineapple",
                                "papaya", "avocado", "cherry", "durian", "elderberry", "coconut", "pineapple", "papaya",
                                "cherry", "durian", "elderberry", "fig", "guava", "jackfruit", "lychee",
                                "tomato", "potato", "onion", "lychee", "mangosteen", "pomegranate",
                                "potato", "onion", "garlic", "ginger", "cucumber", "carrot", "pepper", "garlic",
                                "ginger", "cucumber", "carrot", "pepper", "broccoli", "cauliflower", "spinach", "lettuce",
                                "cabbage", "asparagus", "peas", "beans", "corn", "rice",
                                "asparagus", "peas", "beans", "corn", "rice" };
                        // Remove duplicates
                        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
                        words = uniqueWords.toArray(new String[0]);

                        // Shuffle array
                        Collections.shuffle(Arrays.asList(words), new Random());
                    }
                    if (level > 5 && level <= 10) {
                        words = new String[] { "apple", "banana", "cherry", "durian", "french", "cat", "dog", "fish",
                                "bird", "bear", "german", "italian", "japanese", "korean", "spanish", "turkish", "lion",
                                "tiger", "zebra", "giraffe", "elephant", "monkey", "gorilla", "hippopotamus",
                                "rhinoceros",
                                "elderberry", "fig", "guava", "jackfruit", "lychee",
                                "mangosteen", "pomegranate", "tomato", "potato", "onion", "lychee", "mangosteen",
                                "pomegranate", "tomato", "potato", "onion", "garlic", "ginger", "cucumber", "carrot",
                                "pepper", 
                                "broccoli", "cauliflower",
                                "spinach", "lettuce", "cabbage", "asparagus", "peas", "beans", "corn", "rice", };
                        // Remove duplicates
                        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
                        words = uniqueWords.toArray(new String[0]);

                        // Shuffle array
                        Collections.shuffle(Arrays.asList(words), new Random());
                    }

                }
            }
        }

        System.out.println("Congratulations, you have completed all levels! Final score: " + score);
    }
}