package simpleelevator;

import simpleelevator.RequestQueue;
import simpleelevator.common.Direction;
import simpleelevator.common.RequestType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class IoUtil {
    private static float timeLast;

    public static RequestQueue stringToRequestQueue() {
        Scanner scanner = new Scanner(System.in);
        List<String> inputStrs = new ArrayList<>();
        RequestQueue requestQueue = new RequestQueue();
        System.out.println("请输入请求:");
        while (true) {
            String str = scanner.nextLine();
            if (str.equals("RUN")) {
                break;
            }
            inputStrs.add(str);
        }

        // 记录第几个字符串
        int cnt = 0;
        // 分析字符串 将请求写入到请求队列
        for (String str : inputStrs) {
            cnt ++;

            if(!isLegitimate(str))
                continue;


            String str1 = str.replace(" ", "")
                    .replace("(", "")
                    .replace(")", "");

            String[] strCmd = str1.split(",");

            // 分析第一条命令
            if(cnt == 1){
                if(!strCmd[strCmd.length - 1].equals("0")){
                    strCmd[strCmd.length - 1] = "0";
                    String strTemp;
                    if(strCmd.length == 3){
                        strTemp = "("  + strCmd[0] + "," + strCmd[1] + "," + "0)";
                    } else {
                        strTemp = "("  + strCmd[0] + "," + strCmd[1] + "," + strCmd[2] + "," + "0)";
                    }
                    System.out.println("# 用户输入的第一条指令时间自动置0):" + strTemp);
                }
            }

            Request request;

            // 长度为3 或 4
            if (strCmd.length == 3 || strCmd.length == 4) {
                if (strCmd.length == 3) {
                    request = new Request(Float.valueOf(strCmd[2]), Integer.valueOf(strCmd[1]));
                    request.setRequestType(RequestType.ELEVATOR);
                } else {
                    request = new Request(Float.valueOf(strCmd[3]), Integer.valueOf(strCmd[1]));
                    if(strCmd[2].equals("UP"))
                        request.setDirection(Direction.UP);
                    else
                        request.setDirection(Direction.DOWN);
                    request.setRequestType(RequestType.FLOOR);
                }
                // 加入到请求队列
                requestQueue.add(request);
            }
        }
        return requestQueue;
    }
    private static boolean isLegitimate(String str){
        String str1 = str.replace(" ", "");
        if(str.charAt(0) != '(' || str.charAt(str.length() - 1) != ')') {
            printError(str + "必须以'()'包裹");
            return false;
        }
        str1 = str1.replace("(", "")
                .replace(")", "");

        String[] strCmd = str1.split(",");
        // 字符检验
        if(strCmd.length == 3 || strCmd.length == 4) {
            if(!(strCmd[0].equals("FR") || strCmd[0].equals("ER"))){
                printError(str + "第一个参数必须是'FR'或'ER'");
                return false;
            }

            if(! Pattern.matches("^[1-9]\\d*$", strCmd[1])){
                printError(str + "楼层必须是正整数");
                return false;

            } else {
                if(Integer.valueOf(strCmd[1]) > 10){
                    printError(str + "楼层数应该小于10");
                    return false;
                }

            }

            if(strCmd.length == 3){
                // 电梯内请求
                if(! Pattern.matches("^[+]?(\\d+)$|^[+]?(\\d+\\.\\d+)$", strCmd[2])){
                    printError(str + "输入时间必须是一个正数");
                    return false;
                }

            } else {
                // 楼层请求
                if(! Pattern.matches("^[+]?(\\d+)$|^[+]?(\\d+\\.\\d+)$", strCmd[3])){
                    printError(str + "输入时间必须是一个正数");
                    return false;
                }

                if(!(strCmd[2].equals("UP") || strCmd[2].equals("DOWN"))){
                    printError(str + "楼层请求第三项必须是'UP'或者'DOWN'");
                    return false;
                }
            }

        } else {
            printError(str + "输入格式错误");
            return false;
        }
        // 一些逻辑检验
        if(strCmd.length == 4){
            if(strCmd[1].equals("1") && strCmd[2].equals("DOWN")){
                printError(str + "1楼无法DOWN");
                return false;
            }
            if(strCmd[1].equals("10") && strCmd[2].equals("UP")){
                printError(str + "10楼无法UP");
                return false;
            }
        }

        // 时间
        if(timeLast > Float.valueOf(strCmd[strCmd.length - 1])){
            printError(str + "时间输入不合法，请求必须按时间排序");
            return false;

        }
        timeLast = Float.valueOf(strCmd[strCmd.length - 1]);

        return true;
    }

    private static void printError(String str){
        System.out.println("ERROR");
        System.out.println("# 无效请求 " + str);
    }

}
