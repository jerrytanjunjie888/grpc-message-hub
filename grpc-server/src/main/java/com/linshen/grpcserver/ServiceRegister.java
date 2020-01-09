package com.linshen.grpcserver;

import io.etcd.jetcd.Client;

public class ServiceRegister {

    // etcd 客户端链接
    private  static Client client;

    private String host;

    private String path;

    public ServiceRegister(String host){
        this.host = host;
    }


}
