
public class Executer {
	int[][] field;
	int selectX, selectY;
    boolean firstTouch;
    int stoneSize;
    int selectStone;
    int prevX, prevY, nowX, nowY;


	public Executer(int[][] field) {
		this.field = field;
		firstTouch = true;
		stoneSize = 80;
	}

	void switchStone(double x, double y) {
		int temp;

		if(firstTouch) {
			firstTouch = false;
			/////xとyはwindowでの位置なので、
			////実際のfieldの配列数と一致させる
			selectX = (int)x/stoneSize;
			selectY = (int)y/stoneSize;
			prevX = selectX;
			prevY = selectY;
			selectStone = field[selectY][selectX];
		}

		prevX = nowX;
		prevY = nowY;
	    nowX = (int)x/stoneSize;
		nowY = (int)y/stoneSize;

		if(nowX != prevX || nowY != prevY) {
			temp = field[prevY][prevX];//前にいる場所の石を保存
			field[prevY][prevX] = field[nowY][nowX];//前の場所の石と今の場所の石を入れ替える
			field[nowY][nowX] = temp;//今の場所に、前の場所の石を入れる。
		}


	}

	void decide() {
		///switchStoneで
		///最初に押した位置を取得するため
		firstTouch = true;

		while(calculateScore()) {
			dropStone();
		}
	}

	public boolean calculateStore() {
		//TODO
	}
}
