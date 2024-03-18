package demo;

import com.alipay.sofa.jraft.rhea.client.DefaultRheaKVStore;
import com.alipay.sofa.jraft.rhea.client.RheaKVStore;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import static com.alipay.sofa.jraft.util.BytesUtil.readUtf8;
import static com.alipay.sofa.jraft.util.BytesUtil.writeUtf8;

import java.io.File;
import java.io.IOException;

public class HelloWorldKV {
    static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    static  RheaKVStoreOptions opts = null;
    static final RheaKVStore rheaKVStore = new DefaultRheaKVStore();

    public static void main(String args[]) throws IOException {
        String path = "/Users/yangjiaqi/Documents/hugegraph_concurrency/RaftDemo/src/main/resources/rheakv_conf/application.yaml";

        opts = mapper.readValue(new File(path), RheaKVStoreOptions.class);

        if (rheaKVStore.init(opts)) {
            final byte[] value = writeUtf8("hello world!!!");
            rheaKVStore.bPut("hello", value);

            System.out.println(readUtf8(rheaKVStore.bGet("hello")));
            // ...
            // Have fun !!!
        }

    }


}