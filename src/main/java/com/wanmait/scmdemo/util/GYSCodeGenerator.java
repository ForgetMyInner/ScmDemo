package com.wanmait.scmdemo.util;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GYSCodeGenerator {
    private static final String PREFIX = "GYS";
    private static final int CODE_LENGTH = 6;
    private static final long MAX_CODE_NUMBER = 999999L;
    private static final ConcurrentHashMap<String, Boolean> usedCodes = new ConcurrentHashMap<>();
    private static final AtomicInteger retryCount = new AtomicInteger(0);

    /**
     * 使用UUID生成一个以 "GYS" 开头的六位数编码。
     *
     * @return 返回生成的编码字符串
     */
    public static synchronized String generateCode() {
        String code;
        do {
            // 如果重试次数过多，抛出异常防止无限循环
            if (retryCount.incrementAndGet() > 100) {
                throw new RuntimeException("无法生成唯一编码，请检查已使用的编码数量");
            }

            // 生成UUID并转换为long类型
            long uuidLong = Math.abs(UUID.randomUUID().getMostSignificantBits());

            // 取模运算保证数值在0到999999之间
            long codeNumber = uuidLong % (MAX_CODE_NUMBER + 1);

            // 格式化数字，不足6位前面补零
            code = String.format("%s%0" + CODE_LENGTH + "d", PREFIX, codeNumber);
        } while (usedCodes.putIfAbsent(code, Boolean.TRUE) != null); // 确保编码唯一

        // 重置重试计数器
        retryCount.set(0);

        return code;
    }
    private static final String PREFIXZR = "GYSZR";
    private static final ConcurrentHashMap<String, AtomicInteger> dailySequenceNumbers = new ConcurrentHashMap<>();
    //供应商准入编码
    public static synchronized String generateEntryCode() {
        LocalDate now = LocalDate.now();
        String datePart = now.format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"));
        String key = PREFIXZR + datePart;

        // 获取或初始化当天的序列号计数器
        AtomicInteger sequenceNumber = dailySequenceNumbers.computeIfAbsent(key, k -> new AtomicInteger(0));

        // 如果序列号超过最大值，则重置为0
        if (sequenceNumber.get() >= MAX_CODE_NUMBER) {
            sequenceNumber.set(0);
        }

        // 生成并格式化序列号
        int seq = sequenceNumber.incrementAndGet();
        String formattedSeq = String.format("%0" + CODE_LENGTH + "d", seq);

        return key + "-" + formattedSeq;
    }

    public static void main(String[] args) {
        // 测试生成编码的功能
        for (int i = 0; i < 10; i++) {
            System.out.println(generateCode());
        }
    }
}
