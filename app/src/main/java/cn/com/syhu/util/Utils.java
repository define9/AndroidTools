package cn.com.syhu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Utils {

    private static final String TAG = "Utils";

    public static List<String> getIpv4s() throws SocketException, NullPointerException {

        List<String> list = new ArrayList<>();

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        if (interfaces == null) throw new NullPointerException("获取网络信息出错");
        while (interfaces.hasMoreElements()) {
            NetworkInterface anInterface = interfaces.nextElement();
            if (anInterface.isLoopback()) continue;

            Enumeration<InetAddress> addresses = anInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet6Address) continue;
                Log.i(TAG, "getIpv4: " + address.getHostAddress());
                list.add(address.getHostAddress());
            }
        }
        return list;
    }
}
