# EJOS

ExperimentsJudgeOnlineSystem

实验在线评估系统： 大学生实验/课程设计 在线评估。

环境

wildfly10 + JSF + JPA + CDI + GitHub + Maven 

如何运行

一：

需要首先在wildfly中建立postgresql数据源，参考：

 - http://dz.sdut.edu.cn/blog/subaochen/2016/11/wildfly%E9%85%8D%E7%BD%AEpostgresql%E6%95%B0%E6%8D%AE%E6%BA%90/ 
 
 - http://blog.csdn.net/timo1160139211/article/details/77646542

二：

 - start wildfly server

 - open postgresql service

 - mvn clean package wildfly:deploy


如何参与


PR


[![Build Status](https://travis-ci.org/EJOSystem-core/EJOS.svg?branch=master)](https://travis-ci.org/EJOSystem-core/EJOS)
[![Coverage Status](https://coveralls.io/repos/github/EJOSystem-core/EJOS/badge.svg?branch=master)](https://coveralls.io/github/EJOSystem-core/EJOS?branch=master)
