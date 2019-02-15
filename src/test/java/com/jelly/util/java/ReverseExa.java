package com.jelly.util.java;

/**
 * volatile代表禁止写重排序
 * happens-before关系是用来描述两个操作的内存可见性的.如果操作X happens-before操作Y,那么X的结果对于Y可见.
 *
 * 解决这种数据竞争问题的关键在于构造一个跨线程的happens-before关系：操作X happens-before 操作Y，使得操作X之前的字节码的结果对操作Y之后的字节码可见。禁止写的重排序
 * @author dongxiaohong
 * @date 2019/2/15 09:46
 */
public class ReverseExa {

    int a=0;
    volatile int b=0;

    /**
     * 分两种情况:
     * 1.一开始便将a加载至寄存器中,并在接下来b的赋值操作以及使用b的代码中避免使用该寄存器.
     * 2.在真正使用r2时才将a加载至寄存器中.这样一来,在执行使用b的代码时,我们不再霸占一个通用寄存器,从而减少需要借助栈空间的情况.这时需要使用volatile
     * */
    public void method1(){
        int r2 = a;
        b = 1;
        System.out.println(r2);
    }
    public void method2(){
        int r1 = b;
        a = 2;
        System.out.println(r1);
    }

    /*编译器优化以后,很有可能将b=1移到循环之前,而对r2的赋值语句还停留在循环之中*/
    public void method3(){
        for (;;) {
            int r2 = a;
            b = 1;
            System.out.println(b);
            if (r2 == 2) {
                System.out.println(r2);
            }
        }
    }
    /**
     * 单线程下,先调用线程1再调用线程2的话,(r1,r2)的值为(1,0),先调用线程2再调用线程1的话(r1,r2)的值为(0,2);
     * 多线程环境下,可能为(1,0),(0,2),(1,2)或者(0,0).
     * 造成这一状况的原因有三个:
     * 1.编译器重排序
     * 2.处理器的乱序执行
     * 3.内存系统的重排序
     * 如果两个操作之间存在数据依赖,那么即时编译器(和处理器)不能调整他们的顺序,否则将会造成程序语义上的改变。
     * */
    public static void main(String[] args) {
        final ReverseExa exa = new ReverseExa();
        for (int i=0;i<10;i++) {
            /*线程1*/
            new Thread(() -> {
                exa.method1();
            }).start();
            /*线程2*/
            new Thread(() -> {
                exa.method2();
            }).start();
        }
        /*new Thread(() -> {
            exa.method3();
        }).start();*/
    }
}
