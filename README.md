# AndroidSampleBrowserLogin
Androidアプリからブラウザを開き、サーバー側処理の終了後にAndroidアプリに戻ってくるサンプルです。
サーバーはAWS lambda, API Gatewayを使います。

サーバー側のプログラムは、「server/WebLogin」フォルダ以下に格納しています。
Pythonで作成しています。
Lambdaへアップロードするための環境作成方法など、server/WebLogin/readme.txtを参照してください。

----
#　Androidアプリの設定
ブラウザで開くURLを下記のAPI Gatewayのアドレスに書き換えます。

----
# AWS Lambdaへのアップロード方法
詳細な設定方法はAWSのドキュメントなどを参照してください。
大まかな流れは、
　①deploy.batを動かす
　②作成されたdeploy.zipをS3にアップする
　③S3からlambdaに取り込む
となります。

----
# API Gatewayの設定方法

API Gatewayの設定はコンソールで行う方法しか知らないので、その大まかな手順を記載します。
詳しくはAWSドキュメント等を参照してください。

①リソースとメソッドの構成を作成
このような構成になるように作成する。
>　/
>　　GET
>　　　/login
>　　　　POST
メソッド作成時に次のものを選択すること。
　統合タイプ：Lamda関数
　Lambdaプロキシ統合の使用：チェック入れる
　Lambda関数：上記でアップしたLambda関数

②/ GET のメソッドレスポンスのコンテンツタイプ変更
　「application/json」となっているので「text/html」に変更する

③/login POSTのメソッドレスポンスのコンテンツタイプ変更
　②と同様にHTTPステータス200のコンテンツタイプを「text/html」に変更する
　HTTPのステータスに「303」を追加し、コンテンツタイプを「text/html」にする

一通り作成が終わったら、APIのデプロイを行います。
公開されたURLをAndroidアプリ側に書き込んで準備は完了です。

