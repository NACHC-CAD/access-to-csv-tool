@echo off
echo.
echo.
echo ==========================
echo Committing with message: %1%
@echo on
git add -A 
git commit -m %1%
git push origin
@echo off
echo.
echo.
echo Done
echo ==========================
echo.
echo.
echo.





