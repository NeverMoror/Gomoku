import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Board {
    private int count;//玩家，棋子， 奇数为黑，偶数为白
    private int n;//棋盘大小
    private int x ;//横坐标
    private int y;//纵坐标
    private int[][] arr = new int[16][16] ;//记录棋子



    //------------------------------------------------------------------------------------------------------------------

    public Board(){
        super();
        count = 1;
        n = 15;
        int[][] arr = new int[16][16];
    }
    public int getCount(){return count;}//当前棋子颜色
    public void addCount(){count++;}//交换玩家
    public Board(int num){
        n = num;
    }// 棋盘大小
    public int getN(){return n;} //得到棋盘大小
    public int getX(){return x;}//得到横坐标
    public int getY(){return y;}//得到纵坐标
    public void setN(int n){this.n = n;} //改变横坐标
    public void setX(int x){this.x = x;}//改变纵坐标

    //------------------------------------------------------------------------------------------------------------------


    //绘制棋盘
    public void drawBoard(){

        StdDraw.setCanvasSize(1024, 768);
        StdDraw.setXscale(0, 20);
        StdDraw.setYscale(0, 16);

//        StdDraw.setPenColor(Color.green);
//        StdDraw.filledSquare(8,8,20);
        StdDraw.picture(5,10,"1.jpg");
        StdDraw.picture(17.5,14,"校徽-橙（背景透明）(1).png");
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledSquare(8,8,7.1);
        //画棋盘
        StdDraw.setPenColor(Color.BLACK);
        for (int y = 1; y < 16; y++) {
            StdDraw.line(1, y, 15, y);
        }
        for (int x = 1; x < 16; x++) {
            StdDraw.line(x, 1, x, 15);
        }


        //信息
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(17.5,11.5,2,1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(17.5,11.5,"认输");

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(17.5,9,2,1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(17.5,9,"重新开始");


        StdDraw.rectangle(17.5,6.5,2,1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(17.5,6.5,"悔棋");
        StdDraw.square(8,8,14.2);


        StdDraw.rectangle(17.5,4,2,1);
        StdDraw.rectangle(17.5,1.5,2,1);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(17.5,4,"保存游戏");
        StdDraw.text(17.5,1.5,"加载游戏");
    }

    //------------------------------------------------------------------------------------------------------------------


    //绘制棋子
    public void point(int x ,int y){
        if(arr[x][y] != 0){
//            System.out.println("此处已经有子，请下在别处");
        }
        else if(count%2 == 1 && arr[x][y] ==0){
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledCircle(x,y,0.5);
            arr[x][y] = 1;
            addCount();
        }
        else if(count%2 == 0 && arr[x][y] ==0){
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.filledCircle(x,y,0.5);
            arr[x][y] = 2;
            addCount();
        }
    }

    //消除棋子
    public void delete(int x, int y){
        if(arr[x][y] != 0){
            StdDraw.setPenColor(Color.ORANGE);
            StdDraw.filledCircle(x,y,0.51);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(x-0.51,y,x+0.51,y);
            StdDraw.line(x,y-0.51,x,y+0.51);
            arr[x][y] = 0;
        }
    }
    //------------------------------------------------------------------------------------------------------------------


    //判断输赢
    private boolean WinLose(int x,int y) {
        boolean flag = false;//输赢
        int num = 1;//相连数
        int color = arr[x][y];//记录棋子颜色

        //判断横向棋子是否相连
        int i = 1;//迭代数
        while (color == arr[x + i][y]) {
            num++;
            i++;
        }
        i = 1;//迭代数
        while (color == arr[x - i][y]) {
            num++;
            i++;
        }
        if (num >= 5) {
            flag = true;
        }

        //判断纵向棋子是否相连
        num = 1;
        i = 1;//迭代数
        while (color == arr[x][y + i]) {
            num++;
            i++;
        }
        i = 1;//迭代数
        while (color == arr[x][y - i]) {
            num++;
            i++;
        }
        if (num >= 5) {
            flag = true;
        }

        //判断斜向棋子是否相连（左上右下）
        num = 1;
        i = 1;//迭代数
        while (color == arr[x - i][y - i]) {
            num++;
            i++;
        }
        i = 1;//迭代数
        while (color == arr[x + i][y + i]) {
            num++;
            i++;
        }
        if (num >= 5) {
            flag = true;
        }

        //判断斜向棋子是否相连（左下右上）
        num = 1;
        i = 1;//迭代数
        while (color == arr[x + i][y - i]) {
            num++;
            i++;
        }
        i = 1;//迭代数
        while (color == arr[x - i][y + i]) {
            num++;
            i++;
        }
        if (num >= 5) {
            flag = true;
        }
        return flag;
    }


    //------------------------------------------------------------------------------------------------------------------


    //鼠标点击
    public void mouseClicked(MouseEvent e) {

    }

    //鼠标按下
    public void mousePressed(MouseEvent e) {
        //获取鼠标点击位置，取整
        x = (int)(e.getX()+0.5);
        y = (int)(e.getY()+0.5);

        System.out.println(x);
        System.out.println(y);
        //鼠标位于棋盘中

    }


    //鼠标抬起
    public void mouseReleased(MouseEvent e) {

    }

    //鼠标进入
    public void mouseEntered(MouseEvent e) {

    }

    //鼠标离开
    public void mouseExited(MouseEvent e) {

    }

    //------------------------------------------------------------------------------------------------------------------

    public void play() throws IOException {
        Board b = new Board();
        int[][] arr = new int[16][16];//
        Scanner input = new Scanner(new File("data.txt"));//读入文件
        File file = new File("data.txt");//新建的存储结果文件
        PrintWriter output = new PrintWriter("data.txt");//向result文件中写数据

        b.drawBoard(); //绘制棋盘

        while (true) {
            try {
                if (StdDraw.isMousePressed()) {
                    int x = (int) (0.5 + StdDraw.mouseX());
                    int y = (int) (0.5 + StdDraw.mouseY());

                    //重开
                    if(15.5 < x &&  x <19.5 && 7.5<y && y<10.5){
                        System.out.println("重新开始！");
                        play();
                        break;
                    }

                    //认输
                    if (15.5 < x && x < 19.5 && 10.5 < y && y < 12.5) {
                        if (b.count % 2 == 1) {
                            System.out.println("黑方认输，白方获胜！");
                            play();
                            break;
                        } else if (b.count % 2 == 0) {
                            System.out.println("白方认输，黑方获胜！");
                            play();
                            break;
                        }
                    }
                    //保存
                    if(15.5 < x && x < 19.5 && 2.5 < y && y < 5.5){
                        for (int i = 1; i < 15; i++) {  //保存的数据和原来棋盘比较，上下颠倒，左右不变
                            for (int j = 1; j < 15; j++) {
                                output.print(b.arr[j][i]+" ");
                            }
                            output.print("\n");
                        }
                        output.close();
                        System.out.println("已保存！");
                    }

                    //读取
                    if(15.5 < x && x < 19.5 && 0 < y && y < 3){
                        while (input.hasNext()){
                            for (int j = 1; j < 15; j++) {
                                for (int i = 1; i < 15; i++) {
                                    int num = input.nextInt();
                                    if(num != b.arr[i][j]){
                                        if(b.arr[i][j] == 0){
                                            b.point(i,j);
                                            count--;
                                        }
                                        else if(b.arr[i][j] != 0 && num ==0){
                                            b.delete(i,j);
                                        }
                                        else if(b.arr[i][j] != num){
                                            delete(i,j);
                                            b.arr[i][j] = num;
                                            b.point(i,j);
                                            count--;
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println("已加载上次游戏");
                    }

                    //下棋
                    if (0 < x && x < 16 && 0 < y && y < 16) {
                        //落子处是否为空
                        if (arr[x][y] == 0) {
                            //若不为空，根据棋子颜色填入棋子，交换玩家
                            if (b.count % 2 == 1) {
                                b.point(x, y);

                            } else if (b.count % 2 == 0) {
                                b.point(x, y);
                            }
                        }
                    }

                    //胜负判断
                    if (b.WinLose(x, y) == true) {
                        if (b.count % 2 == 1) {
                            System.out.println("共下" + (b.count - 1) + "步\n\n" + "白方获胜!");
                            play();
                            break;
                        } else if (b.count % 2 == 0) {
                            System.out.println("共下" + (b.count - 1) + "步\n\n" + "黑方获胜!");
                            play();
                            break;
                        }
                    }




                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }

        while (true) {
            if (StdDraw.isMousePressed()) {
                try {
                    //重开
                    if(15.5 < x &&  x <19.5 && 7.5<y && y<10.5){
                        System.out.println("重新开始！");
                        play();
                        break;
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)  throws IOException {
        Board b = new Board();
        b.play();
    }
}