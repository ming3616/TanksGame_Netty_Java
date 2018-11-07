package com.aitank.utils;

public class CodeExchanged {

    /***
     * @Description: 字节转化成整型
     * @param: [data, offset]
     * @return: int
     * @author: lhz
     * @Date: 2018/10/26
    */

    public static int bytesToInt(byte[] data, int offset) {
        int num = 0;
        for (int i = offset; i < offset + 4; i++) {
            num <<= 8;
            num |= (data[i] & 0xff);
        }
        return num;
    }

    /***
     * @Description: 整型转成字节类型
     * @param: [num]
     * @return: byte[]
     * @author: lhz
     * @Date: 2018/10/26
    */

    public static byte[] intToBytes(int num) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (num >>> (24 - i * 8));
        }
        return b;
    }
}
