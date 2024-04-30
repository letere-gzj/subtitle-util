package com.letere.srt;

import com.letere.srt.bean.SrtBody;
import com.letere.srt.bean.SrtStyle;
import com.letere.srt.util.FileUtil;
import com.letere.srt.util.SrtUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author gaozijie
 * @since 2024-04-24
 */
public class SrtRun {

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
        SrtStyle chineseStyle = SrtStyle.defaultStyle();
        SrtStyle englishStyle = SrtStyle.builder()
                .frontName("微软雅黑")
                .frontSize(30)
                .frontColor("#FFFFFF")
                .bgColor("#000000")
                .build();
        SrtUtil.addStyle(srtBodies, Arrays.asList(chineseStyle.getStyleFormat(), englishStyle.getStyleFormat()));
        FileUtil.writeSrt("./file/result/batchStyle.srt", srtBodies);
    }
}
