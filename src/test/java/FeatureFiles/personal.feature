@PersonalInfoApi
Feature: Test personalInfo all endpoints

  Background: 
    * set base uri

  @CreateEntity
  Scenario: create new entity
    Given set as "personalInformation" base path
    And get the request specification object
    And create request body for post request
    And attach request payload to http post request
    When select post request
    Then capture personal id from response body
    And get validatable response interface object
    And validate status code as 201
    And validate status line as "201 Created"
    And validate response time below 5000 ms
    Then validate "Content-type" and "application/json" reponse header
    And validate "Date" and value as current month year response header
    And validate json schema
    And generate response logs

  @RetriveEntity
  Scenario: retrive entity
    Given set as "personalInformation" base path
    And get the request specification object
    And set id as a path param
    When select get request
    And get validatable response interface object
    And validate status code as 200
    And validate status line as "200 OK"
    And validate response time below 5000 ms
    Then validate "Content-type" and "application/json" reponse header
    And validate "Date" and value as current month year response header
    And validate json schema
    And generate response logs
