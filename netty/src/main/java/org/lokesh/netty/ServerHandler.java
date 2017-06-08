package org.lokesh.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
 
public class ServerHandler extends ChannelInboundHandlerAdapter {
	public double time;
	public String topicName;
 
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
    time = 1.0 * ts.timeLapseInNanoSecond() / 1000000L;
    System.out.println("time delay in ms is : " + time);
    
  }
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.ALL_IDLE) {
        ctx.writeAndFlush(new LoopBackTimeStamp());
      }
    }
  }
 
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
