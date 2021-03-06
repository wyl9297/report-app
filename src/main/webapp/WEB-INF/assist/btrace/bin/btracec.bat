@echo off

rem %~dp0 is expanded pathname of the current script under NT
set DEFAULT_BTRACE_HOME=%~dp0..

if "%BTRACE_HOME%"=="" set BTRACE_HOME=%DEFAULT_BTRACE_HOME%
set DEFAULT_BTRACE_HOME=

if not exist "%BTRACE_HOME%\build\btrace-client.jar" goto noBTraceHome

if "%JAVA_HOME%" == "" goto noJavaHome
  if "%1" == "--version" (
    %JAVA_HOME%\bin\java -jar %BTRACE_HOME%/build/btrace-client.jar com.sun.btrace.Main --version
    goto end
  )
  "%JAVA_HOME%/bin/java" -cp "%BTRACE_HOME%/build/btrace-client.jar;%JAVA_HOME%/lib/tools.jar;%BTRACE_HOME%/lib/servlet-api.jar;" com.sun.btrace.compiler.Compiler %*
  goto end
:noJavaHome
  echo Please set JAVA_HOME before running this script
  goto end
:noBTraceHome
  echo Please set BTRACE_HOME before running this script
:end