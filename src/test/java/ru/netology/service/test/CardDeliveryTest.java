package ru.netology.service.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.service.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(String.valueOf(firstMeetingDate));
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button_size_m").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(BACK_SPACE);
        $("[data-test-id=date] input").setValue(String.valueOf(secondMeetingDate));
        $(".button_size_m").click();
        $("[data-test-id=replan-notification] .notification__content").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        $(".button_size_s").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }


    @AfterEach
    void closeWebBrowser() {
        closeWebDriver();
    }
}

