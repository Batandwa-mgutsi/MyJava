import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@DisplayName("Alphabet Class")
@RunWith(Enclosed.class)
public class TestOfAlphabet {

  @Nested
  @DisplayName("contains(char letter)")
  public static class Contains {

    @Test
    @DisplayName("Should return true if the given letter is in the alphabet")
    public void shouldReturnTrueIfLetterIsContained() {
      final Alphabet instance = new Alphabet("orange");

      assertTrue(instance.contains('o'));
      assertTrue(instance.contains('r'));
      assertTrue(instance.contains('a'));
      assertTrue(instance.contains('n'));
      assertTrue(instance.contains('g'));
      assertTrue(instance.contains('e'));
    }

    @Test
    @DisplayName("Should return false if the given letter is NOT in the alphabet")
    public void shouldReturnFalseIfLetterIsNotContained() {
      final Alphabet instance = new Alphabet("orange");

      assertFalse(instance.contains('q'));
    }
  }

  @Nested
  @DisplayName("canSpell(String word)")
  public static class CanSpell {

    @Test
    @DisplayName("Should return true if the alphabet contains all the letters in word")
    public void shouldReturnTrueIfAlphabetCanSpellWord() {
      final Alphabet instance = new Alphabet("orange");

      assertTrue(instance.canSpell("groan"));
    }

    @Test
    @DisplayName("Should return false if the alphabet does not contain at least one letter from word")
    public void shouldReturnFalseIfAlphabetCannotSpellWord() {
      final Alphabet instance = new Alphabet("orange");

      assertFalse(instance.canSpell("oranges"));
    }
  }
}
