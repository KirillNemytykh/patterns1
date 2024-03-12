package ru.netology;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppReplanDeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan meeting")
    void shouldBeSuccessufully() {
        Data.UserInfo validUser = Data.Registration.generateUser("ru");
        int day1 = 5;
        String FistMeeting = Data.GenerateDate(day1);
        int day2 = 7;
        String SecondMeeting = Data.GenerateDate(day2);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(FistMeeting);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement'] ").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + FistMeeting))
                .shouldBe(visible);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(SecondMeeting);
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $("[data-test-id='replan-notification'] button ").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + SecondMeeting))
                .shouldBe(visible);
    }
}