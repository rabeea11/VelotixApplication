
Those are 4 Curls to run the APIs created for this application, First one gets all the logs in the Database, and the other 3 search Logs by (Level, String, or date range)

************ TO GET ALL THE LOGS IN THE DATABASE ****************************
curl -X GET \
  http://localhost:8080/allLogs \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 3ee16dd1-5abe-4e9c-aa06-370eb64ac2ef'

  
  
************ TO GET ALL THE LOGS BY LOG LEVEL ****************************
 curl -X GET \
  'http://localhost:8080/logs/level?level=ERROR' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: df39c67d-0630-43c7-8ac9-62c3b9347d0d'
 
 
 
************ TO GET ALL THE LOGS CONTAINING A STRING ****************************
curl -X GET \
  'http://localhost:8080/logs/message?message=starvation' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 611fe88d-2029-4d15-8009-bd13a2c12818'
 

 
************ TO GET ALL THE LOGS BETWEEN TWO DATES ****************************

curl -X GET \
  'http://localhost:8080/logs/dateRange?from=2021-01-01T17:21:58.694&to=2021-01-02T18:26:58.694' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 4f1386c3-1316-4d67-a13e-54f3b918a08b'