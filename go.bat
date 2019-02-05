@echo off

REM Maven automatically fetches the latest SNAPSHOT on daily basis.
REM You can force Maven to download latest snapshot build using -U
REM switch to any Maven command.

REM build file does this: call mvn clean resources:resources install -U
call build.bat

REM check if build successful
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b

REM portal file does this: call mvn com.unitycloudware.maven.plugins:ucw-maven-plugin:run-portal -U
call run-portal.bat
