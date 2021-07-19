# scheduler
when we hit an api with a task info to be done(eg. over here with a number to be printed)
after dividing the task in small tasks we are goind to save the task details in db/cache 
then we will pick the task detail which are not completed from the db and send them to the servers over kafka topic
which are goindg to get listened by the slave servers and they trigger there process and mark them completed in db.

TechStack:
Java Spring boot, Hibernate , OracleDB ,Quartz Scheduler, Kafka
