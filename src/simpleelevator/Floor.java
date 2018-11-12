package simpleelevator;

import simpleelevator.common.Direction;

public class Floor {
    // 第几楼
    private int num;
    // 请求的方向
    private boolean upButton;
    private boolean downButton;

    public Floor(int num, boolean upButton, boolean downButton) {
        this.num = num;
        this.upButton = upButton;
        this.downButton = downButton;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isUpButton() {
        return upButton;
    }

    public void setUpButton(boolean upButton) {
        this.upButton = upButton;
    }

    public boolean isDownButton() {
        return downButton;
    }

    public void setDownButton(boolean downButton) {
        this.downButton = downButton;
    }
}
