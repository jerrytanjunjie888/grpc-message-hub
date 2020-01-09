package com.linshen.grpcserver;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class EtcdUtil {

    // 日志信息
    private static final Logger logger = LoggerFactory.getLogger(EtcdUtil.class);

    // etcd 客户端链接
    private  static Client client = null;

    //端口
    private  static String IP_PORT = "http://localhost:2379";

    // 链接初始化
    public static Client getEtclClient() {
        if(client == null) {
            synchronized (EtcdUtil.class) {
                client = Client.builder().endpoints(IP_PORT).build();
            }
        }
        return client;
    }

    // 获取键和值的Client
    public static KV getKVClient() {
        return EtcdUtil.getEtclClient().getKVClient();
    }

    // put 设置值
    public static void putKVClient() {
        ByteSequence key = ByteSequence.from("test_key".getBytes());
        ByteSequence value = ByteSequence.from("test_value1212".getBytes());
        EtcdUtil.getKVClient().put(key, value);
    }

    // delete 设置值
    public static void deleteKVClient() {
        ByteSequence key = ByteSequence.from("test_key".getBytes());
        EtcdUtil.getKVClient().delete(key);
    }

    // get 设置值
    public static CompletableFuture<GetResponse> getKVValue() {
        ByteSequence key = ByteSequence.from("test_key".getBytes());
        return  EtcdUtil.getKVClient().get(key);
    }

    public static void main(String args[])throws Exception {
        EtcdUtil.putKVClient();
        logger.info(EtcdUtil.getKVValue().get()+"tests11111");

//        KV kvClient = EtcdUtil.getEtclClient().getKVClient();
//
//        ByteSequence key = ByteSequence.from("test_key".getBytes());
//        ByteSequence value = ByteSequence.from("test_value".getBytes());
//
//        // 新增
//        kvClient.put(key, value);
//
//        // 删除
////        kvClient.delete(key);
//
//        // 查询
//        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
//        logger.info(getFuture.toString() +"1111111111");
//
//
//        // get the value from CompletableFuture
//        GetResponse response = getFuture.get();
//
//        logger.info(response.toString()+"222222");

    }

}
