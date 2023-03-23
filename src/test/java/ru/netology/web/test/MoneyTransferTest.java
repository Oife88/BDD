package ru.netology.web.test;


import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTransferTest {

    @Test
    void pageObjectTest() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = DataHelper.generateValidAmount(firstCardBalance);
        var expectedBalanceOfFirstCard = firstCardBalance - amount;
        var expectedBalanceOfSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceOfFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceOfSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceOfFirstCard, actualBalanceOfFirstCard);
        assertEquals(expectedBalanceOfSecondCard, actualBalanceOfSecondCard);

    }

}

