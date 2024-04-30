package com.letere.whisper;

import com.letere.common.util.CmdUtil;
import com.letere.whisper.bean.WhisperCmd;

import java.util.List;

/**
 * @author gaozijie
 * @since 2024-04-30
 */
public class WhisperRun {

    public static void main(String[] args) {
        WhisperCmd whisperCmd = WhisperCmd.defaultCmd()
                .filePaths(List.of("./file/1.mp3"))
                .build();
        String cmd = whisperCmd.getCmd();
        System.out.println(cmd);
        // 运行cmd
        CmdUtil.execute(cmd, true);
    }
}
