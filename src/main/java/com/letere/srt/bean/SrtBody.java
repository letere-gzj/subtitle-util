package com.letere.srt.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaozijie
 * @since 2024-04-24
 */
@Data
public class SrtBody {

    /**
     * 下标
     */
    private int index;

    /**
     * 时间区间
     */
    private String timeInterval;

    /**
     * 字幕集合
     */
    private List<String> subtitles = new ArrayList<>();
}
