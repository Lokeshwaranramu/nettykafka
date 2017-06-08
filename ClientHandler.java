package org.lokesh.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
 
public class ClientHandler extends ChannelInboundHandlerAdapter {
 
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
    ctx.writeAndFlush(ts); //recieved message sent back directly
  }
 
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
