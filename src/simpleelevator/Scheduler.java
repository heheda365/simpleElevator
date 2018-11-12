package simpleelevator;

import simpleelevator.common.Direction;
import simpleelevator.common.RequestType;

public class Scheduler {
    private Elevator elevator;
    private float timeNow;
    private RequestQueue requestQueue;
    private Floor[] floors;

    public Scheduler(Elevator elevator, float timeNow, RequestQueue requestQueue, Floor[] floors) {
        this.elevator = elevator;
        this.timeNow = timeNow;
        this.requestQueue = requestQueue;
        this.floors = floors;
    }

    // 调度
    public void run(){

        while (true){
            Request request = requestQueue.poll();
            if(request == null){
                break;
            }

            for(Request req: requestQueue){
                //请求时间小于当前时间的请求为实际等待执行的请求
                // 大于当前时间的请求其实是未来的请求
                if(req.getTime() < this.timeNow){
                    if(req.getRequestType() == RequestType.FLOOR){
                        if(req.getToFloor() == request.getToFloor() &&
                                req.getDirection() == request.getDirection()){
                            // 移除重复
                            requestQueue.remove(req);
                        }
                    } else {
                        if(req.getToFloor() == request.getToFloor()){
                            requestQueue.remove(req);
                        }
                    }
                }
            }

//            // 遍历队列 找出请求时间小于当前时间的请求
//            for(Request req: requestQueue){
//                // 请求时间小于当前时间的请求为实际等待执行的请求
//                // 大于当前时间的请求其实是未来的请求
//                if(req.getTime() < this.timeNow){
//                    // 电梯请求
//                    // 点亮电梯里的按键
//                    if(req.getRequestType() == RequestType.ELEVATOR) {
//                        // 数组下标和相关楼层数隔 1 所以减1
//                        if (elevator.getFloorNoms()[req.getToFloor() - 1] == true){
//                            System.out.println("重复按电梯");
//                        } else {
//                            elevator.getFloorNoms()[req.getToFloor() - 1] = true;
//                        }
//                    } else{
//                        if(req.getDirection() == Direction.UP){
//                            if (floors[req.getToFloor() - 1].isUpButton()){
//                                System.out.println("重复按楼层上键");
//                            } else {
//                                floors[req.getToFloor() - 1].setUpButton(true);
//                            }
//                        } else {
//                            if (floors[req.getToFloor() - 1].isDownButton()){
//                                System.out.println("重复按楼层下键");
//                            } else {
//                                floors[req.getToFloor() - 1].setDownButton(true);
//                            }
//                        }
//
//                    }
//                }
//            }

            // 当前时间未到指令执行的时间，等到指令执行的时间
            if(this.timeNow < request.getTime()) {
                this.timeNow = request.getTime();
            }
            int floorSub = request.getToFloor() - elevator.getFloorNow();
            // 请求要去的楼层大于当前楼层 电梯上行
            if(floorSub > 0){
                elevator.setDirection(Direction.UP);
            } else if(floorSub < 0){
                // 电梯下行
                elevator.setDirection(Direction.DOWN);
            } else {
                elevator.setDirection(Direction.STILL);
            }
            // 达到请求去的楼层 还未开门
            this.timeNow = this.timeNow + (float) (Math.abs(floorSub) * 0.5);
            elevator.setFloorNow(request.getToFloor());

            // 不同层开关门之前打印
            if(elevator.getDirection() != Direction.STILL) {
                // 开门前打印信息
                System.out.println("(" + elevator.getFloorNow() + ","
                        + elevator.getDirection().toString() + ","
                        + this.timeNow
                        + ")");
                // 开门
                this.timeNow += 0.5;
                // 关门
                this.timeNow += 0.5;
            } else {
                // 开门
                this.timeNow += 0.5;
                // 关门
                this.timeNow += 0.5;
                System.out.println("(" + elevator.getFloorNow() + ","
                        + elevator.getDirection().toString() + ","
                        + this.timeNow
                        + ")");
            }

        }
    }
}
