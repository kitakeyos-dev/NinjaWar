package ninjawar.model;

import ninjawar.mylib.mFont;

public class mItemOption {
    public int h;
    public ItemOption[] itemOption = new ItemOption[0];
    public String title;

    public int setH(mFont fontPaint, int margin) {
        int sumHTemp = 0;
        int k = 0;
        while (true) {
            ItemOption[] itemOptionArr = this.itemOption;
            if (k < itemOptionArr.length) {
                sumHTemp += itemOptionArr[k].h;
                k++;
            } else {
                this.h = sumHTemp;
                return sumHTemp;
            }
        }
    }
}
