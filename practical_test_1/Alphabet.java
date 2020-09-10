
/**
 * The alphabet of a word (or collection of words) is the collection of unique
 * letters from which it (or they) can be spelt. An alphabet object represents
 * such a an alphabet.
 */
public class Alphabet {
  // A string of the unique letters in this alphabet. No letters appears more
  // than once.
  private String letters;

  public Alphabet(String alphabetLetters) {
    this.letters = alphabetLetters;
  }

  /**
   * Returns true if this Alphabet contains the given letter, false otherwise.
   */
  public boolean contains(char letter) {
    return letters.indexOf(letter) != -1;
  }

  /**
   * Returns true if the given word can be spelt with this alphabet(i.e there is
   * no) letter in word that does not appear in letters, false otherwise.
   */
  public boolean canSpell(String word) {
    for (int index = 0; index < word.length(); index++) {
      final char letter = word.charAt(index);
      if (!this.letters.contains("" + letter)) {
        return false;
      }
    }

    return true;
  }
}