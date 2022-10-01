Feature: As a simple RPG Gamer
  I want to use basic commands
  So I can show basic combat sheet for simple RPG

  Scenario: Create basic character
    Given I have prepared a discord command to create a new character "Disparter" with hp 100
    When I execute a discord command to create a new character
    Then the character is created