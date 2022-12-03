@echo off

rmdir /S /Q deploy
echo d | xcopy /s /e src deploy
pip install -r requirements.txt -t deploy

echo making zip
cd deploy
for /F %%i in ('dir /AD /B /W *dist-info') do rmdir /S /Q %%i
rmdir /S /Q bin
rmdir /S /Q __pycache__
rmdir /S /Q _pytest
zip -r deploy.zip *
copy deploy.zip ..
cd ..

echo finished