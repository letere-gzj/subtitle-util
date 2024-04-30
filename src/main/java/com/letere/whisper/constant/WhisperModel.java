package com.letere.whisper.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gaozijie
 * @since 2024-04-30
 */
@AllArgsConstructor
@Getter
public enum WhisperModel {

    /**
     * 轻量
     */
    TINY("tiny"),

    /**
     * 基础
     */
    BASE("base"),

    /**
     * 小
     */
    SMALL("small"),

    /**
     * 中等
     */
    MEDIUM("medium"),

    /**
     * 大
     */
    LARGE("large");

    private final String value;
}
