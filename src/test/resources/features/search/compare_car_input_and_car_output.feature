Feature: Compare car data with file

  Scenario Outline: Comparing car data with output file

    Given input file with registration numbers <inputFile>
    When user go to the car check homepage
    And user extracts car details
    Then the car data is equal to <outputFile>

    Examples:
      | inputFile                                   | outputFile                                    |
      | 'src/test/resources/car_input.txt'          | 'src/test/resources/car_output.txt'           |