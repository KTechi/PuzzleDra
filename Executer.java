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

			//選択する数値が配列範囲を超えないための処理
			if(selectX >= field[0].length) {
				selectX = field[0].length-1;
			}else if(selectX < 0) {
				selectX = 0;
			}

			if(selectY >= field.length) {
				selectY = field.length-1;
			}else if(selectY < 0) {
				selectY = 0;
			}

			prevX = selectX;
			prevY = selectY;
			selectStone = field[selectY][selectX];
		}else {


			prevX = nowX;
			prevY = nowY;
		    nowX = (int)x/stoneSize;
			nowY = (int)y/stoneSize;

			//選択する数値が配列範囲を超えないための処理
			if(nowX >= field[0].length) {
				nowX = field[0].length-1;
			}else if(nowX < 0) {
				nowX = 0;
			}

			if(nowY >= field.length) {
				nowY = field.length-1;
			}else if(nowY < 0) {
				nowY = 0;
			}
		}

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
	}
	
	boolean calculateScore() {
		boolean[][] deleteMask = new boolean[field.length][field[0].length];
		boolean haveToDelete = false;

		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++) {
			if (field[y][x] == -1) continue;
			if (x + 1 < field[0].length && field[y][x] == field[y][x+1]) {
				if (x + 2 < field[0].length && field[y][x] == field[y][x+2]) haveToDelete = true;
				if (y + 1 < field.length    && field[y][x] == field[y+1][x]) haveToDelete = true;
				if (y + 1 < field.length    && field[y][x] == field[y+1][x+1]) haveToDelete = true;
			}
			if (y + 1 < field.length && field[y][x] == field[y+1][x]) {
				if (y + 2 < field.length    && field[y][x] == field[y+2][x]) haveToDelete = true;
				if (x + 1 < field[0].length && field[y][x] == field[y+1][x+1]) haveToDelete = true;
				if (0 <= x - 1              && field[y][x] == field[y+1][x-1]) haveToDelete = true;
			}
			if (haveToDelete) {
				for (int i = 0; i < deleteMask.length; i++)
				for (int j = 0; j < deleteMask[0].length; j++)
					deleteMask[i][j] = false;

				delete(x, y, deleteMask);

				for (int i = 0; i < deleteMask.length; i++)
				for (int j = 0; j < deleteMask[0].length; j++)
					if (deleteMask[i][j]) field[i][j] = -1;
				return true;
			}
		}
		return false;
	}

	private void delete(int x, int y, boolean[][] deleteMask) {
		deleteMask[y][x] = true;
		if (  0   <=     x - 1      && field[y][x] == field[y][x-1] && !deleteMask[y][x-1]) delete(x-1, y, deleteMask);
		if (  0   <=     y - 1      && field[y][x] == field[y-1][x] && !deleteMask[y-1][x]) delete(x, y-1, deleteMask);
		if (x + 1 < field[0].length && field[y][x] == field[y][x+1] && !deleteMask[y][x+1]) delete(x+1, y, deleteMask);
		if (y + 1 < field.length    && field[y][x] == field[y+1][x] && !deleteMask[y+1][x]) delete(x, y+1, deleteMask);
	}

	boolean dropStone() {
		// drop stones only 1 block
		for (int x = 0; x < field[0].length; x++)
		for (int y = field.length-2; 0 <= y; y--) {
			if (field[y+1][x] != -1) continue;
			field[y+1][x] = field[y][x];
			field[y][x] = -1;
		}
		// return true, if you have to drop stones next turn
		for (int x = 0; x < field[0].length; x++)
		for (int y = 0; y < field.length - 1; y++)
			if (field[y][x] != -1 && field[y+1][x] == -1) return true;
		return false;
	}
	
	void fillField() {
		for (int y = 0; y < field.length; y++)
		for (int x = 0; x < field[0].length; x++)
			if (field[y][x] == -1) field[y][x] = (int)(Math.random()*6);
	}
}