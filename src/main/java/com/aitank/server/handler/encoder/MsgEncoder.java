package com.aitank.server.handler.encoder;

import com.aitank.server.protocol.SocketModel;
import com.aitank.utils.CodeExchanged;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class MsgEncoder extends MessageToByteEncoder<SocketModel> {

    private Schema<SocketModel> schema = RuntimeSchema.getSchema(SocketModel.class);
    @Override
    protected void encode(ChannelHandlerContext ctx, SocketModel message,
                          ByteBuf out) {

        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        byte[] data = ProtobufIOUtil.toByteArray(message, schema, buffer);
        //在写消息之前需要把消息的长度添加到前4个字节
        ByteBuf buf = Unpooled.copiedBuffer(CodeExchanged.intToBytes(data.length),data);
        out.writeBytes(buf);
    }
}
