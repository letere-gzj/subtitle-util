package com.letere.srt.util;

import com.letere.srt.bean.SrtBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gaozijie
 * @since 2024-04-25
 */
public class SrtUtil {

    private final static Pattern SUB_TITLE_PATTERN = Pattern.compile("(<.*?>)*(.*)");

    /**
     * 合并多条字幕
     * @param srtBodyGroup 字幕分组
     * @return 合并后的字幕
     */
    public static List<SrtBody> mergeSubtitles(List<List<SrtBody>> srtBodyGroup) {
        if (srtBodyGroup == null || srtBodyGroup.size() == 0) {
            return Collections.emptyList();
        }
        List<SrtBody> baseSrt = srtBodyGroup.get(0);
        if (srtBodyGroup.size() == 1) {
            return baseSrt;
        }
        // 校验字幕条数
        for (int i=1; i<srtBodyGroup.size(); i++) {
            if (!Objects.equals(baseSrt.size(), srtBodyGroup.get(i).size())) {
                throw new RuntimeException("字幕条数不一致，合并失败");
            }
        }
        // 合并字幕条数
        List<SrtBody> mergeSrt;
        for (int i=1; i<srtBodyGroup.size(); i++) {
            mergeSrt = srtBodyGroup.get(i);
            for (int j=0; j<baseSrt.size(); j++) {
                baseSrt.get(j).getSubtitles().addAll(
                        mergeSrt.get(j).getSubtitles()
                );
            }
        }
        return baseSrt;
    }

    /**
     * 添加样式
     * @param srtBodies srt字幕集合
     * @param style 样式
     */
    public static void addStyle(List<SrtBody> srtBodies, String style) {
        if (srtBodies == null
                || srtBodies.size() == 0
                || style == null
                || style.length() == 0) {
            return;
        }
        // 添加样式
        List<String> subTitles;
        for (SrtBody srtBody : srtBodies) {
            subTitles = new ArrayList<>();
            for (String subTitle : srtBody.getSubtitles()) {
                subTitle = getSubtitleContent(subTitle);
                subTitles.add(String.format(style, subTitle));
            }
            srtBody.setSubtitles(subTitles);
        }
    }

    /**
     * 多语言添加样式
     * @param srtBodies srt字幕集合
     * @param styles 样式集合
     */
    public static void addStyle(List<SrtBody> srtBodies, List<String> styles) {
        if (srtBodies == null
                || srtBodies.size() == 0
                || styles == null
                || styles.size() == 0) {
            return;
        }
        if (!Objects.equals(srtBodies.get(0).getSubtitles().size(), styles.size())) {
            throw new RuntimeException("字幕行数和样式个数不一致");
        }
        // 添加样式
        List<String> subtitles;
        String subtitle;
        for (SrtBody srtBody : srtBodies) {
            subtitles = new ArrayList<>();
            for (int i=0; i<srtBody.getSubtitles().size(); i++) {
                subtitle = getSubtitleContent(srtBody.getSubtitles().get(i));
                subtitles.add(String.format(styles.get(i), subtitle));
            }
            srtBody.setSubtitles(subtitles);
        }
    }

    /**
     * 获取字幕文本内容
     * @param subtitle 字幕
     * @return 字幕文本
     */
    private static String getSubtitleContent(String subtitle) {
        int index = subtitle.indexOf("</");
        // 判断文本是否有样式，有样式则提取样式中的文本
        if (index > 0) {
            subtitle = subtitle.substring(0, index);
            Matcher matcher = SUB_TITLE_PATTERN.matcher(subtitle);
            if (matcher.find()) {
                subtitle = matcher.group(2);
            }
        }
        return subtitle;
    }
}
