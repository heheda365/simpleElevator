package simpleelevator;

import com.sun.org.apache.regexp.internal.REUtil;
import simpleelevator.common.Direction;
import simpleelevator.common.RunStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public void start(){
        // 分析请求字符串得到请求列队
        RequestQueue requestQueue = IoUtil.stringToRequestQueue();
        // 检查相邻两个命令之间的重复
        requestQueue = requestQueue.queueCheck(requestQueue);
//        requestQueue.printAll();
        // 初始化10层楼
        Floor[] floors = new Floor[10];

        for(int i=0; i < 10; i ++){
            Floor floor = new Floor(i + 1, false, false);
            floors[i] = floor;
        }
        // 初始化电梯在1楼, 共10楼
        Elevator elevator = new Elevator(RunStatus.STAY, Direction.STILL, 1, 10);
        Scheduler scheduler = new Scheduler(elevator, 0, requestQueue, floors);
//        System.out.println("# ============电梯调度开始=============");

        System.out.println("# 电梯运行状态为：");
        scheduler.run();
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.start();
    }

}
