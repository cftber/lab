package com.tgzhao.core.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgzhao on 16/7/17.
 */
public class FileUtil {

    /**
     * 递归查找获取一个目录下所有子目录(及子目录的子目录)。子目录如果以 '.' 开头，将被忽略
     * @param dir 目录
     * @return 子目录数组
     */
    public static File[] scanDirs(File dir) {
        ArrayList<File> list = new ArrayList<>();
        list.add(dir);
        scanDirs(dir, list);
        return list.toArray(new File[list.size()]);
    }

    private static void scanDirs(File rootDir, List<File> list) {
        File[] dirs = rootDir.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return !f.isHidden()
                        && f.isDirectory()
                        && !f.getName().startsWith(".");
            }
        });

        if (dirs != null) {
            for (File dir : dirs) {
                scanDirs(dir, list);
                list.add(dir);
            }
        }
    }

    /**
     * 扫描一个根目录下的所有文件，不包括隐藏文件及.开头的文件
     * @param rootDir
     * @param ruleFile
     * @return
     */
    public static File[] scanFiles(String rootDir, String ruleFile) {
        File root = new File(rootDir);
        if (!root.exists() || !root.isDirectory()) {
            System.out.println("根目录不存在:" + rootDir);
            return new File[0];
        }
        rootDir = root.getAbsolutePath();

        File[] dirs = scanDirs(root);
        System.out.println("目录数:" + dirs.length);

        ArrayList<File> files = new ArrayList<>();
        for (File dir : dirs) {
            File[] aFiles = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return !f.isHidden()
                            && f.isFile();
                }
            });
            for (File file:aFiles) {
                files.add(file);
            }
        }

        return files.toArray(new File[files.size()]);
    }

    /**
     * 计算总大小
     * @param rootDir
     * @return
     */
    public static long caculateFileSizeSum(String rootDir) {
        File root = new File(rootDir);
        if(!root.exists() || !root.isDirectory()) {
            System.out.println("根目录不存在:" + rootDir);
            return 0;
        }
        rootDir = root.getAbsolutePath();

        File[] dirs = scanDirs(root);
        System.out.println("目录数:" + dirs.length);

        int fileCount = 0;
        long sizeSum = 0;
        for(File dir:dirs) {
            File[] aFiles = dir.listFiles(new FileFilter() {
                public boolean accept(File f) {
                    return true;
                }
            });
            fileCount += aFiles.length;
            for(File file:aFiles) {
                sizeSum += file.length();
            }
        }

        System.out.println(String.format("目录数：%d，文件数：%d，总大小：%d", dirs.length, fileCount, sizeSum));
        return sizeSum;
    }
}
