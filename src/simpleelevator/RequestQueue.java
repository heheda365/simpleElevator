package simpleelevator;


import simpleelevator.common.RequestType;

import java.util.LinkedList;
import java.util.Queue;

public class RequestQueue extends LinkedList<Request>{
    public RequestQueue() {
        super();
    }

    public RequestQueue queueCheck(RequestQueue requestQueue){
        RequestQueue reqQue = new RequestQueue();
        Request reqLast = null;
        for(Request request: requestQueue){
            if(reqLast != null){
                if(reqLast.getRequestType() == request.getRequestType()){
                    if(reqLast.getRequestType() == RequestType.FLOOR) {
                        if (reqLast.getToFloor() == request.getToFloor() &&
                                reqLast.getDirection() == request.getDirection()) {
                            System.out.println("# 无效请求：" + request + "去除");
                            System.out.println("# 原因：与 " + reqLast + "是相同请求");
                            continue;
                        }
                    } else {
                        if (reqLast.getToFloor() == request.getToFloor()) {
                            System.out.println("# 无效请求：" + request + "去除");
                            System.out.println("# 原因：与 " + reqLast + "是相同请求");
                            continue;
                        }
                    }
                } else if(reqLast.getRequestType() == RequestType.FLOOR ||
                        request.getRequestType() == RequestType.ELEVATOR){
                    if(reqLast.getToFloor() == request.getToFloor()){
                        System.out.println("# 无效请求：" + request + "去除");
                        System.out.println("# 原因：静止状态电梯内的同层请求");
                        continue;
                    }
                }
            }

            reqQue.add(request);
            reqLast = request;
        }

        return reqQue;
    }


}
