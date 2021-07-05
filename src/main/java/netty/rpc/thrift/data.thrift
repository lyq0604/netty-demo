namespace java netty.rpc.thrift

typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {

    1: optional String name;
    2: optional int age;
    3: optional boolean married;

}

exception DataException {

    1: optional int code;
    2: optional String message;
    3: optional String timestamp;

}

service PersonService {

    Person getPersonByName(1: required String name) throws (1: DataException e);
    void savePerson(1: required Person person) throws (1: DataException e);

}