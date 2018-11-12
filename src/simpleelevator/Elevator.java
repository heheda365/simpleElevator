package simpleelevator;

import simpleelevator.common.Direction;
import simpleelevator.common.RunStatus;

public class Elevator {
    // 运行状态
    private RunStatus runStatus;
    // 停止之前的运行方向
    private Direction direction;
    private int floorNow;
    // 电梯里的楼层按键
    private boolean[] floorNoms;

    public Elevator(RunStatus runStatus, Direction direction, int floorNow, int floorNums) {
        this.runStatus = runStatus;
        this.direction = direction;
        this.floorNow = floorNow;
        this.floorNoms = new boolean[floorNums];
    }

    public int getFloorNow() {
        return floorNow;
    }

    public void setFloorNow(int floorNow) {
        this.floorNow = floorNow;
    }

    public RunStatus getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(RunStatus runStatus) {
        this.runStatus = runStatus;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean[] getFloorNoms() {
        return floorNoms;
    }

    public void setFloorNoms(boolean[] floorNoms) {
        this.floorNoms = floorNoms;
    }

    /**
     * 打印电梯当前的信息
     */
    public void printNow(){

    }
}
