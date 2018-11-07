package com.aitank.server.protocol;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class SocketModel {

    public SocketModel() {
        super();
    }

    ////协议名称
    private String ProtocolName;
    ////描述
    private String ProtocolDesc;
    //传输的字节流数据
    private byte[] bytes;


    public <T> byte[] serializeList(List<T> objList) {
        if (objList == null || objList.isEmpty()) {
            throw new RuntimeException("序列化对象列表(" + objList + ")参数异常!");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(objList.get(0).getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        byte[] protostuff = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            protostuff = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("序列化对象列表(" + objList + ")发生异常!", e);
        } finally {
            buffer.clear();
            try {
                if(bos!=null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bytes = protostuff;
        return protostuff;
    }

    public <T> List<T> deserializeList( Class<T> targetClass) {
        byte[] paramArrayOfByte = bytes;

        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        List<T> result = null;
        try {
            result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(paramArrayOfByte), schema);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象列表发生异常!",e);
        }
        return result;
    }


    public <T> byte[] serialize(T obj) {

        if (obj == null) {
            throw new RuntimeException("序列化对象(" + obj + ")!");
        }

        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        byte[] protostuff = null;

        try {
            protostuff = ProtobufIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("序列化(" + obj.getClass() + ")对象(" + obj + ")发生异常!", e);
        } finally {
            buffer.clear();
        }

        bytes = protostuff;
        return bytes;
    }

    public <T> T deserialize(Class<T> targetClass) {
        byte[] paramArrayOfByte = bytes;

        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }

        T instance = null;

        try {
            // T message = objenesis.newInstance(cls);
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("反序列化过程中依据类型创建对象失败!", e);
        }
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtobufIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
        return instance;
    }


    public String getProtocolName() {
        return ProtocolName;
    }
    public void setProtocolName(String protocolName) {
        ProtocolName = protocolName;
    }
    public String getProtocolDesc() {
        return ProtocolDesc;
    }
    public void setProtocolDesc(String protocolDesc) {
        ProtocolDesc = protocolDesc;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
