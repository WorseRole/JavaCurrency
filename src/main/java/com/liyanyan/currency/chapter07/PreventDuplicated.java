package com.liyanyan.currency.chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/2 12:19 上午
 *
 * Hook线程只有在收到退出信号的时候会被执行，如果在kill的时候使用了参数-9，
 *      那么Hook线程不会得到执行，进程会立即退出，因此.lock文件将得不到清理
 * Hook线程中也可以执行一些资源释放的工作，比如：关闭文件句柄，socket链接，数据库connection
 * 尽量不要在Hook线程中执行一些好事非常长的操作，因为会导致程序迟迟不能退出
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "/Users/liyanyan/Desktop/";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        //检查是否存在.lock 文件
        checkRunning();

        //注入Hook 线程，在程序退出时删除lock 文件
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("the program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //简单模拟当前程序正在运行
        for( ; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if(path.toFile().exists()) {
            throw new RuntimeException("the program already running.");
        }
        Set<PosixFilePermission> params = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(params));
    }
    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
