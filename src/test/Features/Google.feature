Feature: Google Homepage
  Background:
  Given I launch "CHROME" browser in "UAT" enviroment using "Objects.yaml" Object Repository

    @Test1
  Scenario: Google Search
    Given I open "Google_HomepageURL" page
    When I search for "Lokesh Kumar K" in Google homepage
    Then I should get results with "Lokesh Kumar K"