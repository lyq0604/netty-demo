package netty.rpc.protobuf.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.rpc.protobuf.model.MyDataInfo;

import java.util.Random;

/**
 * @author lyq on 2021-07-04 10:35 上午
 * @desc
 */
public class ProtobufClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.Student student) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(2);
        MyDataInfo.MyMessage message;
        if (i == 0) {
            MyDataInfo.Student student = MyDataInfo.Student.newBuilder().setName("张三").setAge(20).setJob("student").build();
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                    .setStudent(student)
                    .build();
        } else {
            MyDataInfo.Teacher teacher = MyDataInfo.Teacher.newBuilder().setName("李四").setAge(20).setJob("teacher").build();
            message = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.TeacherType)
                    .setTeacher(teacher)
                .build();
        }
        ctx.writeAndFlush(message);
    }
}
