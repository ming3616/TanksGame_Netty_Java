package com.aitank.server.initializer;

import com.aitank.server.handler.TcpHandler;
import com.aitank.server.handler.decoder.LengthDecoder;
import com.aitank.server.handler.decoder.MsgDecoder;
import com.aitank.server.handler.encoder.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

    protected TcpHandler tcpHandler ;

    public TcpServerInitializer(TcpHandler tcpHandler) {
        this.tcpHandler = tcpHandler;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new LengthDecoder(1024,0,4,0,4))
                                .addLast(new MsgDecoder())
                                .addLast(new MsgEncoder())
                                .addLast(tcpHandler);
    }
}
