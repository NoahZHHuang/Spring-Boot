##################  how to specify the spring boot active profile   ######################
if start the jar by java command
java -jar -Dspring.profiles.active=prod ***-0.0.1-SNAPSHOT.jar

if start the application in IDE
add "--spring.profiles.active=dev" in the program arguments
##########################################################################################



########## the .yml file specification  ##################
indent has to use "space" not "tab", example

key_level_1:
<space><space>key_level_2:
<space><space><space><space>key_level_3:<space>value

the <space> between key_level_3: and value is necessary
##########################################################
