# API WordView
このAPIの目的は音楽の歌詞や歌詞の言葉としての情報を持っている返事するべきです。その歌詞の言葉の情報は[gengolex](https://github.com/word-view/gengolex)って呼ばれたライブラリーで取り出します。
最終的にAPIの返事はJSONのオブジェクトで様々な歌詞情報を送っている。

そんな情報でWordViewのアプリは色んなリクエストをする、APIから写真を頼んで、そしてその写真で自動的に新しい従業を生成される。

それよりこのAPIにはアカウント作るとログイン機能は入ています。

# 機動方法
```sh
  # リポジトリーをクーロンして
  git clone https://github.com/word-view/APIWordView

  # フォルダに入って
  cd APIWordView/

  # スタートコマンド
  ./mvnw spring-boot:run 
```

# JARで機動
リリース欄のJARにはおすすめしません、アプリには多分使えない古いバージョンの確率が高いし。
```sh
  # リリース欄からダウロードして
  # そして--devや--prodで機動してください
  java -jar ./wordview-0.0.4-SNAPSHOT-exec.jar --prod
```
