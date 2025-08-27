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

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages.{Auth, Exclusions}

class ChangeAnswersSpec extends BaseSpec {

  private val exclusions = Exclusions
  private val auth       = Auth

  Feature("Change your answers journeys") {

    Scenario(
      "Intermediary changes leaving service date for client who is no longer making eligible sales"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "standard")

      When("the intermediary answers yes on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-selling-goods-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods-date")
      exclusions.enterDate("today")

      When("the trader clicks change on the check-your-answers page for exclusions-stopped-selling-goods-date")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.selectChangeLink("exclusions-stopped-selling-goods-date\\?waypoints\\=check-your-answers")

      Then("the trader amends the move date to yesterday")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods-date?waypoints=check-your-answers")
      exclusions.enterDate("yesterday")

      And("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the client-exclusions-request-received page")
      exclusions.checkJourneyUrl("client-exclusions-request-received")

    }

    Scenario(
      "Intermediary changes from client no longer making eligible sales journey to voluntary journey"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "standard")

      When("the intermediary answers yes on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-selling-goods-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods-date")
      exclusions.enterDate("today")

      And("the trader clicks change on the check-your-answers page for exclusions-stopped-selling-goods")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.selectChangeLink("exclusions-stopped-selling-goods\\?waypoints\\=check-your-answers")

      When("the intermediary answers no on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods?waypoints=check-your-answers")
      exclusions.answerRadioButton("no")

      And("the intermediary answers yes on the exclusions-leave-scheme page")
      exclusions.checkJourneyUrl("exclusions-leave-scheme?waypoints=check-your-answers")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-using-service-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-using-service-date?waypoints=check-your-answers")
      exclusions.enterDate("today")

      And("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the client-exclusions-request-received page")
      exclusions.checkJourneyUrl("client-exclusions-request-received")

    }

    Scenario(
      "Intermediary changes leaving service date for client who is leaving the service voluntarily"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "standard")

      When("the intermediary answers no on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("no")

      And("the intermediary answers yes on the exclusions-leave-scheme page")
      exclusions.checkJourneyUrl("exclusions-leave-scheme")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-using-service-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-using-service-date")
      exclusions.enterDate("today")

      When("the trader clicks change on the check-your-answers page for exclusions-stopped-using-service-date")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.selectChangeLink("exclusions-stopped-using-service-date\\?waypoints\\=check-your-answers")

      Then("the trader amends the move date to yesterday")
      exclusions.checkJourneyUrl("exclusions-stopped-using-service-date?waypoints=check-your-answers")
      exclusions.enterDate("yesterday")

      When("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the client-exclusions-request-received page")
      exclusions.checkJourneyUrl("client-exclusions-request-received")

    }

    Scenario(
      "Intermediary changes from client leaving the service voluntarily to stopped making eligible sales journey"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "standard")

      When("the intermediary answers no on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("no")

      And("the intermediary answers yes on the exclusions-leave-scheme page")
      exclusions.checkJourneyUrl("exclusions-leave-scheme")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-using-service-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-using-service-date")
      exclusions.enterDate("today")

      And("the trader clicks change on the check-your-answers page for exclusions-stopped-selling-goods")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.selectChangeLink("exclusions-stopped-selling-goods\\?waypoints\\=check-your-answers")

      When("the intermediary answers yes on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods?waypoints=check-your-answers")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-selling-goods-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods-date?waypoints=check-your-answers")
      exclusions.enterDate("today")

      When("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the client-exclusions-request-received page")
      exclusions.checkJourneyUrl("client-exclusions-request-received")

    }
  }
}
