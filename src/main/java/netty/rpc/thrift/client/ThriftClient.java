package netty.rpc.thrift.client;

import netty.rpc.thrift.DataException;
import netty.rpc.thrift.Person;
import netty.rpc.thrift.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

/**
 * @author lyq on 2021-07-05 9:59 下午
 * @desc
 */
public class ThriftClient {

    public static void main(String[] args) throws TTransportException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8888), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByName("张三");
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.married);

            System.out.println("----------");

            Person person1 = new Person();
            person1.setName("李四");
            person1.setAge(38);
            person1.setMarried(false);
            client.savePerson(person1);

        } catch (DataException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }

    }

}
