package com.letere.srt.util;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.letere.srt.bean.SrtBody;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author gaozijie
 * @since 2024-04-24
 */
public class FileUtil {

    /**
     * 读取srt字幕
     * @param filePath 文件路径
     * @return srt内容集合
     */
    public static List<SrtBody> readAsSrt(String filePath) {
        // 读取文件
        FileReader fileReader = new FileReader(new File(filePath));
        List<String> lines = fileReader.readLines();
        if (lines.size() == 0) {
            return Collections.emptyList();
        }
        // 封装为str
        List<SrtBody> srtBodies = new ArrayList<>();
        SrtBody srtBody = new SrtBody();
        int idx = 0;
        int srtIdx = 0;
        for (String line : lines) {
            if (Objects.equals(line, "")) {
                if (!Objects.isNull(srtBody.getTimeInterval())) {
                    srtBodies.add(srtBody);
                    srtBody = new SrtBody();
                    srtIdx = 0;
                }
                continue;
            }
            // 0：序号, 1：时间区间, 2：字幕内容
            switch (srtIdx) {
                case 0 -> {
                    srtBody.setIndex(idx++);
                    srtIdx++;
                }
                case 1 -> {
                    srtBody.setTimeInterval(line);
                    srtIdx++;
                }
                case 2 -> {
                    srtBody.getSubtitles().add(line);
                }
                default -> {}
            }
        }
        if (!Objects.isNull(srtBody.getTimeInterval())) {
            srtBodies.add(srtBody);
        }
        return srtBodies;
    }

    /**
     * 写入srt字幕文件
     * @param filePath 文件路径
     * @param srtBodies srt内容集合
     */
    public static void writeSrt(String filePath, List<SrtBody> srtBodies) {
        // 将srt内容集合转成字符串集合
        List<String> lines = new ArrayList<>();
        if (srtBodies != null && srtBodies.size() > 0) {
            SrtBody srtBody;
            for (int i=0; i<srtBodies.size(); i++) {
                srtBody = srtBodies.get(i);
                lines.add(String.valueOf(srtBody.getIndex()));
                lines.add(srtBody.getTimeInterval());
                lines.addAll(srtBody.getSubtitles());
                if (i != srtBodies.size()-1) {
                    lines.add("");
                }
            }
        }
        FileWriter fileWriter = new FileWriter(new File(filePath));
        fileWriter.writeLines(lines);
    }
}
