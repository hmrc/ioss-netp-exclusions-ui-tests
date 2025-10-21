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

class KickoutSpec extends BaseSpec {

  private val exclusions = Exclusions
  private val auth       = Auth

  Feature("Kickout journeys") {

    Scenario(
      "Cannot access the NETP Exclusions service without being registered on the Intermediary service "
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(false, true, "standard")

      Then("the intermediary is on the cannot-use-this-service page")
      exclusions.checkJourneyUrl("cannot-use-this-service")

    }

    Scenario(
      "No VAT enrolment when intermediary logs into the IOSS NETP Exclusions service"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, false, "standard")

      Then("the intermediary is shown the sorry there is a problem page")
      exclusions.checkProblemPage()

    }

    Scenario(
      "Failure when submitting exclusion on behalf of a client who is no longer making eligible sales"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "failure")

      When("the intermediary answers yes on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-selling-goods-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods-date")
      exclusions.enterDate("today")

      When("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the submission-failure page")
      exclusions.checkJourneyUrl("submission-failure")

    }

    Scenario(
      "Failure when submitting exclusion on behalf of a client leaving voluntarily"
    ) {

      Given("the intermediary accesses the IOSS NETP Exclusions Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard(true, true, "failure")

      When("the intermediary answers no on the exclusions-stopped-selling-goods page")
      exclusions.checkJourneyUrl("exclusions-stopped-selling-goods")
      exclusions.answerRadioButton("no")

      And("the intermediary answers yes on the exclusions-leave-scheme page")
      exclusions.checkJourneyUrl("exclusions-leave-scheme")
      exclusions.answerRadioButton("yes")

      And("the intermediary enters today on the exclusions-stopped-using-service-date page")
      exclusions.checkJourneyUrl("exclusions-stopped-using-service-date")
      exclusions.enterDate("today")

      When("the intermediary submits their exclusion")
      exclusions.checkJourneyUrl("check-your-answers")
      exclusions.submitExclusion()

      Then("the intermediary is on the submission-failure page")
      exclusions.checkJourneyUrl("submission-failure")

    }
  }
}
