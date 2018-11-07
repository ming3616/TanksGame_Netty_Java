package com.aitank.server.component;

import com.aitank.server.context.SpringContext;
import com.aitank.server.enums.EventType2;
import com.aitank.server.handler.TcpHandler;
import com.aitank.server.protocol.PlayInfoData;
import com.aitank.server.protocol.PlayInfoDataList;
import com.aitank.server.protocol.SocketModel;
import com.aitank.server.protocol.UserLoginData;
import com.aitank.utils.ChannelHandlerContextInfo;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class ObjectMultiAction {

    public  void MsgLogin(ChannelHandlerContext ctx, SocketModel message){
        UserLoginData UserLoginData = message.deserialize(UserLoginData.class);

        //获取数值
        System.out.println("clinet:" + ctx.channel().id() + " 用户名:" + UserLoginData.getUserName());
        System.out.println("clinet:" + ctx.channel().id() + " 密码:" + UserLoginData.getPassWord());

        //数据库查询用户是否存在   试验过可行
//        if(SpringContext.getUserService().selectUserByName(UserLoginData.getUserName())==null)
//        {
//            System.out.println("此用户不在数据库中："+ UserLoginData.getUserName());
//        }


        TcpHandler.players.put(
                ctx.channel().id().toString(),
                new ChannelHandlerContextInfo(UserLoginData.getUserName(), ctx)
        );
        System.out.println("PlayerNumbers : " + TcpHandler.players.size());

        //构建返回协议
        UserLoginData.setUserId(ctx.channel().id().toString());

        SocketModel response = new SocketModel();
        response.setProtocolName(EventType2.MsgLogin.getName());//("MsgLogin");
        response.serialize(UserLoginData);
        ctx.writeAndFlush(response);
    }
    public void MsgPlayInfoData(ChannelHandlerContext ctx, SocketModel message){
        PlayInfoData playInfoData = message.deserialize(PlayInfoData.class);

        ChannelHandlerContextInfo currentPlayer = TcpHandler.players.get(ctx.channel().id().toString());
        currentPlayer.setPosition(new float[]{playInfoData.position1,playInfoData.position2,playInfoData.position3});
        currentPlayer.setRotation(new float[]{playInfoData.rotation1,playInfoData.rotation2,playInfoData.rotation3,playInfoData.rotation4});
        currentPlayer.setShoot(playInfoData.shoot);
        currentPlayer.serverHealth = playInfoData.serverHealth;
        SocketModel response = new SocketModel();
        response.setProtocolName(EventType2.MsgPlayInfoData.getName());//("MsgPlayInfoData");

        response.serialize(playInfoData);
        for (String cid : TcpHandler.players.keySet())
        {
            if(cid.equals(ctx.channel().id().toString()))
                continue;
            ChannelHandlerContextInfo player = TcpHandler.players.get(cid);
            player.getChx().writeAndFlush(response);
        }
    }
    public void MsgInitPlay(ChannelHandlerContext ctx, SocketModel message){
        PlayInfoDataList playInfoDataList = new PlayInfoDataList();

        for (String cid : TcpHandler.players.keySet()) {
            ChannelHandlerContextInfo player = TcpHandler.players.get(cid);
            PlayInfoData playInfoData = new PlayInfoData();
            playInfoData.position1 = player.getPosition()[0];
            playInfoData.position2 = player.getPosition()[1];
            playInfoData.position3 = player.getPosition()[2];
            playInfoData.rotation1 = player.getRotation()[0];
            playInfoData.rotation2 = player.getRotation()[1];
            playInfoData.rotation3 = player.getRotation()[2];
            playInfoData.rotation4 = player.getRotation()[3];
            playInfoData.shoot = player.isShoot();
            playInfoData.serverHealth = player.serverHealth;
            playInfoData.userId = player.getChx().channel().id().toString();
            playInfoData.playName = player.getName();

            playInfoDataList.playInfoDataList.add(playInfoData);
        }

        SocketModel response = new SocketModel();
        response.setProtocolName(EventType2.MsgInitPlay.getName());//("MsgInitPlay");
        //response.serializeList(playInfoDataList);
        response.serialize(playInfoDataList);

        ctx.writeAndFlush(response);
    }
}
