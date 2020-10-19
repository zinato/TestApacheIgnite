package com.braincolla.ignite.main;

import com.braincolla.ignite.fileread.JsonConvert;
import com.braincolla.ignite.utils.ExamplesUtils;
import com.braincolla.ignite.vo.JoaraBook;
import com.braincolla.ignite.vo.RecommendData;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

public class Main {

  private static final String BOOKMETADATAFILE = "book_meta_data.jsonl";
  private static final String RECOMMENDFILE = "recommend_data_100_5m.jsonl";
  private static final String BOOKTEST = "test_book.jsonl";

  private static final String JOARA_BOOK_METE_CACHE = "joaraBookMetaData";
  private static final String JOARA_RECOMMEND_DATA_CACHE = "joaraReommendData";

  /** Heap size required to run this example. */
  public static final int MIN_MEMORY = 512 * 1024 * 1024;

  public static void main(String[] args) throws IgniteException, IOException, URISyntaxException {
    ExamplesUtils.checkMinMemory(MIN_MEMORY);
    System.out.println("Main Class Execute!!");
    //embedded mode

    //set server
    IgniteConfiguration serverConfig = new IgniteConfiguration()

      .setIgniteInstanceName("my-server")

      .setDiscoverySpi(new TcpDiscoverySpi()

        .setIpFinder(

          new TcpDiscoveryVmIpFinder()

            .setAddresses(Collections.singleton("127.0.0.1:47500..47502"))

        ));

    Ignite server = Ignition.start(serverConfig);

    //set client
    IgniteConfiguration clientConfig = new IgniteConfiguration()

      .setIgniteInstanceName("my-client")

      .setDiscoverySpi(new TcpDiscoverySpi()

        .setIpFinder(

          new TcpDiscoveryVmIpFinder()

            .setAddresses(Collections.singleton("127.0.0.1:47500..47502"))

        ))

      .setClientMode(true);

    Ignite client = Ignition.start(clientConfig);
//    server-client mode
//    IgniteConfiguration cfg = new IgniteConfiguration();
//    cfg.setClientMode(true);
//    // Classes of custom Java logic will be transferred over the wire from this app.
//    cfg.setPeerClassLoadingEnabled(true);
//
//    // Setting up an IP Finder to ensure the client can locate the servers.
//    TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
//    ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
//    cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
//
//    // Starting the node
//    Ignite ignite = Ignition.start(cfg);

//    addCacheData(ignite);

    // Create an IgniteCache and put some values in it.
//    IgniteCache<Integer, JoaraBook> cache = ignite.getOrCreateCache(JOARA_BOOK_METE_CACHE);
    try (IgniteCache<Integer, JoaraBook> cache = client.getOrCreateCache(JOARA_BOOK_METE_CACHE)) {
      long start = System.currentTimeMillis();

      //data import with stream
      try (IgniteDataStreamer<Integer, JoaraBook> stmr = client
        .dataStreamer(JOARA_BOOK_METE_CACHE)) {
        // Configure loader.
        stmr.perNodeBufferSize(1024);
        stmr.perNodeParallelOperations(8);

        JsonConvert jsonConvert = new JsonConvert();
        List<JoaraBook> joaraBookList = jsonConvert
          .makeJoaraBookMetaDataMapFromJson(BOOKMETADATAFILE);
        for (int i = 0; i < joaraBookList.size(); i++) {
          stmr.addData(i, joaraBookList.get(i));
        }
      }
      long end = System.currentTimeMillis(); // end time
      System.out.println("cache input total time : " + (end - start) / 1000.0 + "초");
      System.out.println("cache size: " + cache.size());
      System.out.println("cache 1: " + cache.get(502341));
      System.out.println(">> Created Joara Book Metadata Cache.");

      // Executing custom Java compute task on server nodes.
//    ignite.compute(ignite.cluster().forServers()).broadcast(new RemoteTask());

//    ignite.destroyCache(JOARA_BOOK_METE_CACHE);

    }

    try (IgniteCache<Integer, RecommendData> cache = client
      .getOrCreateCache(JOARA_RECOMMEND_DATA_CACHE)) {
      long start = System.currentTimeMillis();

      //data import with stream
      try (IgniteDataStreamer<Integer, RecommendData> stmr = client
        .dataStreamer(JOARA_RECOMMEND_DATA_CACHE)) {
        // Configure loader.
        stmr.perNodeBufferSize(1024);
        stmr.perNodeParallelOperations(8);

        JsonConvert jsonConvert = new JsonConvert();
        List<RecommendData> joaraRecommendList = jsonConvert
          .makeRecommendDataMapFromJson(RECOMMENDFILE);
//    joaraBookList.forEach(System.out::println);
        for (int i = 0; i < joaraRecommendList.size(); i++) {
          stmr.addData(i, joaraRecommendList.get(i));
        }
      }

      long end = System.currentTimeMillis(); // end time
      System.out.println(">> Joara Recommend Data Result");
      System.out.println("cache input total time : " + (end - start) / 1000.0 + "초");
      System.out.println("cache size: " + cache.size());
      System.out.println("cache 1: " + cache.get(3000));
      System.out.println(">> Created Recommend Data Cache.");

  }
    client.close();
    server.close();
}

//private static class addCacheData<T>(Ignite ignite, Type t) {
//    try (IgniteCache<Integer, T> cache = ignite
//    .getOrCreateCache(JOARA_RECOMMEND_DATA_CACHE)) {
//    long start = System.currentTimeMillis();
//
//    //data import with stream
//    try (IgniteDataStreamer<Integer, T> stmr = ignite
//      .dataStreamer(JOARA_RECOMMEND_DATA_CACHE)) {
//      // Configure loader.
//      stmr.perNodeBufferSize(1024);
//      stmr.perNodeParallelOperations(8);
//
//      JsonConvert jsonConvert = new JsonConvert();
//      List<T> joaraRecommendList = jsonConvert
//        .makeRecommendDataMapFromJson(RECOMMENDFILE);
////    joaraBookList.forEach(System.out::println);
//      for (int i = 0; i < joaraRecommendList.size(); i++) {
//        stmr.addData(i, joaraRecommendList.get(i));
//      }
//    }
//
//    long end = System.currentTimeMillis(); // end time
//    System.out.println(">> Joara Recommend Data Result");
//    System.out.println("cache input total time : " + (end - start) / 1000.0 + "초");
//    System.out.println("cache size: " + cache.size());
//    System.out.println("cache 1: " + cache.get(3000));
//    System.out.println(">> Created Recommend Data Cache.");
//
//  }
//}



}
