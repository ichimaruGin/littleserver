package com.iwebirth.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.firewall.Subnet;

/** 
 *@author YY_410
 *合法名单过滤器
 *两种方式：正则表达式过滤和列表名单过滤
 */  
public class LegalListFilter extends IoFilterAdapter {  
  
    private final List<Subnet> legalList = new CopyOnWriteArrayList<Subnet>();  
    //默认匹配所有
    String addrRegex = "\\.*"; 
    
    public String getAddrRegex() {
		return addrRegex;
	}
	public void setAddrRegex(String addrRegex) {
		this.addrRegex = addrRegex;
	}

	private final static Logger logger = Logger.getLogger(LegalListFilter.class);  
    /**
     * 在构造函数中完成白名单列表
     * @param InetAddress[] addresses : 白名单数组
     * **/
    public LegalListFilter(InetAddress[] addresses){
    	if(addresses == null){
    		throw new NullPointerException("in WhitelistFilter constructor: addresses is null");
    	}
    	legalList.clear();
        for (InetAddress addr : addresses) {  
            allow(addr);  
        } 
    }
    /**
     * 在构造函数中完成白名单列表
     * @param Subnet[] subnets : 白名单数组
     * **/
    public LegalListFilter(Subnet[] subnets){
    	if(subnets == null){
    		throw new NullPointerException("in WhitelistFilter constructor: subnets is null");
    	}
    	legalList.clear();
        for (Subnet subnet : subnets) {  
            allow(subnet);  
        } 
    }
    /**
     * 正则表达式判断
     * **/
    public LegalListFilter(String regex){
    	this.addrRegex = regex;
    }
    public void setWhitelist(InetAddress[] addresses) {  
        if (addresses == null) {  
            throw new NullPointerException("addresses");  
        }  
        legalList.clear();  
        for (InetAddress addr : addresses) {  
            allow(addr);  
        }  
    }  
  
    public void setSubnetWhitelist(Subnet[] subnets) {  
        if (subnets == null) {  
            throw new NullPointerException("Subnets must not be null");  
        }  
        legalList.clear();  
        for (Subnet subnet : subnets) {  
            allow(subnet);  
        }  
    }  
  
    public void setWhitelist(Iterable<InetAddress> addresses) {  
        if (addresses == null) {  
            throw new NullPointerException("addresses");  
        }    
        legalList.clear();  
        for (InetAddress address : addresses) {  
            allow(address);  
        }  
    }  
  
    public void setSubnetWhitelist(Iterable<Subnet> subnets) {  
        if (subnets == null) {  
            throw new NullPointerException("Subnets must not be null");  
        }  
        legalList.clear();  
        for (Subnet subnet : subnets) {  
            allow(subnet);  
        }  
    }  
  
    public void allow(InetAddress address) {  
        if (address == null) {  
            throw new NullPointerException("Adress to block can not be null");  
        }  
  
        allow(new Subnet(address, 32));  
    }  
  
    public void allow(Subnet subnet) {  
        if (subnet == null) {  
            throw new NullPointerException("Subnet can not be null");  
        }  
  
        legalList.add(subnet);  
    }  
  
    public void disallow(InetAddress address) {  
        if (address == null) {  
            throw new NullPointerException("Adress to unblock can not be null");  
        }  
  
        disallow(new Subnet(address, 32));  
    }  
  
    public void disallow(Subnet subnet) {  
        if (subnet == null) {  
            throw new NullPointerException("Subnet can not be null");  
        }  
        legalList.remove(subnet);  
    }  
  
    @Override  
    public void sessionCreated(NextFilter nextFilter, IoSession session) {  
        if (isAllowed(session)) {  
            nextFilter.sessionCreated(session);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    @Override  
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {  
        if (isAllowed(session)) {  
            nextFilter.sessionOpened(session);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    @Override  
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {  
        if (isAllowed(session)) {  
            nextFilter.sessionClosed(session);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    @Override  
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {  
        if (isAllowed(session)) {  
            nextFilter.sessionIdle(session, status);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    @Override  
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) {  
        if (isAllowed(session)) {  
            nextFilter.messageReceived(session, message);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    @Override  
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {  
        if (isAllowed(session)) {  
            nextFilter.messageSent(session, writeRequest);  
        } else {  
            blockSession(session);  
        }  
    }  
  
    private void blockSession(IoSession session) {  
        logger.warn("Remote address"+session.getRemoteAddress()+" is not allowed; closing.");  
        session.close(true);  
    }  
    
    //此方法判断远程地址是否在白名单列表内，若在，则返回true;否则返回false
    private boolean isAllowed(IoSession session) {  
        SocketAddress remoteAddress = session.getRemoteAddress();  
        if (remoteAddress instanceof InetSocketAddress) {  
            InetAddress address = ((InetSocketAddress) remoteAddress).getAddress();  
            if(legalList.size() == 0){
            	//这种情况是设置了正则表达式过滤，没有设置白名单列表过滤
            	return Pattern.matches(addrRegex, address.getHostAddress());
            }else{
                for (Subnet subnet : legalList) {  
                    if (subnet.inSubnet(address)) {  
                        return true;  
                    }  
                }  
            }
        }  
        return false;  
    } 
    
    public LegalListFilter(){
    	
    }
}  
