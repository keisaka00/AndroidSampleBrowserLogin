"""
ログインのコールバック処理
"""
import awsgi
import requests
import os
from flask import Flask, redirect, request, render_template, make_response

app = Flask(__name__)

#
# トップページ
#
@app.route('/', methods=['GET'])
def login_page():
	# GETパラメータ取得
	#   redirect_uri : アプリへ戻るためのURLスキーム
	redirect_uri = request.args.get("redirect_uri")
	if redirect_uri == None:
		return render_template("error.html", tag_error_mes="パラメータに redirect_uri が必須です。")
	
	# 応答用のhtmlページを準備
	resp = make_response(render_template("login.html", tag_redirect_uri=redirect_uri))
	# 次の処理へ引き継ぎたい情報をクッキーに登録（クッキーのテスト）
	resp.set_cookie("cookie_uri", redirect_uri)

	return resp

#
# トップページから呼ばれ、アプリへ戻る処理
#
@app.route('/login', methods=['POST'])
def line_login_action():
	# POSTパラメータ取得
	redirect_uri = request.form.get("redirect_uri")
	# クッキーの情報を取得（クッキーのテスト）
	cookie_uri = request.cookies.get("cookie_uri")

	# デバッグ用ページを返す
	# return render_template("test_info.html", tag_redirect_uri=redirect_uri, tag_cookie_uri=cookie_uri)

	# トップページのパラメータ（redirect_uri）で指定されたところへ飛ばす（アプリへ戻る）
	#   テスト的に code というパラメータを付加している
	resp = redirect(cookie_uri+"?code=1234", code=303)

	return resp


def lambda_handler(event, context):
    return awsgi.response(app, event, context)

# if __name__ == '__main__':
#     app.run(port=4242)