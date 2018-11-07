package com.aitank.server.handler.decoder;

import com.aitank.server.protocol.SocketModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;


import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {

    private Schema<SocketModel> schema = RuntimeSchema.getSchema(SocketModel.class);//protostuff的写法

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
                          List<Object> obj) throws Exception {
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        SocketModel message = new SocketModel();
        ProtobufIOUtil.mergeFrom(data, message, schema);
        obj.add(message);
    }

}
