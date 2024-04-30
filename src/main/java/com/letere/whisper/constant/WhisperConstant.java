package com.letere.whisper.constant;

/**
 * @author gaozijie
 * @since 2024-04-30
 */
public interface WhisperConstant {

    String LANGUAGE_CMD = "--language";

    String MODEL_CMD = "--model";

    String NOT_REPEAT_CMD = "--temperature_increment_on_fallback";

    String WORD_TIMESTAMPS_CMD = "--word_timestamps";

    String MAX_LINE_WIDTH_CMD = "--max_line_width";

    String MAX_LINE_COUNT_CMD = "--max_line_count";

    String INITIAL_PROMPT = "--initial_prompt";

    String SIMPLE_CHINESE_INITIAL = "以下是普通话的句子。";
}
