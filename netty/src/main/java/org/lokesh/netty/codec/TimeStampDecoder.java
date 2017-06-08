package org.lokesh.netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import org.lokesh.netty.LoopBackTimeStamp;
public class TimeStampDecoder extends ByteToMessageDecoder {
	 
	  @Override
	  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	    final int messageLength = Long.SIZE/Byte.SIZE *2;
	    if (in.readableBytes() < messageLength) {
	      return;
	    }
	     
	    byte [] ba = new byte[messageLength];
	    in.readBytes(ba, 0, messageLength);
	    LoopBackTimeStamp loopBackTimeStamp = new LoopBackTimeStamp();
	    loopBackTimeStamp.fromByteArray(ba);
	    out.add(loopBackTimeStamp);
	  }
	}
