package com.letere.whisper.bean;

import com.letere.whisper.constant.WhisperConstant;
import com.letere.whisper.constant.WhisperLanguage;
import com.letere.whisper.constant.WhisperModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gaozijie
 * @since 2024-04-30
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhisperCmd {

    /**
     * 文件路径集合
     */
    private List<String> filePaths;

    /**
     * 模型
     */
    private WhisperModel model;

    /**
     * 听译语言
     */
    private WhisperLanguage language;

    /**
     * 词时间戳(用于分词)
     */
    private Boolean wordTimestamps;

    /**
     * 最大行宽
     */
    private Integer maxLineWidth;

    /**
     * 最大行数
     */
    private Integer maxLineCount;

    /**
     * 过滤重复
     */
    private Boolean filterRepeat;

    /**
     * 初始动机
     */
    private String initialPrompt;

    /**
     * 默认cmd
     * @return whisperCmd对象
     */
    public static WhisperCmdBuilder defaultCmd() {
         return WhisperCmd.builder()
                 .language(WhisperLanguage.CHINESE)
                 .model(WhisperModel.LARGE)
                 .wordTimestamps(true)
                 .maxLineWidth(14)
                 .maxLineCount(2)
                 .filterRepeat(true)
                 .initialPrompt(WhisperConstant.SIMPLE_CHINESE_INITIAL);
    }

    /**
     * 获取cmd
     * @return cmd字符串
     */
    public String getCmd() {
        StringBuilder builder = new StringBuilder();
        builder.append("whisper ").append(String.join(" ", filePaths));
        if (!Objects.isNull(model)) {
            builder.append(" ").append(WhisperConstant.MODEL_CMD).append(" ").append(model.getValue());
        }
        if (!Objects.isNull(language)) {
            builder.append(" ").append(WhisperConstant.LANGUAGE_CMD).append(" ").append(language.getValue());
        }
        if (Objects.equals(wordTimestamps, true)) {
            builder.append(" ").append(WhisperConstant.WORD_TIMESTAMPS_CMD).append(" ").append(true);
        }
        if (!Objects.isNull(maxLineWidth)) {
            builder.append(" ").append(WhisperConstant.MAX_LINE_WIDTH_CMD).append(" ").append(maxLineWidth);
        }
        if (!Objects.isNull(maxLineCount)) {
            builder.append(" ").append(WhisperConstant.MAX_LINE_COUNT_CMD).append(" ").append(maxLineCount);
        }
        if (Objects.equals(filterRepeat, true)) {
            builder.append(" ").append(WhisperConstant.NOT_REPEAT_CMD);
        }
        if (!Objects.isNull(initialPrompt)) {
            builder.append(" ").append(WhisperConstant.INITIAL_PROMPT).append(" \"").append(initialPrompt).append("\"");
        }
        return builder.toString();
    }
}
