
public class Executer {
	int[][] field;
	int selectX, selectY;
    boolean firstTouch;
    int stoneSize;
    int selectStone;

	public Executer(int[][] field) {
		this.field = field;
		firstTouch = true;
		stoneSize = 80;
	}

	void switchStone(double x, double y) {
		if(firstTouch) {
			firstTouch = false;
			/////xとyはwindowでの位置なので、
			////実際のfieldの配列数と一致させる
			selectX = (int)x/stoneSize;
			selectY = (int)y/stoneSize;
			selectStone = field[selectY][selectX];
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
