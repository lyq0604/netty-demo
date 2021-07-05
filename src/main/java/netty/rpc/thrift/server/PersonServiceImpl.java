package netty.rpc.thrift.server;

import netty.rpc.thrift.DataException;
import netty.rpc.thrift.Person;
import netty.rpc.thrift.PersonService;
import org.apache.thrift.TException;

/**
 * @author lyq on 2021-07-05 9:45 下午
 * @desc
 */
public class PersonServiceImpl implements PersonService.Iface{

    @Override
    public Person getPersonByName(String name) throws DataException, TException {
        System.out.println("Get Client param: " + name);
        Person person = new Person();
        person.setName(name);
        person.setAge(18);
        person.setMarried(true);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.married);
    }
}
