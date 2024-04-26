package com.letere.bean;

import com.letere.constant.FrontAlign;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author gaozijie
 * @since 2024-04-25
 */
@NoArgsConstructor
public class SrtStyleBuilder {

    /**
     * 字体名称
     */
    private String frontName;

    /**
     * 字体大小
     */
    private Integer frontSize;

    /**
     * 字体颜色
     */
    private String frontColor;

    /**
     * 背景颜色
     */
    private String bgColor;

    /**
     * 上边距
     */
    private Integer top;

    /**
     * 下边距
     */
    private Integer bottom;

    /**
     * 对齐方式
     */
    private FrontAlign align;

    /**
     * 默认样式
     * @return srt样式构造类
     */
    public static SrtStyleBuilder defaultStyle() {
        return new SrtStyleBuilder()
                .frontName("宋体")
                .frontSize(36)
                .frontColor("#FFFFFF")
                .backgroundColor("#000000");
    }

    public SrtStyleBuilder frontName(String frontName) {
        this.frontName = frontName;
        return this;
    }

    public SrtStyleBuilder frontSize(Integer frontSize) {
        this.frontSize = frontSize;
        return this;
    }

    public SrtStyleBuilder frontColor(String frontColor) {
        this.frontColor = frontColor;
        return this;
    }

    public SrtStyleBuilder backgroundColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public SrtStyleBuilder topPadding(int top) {
        this.top = top;
        return this;
    }

    public SrtStyleBuilder bottomPadding(int bottom) {
        this.bottom = bottom;
        return this;
    }

    public SrtStyleBuilder frontAlign(FrontAlign align) {
        this.align = align;
        return this;
    }

    public String build() {
        String base = "%s";
        String frontStyle = this.buildFront();
        if (!Objects.isNull(frontStyle)) {
            base = String.format(base, frontStyle);
        }
        String subStyle = this.buildSub();
        if (!Objects.isNull(subStyle)) {
            base = String.format(base, subStyle);
        }
        String alignStyle = this.buildAlign();
        if (!Objects.isNull(alignStyle)) {
            base = String.format(base, alignStyle);
        }
        return base;
    }

    private String buildFront() {
        if (Objects.isNull(frontName)
                && Objects.isNull(frontSize)
                && Objects.isNull(frontColor)
                && Objects.isNull(bgColor)) {
            return null;
        }
        // 构建front标签
        StringBuilder builder = new StringBuilder();
        builder.append("<front");
        if (!Objects.isNull(frontName)) {
            builder.append(" face=\"").append(frontName).append("\"");
        }
        if (!Objects.isNull(frontSize)) {
            builder.append(" size=\"").append(frontSize).append("\"");
        }
        if (!Objects.isNull(frontColor)) {
            builder.append(" color=\"").append(frontColor).append("\"");
        }
        if (!Objects.isNull(bgColor)) {
            builder.append(" back=\"").append(bgColor).append("\"");
        }
        builder.append(">%s</front>");
        return builder.toString();
    }

    private String buildSub() {
        if (Objects.isNull(top)
                && Objects.isNull(bottom)) {
            return null;
        }
        // 构建sub标签
        StringBuilder builder = new StringBuilder();
        builder.append("<sub");
        if (!Objects.isNull(top)) {
            builder.append(" top=\"").append(top).append("px\"");
        }
        if (!Objects.isNull(bottom)) {
            builder.append(" bottom=\"").append(bottom).append("px\"");
        }
        builder.append(">%s</sub>");
        return builder.toString();
    }

    private String buildAlign() {
        if (Objects.isNull(align)) {
            return null;
        }
        // 构建align标签
        return "<align=" + align.getVal() + ">%s</align>";
    }
}
