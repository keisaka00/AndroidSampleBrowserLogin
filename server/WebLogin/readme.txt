Lambda向けPython開発環境です。

１．環境生成手順
	cd （作業フォルダ）
	python -m venv .
	pip install aws-wsgi

２．開発を始める時に最初にやること
	（コマンドプロンプトを起動）
	cd （作業フォルダ）
	Scripts\activate.bat
	pip install -r requirements.txt

３．開発を終わる時に最後にやること
	Scripts\deactivate.bat

４．開発中に新しくライブラリをインストールしたら
	pip freeze > requirements.txt

５．デプロイ手順
	deploy.bat


■Tips
・pytest
	※「test」から始まるファイル・メソッドが、テストのコードであると認識される
	python -m pytest

・PowerShell のスクリプトが実行できない場合の対処方法
	https://qiita.com/Targityen/items/3d2e0b5b0b7b04963750
		Set-ExecutionPolicy Unrestricted

