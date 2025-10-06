/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import uk.gov.hmrc.configuration.TestEnvironment
import org.openqa.selenium.By
import org.scalatest.matchers.should.Matchers.*
import uk.gov.hmrc.selenium.webdriver.Driver
import org.junit.Assert
import org.openqa.selenium.support.ui.ExpectedConditions

import java.time.LocalDate

object Exclusions extends BasePage {

  private val exclusionsUrl: String =
    TestEnvironment.url("ioss-netp-exclusions-frontend")

  private val journeyUrl: String = "/pay-clients-vat-on-eu-sales/leave-import-one-stop-shop-netp"

  def goToExclusionsJourney(): Unit =
    get(exclusionsUrl + journeyUrl)

  def checkJourneyUrl(page: String): Unit =
    val url = s"$exclusionsUrl$journeyUrl/$page"
    fluentWait.until(ExpectedConditions.urlContains(url))
    getCurrentUrl should startWith(url)

  def answerRadioButton(answer: String): Unit = {

    answer match {
      case "yes" => click(By.id("value"))
      case "no"  => click(By.id("value-no"))
      case _     => throw new Exception("Option doesn't exist")
    }
    click(continueButton)
  }

  def enterDate(day: String): Unit = {

    val date =
      if (day == "today") {
        LocalDate.now()
      } else if (day == "mid-month") {
        LocalDate.now().withDayOfMonth(15)
      } else if (day == "yesterday") {
        LocalDate.now().minusDays(1)
      } else {
        LocalDate.now().plusDays(1)
      }

    sendKeys(By.id("value.day"), date.getDayOfMonth.toString)
    sendKeys(By.id("value.month"), date.getMonthValue.toString)
    sendKeys(By.id("value.year"), date.getYear.toString)

    click(continueButton)
  }

  def submitExclusion(): Unit =
    click(submitButton)

  def checkProblemPage(): Unit = {
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")))
    val h1 = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(h1.equals("Sorry, there is a problem with the service"))
  }

  def selectChangeLink(link: String): Unit =
    click(By.cssSelector(s"a[href*=$link]"))

}
