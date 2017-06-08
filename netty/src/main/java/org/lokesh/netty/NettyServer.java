package org.lokesh.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import java.io.IOException;
import org.lokesh.netty.codec.TimeStampDecoder;
import org.lokesh.netty.codec.TimeStampEncoder;
 
public class NettyServer {
 
  public static void main(String[] args) throws IOException, InterruptedException {
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(boosGroup, workerGroup);
    bootstrap.channel(NioServerSocketChannel.class);
    final EventExecutorGroup group = new DefaultEventExecutorGroup(1500); 
     
    bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
      @Override
      protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("idleStateHandler",new IdleStateHandler(0,0,10));
        pipeline.addLast(new TimeStampEncoder()); 
        pipeline.addLast(new TimeStampDecoder()); 
        pipeline.addLast(group,"serverHandler",new ServerHandler()); 
      }
    });
     
    bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
    bootstrap.bind(19000).sync();
  }
}
