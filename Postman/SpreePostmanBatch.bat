@ECHO OFF

SET Postman=%cd%

cd %Postman%

@ECHO Spree collections
SET postman_collection=Spree_collection.json
SET postman_environment=Spree_environment.json

call newman run Spree_collection.json -e Spree_environment.json -r htmlextra

REM Pause to see the output
PAUSE
