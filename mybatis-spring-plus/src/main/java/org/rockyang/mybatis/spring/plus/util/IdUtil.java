package org.rockyang.mybatis.spring.plus.util;


import java.io.InputStream;
import java.util.Properties;

/**
 * 全局唯一 id 工具(时间递增)  <br/>
 * <p>
 * 支持服务器id配置（0 ~ 0xFF） ： 参数名称 : ppblock.serverid <br/>
 * 可以使用 -Dppblock.serverid=7 配置
 * </p>
 * @author chenzhaoju
 *
 */
public final class IdUtil {

  /**
   * 用于以时间戳生成ID<br/>
   * 格式为：时间戳 + 以0x1每步递增的计数器 + 服务器ID
   */
  protected volatile long timestamp;
  /** 当前ID序列计数 */
  protected volatile int ordinal;
  /** 可选的服务器ID（不大于0xFF） */
  public static final int serviceId;
  public static final String serviceIdHex;
  /** 线程变量 ，保存当前线程的时间戳 */
  protected volatile ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

  static {
    String serverid = System.getProperty("ppblock.serverid");
    if(Misc.isEmpty(serverid)){
      try {
        InputStream resourceAsStream = IdUtil.class.getClassLoader().getResourceAsStream("application.properties");
        if(null != resourceAsStream){
          Properties properties = new Properties();
          properties.load(resourceAsStream);
          serverid = properties.getProperty("ppblock.serverid");
        }
      } catch (Throwable e) {
      }
    }
    int service = Misc.toInt(serverid, 160);
    if (0 > service || service > 0xFF) {
      throw new IllegalArgumentException("serviceId 的范围应该为 0 ~ 0xFF  , " + service);
    }
    serviceId = service;
    serviceIdHex = Misc.toHex(serviceId);
  }

  private IdUtil() {
    initTimestamp();
  }

  public static IdUtil getInstance() {
    return new IdUtil();
  }

  /**
   * 生成id
   *
   * @return
   */
  public String getNewId() {
    return getNewId(null);
  }

  /**
   * 生成带前缀的 id
   * @param prefix 前缀
   * @return
   */
  public String getNewId(String prefix) {
    StringBuilder sb;
    if (!Misc.isEmpty(prefix)) {
      sb = new StringBuilder(prefix.length() + 12 + 6);
      sb.append(prefix);
    } else {
      sb = new StringBuilder(12 + 6);
    }
    short ordinal = incOrdinal();
    long now = this.threadLocal.get();
    Misc.toHexFixed12(now, sb);
    Misc.toHexFixed(ordinal, sb);
    sb.append(serviceIdHex);
    return sb.toString();
  }

  /** 累加序列计数，返回累加后的计数 */
  private short incOrdinal() {
    synchronized (this) {
      this.ordinal += 0x1;
      if (this.ordinal > 0xFFFF) {
        this.ordinal = 0x1;
        initTimestamp();
      }
      this.threadLocal.set(this.timestamp);
      return (short) this.ordinal;
    }
  }

  /** 使用当前时间生成ID的时间戳 */
  private void initTimestamp() {
    long tt = System.currentTimeMillis();
    this.timestamp = tt;
  }
}
