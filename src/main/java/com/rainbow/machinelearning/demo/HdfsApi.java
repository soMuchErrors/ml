package com.rainbow.machinelearning.demo;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HdfsApi {

    // 定义一个fileSystem的变量，用的是Hadoop的包
    FileSystem fileSystem = null;

    // 添加一个注解
    @Before
    public void getfileSystem() throws IOException, InterruptedException, URISyntaxException{
//        System.setProperty("hadoop.home.dir", "/Users/mc");

        // 获取一个具体的文件系统对象
//        fileSystem = FileSystem.get(
//                // 创建一下HDFS文件系统的访问路径，就是Hadoop配置文件中的core-sit.xml中的HDFS文件系统的所在机器
//                new URI("hdfs://10.45.136.143:9000"),
//                // 创建一个Hadoop的配置文件的类
//                new  Configuration(),
//                // 就是Linux启动的用户名
//                "admin");

        fileSystem = FileSystem.get(// 创建一下HDFS文件系统的访问路径，就是Hadoop配置文件中的core-sit.xml中的HDFS文件系统的所在机器
                new URI("hdfs://10.45.136.143:9000"),
                // 创建一个Hadoop的配置文件的类
                new  Configuration());
    }

    /**
     *  从HDFS文件系统下载文件
     *      首先编写一个输入流，将内容输入到本地文件缓存。
     *      然后编写一个输出流，将内容输出到本地磁盘。
     */
    /**
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testDownload() throws IllegalArgumentException, IOException{

        // 构建一个输入流，将需要下载的文件写入到客户端的内存中
        FSDataInputStream in = fileSystem.open(new Path("/input/sting1.rtf"));

        // 构建一个输出流，将需要下载的文件从内存中写入到本地磁盘
//        FileOutputStream out = new FileOutputStream("D://11111111111111111111111111111111111111.txt");

        String str;

        while ((str = in.readLine()) != null){
            System.out.println(str);
        }


        in.close();
        /**
         * 参数说明：
         *     in
         *         代表输入流，读取HDFS文件系统的文件到本机内存中
         *     out
         *         代表输出流，将本机内存中的文件写入到本地磁盘中
         *     4096
         *         缓冲区大小
         *     true
         *         自动关闭流，如果不使用自动关闭的话需要手动关闭输入输出流
         *
         *         手动关闭输入输出流：
         *             IOUtils.closeStream(in);
         *            IOUtils.closeStream(out);
         */
//        IOUtils.copyBytes(in, out, 4096, true);

    }

    /**
     * 上传文件到HDFS文件系统
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testUpload() throws IllegalArgumentException, IOException{

        // 构建一个输入流，将本机需要上传的文件写入到内存中
        FileInputStream in = new FileInputStream("/Users/mc/Desktop/20180411.rtf");

        // 构建一个输出流，将客户端内存的数据写入到HDFS文件系统指定的路径中
        FSDataOutputStream out = fileSystem.create(new Path("/input/sting1.rtf"), true);

        /*
         *    参数说明：
         *     in
         *         代表输入流，读取HDFS文件系统的文件到本机内存中
         *     out
         *         代表输出流，将本机内存中的文件写入到本地磁盘中
         *     4096
         *         缓冲区大小
         *     true
         *         自动关闭流，如果不使用自动关闭的话需要手动关闭输入输出流
         *
         *         手动关闭输入输出流：
         *             IOUtils.closeStream(in);
         *            IOUtils.closeStream(out);
         */
        IOUtils.copyBytes(in, out, 4096, true);

    }

    /**
     * 测试创建一个目录
     *     bin/hdsf dfs -mkdir [-p] /dir
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testMakeDir() throws IllegalArgumentException, IOException{

        boolean isSuccess = fileSystem.mkdirs(new Path("/testMK"));

        System.out.println(isSuccess);

    }

    /**
     * 测试删除目录/文件：
     * @throws IOException
     * @throws IllegalArgumentException
     *
     */
    @Test
    public void testDel() throws IllegalArgumentException, IOException{
        System.out.println(
                // 返回的是一个boolean类型的值
                fileSystem.delete(
                        // 指定要删除的目录
                        new Path("/testMK"),
                        // 是否使用递归删除
                        true
                ));
    }
}