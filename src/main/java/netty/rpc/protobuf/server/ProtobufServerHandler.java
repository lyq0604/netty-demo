package netty.rpc.protobuf.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.rpc.protobuf.model.MyDataInfo;

/**
 * @author lyq on 2021-07-04 10:20 上午
 * @desc
 */
public class ProtobufServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage msg) {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (MyDataInfo.MyMessage.DataType.StudentType == dataType) {
            MyDataInfo.Student student = msg.getStudent();
            System.out.println(student.getName());
            System.out.println(student.getAge());
            System.out.println(student.getJob());
        } else {
            MyDataInfo.Teacher teacher = msg.getTeacher();
            System.out.println(teacher.getName());
            System.out.println(teacher.getAge());
            System.out.println(teacher.getJob());
        }
    }

}
