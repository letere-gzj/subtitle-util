package com.letere.whisper.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gaozijie
 * @since 2024-04-30
 */
@Getter
@AllArgsConstructor
public enum WhisperLanguage {

    /**
     * 中文
     */
    CHINESE("Chinese"),

    /**
     * 英语
     */
    ENGLISH("English"),

    /**
     * 日语
     */
    JAPANESE("Japanese");

    private final String value;
}
