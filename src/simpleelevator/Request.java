package simpleelevator;


import simpleelevator.common.Direction;
import simpleelevator.common.RequestType;

public class Request {
    // 请求时间
    private float time;
    // 要去的楼层
    private int toFloor;

    // 方向
    private Direction direction;
    // 请求类型
    private RequestType requestType;

    public Request(float time, int toFloor) {
        this.time = time;
        this.toFloor = toFloor;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getToFloor() {
        return toFloor;
    }

    public void setToFloor(int toFloor) {
        this.toFloor = toFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        if(this.requestType == RequestType.FLOOR){
            return "(" + "FR" + "," + this.toFloor + "," + this.direction + "," + this.time + ")";
        } else {
            return "(" + "ER" + "," + this.toFloor + "," + this.time + ")";
        }

    }
}
