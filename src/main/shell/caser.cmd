@echo off
setlocal

if %errorlevel% neq 0 (
    set JAVA_OPTS=%JAVA_OPTS% -Xms64m -Xmx64m
)
echo %JAVA_OPTS% | findstr /C:"-Dfile.encoding" >nul
if %errorlevel% neq 0 (
    set JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8
)

SET SCRIPT_DIR=%~dp0

java %JAVA_OPTS% -cp %SCRIPT_DIR%\..\lib\*" ${main.class} %*