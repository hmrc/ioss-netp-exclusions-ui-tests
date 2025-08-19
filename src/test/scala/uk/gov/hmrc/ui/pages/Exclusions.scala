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
import org.openqa.selenium.{By, Keys}
import org.scalatest.matchers.should.Matchers.*
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.selenium.webdriver.Driver
import org.junit.Assert

import java.time.LocalDate

object Exclusions extends BasePage {

  private val exclusionsUrl: String =
    TestEnvironment.url("ioss-netp-exclusions-frontend")

  private val journeyUrl: String = "/pay-clients-vat-on-eu-sales/leave-new-ioss-client"

  def goToExclusionsJourney(): Unit =
    get(exclusionsUrl + journeyUrl)

}
