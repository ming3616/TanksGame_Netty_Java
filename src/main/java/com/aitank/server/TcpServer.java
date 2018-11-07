package com.aitank.server;

import com.aitank.server.config.ServerConfig;
import com.aitank.server.context.SpringContext;
import com.aitank.server.dao.UserDao;
import com.aitank.server.handler.TcpHandler;
import com.aitank.server.initializer.TcpServerInitializer;
import com.aitank.server.pojo.User;
import com.aitank.server.service.LoginService;
import com.aitank.server.service.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpServer {

        public void startTcpServer(){

            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workGroup = new NioEventLoopGroup();
            int port = SpringContext.getServerConfigs().getServerPort();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        .childHandler( new TcpServerInitializer(new TcpHandler()));
                ChannelFuture f = b.bind(port).sync();
                if (f.isSuccess())
                {
                    System.out.println("Server starts success at port:" + port);
                }
                f.channel().closeFuture().sync();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}
