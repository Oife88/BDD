package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

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
   /* @Test
    void shouldTransferMoneyFirstToSecond() {
      var firstCardInfo = DataHelper.getFirstCardInfo();
      var secondCardInfo = DataHelper.getSecondCardInfo();
      var firstCardBalance = DashboardPage.getCardBalance(firstCardInfo);
      var secondCardInfo = DataHelper.getSecondCardInfo(secondCardInfo);
      var amount = generateValidAmount(firstCardBalance);
      var expectedBalanceFirstCard = firstCardInfo - amount;
      var expectedBalanceSecond = secondCardInfo + amount;
      var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
      dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
      var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
      var actualBalancesecond = dashboardPage.getCardBalance(secondCardInfo);
      assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
      assertEquals(expectedBalanceSecondCard,actualBalanceSecondCard);
    }

  @Test
  void pageObjectTest2() {

    var firstCardBalance = DataHelper.getFirstCardBalance();
    var secondCardBalance = DataHelper.getSecondCardBalance();
    var firstCardInfo = dashboardPage.getCardBalance(firstCardBalance);
    var secondCardInfo = dashboardPage.getCardBalance(secondCardBalance);
    var amount = DataHelper.generateValidAmount(firstCardInfo);
    var expectedBalanceOfFirstCard = firstCardInfo - amount;
    var expectedBalanceOfSecondCard = secondCardInfo + amount;
    var transferPage = dashboardPage.selectCardToTransfer(secondCardBalance);
    dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardBalance);
    var actualBalanceOfFirstCard = dashboardPage.getCardBalance(firstCardBalance);
    var actualBalanceOfSecondCard = dashboardPage.getCardBalance(secondCardBalance);
    assertEquals(expectedBalanceOfFirstCard, actualBalanceOfFirstCard);
    assertEquals(expectedBalanceOfSecondCard, actualBalanceOfSecondCard);

  }



  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV3() {
    var loginPage = open("http://localhost:9999", LoginPageV3.class);
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }*/
}

