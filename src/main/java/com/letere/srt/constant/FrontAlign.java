package com.letere.srt.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字幕对齐方式
 * @author gaozijie
 * @since 2024-04-25
 */
@AllArgsConstructor
@Getter
public enum FrontAlign {

    /**
     * 左对齐
     */
    LEFT("left"),

    /**
     * 居中对齐
     */
    CENTER("center"),

    /**
     * 右对齐
     */
    RIGHT("right");

    private final String val;
}
