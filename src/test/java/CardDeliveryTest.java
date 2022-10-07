import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    public void cardDeliveryValid() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//input[@placeholder='Город']").setValue("Санкт-Петербург");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.CONTROL, "a");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        String forwardDate = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $x("//input[@placeholder='Дата встречи']").setValue(forwardDate);
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79011234567");
        $("[data-test-id = \"agreement\"]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + forwardDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }

}