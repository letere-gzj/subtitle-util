package com.letere;

import com.letere.bean.SrtBody;
import com.letere.bean.SrtStyleBuilder;
import com.letere.util.FileUtil;
import com.letere.util.SrtUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author gaozijie
 * @since 2024-04-24
 */
public class MainRun {

    public static void main(String[] args) {
//        mergeTest();
        batchAddStyleTest();
    }

    /**
     * 字幕合并测试
     */
    public static void mergeTest() {
        List<SrtBody> chinese = FileUtil.readAsSrt("./file/sample/chinese.srt");
        List<SrtBody> english = FileUtil.readAsSrt("./file/sample/english.srt");
        List<SrtBody> mergeBodies = SrtUtil.mergeSubtitles(Arrays.asList(chinese, english));
        FileUtil.writeSrt("./file/result/merge.srt", mergeBodies);
    }

    /**
     * 多行添加样式测试
     */
    public static void batchAddStyleTest() {
        List<SrtBody> srtBodies = FileUtil.readAsSrt("./file/sample/merge.srt");
        String chineseStyle = SrtStyleBuilder.defaultStyle().build();
        String englishStyle = new SrtStyleBuilder()
                .frontName("微软雅黑")
                .frontSize(30)
                .frontColor("#FFFFFF")
                .backgroundColor("#000000")
                .build();
        SrtUtil.addStyle(srtBodies, Arrays.asList(chineseStyle, englishStyle));
        FileUtil.writeSrt("./file/result/batchStyle.srt", srtBodies);
    }
}
