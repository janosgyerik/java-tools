package com.janosgyerik.demo.objectstore;

import com.example.tutorial.AddressBookProtos;
import com.janosgyerik.tools.objectstore.api.ObjectStore;
import com.janosgyerik.tools.objectstore.impl.CountingPathGenerator;
import com.janosgyerik.tools.objectstore.impl.Reader;
import com.janosgyerik.tools.objectstore.impl.SimpleObjectStore;
import com.janosgyerik.tools.objectstore.impl.Writer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Optional;

import static com.janosgyerik.demo.objectstore.ProtobufUtils.writer;
import static org.assertj.core.api.Assertions.assertThat;

public class ObjectStoreDemo {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test_1() throws IOException {
        CountingPathGenerator pathGenerator = new CountingPathGenerator(2, 2, folder.getRoot().toPath());
        Reader<AddressBookProtos.Person> reader = ProtobufUtils.reader(AddressBookProtos.Person.parser());
        Writer<AddressBookProtos.Person> writer = writer();
        ObjectStore<String, AddressBookProtos.Person> store = new SimpleObjectStore<>(pathGenerator, reader, writer);

        AddressBookProtos.Person.Builder builder = AddressBookProtos.Person.newBuilder();
        AddressBookProtos.Person jack = builder.setName("Jack").setId(1).build();
        AddressBookProtos.Person mike = builder.setName("Mike").setId(2).build();

        store.write(jack.getName(), jack);
        store.write(mike.getName(), mike);

        assertThat(store.read(jack.getName())).isEqualTo(Optional.of(jack));
        assertThat(store.read(mike.getName())).isEqualTo(Optional.of(mike));
    }
}
