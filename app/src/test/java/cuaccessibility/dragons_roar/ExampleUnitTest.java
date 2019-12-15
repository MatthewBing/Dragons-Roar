package cuaccessibility.dragons_roar;

import android.widget.TextView;


import org.junit.Test;

import static org.junit.Assert.*;
import android.os.Bundle;
import cuaccessibility.dragons_roar.*;
import res.layout.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
voiceButton button = new voiceButton();
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void simple_battle_returnsTrue(){
        new assertThat(button.testInputString("What's my class?")
    }
}