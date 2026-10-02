@echo off
setlocal

set DIR=%~dp0
if "%DIR:~-1%"=="\" set DIR=%DIR:~0,-1%

set APP_HOME=%DIR%

if not exist "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" (
  echo Gradle wrapper JAR is missing. Please generate it with: gradle wrapper
  exit /b 1
)

set JAVA_EXE=java
if defined JAVA_HOME (
  set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
)

if not exist "%JAVA_EXE%" (
  echo JAVA_HOME is not set correctly or Java is not installed.
  exit /b 1
)

"%JAVA_EXE%" -classpath "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
