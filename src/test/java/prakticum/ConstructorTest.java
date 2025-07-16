package prakticum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void testBunsSection() {
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        assertEquals("Булки", mainPage.getActiveSectionText());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void testSaucesSection() {
        mainPage.clickSaucesSection();
        assertEquals("Соусы", mainPage.getActiveSectionText());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void testFillingsSection() {
        mainPage.clickFillingsSection();
        assertEquals("Начинки", mainPage.getActiveSectionText());
    }
}