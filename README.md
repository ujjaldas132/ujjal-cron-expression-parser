# deliveroo-cron-parser

### Main Class : `deliveroo.Parser`

### How to run it
- first you have to build the jar of this programme. Use below command to create the jar.<br>
    `./gradlew clean build`
- It will create the jar in `build/libs/` directory by the name `deliveroo-cron-parser-1.0-SNAPSHOT.jar`
- To run the the jar use the below command<br>
    `java -cp build/libs/deliveroo-cron-parser-1.0-SNAPSHOT.jar  deliveroo.Parser <CRON EXPRESSION>`


### Sample command and corresponding output
#### Command
`java -cp build/libs/deliveroo-cron-parser-1.0-SNAPSHOT.jar  deliveroo.Parser "*/15 0 1,15  * 1-5 /usr/bin/find"`
#### Output
```
minute          0 15 30 45
hour            0
day of month    1 15
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5
command         /usr/bin/find
```