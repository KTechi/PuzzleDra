# PuzzleDra
パズドラ

ユーザ
* インプットは石の操作だけ。

システム　ループ
* 石を触れている間、
    * 動かす、入れ替える。
* 石を離したら、
    * 結合判定（３つ石が連続しているか）
    * 消す
    * 落とす
    * ダメージ計算（色毎）
    * 敵の体力を減らす
    * 敵が死んだか判定する→クリア

ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー

上
* キャンバス
    * ラベル（体力）
    * 画像（味方x5、敵x1）
下
* キャンバス
    * 石（光、闇、水、火、木、ハート）

ーーーーーーーーーーーーーーーーーーーーーーーーーーーーー

Main
* イベントハンドラの作成と登録
* 各種コンポーネントの作成
* イベントハンドラ
    * 押したら（ドラッグ）、押した座標（int x, int y）のデータを送信する
        * 
    * 離したら、計算してね

PreviewCanvas
* 実用なデータ
    * 画像データ（モンスタ）
    * 体力ラベル

PuzzleCanvas
* 必要データ
    * int[][] field（石の配列）5x6
    * 画像データ（石の）
    * 選択している石の座標 X Y
* 送信内容
    * どこを選択しているか

Executer
* 必要データ
    * int[][] field（石の配列の引数）5x6
    * int x, y//どの石を選択しているか
* 処理内容
    * どの石を触れているかを認識する
    * 侵入領域（マス）を認識する
        * 入れ替え
        * 離すまでループ
* ーーーーーー離したらーーーーーーーーーーー
    * 結合を認識する（連結数とか）（点数を計算しながら）（連結０でループから出る）
    * 削除
    * 下に落とす
        * ループ
    * 上のキャンバスのラベルに体力をセットする
    * クリア判定

・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・

* 石を触れている間、
    * 動かす、入れ替える。
* 石を離したら、
    * 結合判定（３つ石が連続しているか）
    * 消す
    * 落とす
    * ダメージ計算（色毎）
    * 敵の体力を減らす
    * 敵が死んだか判定する→クリア


Main
    PreviewCanvas
    PuzzleCanvas
    Executer

    constrector {}
    EventHandler(Event event) {
        if (event instanceof MouseEvent.DRAG_DETECTED) {
            switchStone();
        }
        else if (event instanceof MouseEvent.MOUSE_RELEASED) {
            decide()
        }
    }

PreviewCanvas
    Image[] monster
    Label enemyHP

    paint();

PuzzleCanvas
    int[][] field
    Image[] stones
    int size;
    int x, y
/*石の色コード
光　０
闇　１
水　２
火　３
木　４
ハート　５
*/
    paint();

Executer
    int[][] field;
    int selectX, selectY//どの石を選択しているか

    //触れたら
    switchStone(x, y) {
        if (x < over) {
            tmp = 
        }
    }

    //離したら
    decide(){
        while (calculateScore()) {    //結合を認識して、消して、ダメージ計算
            dropStone();
        }
    }
    
    calculateScore() {//点数0 で return false;
        
    }







