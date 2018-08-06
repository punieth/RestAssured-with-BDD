Feature: get the weather report using city name


 Scenario: User calls web service to get details of current weather information using city name
 
	Given city name "London,uk" and API key "54f92cf245d2ccf9185486bc88e2d0e9" as a query parameter
	When a user visit to "https://api.openweathermap.org/data/2.5/weather"
	Then response the status code is 200 response time is less then 2000 ms
	And response body includes "name" is equal to "London" and "weather[0].id" is equal to 800
	